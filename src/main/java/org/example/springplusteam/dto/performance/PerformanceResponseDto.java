package org.example.springplusteam.dto.performance;

import lombok.Getter;
import lombok.Setter;

@Setter
public class PerformanceResponseDto {
  private Long id;
  private String name;
  private String place;
  private String genre;
  private String mainAddress;
  private String subAddress;
  private String startDate;
  private String endDate;

  public PerformanceResponseDto(Long id,String name, String place, String genre, String mainAddress, String subAddress, String startDate, String endDate) {
    this.id = id;
    this.name = name;
    this.place = place;
    this.genre = genre;
    this.mainAddress = mainAddress;
    this.subAddress = subAddress;
    this.startDate = startDate;
    this.endDate = endDate;
  }
}
