package org.example.springplusteam.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.springplusteam.domain.performances.Performances;
import org.example.springplusteam.service.performanceservice.PerformanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PerformancesController {
  private final PerformanceService performanceService;

  /**
   * Get all performances stored in the database.
   * @return List of performances.
   */
  @GetMapping("performances")
  public ResponseEntity<List<Performances>> getAllPerformances() {
    List<Performances> performances = performanceService.getAllPerformances();
    return ResponseEntity.ok(performances);
  }
}
