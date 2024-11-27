package org.example.springplusteam.domain.performances;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.AccessLevel;
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

  public Performances(String name, String genre, String place, String mainAddress, String subAddress, String startData, String endData) {
    this.name = name;
    this.genre = genre;
    this.place = place;
    this.mainAddress = mainAddress;
    this.subAddress = subAddress;
    this.startData = startData;
    this.endData = endData;
  }
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