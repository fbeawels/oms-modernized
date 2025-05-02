package com.oms.productcontroller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.oms.config.ProductConfig;
import com.oms.config.OpenApiConfig;

@SpringBootApplication
@EntityScan(basePackages = { "com.oms.entity" })
@EnableJpaRepositories(basePackages = { "com.oms.repository" })
@Import({ ProductConfig.class, OpenApiConfig.class })
public class ProductControllerApp {
	public static void main(String[] args) {
		SpringApplication.run(ProductControllerApp.class, args);
	}
}