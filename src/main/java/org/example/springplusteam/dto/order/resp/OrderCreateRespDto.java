package org.example.springplusteam.dto.order.resp;

import lombok.Builder;
import lombok.Getter;
import org.example.springplusteam.domain.order.DeliveryStatus;

import java.time.LocalDateTime;
@Builder
@Getter
public class OrderCreateRespDto {
    private Long orderId;
    private String productName;
    private int price;
    private DeliveryStatus deliveryStatus;
    private LocalDateTime createdAt;
}
