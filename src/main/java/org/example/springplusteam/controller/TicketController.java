package org.example.springplusteam.controller;

import lombok.RequiredArgsConstructor;
import org.example.springplusteam.common.security.AuthUser;
import org.example.springplusteam.dto.ticket.req.TicketCreateReqDto;
import org.example.springplusteam.dto.ticket.req.TicketReserveReqDto;
import org.example.springplusteam.dto.ticket.resp.TicketCreateRespDto;
import org.example.springplusteam.dto.ticket.resp.TicketReserveRespDto;
import org.example.springplusteam.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/api/v1/tickets")
    public ResponseEntity<TicketCreateRespDto> saveTicket(@RequestBody TicketCreateReqDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketService.addTicket(dto));
    }

    @PostMapping("/api/ticket/{ticketId}")
    public ResponseEntity<TicketReserveRespDto> reserve(
            @PathVariable Long ticketId,
            @RequestBody TicketReserveReqDto dto,
            @AuthenticationPrincipal AuthUser authUser
    ) {
        TicketReserveRespDto respDto
                = ticketService.reserveTicket(ticketId, authUser.getId(), dto.getSeatNumber());

        return ResponseEntity.status(HttpStatus.OK).body(respDto);
    }
}
