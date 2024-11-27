package org.example.springplusteam.controller;

import lombok.RequiredArgsConstructor;
import org.example.springplusteam.common.security.AuthUser;
import org.example.springplusteam.dto.coupon.req.CouponCreateReqDto;
import org.example.springplusteam.dto.coupon.resp.CouponCreateRespDto;
import org.example.springplusteam.dto.coupon.resp.CouponReserveRespDto;
import org.example.springplusteam.service.CouponService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CouponController {
    private final CouponService couponService;

    @PostMapping("/api/v1/coupons")
    public ResponseEntity<CouponCreateRespDto> createCoupon(@RequestBody CouponCreateReqDto reqDto
    ) {
        CouponCreateRespDto respDto = couponService.createCoupon(reqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(respDto);
    }

    @PostMapping("/api/v1/coupons/{couponId}")
    public ResponseEntity<CouponReserveRespDto> reserveCoupon(
            @AuthenticationPrincipal AuthUser user,
            @PathVariable Long couponId
    ) {
        Long userId = user.getId();
        CouponReserveRespDto respDto = couponService.reserveCoupon(userId, couponId);
        return ResponseEntity.status(HttpStatus.OK).body(respDto);
    }
}
