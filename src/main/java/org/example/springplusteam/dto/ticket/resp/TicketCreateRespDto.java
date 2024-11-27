package org.example.springplusteam.dto.ticket.resp;

import lombok.Getter;
import org.example.springplusteam.domain.ticket.Ticket;

@Getter
public class TicketCreateRespDto {
    private Long id;
    private String name;
    private int price;
    private int quantity;
    private int useQuantity;

    public TicketCreateRespDto(Ticket ticket) {
        this.id = ticket.getId();
        this.name = ticket.getName();
        this.price = ticket.getPrice();
        this.quantity = ticket.getQuantity();
        this.useQuantity = ticket.getUseQuantity();
    }
}
