package org.example.springplusteam.controller;

import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.springplusteam.csv.PerformancesBulkInsert;
import org.example.springplusteam.service.performanceservice.PerformanceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/performances")
@RequiredArgsConstructor
public class PerformancesController {
  private final PerformancesBulkInsert performancesBulkInsert;

  @GetMapping
  public PerformancesBulkInsert getPerformancesBulkInsert(@RequestParam(required = false)String startDate,
      @RequestParam(required = false) String endDate,
      @RequestParam(required = false) String mainAddress,
      @RequestParam(required = false) String subAddress) {

    return performancesBulkInsert;
  }
}
