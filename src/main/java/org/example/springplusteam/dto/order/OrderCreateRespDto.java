package org.example.springplusteam.dto.order;

import lombok.Getter;

@Getter
public class OrderCreateRespDto {
    private Long id;

    public OrderCreateRespDto(Long id) {
        this.id = id;
    }
}
