package org.example.springplusteam.dto.product.req;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateReqDto {
	private String name;
	private int price;
	private String performanceTime;
	private LocalDateTime performancePeriod;
}
