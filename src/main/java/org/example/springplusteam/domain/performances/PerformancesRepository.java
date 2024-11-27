package org.example.springplusteam.domain.performances;


import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformancesRepository extends JpaRepository<Performances, Long> {
  Optional<Performances> findById(Long id);
  Optional<Performances> findByStartData(String startDate);
  Optional<Performances> findByEndData(String endData);
  Optional<Performances> findByMainAddress(String mainAddress);
  Optional<Performances> findBySubAddress(String subAddress);
  Page<Performances> findAll(Pageable pageable);
}