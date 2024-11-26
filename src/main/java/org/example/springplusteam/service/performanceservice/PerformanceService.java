package org.example.springplusteam.service.performanceservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.springplusteam.domain.performances.Performances;
import org.example.springplusteam.domain.performances.PerformancesRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerformanceService {

  private final PerformancesRepository performancesRepository;

  public void run(String... args) throws Exception {
    loadPerformancesFromCsv();
  }

  public void loadPerformancesFromCsv() {
    try {
      Resource resource = new ClassPathResource("한국문화예술위원회_공연예술_편람정보_20210121.csv");
      try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {
        String line;
        int lineCount = 0;
        List<Performances> performancesList = new ArrayList<>();

        while ((line = reader.readLine()) != null) {
          if (lineCount++ == 0) continue; // Skip header line

          String[] columns = line.split(",");
          if (columns.length >= 7) {
            Performances performance = new Performances(
                columns[0].trim(),
                columns[1].trim(),
                columns[2].trim(),
                columns[3].trim(),
                columns[4].trim(),
                LocalDate.parse(columns[5].trim()),
                LocalDate.parse(columns[6].trim())
            );
            performancesList.add(performance);
          }
        }

        performancesRepository.saveAll(performancesList);
      }
    } catch (IOException | DateTimeParseException e) {
      e.printStackTrace();
      throw new RuntimeException("Error reading or processing the CSV file: " + e.getMessage());
    }
  }

  /**
   * Retrieve all performances from the database.
   * @return List of Performances.
   */
  public List<Performances> getAllPerformances() {
    return performancesRepository.findAll();
  }
}