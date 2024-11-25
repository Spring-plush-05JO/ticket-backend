package org.example.springplusteam.dto.product.req;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class ProductCreateReqDto {
	private String name;
	private String price;
	private String performanceTime;
	private String performancePeriod;

}
