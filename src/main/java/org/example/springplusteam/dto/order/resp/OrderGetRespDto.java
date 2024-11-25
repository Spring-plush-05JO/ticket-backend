package org.example.springplusteam.dto.order.resp;

import lombok.Getter;
import org.example.springplusteam.domain.order.DeliveryStatus;

import java.time.LocalDateTime;

@Getter
public class OrderGetRespDto {
    private Long id;
    private String name;
    private int price;
    private DeliveryStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public OrderGetRespDto(
            Long id,
            String name,
            int price,
            DeliveryStatus status,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt
    ) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.status = status;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}

