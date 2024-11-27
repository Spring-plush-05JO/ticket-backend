package org.example.springplusteam.dto.ticket.req;


import lombok.Getter;
import org.example.springplusteam.domain.ticket.Ticket;

@Getter
public class TicketCreateReqDto {
    private String name;
    private int price;
    private int quantity;
    private int useQuantity;

    public static Ticket from(TicketCreateReqDto dto) {
        return Ticket.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .quantity(dto.getQuantity())
                .useQuantity(dto.getUseQuantity())
                .build();
    }
}
