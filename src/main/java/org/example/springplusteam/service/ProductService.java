package org.example.springplusteam.service;

import java.util.Optional;

import org.example.springplusteam.domain.product.Product;
import org.example.springplusteam.domain.product.ProductRepository;
import org.example.springplusteam.domain.product.QProduct;
import org.example.springplusteam.dto.product.req.ProductCreateReqDto;
import org.example.springplusteam.dto.product.resp.ProductCreateRespDto;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;

	public ProductCreateRespDto createProduct(ProductCreateReqDto reqDto) {
		Product product = Product.builder()
			.name(reqDto.getName())
			.price(reqDto.getPrice())
			.performanceTime(reqDto.getPerformanceTime())
			.performancePeriod(reqDto.getPerformancePeriod())
			.build();

		Product savedProduct = productRepository.save(product);
		return new ProductCreateRespDto(savedProduct);
	}

	public ProductCreateRespDto getProduct(Long id) {
		QProduct qProduct = QProduct.product;
		BooleanBuilder builder = new BooleanBuilder();
		builder.and(qProduct.id.eq(id));
		Product product = productRepository.findOne(builder).orElseThrow(() -> new RuntimeException("상품을 찾을 수 없습니다."));
		return new ProductCreateRespDto(
			product.getId(),
			product.getName(),
			product.getPrice(),
			product.getPerformance_time(),
			product.getPerformance_period(),
			product.getViewCount()
		);
	}
}
