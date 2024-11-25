package org.example.springplusteam.service;

import java.util.Optional;

import org.example.springplusteam.domain.product.Product;
import org.example.springplusteam.domain.product.ProductRepository;
import org.example.springplusteam.dto.product.req.ProductCreateReqDto;
import org.example.springplusteam.dto.product.resp.ProductCreateRespDto;
import org.springframework.stereotype.Service;

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
}
