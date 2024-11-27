package org.example.springplusteam.domain.ticket;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.springplusteam.common.BaseEntity;

@Entity
@Getter
@Table(name = "tickets")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ticket extends BaseEntity {

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

    public void updateQuantities(){
        this.quantity -= 1;
        this.useQuantity += 1;
    }
}
