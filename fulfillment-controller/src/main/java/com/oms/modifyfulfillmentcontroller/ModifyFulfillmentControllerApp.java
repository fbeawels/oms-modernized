package com.oms.modifyfulfillmentcontroller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.oms.config.FulfillmentConfig;

@SpringBootApplication
@EntityScan(basePackages = "com.oms.entity")
@EnableJpaRepositories(basePackages = "com.oms.repository")
@Import(FulfillmentConfig.class)
public class ModifyFulfillmentControllerApp {
    public static void main(String[] args) {
        SpringApplication.run(ModifyFulfillmentControllerApp.class, args);
    }
}