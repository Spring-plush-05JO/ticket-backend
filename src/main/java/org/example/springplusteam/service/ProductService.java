package org.example.springplusteam.service;

import java.util.List;
import java.util.stream.Collectors;

import org.example.springplusteam.common.exception.CustomApiException;
import org.example.springplusteam.common.exception.ErrorCode;
import org.example.springplusteam.domain.product.Product;
import org.example.springplusteam.domain.product.ProductRepository;
import org.example.springplusteam.dto.product.req.ProductCreateReqDto;
import org.example.springplusteam.dto.product.resp.ProductCreateRespDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

	public ProductCreateRespDto getProduct(Long id) {
		Product product = productRepository.findById(id)
			.orElseThrow(()-> new CustomApiException(ErrorCode.PRODUCT_NOT_FOUND));
		return new ProductCreateRespDto(
			product.getId(),
			product.getName(),
			product.getPrice(),
			product.getPerformance_time(),
			product.getPerformance_period(),
			product.getViewCount()
		);
	}

	public Page<ProductCreateRespDto> listProducts(Pageable pageable) {
		List<Product> products = productRepository.findAllWithPagination(pageable);
		long total = productRepository.countProducts();

		List<ProductCreateRespDto> content = products.stream()
			.map(ProductCreateRespDto::new)
			.collect(Collectors.toList());

		return new PageImpl<>(content, pageable, total);
	}
}