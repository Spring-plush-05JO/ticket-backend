package org.example.springplusteam.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.springplusteam.csv.PerformancesBulkInsert;
import org.example.springplusteam.domain.performances.Performances;
import org.example.springplusteam.dto.performance.PerformanceDto;
import org.example.springplusteam.service.performanceservice.PerformanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/performances")
@RequiredArgsConstructor
public class PerformancesController {
  private final PerformanceService performanceService;
  private final PerformancesBulkInsert performancesBulkInsert;

  @GetMapping
  public ResponseEntity<List<Performances>> listPerformances()throws Exception {

    return null;
  }
}
