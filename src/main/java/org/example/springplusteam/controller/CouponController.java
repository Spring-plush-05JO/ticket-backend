package org.example.springplusteam.controller;

import lombok.RequiredArgsConstructor;
import org.example.springplusteam.dto.coupon.req.CouponCreateReqDto;
import org.example.springplusteam.dto.coupon.resp.CouponCreateRespDto;
import org.example.springplusteam.service.CouponService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CouponController {
    private final CouponService couponService;

    @PostMapping("/api/v1/coupones")
    public ResponseEntity<CouponCreateRespDto> createCoupon(@RequestBody CouponCreateReqDto reqDto
    ) {
        CouponCreateRespDto respDto = couponService.createCoupon(reqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(respDto);
    }
}
