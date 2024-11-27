package org.example.springplusteam.service;

import lombok.RequiredArgsConstructor;
import org.example.springplusteam.common.exception.CustomApiException;
import org.example.springplusteam.common.exception.ErrorCode;
import org.example.springplusteam.domain.coupon.Coupon;
import org.example.springplusteam.domain.coupon.CouponRepository;
import org.example.springplusteam.domain.myCoupon.MyCoupon;
import org.example.springplusteam.domain.myCoupon.MyCouponRepository;
import org.example.springplusteam.domain.user.User;
import org.example.springplusteam.domain.user.UserRepository;
import org.example.springplusteam.dto.coupon.req.CouponCreateReqDto;
import org.example.springplusteam.dto.coupon.resp.CouponCreateRespDto;
import org.example.springplusteam.dto.coupon.resp.CouponReserveRespDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CouponService {
    private final CouponRepository couponRepository;
    private final UserRepository userRepository;
    private final MyCouponRepository myCouponRepository;

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

    public CouponReserveRespDto reserveCoupon(Long userId, Long couponId) {
         User user = userRepository.findById(userId).orElseThrow(() ->
                new CustomApiException(ErrorCode.USER_NOT_FOUND));

         Coupon coupon = couponRepository.findById(couponId).orElseThrow(() ->
                new CustomApiException(ErrorCode.COUPON_NOT_FOUND));

        if (!(coupon.getQuantity() > 0)) {
            throw new CustomApiException(ErrorCode.NO_COUPON_AVAILABLE);
        }

        coupon.updateQuantities();

        MyCoupon myCoupon = new MyCoupon(user, coupon);

        couponRepository.save(coupon);

        myCouponRepository.save(myCoupon);

        CouponReserveRespDto respDto = CouponReserveRespDto.builder()
                .id(coupon.getId())
                .name(coupon.getName())
                .price(coupon.getPrice())
                .createdAt(coupon.getModifiedAt())
                .build();

        return respDto;
    }
}
