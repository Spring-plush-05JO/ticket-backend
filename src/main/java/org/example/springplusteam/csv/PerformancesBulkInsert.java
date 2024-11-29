package org.example.springplusteam.csv;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.example.springplusteam.service.performanceservice.PerformanceService;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

@Component
@RequiredArgsConstructor
public class PerformancesBulkInsert {

  private final PerformanceService performanceService;

  @Value("${spring.jpa.hibernate.ddl-auto}")
  private String options;

  @PostConstruct
  public void initializePer() throws Exception {
    if(options.equals("create")){
      performanceService.save();
    }
  }
}