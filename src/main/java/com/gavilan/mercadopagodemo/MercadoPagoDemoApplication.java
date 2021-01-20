package com.gavilan.mercadopagodemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class MercadoPagoDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MercadoPagoDemoApplication.class, args);
    }

}
