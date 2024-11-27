package org.example.springplusteam.dto.order.req;

import lombok.Getter;
import org.example.springplusteam.domain.order.DeliveryStatus;

@Getter
public class OrderStatusReqDto {
    private DeliveryStatus status;
}
