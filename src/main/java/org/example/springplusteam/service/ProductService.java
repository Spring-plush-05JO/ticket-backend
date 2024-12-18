package org.example.springplusteam.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;
	private final ViewRepository viewRepository;
	private final ViewService viewService;
	private final CacheManager cacheManager;

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

	public void saveDummyProducts() {
		List<Product> products = new ArrayList<>();
		for (int i = 1; i <= 100000; i++) {
			Product product = Product.createProduct(
				"Product" + i,
				(int) (Math.random() * 100000) + 1,
				"PerformanceTime" + i,
				LocalDateTime.now().plusDays(i)
			);
			products.add(product);

			// Batch size 설정 (예: 1,000개씩 저장)
			if (i % 1000 == 0) {
				productRepository.saveAll(products);
				products.clear();
			}
		}
		if (!products.isEmpty()) {
			productRepository.saveAll(products);
		}
	}

	@Transactional
	@Cacheable(cacheNames = "popular_products", key = "#id")
	public ProductCreateRespDto getProduct(Long id) {
		Product product = productRepository.findById(id)
			.orElseThrow(()-> new CustomApiException(ErrorCode.PRODUCT_NOT_FOUND));
		product.updateViewCount();
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

	public ProductRespDto getProductWithViewCount(Long productId, Long authUserId) {
		Product product = productRepository.findById(productId)
				.orElseThrow(()-> new CustomApiException(ErrorCode.PRODUCT_NOT_FOUND));

		saveViewLog(authUserId, product.getId());

		int viewCount = viewService.getViewCount(product.getId());
		return new ProductRespDto(product, viewCount);
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

	// 매일 자정에 조회수 리셋
	@Scheduled(cron = "0 0 0 * * ?")
	@Transactional
	public void resetViewCounts() {
		List<Product> products = productRepository.findAll();
		products.forEach(product -> {
			product.setViewCount();
			productRepository.save(product);
		});
		cacheManager.getCache("popular_products").clear();
	}
}