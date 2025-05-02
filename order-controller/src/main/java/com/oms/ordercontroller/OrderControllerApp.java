package com.oms.ordercontroller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@ComponentScan(basePackages = {"com.oms"})
@EntityScan(basePackages = {"com.oms.entity"})
@EnableJpaRepositories(basePackages = {"com.oms.repository"})
@OpenAPIDefinition(
    info = @Info(
        title = "Order Controller API",
        version = "1.0",
        description = "API pour la gestion des commandes dans le système OMS",
        contact = @Contact(name = "OMS Team", email = "oms-team@example.com")
    ),
    servers = {
        @Server(url = "/", description = "Serveur local")
    }
)
public class OrderControllerApp {
    public static void main(String[] args) {
        SpringApplication.run(OrderControllerApp.class, args);
    }
}