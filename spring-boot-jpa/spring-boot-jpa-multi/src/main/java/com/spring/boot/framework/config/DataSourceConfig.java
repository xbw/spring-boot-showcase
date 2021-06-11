package com.spring.boot.framework.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

//@Configuration
public class DataSourceConfig {

//    @Bean
//    @Primary
//    @ConfigurationProperties("spring.datasource.primary")
//    public DataSource dataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean
//    @ConfigurationProperties("spring.datasource.secondary")
//    public DataSource dataSourceSecondary() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean
//    @ConfigurationProperties("spring.datasource.tertiary")
//    public DataSource dataSourceTertiary() {
//        return DataSourceBuilder.create().build();
//    }

}