package org.example.springplusteam.domain.product;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.springplusteam.common.BaseEntity;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "products")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int price;
    private String performance_time;
    private LocalDateTime performance_period;
    private int viewCount;

    @Builder
    public Product(String name, int price, String performanceTime, LocalDateTime performancePeriod, int viewCount) {
        this.name = name;
        this.price = price;
        this.performance_time = performanceTime;
        this.performance_period = performancePeriod;
        this.viewCount = viewCount;
    }
}
