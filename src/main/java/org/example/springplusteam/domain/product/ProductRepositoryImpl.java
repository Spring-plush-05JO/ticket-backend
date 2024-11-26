package org.example.springplusteam.domain.product;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom{

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<Product> findAllWithPagination(Pageable pageable) {
		QProduct product = QProduct.product;

		return jpaQueryFactory
			.selectFrom(product)
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();
	}

	@Override
	public long countProducts() {
		QProduct product = QProduct.product;

		return jpaQueryFactory
			.selectFrom(product)
			.fetchCount();
	}
}
