package org.example.springplusteam.service.performanceservice;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import jakarta.transaction.Transactional;
import java.io.FileReader;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.springplusteam.domain.performances.Performances;
import org.example.springplusteam.domain.performances.PerformancesRepository;
import org.example.springplusteam.dto.performance.PerformanceDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerformanceService{

  private final PerformancesRepository performancesRepository;

  public void save() throws Exception {
    List<PerformanceDto> performanceDto = readCsvFile();
    List<Performances> list = performanceDto.stream().map(PerformanceDto::from).toList();
    performancesRepository.saveAll(list);
  }

  public List<PerformanceDto> readCsvFile() throws Exception {
    try (FileReader reader = new FileReader("Korea_Performance_Information_20210121.csv")) {
      CsvToBean<PerformanceDto> csvToBean = new CsvToBeanBuilder<PerformanceDto>(reader)
          .withType(PerformanceDto.class)
          .withIgnoreLeadingWhiteSpace(true)
          .build();
      return csvToBean.parse();
    }
  }
}