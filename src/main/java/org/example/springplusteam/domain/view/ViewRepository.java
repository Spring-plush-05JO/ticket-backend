package org.example.springplusteam.domain.view;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ViewRepository extends JpaRepository<View, Long> {

    int countByProductId(Long productId);

    boolean existsByProductIdAndUserId(Long id, Long authUserId);
}