package org.example.springplusteam.common.csv;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.example.springplusteam.service.CsvService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OnlineShopBulkInsert {

    private final CsvService csvService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String options;

    @PostConstruct
    public void initializeShot() throws Exception {
        if(options.equals("create")){
            csvService.saveShop();
        }
    }
}
