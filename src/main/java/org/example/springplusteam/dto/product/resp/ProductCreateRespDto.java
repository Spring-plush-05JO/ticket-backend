package org.example.springplusteam.dto.product.resp;

import java.time.LocalDateTime;

import org.example.springplusteam.domain.product.Product;

public class ProductCreateRespDto {
	private Long id;
	private String name;
	private String price;
	private String performanceTime;
	private String performancePeriod;
	private String viewCount;

	public ProductCreateRespDto(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.price = product.getPrice();
		this.performanceTime = product.getPerformance_time();
		this.performancePeriod = product.getPerformance_period();
		this.viewCount = product.getViewCount();
	}
}
