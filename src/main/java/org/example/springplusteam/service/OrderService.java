package org.example.springplusteam.service;

import lombok.RequiredArgsConstructor;
import org.example.springplusteam.domain.order.Order;
import org.example.springplusteam.domain.order.OrderRepository;
import org.example.springplusteam.dto.order.resp.OrderGetRespDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    @Transactional
    public Page<OrderGetRespDto> getOrders(
            Long userId,
            int page,
            int limit,
            String criteria
    ) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, criteria));
        Page<Order> orders = orderRepository.findAllByUserId(userId, pageable);

        Page<OrderGetRespDto> response = orders.map(order ->
                new OrderGetRespDto(
                        order.getId(),
                        order.getProduct().getName(),
                        order.getProduct().getPrice(),
                        order.getDeliveryStatus(),
                        order.getCreatedAt(),
                        order.getModifiedAt()
                )
        );

        return response;
    }
}

