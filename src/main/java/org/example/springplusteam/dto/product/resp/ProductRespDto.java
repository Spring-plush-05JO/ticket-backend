package org.example.springplusteam.dto.product.resp;

import lombok.Getter;
import org.example.springplusteam.domain.product.Product;

import java.time.LocalDateTime;

@Getter
public class ProductRespDto {

    private Long id;
    private String name;
    private int price;
    private String performance_time;
    private LocalDateTime performance_period;
    private int count;


    public ProductRespDto(Product product, int count) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.performance_time = product.getPerformance_time();
        this.performance_period = product.getPerformance_period();
        this.count = count;
    }


}
