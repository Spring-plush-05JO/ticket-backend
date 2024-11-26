package org.example.springplusteam.dto.product.req;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class ProductCreateReqDto {
	private String name;
	private int price;
	private String performanceTime;
	private LocalDateTime performancePeriod;
}
