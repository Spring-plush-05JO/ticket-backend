package org.example.springplusteam.dto.performancesDto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class PerformanceReqDto {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String mainAddress;
  private String subAddress;
  private String startData;
  private String endData;

  public PerformanceReqDto(String mainAddress, String subAddress, String startData, String endData) {
    this.mainAddress = mainAddress;
    this.subAddress = subAddress;
    this.startData = startData;
    this.endData = endData;
  }

}

