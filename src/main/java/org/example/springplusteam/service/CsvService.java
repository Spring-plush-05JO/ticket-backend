package org.example.springplusteam.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.example.springplusteam.domain.onlinestore.OnlineStore;
import org.example.springplusteam.domain.onlinestore.OnlineStoreRepository;
import org.example.springplusteam.dto.onlinestore.ShopInfoDto;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CsvService {

    private final OnlineStoreRepository onlineStoreRepository;

    public void saveShop() throws Exception {
        List<ShopInfoDto> shopInfoDtos = readCsvFile();
        List<OnlineStore> list = shopInfoDtos.stream().map(ShopInfoDto::from).toList();
        onlineStoreRepository.saveAll(list);
    }


    public List<ShopInfoDto> readCsvFile() throws Exception {
        try (FileReader reader = new FileReader("서울시 인터넷 쇼핑몰 현황.csv")) {
            CsvToBean<ShopInfoDto> csvToBean = new CsvToBeanBuilder<ShopInfoDto>(reader)
                    .withType(ShopInfoDto.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            return csvToBean.parse();
        }
    }
}
