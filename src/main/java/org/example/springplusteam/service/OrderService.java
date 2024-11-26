package org.example.springplusteam.service;

import lombok.RequiredArgsConstructor;
import org.example.springplusteam.common.exception.CustomApiException;
import org.example.springplusteam.common.exception.ErrorCode;
import org.example.springplusteam.domain.order.Order;
import org.example.springplusteam.domain.order.OrderRepository;
import org.example.springplusteam.domain.product.Product;
import org.example.springplusteam.domain.product.ProductRepository;
import org.example.springplusteam.domain.user.User;
import org.example.springplusteam.domain.user.UserRepository;
import org.example.springplusteam.dto.order.OrderCreateRespDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.example.springplusteam.common.exception.ErrorCode.USER_NOT_FOUND;
import static org.example.springplusteam.domain.order.DeliveryStatus.READY;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

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
}
