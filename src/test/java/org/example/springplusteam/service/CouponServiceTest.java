package org.example.springplusteam.service;

import org.example.springplusteam.common.exception.CustomApiException;
import org.example.springplusteam.common.exception.ErrorCode;
import org.example.springplusteam.domain.coupon.Coupon;
import org.example.springplusteam.domain.coupon.CouponRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@SpringBootTest
@Transactional
class CouponServiceTest {

    @Autowired
    private CouponService couponService;
    @Autowired
    private CouponRepository couponRepository;

    @BeforeEach
    void setup() {
        couponRepository.deleteAll(); // 기존 데이터 초기화
    }

    @Test
    @DisplayName("유저 1000명이 동시에 쿠폰 100개를 발급하려는 경우, 동시성이 발생하여 쿠폰 발급이 이뤄지지 않는다.")
    void testCounterConcurrency() throws InterruptedException {
        Coupon coupon = Coupon.builder()
                .name("테스트 쿠폰")
                .price(10000)
                .quantity(100)
                .useQuantity(0)
                .build();

        couponRepository.save(coupon);

        int numberOfThreads = 1000;

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CountDownLatch doneSignal = new CountDownLatch(numberOfThreads);

        AtomicInteger successCount = new AtomicInteger();
        AtomicInteger failCount = new AtomicInteger();
        AtomicLong userIdGenerator = new AtomicLong(1L);

        for (int i = 0; i < numberOfThreads; i++) {
            executorService.execute(() -> {
                try {
                    couponService.reserveCoupon(userIdGenerator.getAndIncrement(), coupon.getId());
                    successCount.getAndIncrement();
                } catch (ObjectOptimisticLockingFailureException e) {
                    failCount.getAndIncrement(); // 낙관적 잠금 실패 시 카운트 증가
                } catch (Exception e) {
                    failCount.getAndIncrement();
                } finally {
                    doneSignal.countDown();
                }
            });
        }

        doneSignal.await();
        executorService.shutdown();

        Coupon findCoupon = couponRepository.findById(coupon.getId()).orElseThrow(() ->
                new CustomApiException(ErrorCode.COUPON_NOT_FOUND));


        Assertions.assertEquals(findCoupon.getUseQuantity(), successCount.get());

        System.out.println("발급된 쿠폰 개수: " + findCoupon.getUseQuantity());
        System.out.println("미급된 쿠폰 개수: " + findCoupon.getQuantity());
    }
}