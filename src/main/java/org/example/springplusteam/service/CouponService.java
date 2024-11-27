package org.example.springplusteam.service;

import lombok.RequiredArgsConstructor;
import org.example.springplusteam.domain.coupon.Coupon;
import org.example.springplusteam.domain.coupon.CouponRepository;
import org.example.springplusteam.dto.coupon.req.CouponCreateReqDto;
import org.example.springplusteam.dto.coupon.resp.CouponCreateRespDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CouponService {
    private final CouponRepository couponRepository;

    @Transactional
    public CouponCreateRespDto createCoupon(CouponCreateReqDto reqDto) {
        Coupon coupon = Coupon.builder()
                .name(reqDto.getName())
                .price(reqDto.getPrice())
                .quantity(reqDto.getQuantity())
                .useQuantity(reqDto.getUseQuantity())
                .build();

        couponRepository.save(coupon);

        CouponCreateRespDto respDto = CouponCreateRespDto.builder()
                .id(coupon.getId())
                .name(coupon.getName())
                .price(coupon.getPrice())
                .quantity(coupon.getQuantity())
                .useQuantity(coupon.getUseQuantity())
                .build();

        return respDto;
    }
}
