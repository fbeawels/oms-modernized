package com.oms.config;

import com.oms.util.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.oms")
public class StoreSearchConfig {

    @Bean
    public Logger logger() {
        Logger logger = new Logger();
        logger.setPath("oms-log.txt");
        return logger;
    }
}
