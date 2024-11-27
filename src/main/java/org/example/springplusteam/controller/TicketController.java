package org.example.springplusteam.controller;

import lombok.RequiredArgsConstructor;
import org.example.springplusteam.dto.ticket.req.TicketCreateReqDto;
import org.example.springplusteam.dto.ticket.resp.TicketCreateRespDto;
import org.example.springplusteam.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
