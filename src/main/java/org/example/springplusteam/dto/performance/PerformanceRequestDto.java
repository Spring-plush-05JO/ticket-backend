package org.example.springplusteam.dto.performance;

import lombok.Data;

@Data
public class PerformanceRequestDto {
  private String mainAddress;
  private String subAddress;
  private String startDate;
  private String endDate;

  public PerformanceRequestDto(String mainAddress, String subAddress, String startDate, String endDate) {
    this.mainAddress = mainAddress;
    this.subAddress = subAddress;
    this.startDate = startDate;
    this.endDate = endDate;
  }

}
