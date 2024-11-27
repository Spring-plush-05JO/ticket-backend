package org.example.springplusteam.dto.coupon.resp;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
@Builder
@Getter
public class CouponReserveRespDto {
    private Long id;
    private String name;
    private int price;
    private LocalDateTime createdAt;
}
