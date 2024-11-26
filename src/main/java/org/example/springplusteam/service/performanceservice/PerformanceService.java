package org.example.springplusteam.service.performanceservice;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.springplusteam.domain.performances.Performances;
import org.example.springplusteam.domain.performances.PerformancesRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerformanceService {

  private final PerformancesRepository performancesRepository;

  /**
   * Retrieve all performances from the database.
   * @return List of performances.
   */
  public List<Performances> getAllPerformances() {
    return performancesRepository.findAll();
  }
}