package org.example.springplusteam.domain.performances;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name="performances")
public class Performances implements Serializable {

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

  @Builder
  public Performances(String name, String genre, String place, String mainAddress, String subAddress, String startData, String endData, Long id) {
    this.name = name;
    this.genre = genre;
    this.place = place;
    this.mainAddress = mainAddress;
    this.subAddress = subAddress;
    this.startData = startData;
    this.endData = endData;
    this.id = id;
  }
}