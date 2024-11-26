package org.example.springplusteam.dto.product.resp;

import java.time.LocalDateTime;

import org.example.springplusteam.domain.product.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateRespDto {
	private Long id;
	private String name;
	private int price;
	private String performanceTime;
	private LocalDateTime performancePeriod;
	private int viewCount;

	public ProductCreateRespDto(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.price = product.getPrice();
		this.performanceTime = product.getPerformance_time();
		this.performancePeriod = product.getPerformance_period();
		this.viewCount = product.getViewCount();
	}
}
