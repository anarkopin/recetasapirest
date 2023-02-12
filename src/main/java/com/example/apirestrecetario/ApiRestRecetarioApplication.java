package com.example.apirestrecetario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties
public class ApiRestRecetarioApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiRestRecetarioApplication.class, args);
    }

}
