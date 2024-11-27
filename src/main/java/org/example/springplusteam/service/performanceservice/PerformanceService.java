package org.example.springplusteam.service.performanceservice;

import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.springplusteam.domain.performances.Performances;
import org.example.springplusteam.domain.performances.PerformancesRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerformanceService{

  private final PerformancesRepository performancesRepository;

  public Performances savePerformances(Performances performances){
    performances.setId(performances.getId());
    performances.setGenre(performances.getGenre());
    performances.setName(performances.getName());
    performances.setPlace(performances.getPlace());
    performances.setMainAddress(performances.getMainAddress());
    performances.setSubAddress(performances.getSubAddress());
    performances.setStartData(performances.getStartData());
    performances.setEndData(performances.getEndData());
    return performancesRepository.save(performances);
  }

  @PostConstruct
  public void savePerformances(){
    Performances performances = new Performances();
    performances.setId(performances.getId());
    performances.setGenre(performances.getGenre());
    performances.setName(performances.getName());
    performances.setPlace(performances.getPlace());
    performances.setMainAddress(performances.getMainAddress());
    performances.setSubAddress(performances.getSubAddress());
    performances.setStartData(performances.getStartData());
    performances.setEndData(performances.getEndData());
  }

  public List<Performances> getPerformances(){
    return performancesRepository.findAll();
  }
}