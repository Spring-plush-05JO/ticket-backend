package org.example.springplusteam.dto.order.resp;

import lombok.Getter;
import org.example.springplusteam.domain.order.DeliveryStatus;

import java.time.LocalDateTime;


@Getter
public class OrderStatusRespDto {
    private Long orderId;
    private String name;
    private int price;
    private DeliveryStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
