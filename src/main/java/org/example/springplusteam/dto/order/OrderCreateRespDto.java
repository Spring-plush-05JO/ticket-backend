package org.example.springplusteam.dto.order;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OrderCreateRespDto {
    private Long id;
    private String productName;
    private int price;
    private LocalDateTime createdAt;


    public OrderCreateRespDto(Long id, String productName, int price, LocalDateTime createdAt) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.createdAt = createdAt;
    }
}
