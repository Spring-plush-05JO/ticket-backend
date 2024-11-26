package org.example.springplusteam.service.performanceservice;

import jakarta.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
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
   * Reads data from a CSV file, maps it to Performances objects, and saves them to the database.
   */
  public void loadPerformancesFromCsv() {
    String csvFilePath = "path/to/한국문화예술위원회_공연예술_편람정보_20210121.csv";
    List<Performances> performancesList = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
      String line;
      int lineCount = 0;

      while ((line = reader.readLine()) != null) {
        if (lineCount++ == 0) continue; // Skip the header line

        // Parse the CSV line into columns
        String[] columns = line.split(","); // Adjust the delimiter if needed
        if (columns.length >= 7) {
          // Map CSV columns to Performances object
          Performances performance = new Performances(
              columns[0].trim(),                     // name
              columns[1].trim(),                     // genre
              columns[2].trim(),                     // place
              columns[3].trim(),                     // mainAddress
              columns[4].trim(),                     // subAddress
              LocalDate.parse(columns[5].trim()),    // startData
              LocalDate.parse(columns[6].trim())     // endData
          );
          performancesList.add(performance);
        }
      }

      // Save all performances to the database
      performancesRepository.saveAll(performancesList);

    } catch (IOException | DateTimeParseException e) {
      e.printStackTrace();
      throw new RuntimeException("Error reading or processing the CSV file: " + e.getMessage());
    }
  }


  @PostConstruct
  public void init() {
    loadPerformancesFromCsv(); // Automatically load and save CSV data during startup
  }

  /**
   * Retrieve all performances from the database.
   * @return List of Performances.
   */
  public List<Performances> getAllPerformances() {
    return performancesRepository.findAll();
  }
}