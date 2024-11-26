package org.example.springplusteam.dto.performancesDto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class PerformanceRespDto {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  private String genre;
  private String place ;
  private String mainAddress;
  private String subAddress;
  private String startData;
  private String endData;

  public PerformanceRespDto(Long id,String name, String genre, String place, String mainAddress, String subAddress,String startData,String endData) {
    this.id = id;
    this.name = name;
    this.genre = genre;
    this.place = place;
    this.mainAddress = mainAddress;
    this.subAddress = subAddress;
    this.startData = startData;
    this.endData = endData;
  }
}
