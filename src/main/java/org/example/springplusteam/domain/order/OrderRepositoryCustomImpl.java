package org.example.springplusteam.domain.order;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.springplusteam.dto.order.resp.OrderSearchRespDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.example.springplusteam.domain.order.QOrder.order;
import static org.example.springplusteam.domain.product.QProduct.product;

@RequiredArgsConstructor
public class OrderRepositoryCustomImpl implements OrderRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public Page<OrderSearchRespDto> searchOrdersByUser(Long userId, Pageable pageable) {

        List<OrderSearchRespDto> content = jpaQueryFactory
                .select(Projections.constructor(OrderSearchRespDto.class,
                        order.id,
                        order.product.name,
                        order.product.price,
                        order.deliveryStatus,
                        order.createdAt,
                        order.modifiedAt
                ))
                .from(order)
                .join(order.product, product)
                .where(order.user.id.eq(userId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(order.modifiedAt.desc())
                .fetch();

        Long totalCount = jpaQueryFactory
                .select(order.count())
                .from(order)
                .join(order.product, product)
                .where(order.user.id.eq(userId))
                .fetchOne();


        return new PageImpl<>(content, pageable, totalCount);
    }
}
