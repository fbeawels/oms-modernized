package com.oms.emailservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.oms.config.EmailConfig;
import com.oms.config.OpenApiConfig;

@SpringBootApplication
@Import({EmailConfig.class, OpenApiConfig.class})
public class EmailServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(EmailServiceApp.class, args);
    }
}