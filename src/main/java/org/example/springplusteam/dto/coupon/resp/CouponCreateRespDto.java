package org.example.springplusteam.dto.coupon.resp;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CouponCreateRespDto {
    private Long id;
    private String name;
    private int price;
    private int quantity;
    private int useQuantity;
}
