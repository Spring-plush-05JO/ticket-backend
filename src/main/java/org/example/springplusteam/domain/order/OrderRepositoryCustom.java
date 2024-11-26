package org.example.springplusteam.domain.order;

import org.example.springplusteam.dto.order.resp.OrderSearchRespDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderRepositoryCustom {
    Page<OrderSearchRespDto> searchOrdersByUser(Long userId, Pageable pageable);
}
