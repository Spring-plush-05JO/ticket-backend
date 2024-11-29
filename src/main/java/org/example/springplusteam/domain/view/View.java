package org.example.springplusteam.domain.view;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.springplusteam.common.BaseEntity;

@Entity
@Getter
@Table(name = "views")
@NoArgsConstructor
public class View extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long productId;

    public View(Long productId, Long authUserId) {
        this.userId = authUserId;
        this.productId = productId;
    }
}