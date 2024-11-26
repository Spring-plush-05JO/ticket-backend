package org.example.springplusteam.service;

import lombok.RequiredArgsConstructor;
import org.example.springplusteam.common.exception.CustomApiException;
import org.example.springplusteam.common.exception.ErrorCode;
import org.example.springplusteam.domain.order.DeliveryStatus;
import org.example.springplusteam.domain.order.Order;
import org.example.springplusteam.domain.order.OrderRepository;
import org.example.springplusteam.domain.order.OrderRepositoryCustomImpl;
import org.example.springplusteam.domain.product.Product;
import org.example.springplusteam.domain.product.ProductRepository;
import org.example.springplusteam.domain.user.User;
import org.example.springplusteam.domain.user.UserRepository;
import org.example.springplusteam.dto.order.req.OrderStatusReqDto;
import org.example.springplusteam.dto.order.resp.OrderCreateRespDto;
import org.example.springplusteam.dto.order.resp.OrderSearchRespDto;
import org.example.springplusteam.dto.order.resp.OrderStatusRespDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.example.springplusteam.common.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderRepositoryCustomImpl orderRepositoryCustomImpl;

    @Transactional
    public OrderCreateRespDto createOrder(Long userId, Long productId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomApiException(USER_NOT_FOUND));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new CustomApiException(ErrorCode.PRODUCT_NOT_FOUND));

        Order order = new Order(user, product);

        orderRepository.save(order);

        OrderCreateRespDto respDto = new OrderCreateRespDto(
                order.getId(),
                order.getProduct().getName(),
                order.getProduct().getPrice(),
                order.getDeliveryStatus(),
                order.getCreatedAt()
        );

        return respDto;
    }

    @Transactional
    public Page<OrderSearchRespDto> getOrders(
            Long userId,
            Pageable pageable
    ) {
        return orderRepositoryCustomImpl.searchOrdersByUser(userId, pageable);
    }

    @Transactional
    public OrderStatusRespDto changeOrder(Long userId, Long orderId, OrderStatusReqDto reqDto) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new CustomApiException(ORDER_NOT_FOUND));

        if (order.getDeliveryStatus().equals(DeliveryStatus.COMPLETED)) {
            throw new CustomApiException(ORDER_BAD_REQUEST);
        }

        order.update(reqDto.getStatus());

        orderRepository.saveAndFlush(order);
        OrderStatusRespDto respDto = OrderStatusRespDto.builder()
                .orderId(orderId)
                .name(order.getProduct().getName())
                .price(order.getProduct().getPrice())
                .status(order.getDeliveryStatus())
                .createdAt(order.getCreatedAt())
                .modifiedAt(order.getModifiedAt())
                .build();

        return respDto;
    }
}

