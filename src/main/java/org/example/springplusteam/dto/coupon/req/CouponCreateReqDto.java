package org.example.springplusteam.dto.coupon.req;

import lombok.Getter;

@Getter
public class CouponCreateReqDto {
    private String name;
    private int price;
    private int quantity;
    private int useQuantity;
}
