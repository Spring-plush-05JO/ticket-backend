package org.example.springplusteam.controller;

import lombok.RequiredArgsConstructor;
import org.example.springplusteam.common.security.AuthUser;
import org.example.springplusteam.dto.order.OrderCreateRespDto;
import org.example.springplusteam.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/api/v1/products/{productId}/orders")
    public ResponseEntity<OrderCreateRespDto> createOrder(
            @AuthenticationPrincipal AuthUser user,
            @PathVariable Long productId) {
        OrderCreateRespDto orderCreateRespDto = orderService.createOrder(user, productId);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderCreateRespDto);
    }
}
