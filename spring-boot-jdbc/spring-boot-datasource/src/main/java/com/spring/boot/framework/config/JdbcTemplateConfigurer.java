package com.spring.boot.framework.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class JdbcTemplateConfigurer {
    @Autowired
    DataSource dataSource;

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }

    @ConditionalOnBean(com.spring.boot.framework.datasource.DataSourceDynamicConfigurer.class)
    @Bean
    public JdbcTemplate primaryJdbcTemplate(@Qualifier("primaryDS") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @ConditionalOnBean(com.spring.boot.framework.datasource.DataSourceDynamicConfigurer.class)
    @Bean
    public JdbcTemplate secondJdbcTemplate(@Qualifier("secondaryDS") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}