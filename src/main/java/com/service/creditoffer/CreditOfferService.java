package com.service.creditoffer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CreditOfferService {
    public static void main(String[] args) {
        SpringApplication.run(CreditOfferService.class, args)
                .getBean(CreditOfferService.class);
    }
}
