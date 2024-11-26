package org.example.springplusteam.controller;

import lombok.RequiredArgsConstructor;
import org.example.springplusteam.common.security.AuthUser;
import org.example.springplusteam.dto.order.resp.OrderGetRespDto;
import org.example.springplusteam.dto.order.OrderCreateRespDto;
import org.example.springplusteam.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        Long userId = user.getId();
        OrderCreateRespDto orderCreateRespDto = orderService.createOrder(userId, productId);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderCreateRespDto);
    @GetMapping("/api/v1/orders")
    public ResponseEntity<Page<OrderGetRespDto>> getOrders(
            @AuthenticationPrincipal AuthUser authUser,
            @RequestParam(defaultValue = "1", value = "page") int page,
            @RequestParam(defaultValue = "10", value = "limit") int limit,
            @RequestParam(defaultValue = "modifiedAt", value = "criteria") String criteria

    ){
        Page<OrderGetRespDto> respDtos = orderService.getOrders(authUser.getId(), page, limit, criteria);
        return ResponseEntity.status(HttpStatus.OK).body(respDtos);
    }
}
