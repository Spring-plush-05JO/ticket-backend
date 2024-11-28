package org.example.springplusteam.service;

import lombok.RequiredArgsConstructor;
import org.example.springplusteam.domain.view.ViewRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ViewService {

    private final ViewRepository viewRepository;

    @Cacheable(value = "ViewCount", key = "#productId")
    public int getViewCount(Long productId) {
        // 이력테이블에서 product.id로 count 쿼리로 조회수 카운트한다.
        return viewRepository.countByProductId(productId);
    }

}
