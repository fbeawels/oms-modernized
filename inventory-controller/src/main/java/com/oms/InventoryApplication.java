package com.oms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.oms.config.InventoryConfig;

@SpringBootApplication
@EntityScan(basePackages = "com.oms.entity")
@EnableJpaRepositories(basePackages = "com.oms.repository")
@Import(InventoryConfig.class)
public class InventoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryApplication.class, args);
    }
}
