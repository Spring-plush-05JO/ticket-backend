package org.example.springplusteam.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.example.springplusteam.common.exception.CustomApiException;
import org.example.springplusteam.common.exception.ErrorCode;
import org.example.springplusteam.domain.ticket.Ticket;
import org.example.springplusteam.domain.ticket.TicketRepository;
import org.example.springplusteam.dto.ticket.req.TicketCreateReqDto;
import org.example.springplusteam.dto.ticket.resp.TicketCreateRespDto;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final RedisTemplate<String, String> redisTemplate;

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
}
