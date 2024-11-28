package org.example.springplusteam.service;

import java.util.List;
import java.util.stream.Collectors;

import org.example.springplusteam.common.exception.CustomApiException;
import org.example.springplusteam.common.exception.ErrorCode;
import org.example.springplusteam.domain.product.Product;
import org.example.springplusteam.domain.product.ProductRepository;
import org.example.springplusteam.domain.view.View;
import org.example.springplusteam.domain.view.ViewRepository;
import org.example.springplusteam.dto.product.req.ProductCreateReqDto;
import org.example.springplusteam.dto.product.resp.ProductCreateRespDto;
import org.example.springplusteam.dto.product.resp.ProductRespDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;
	private final ViewRepository viewRepository;

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

	public ProductRespDto getProductViewCount(Long productId, Long authUserId) {
		Product product = productRepository.findById(productId)
				.orElseThrow(()-> new CustomApiException(ErrorCode.PRODUCT_NOT_FOUND));

		saveViewLog(authUserId, product.getId());

		// 이력테이블에서 product.id로 count 쿼리로 조회수 카운트한다.
		int count = viewRepository.countByProductId(product.getId());
        return new ProductRespDto(product, count);
	}

	private void saveViewLog(Long authUserId, Long productId) {
		// 존재한다면? 다음 로직 타면 안돼
		if (hasUserViewedProduct(authUserId, productId)){
			return;
		}
		// 조회한 product.id와 로그인한 유저를 조회 이력에 남김
		View view = new View(productId, authUserId);
		// 조회 이력 저장
		viewRepository.save(view);
	}

	private boolean hasUserViewedProduct(Long authUserId, Long productId) {
		// 동일 유저가 조회 했을때 이력이 쌓이지 않아야함
		return viewRepository.existsByProductIdAndUserId(productId, authUserId);
	}
}