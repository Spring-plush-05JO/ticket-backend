package org.example.springplusteam.domain.performances;

import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformancesRepository extends JpaRepository<Performances, Long> {
  Page<Performances> findByStartDataBetweenAndMainAddressContainingAndSubAddressContaining(
      LocalDate startData,
      LocalDate endData,
      String mainAddress,
      String subAddress,
      Pageable pageable);
}