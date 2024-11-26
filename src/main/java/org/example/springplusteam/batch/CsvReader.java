package org.example.springplusteam.batch;

import lombok.RequiredArgsConstructor;
import org.example.springplusteam.domain.performances.Performances;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@RequiredArgsConstructor
public class CsvReader {
  @Bean
  public FlatFileItemReader<Performances> csvFileItemReader() {
    /* file read */
    FlatFileItemReader<Performances> flatFileItemReader = new FlatFileItemReader<>();
    flatFileItemReader.setResource(new ClassPathResource("ticket-backend/한국문화예술위원회_공연예술_편람정보_20210121.csv"));
    flatFileItemReader.setLinesToSkip(1); // header line skip
    flatFileItemReader.setEncoding("UTF-8"); // encoding

    /* read하는 데이터를 내부적으로 LineMapper을 통해 Mapping */
    DefaultLineMapper<Performances> defaultLineMapper = new DefaultLineMapper<>();

    /* delimitedLineTokenizer : setNames를 통해 각각의 데이터의 이름 설정 */
    DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer(",");
    delimitedLineTokenizer.setNames("id", "name","genre","place","mainAddress","subAddress","startData","endData");
    defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);

    /* beanWrapperFieldSetMapper : Tokenizer에서 가지고온 데이터들을 VO로 바인드하는 역할 */
    BeanWrapperFieldSetMapper<Performances> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
    beanWrapperFieldSetMapper.setTargetType(Performances.class);

    defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);

    /* lineMapper 지정 */
    flatFileItemReader.setLineMapper(defaultLineMapper);

    return flatFileItemReader;
  }
}
