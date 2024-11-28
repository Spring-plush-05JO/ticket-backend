package org.example.springplusteam.domain.performances;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformancesRepository extends JpaRepository<Performances, Long> {
  Optional<Performances> findById(Long Id);
}