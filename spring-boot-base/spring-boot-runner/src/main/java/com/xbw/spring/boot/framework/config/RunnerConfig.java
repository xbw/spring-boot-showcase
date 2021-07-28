package com.xbw.spring.boot.framework.config;

import com.xbw.spring.boot.framework.runner.InitMethodBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xbw
 */
@Configuration
public class RunnerConfig {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean
    public ApplicationListener applicationListener() {
        return (ApplicationListener<ApplicationReadyEvent>) event -> {
            logger.info("Use ApplicationListener start to initialize ...");
        };
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {
            logger.info("Use ApplicationRunner start to initialize ...");
        };
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            logger.info("Use ApplicationRunner start to initialize ...");
        };
    }

    @Bean
    public InitializingBean initializingBean() {
        return () -> {
            logger.info("Use InitializingBean start to initialize ...");
        };
    }

    @Bean(initMethod = "init")
    public InitMethodBean initMethodBean() {
        logger.info("Use @Bean initMethod start to initialize ...");
        return new InitMethodBean();
    }
}
