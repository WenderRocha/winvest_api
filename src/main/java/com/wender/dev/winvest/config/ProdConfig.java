package com.wender.dev.winvest.config;

import com.wender.dev.winvest.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class ProdConfig {

    @Autowired
    private DBService dbservice;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddl;

    @Bean
    public Boolean instaceDB() {

        if (ddl.equals("update")) {
            this.dbservice.getInstanceDB();
        }

        return false;
    }
}
