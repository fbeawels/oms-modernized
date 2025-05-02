package com.oms.paymentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
@EntityScan(basePackages = "com.oms.entity")
@OpenAPIDefinition
public class PaymentServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(PaymentServiceApp.class, args);
    }
}