package org.example.springplusteam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringPlusTeamApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringPlusTeamApplication.class, args);
    }

}
