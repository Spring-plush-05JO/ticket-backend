package org.example.springplusteam.domain.ticket;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "tickets")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private int price;
    private int quantity;
    private int useQuantity;

    @Builder
    public Ticket(Long id, String name, int price, int quantity, int useQuantity) {
        Id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.useQuantity = useQuantity;
    }
}
