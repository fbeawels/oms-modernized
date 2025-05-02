package com.oms.config;

import com.oms.util.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration principale de l'application
 * Remplace le fichier spring-config.xml
 */
@Configuration
public class AppConfig {

    /**
     * Configuration du logger de l'application
     * @return instance du logger
     */
    @Bean
    public Logger logger() {
        Logger logger = new Logger();
        logger.setPath("oms-log.txt");
        return logger;
    }
}
