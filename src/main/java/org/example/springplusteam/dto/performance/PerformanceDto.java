package org.example.springplusteam.dto.performance;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;
import org.example.springplusteam.domain.performances.Performances;

@Data
public class PerformanceDto {

  @Id
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name="genre")
  private String genre;

  @Column(name="place")
  private String place ;

  @Column(name="main_address")
  private String mainAddress;

  @Column(name="sub_address")
  private String subAddress;

  @Column(name="start_data")
  private String startData;

  @Column(name="end_data")
  private String endData;

  public static Performances from(PerformanceDto dto) {
    return Performances.builder()
        .id(dto.getId())
        .name(dto.getName())
        .genre(dto.getGenre())
        .mainAddress(dto.getMainAddress())
        .subAddress(dto.getSubAddress())
        .startData(dto.getStartData())
        .endData(dto.getEndData())
        .build();
  }
}
