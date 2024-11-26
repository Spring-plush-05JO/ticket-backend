package org.example.springplusteam.dto.order.resp;

import lombok.Getter;
import org.example.springplusteam.domain.order.DeliveryStatus;

import java.time.LocalDateTime;

@Getter
public class OrderSearchRespDto {
    private Long orderId;
    private String name;
    private int price;
    private DeliveryStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public OrderSearchRespDto(Long orderId, String name,
                              int price, DeliveryStatus status,
                              LocalDateTime createdAt,
                              LocalDateTime modifiedAt
    ) {
        this.orderId = orderId;
        this.name = name;
        this.price = price;
        this.status = status;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
