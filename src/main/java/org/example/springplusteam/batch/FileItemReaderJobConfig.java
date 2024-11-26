package org.example.springplusteam.batch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springplusteam.domain.performances.Performances;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableBatchProcessing
public class FileItemReaderJobConfig {
  private final CsvReader csvReader;
  private final CsvWriter csvWriter;

  private static final int chunkSize = 1000;
  private final JobLauncher jobLauncher;

  @Bean
  public Job csvFileItemReaderJob(JobRepository jobRepository, Step csvFileItemReaderStep) {
    return new JobBuilder("csvFileItemReaderJob", jobRepository)
        .start(csvFileItemReaderStep)
        .build();
  }

  @Bean
  public Step csvFileItemReaderStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
    return new StepBuilder("csvFileItemReaderStep", jobRepository)
        .<Performances, Performances>chunk(chunkSize, transactionManager)
        .reader(csvReader.csvFileItemReader())
        .writer(csvWriter)
        .build();
  }
}
