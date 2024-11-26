package org.example.springplusteam.controller;

import lombok.RequiredArgsConstructor;
import org.example.springplusteam.common.security.AuthUser;
import org.example.springplusteam.dto.order.resp.OrderCreateRespDto;
import org.example.springplusteam.dto.order.resp.OrderSearchRespDto;
import org.example.springplusteam.dto.order.resp.OrderStatusRespDto;
import org.example.springplusteam.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
    }

    @GetMapping("/api/v1/orders")
    public ResponseEntity<Page<OrderSearchRespDto>> getOrders(
            @AuthenticationPrincipal AuthUser user,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "modifiedAt") String sortBy,
            @RequestParam(defaultValue = "desc") String direction

    ) {
        Long userId = user.getId();
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, limit, sort);
        Page<OrderSearchRespDto> respDtos = orderService.getOrders(userId, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(respDtos);
    }

    // 주문 상태 변경 메서드
    @PutMapping("/api/v1/orders/{orderId}/status")
    public ResponseEntity<OrderStatusRespDto> changeOrder(
            @AuthenticationPrincipal AuthUser user,
            @PathVariable Long orderId
    ) {
        Long userId = user.getId();
        OrderStatusRespDto respDto = orderService.changeOrder(userId, orderId);
        return ResponseEntity.status(HttpStatus.OK).body(respDto);
    }
}
