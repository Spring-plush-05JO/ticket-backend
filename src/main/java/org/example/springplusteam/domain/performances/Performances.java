package org.example.springplusteam.domain.performances;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Performances implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  private String genre;
  private String place ;
  private String mainAddress;
  private String subAddress;
  private LocalDate startData;
  private LocalDate endData;

  public Performances(String name, String genre, String place, String mainAddress, String subAddress, LocalDate startData, LocalDate endData) {
    this.name = name;
    this.genre = genre;
    this.place = place;
    this.mainAddress = mainAddress;
    this.subAddress = subAddress;
    this.startData = startData;
    this.endData = endData;
  }
}
