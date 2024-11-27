package org.example.springplusteam.service;

import lombok.RequiredArgsConstructor;
import org.example.springplusteam.common.exception.CustomApiException;
import org.example.springplusteam.common.exception.ErrorCode;
import org.example.springplusteam.domain.myTicket.MyTicket;
import org.example.springplusteam.domain.myTicket.MyTicketRepository;
import org.example.springplusteam.domain.ticket.Ticket;
import org.example.springplusteam.domain.ticket.TicketRepository;
import org.example.springplusteam.domain.user.User;
import org.example.springplusteam.domain.user.UserRepository;
import org.example.springplusteam.dto.ticket.req.TicketCreateReqDto;
import org.example.springplusteam.dto.ticket.resp.TicketCreateRespDto;
import org.example.springplusteam.dto.ticket.resp.TicketReserveRespDto;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final RedisTemplate<String, String> redisTemplate;
    private final UserRepository userRepository;
    private final MyTicketRepository myTicketRepository;

    public TicketCreateRespDto addTicket(TicketCreateReqDto dto) {
        Ticket savedTicket = ticketRepository.save(TicketCreateReqDto.from(dto));
        initializeTicket(savedTicket.getId());
        return new TicketCreateRespDto(savedTicket);
    }

    private void initializeTicket(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new CustomApiException(ErrorCode.TICKET_NOT_FOUND));

        String key = "ticket:seat:" + ticketId;

        redisTemplate.delete(key);
        redisTemplate.opsForValue().set(key + ":seatCount", String.valueOf(ticket.getQuantity()));
    }

    @Transactional
    public TicketReserveRespDto reserveTicket(Long ticketId, Long userId, String seatNumber) {
        String key = "ticket:seat:" + ticketId;

        Ticket ticket = ticketRepository.findTicketWithLock(ticketId)
                .orElseThrow(() -> new CustomApiException(ErrorCode.TICKET_NOT_FOUND));

        if (ticket.getQuantity() <= 0) {
            throw new CustomApiException(ErrorCode.NO_SEAT_AVAILABLE);
        }

        Long reserveSeat = redisTemplate.opsForSet().add(key, seatNumber);
        if (reserveSeat != 1L) {
            throw new CustomApiException(ErrorCode.ALREADY_RESERVE_SEAT);
        }

        Long decrement = redisTemplate.opsForValue().decrement(key + ":seatCount");
        if (decrement < 0) {
            throw new CustomApiException(ErrorCode.NO_SEAT_AVAILABLE);
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomApiException(ErrorCode.USER_NOT_FOUND));
        ticket.updateQuantities();
        myTicketRepository.save(new MyTicket(user, ticket));
        ticketRepository.save(ticket);
        return new TicketReserveRespDto(user.getId(), seatNumber);
    }
}
