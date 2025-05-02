package com.oms.shippingpricecontroller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.oms.config.ShippingPriceConfig;
import com.oms.config.OpenApiConfig;

@SpringBootApplication
@EntityScan(basePackages = "com.oms.entity")
@EnableJpaRepositories(basePackages = "com.oms.repository")
@Import({ShippingPriceConfig.class, OpenApiConfig.class})
public class ShippingPriceControllerApp {
    public static void main(String[] args) {
        SpringApplication.run(ShippingPriceControllerApp.class, args);
    }
}