package org.example.springplusteam.dto.ticket.resp;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TicketReserveRespDto {
    private Long userId;
    private String seatNumber;
}
