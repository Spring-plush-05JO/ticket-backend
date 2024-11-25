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
    private String price;
    private String performance_time;
    private String performance_period;
    private String viewCount;

    @Builder
    public Product(String name, String price, String performanceTime, String performancePeriod, String viewCount) {
        this.name = name;
        this.price = price;
        this.performance_time = performanceTime;
        this.performance_period = performancePeriod;
        this.viewCount = viewCount;
    }
}
