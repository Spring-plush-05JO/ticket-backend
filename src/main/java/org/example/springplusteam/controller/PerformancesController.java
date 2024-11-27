package org.example.springplusteam.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.springplusteam.domain.performances.Performances;
import org.example.springplusteam.service.performanceservice.PerformanceService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/performances")
@RequiredArgsConstructor
public class PerformancesController {
  private final PerformanceService performanceService;

  @GetMapping
  public ResponseEntity<List<Performances>> listPerformances(
      @Param("startData")String startData,@Param("endData") String endData, @Param(" mainAddress") String mainAddress,
      @Param("subAddress") String subAddress) {
    List<Performances> performancesList = performanceService.getPerformances();
    return ResponseEntity.ok(performancesList);
  }
}
