package org.example.springplusteam.batch;

import lombok.RequiredArgsConstructor;
import org.example.springplusteam.domain.performances.Performances;
import org.example.springplusteam.domain.performances.PerformancesRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CsvWriter implements ItemWriter<Performances> {

  private final PerformancesRepository performancesRepository;

  @Override
  public void write(Chunk<? extends Performances> chunk) throws Exception {
    performancesRepository.saveAll(chunk.getItems());
  }
}