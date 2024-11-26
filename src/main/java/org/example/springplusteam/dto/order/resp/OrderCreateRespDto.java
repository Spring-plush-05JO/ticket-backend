package org.example.springplusteam.dto.order.resp;

import lombok.Getter;
import org.example.springplusteam.domain.order.DeliveryStatus;

import java.time.LocalDateTime;

@Getter
public class OrderCreateRespDto {
    private Long id;
    private String productName;
    private int price;
    private DeliveryStatus deliveryStatus;
    private LocalDateTime createdAt;

    public OrderCreateRespDto(Long id, String productName,
                              int price,
                              DeliveryStatus deliveryStatus,
                              LocalDateTime createdAt
                              ) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.deliveryStatus = deliveryStatus;
        this.createdAt = createdAt;
    }
}
