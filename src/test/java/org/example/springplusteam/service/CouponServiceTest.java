package org.example.springplusteam.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
class CouponServiceTest {


    @Autowired
    private CouponService couponService;

    @Test
    @DisplayName("동시성 테스트")
    void tsetCounterConcurrency() throws InterruptedException {
        int numberOfThreads = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CountDownLatch doneSignal = new CountDownLatch(numberOfThreads);

        AtomicInteger successCount = new AtomicInteger();
        AtomicInteger failCount = new AtomicInteger();

        for (int i = 0; i < numberOfThreads; i++) {
            executorService.execute(() -> {
                try {
                    Long couponId = 1L;
                    couponService.reserveCoupon(,couponId);
                    successCount.getAndIncrement();
                } catch (Exception e) {
                    failCount.getAndIncrement();
                }  finally {
                    doneSignal.countDown();
                }
            });
        }

        doneSignal.await();
        executorService.shutdown();

        Assertions.assertEquals(successCount.get(), couponService.);

    }


    @Test


}