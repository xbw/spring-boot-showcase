package com.xbw.spring.boot.framework.datasource;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/jdbc/DataSourceBuilder.html
 *
 * @see org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
 */
@ConditionalOnProperty(name = "spring.profiles.include", havingValue = "multi")
@Configuration
public class DataSourceConfigurerByConfigurationProperties {

    @Bean
    @ConfigurationProperties("spring.datasource.hikari")
    public DataSource dataSource() {
        return DataSourceBuilder.create().type(com.zaxxer.hikari.HikariDataSource.class).build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.tomcat")
    public DataSource tomcatDataSource() {
        return DataSourceBuilder.create().type(org.apache.tomcat.jdbc.pool.DataSource.class).build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.dbcp2")
    public DataSource dbcp2DataSource() {
        return DataSourceBuilder.create().type(org.apache.commons.dbcp2.BasicDataSource.class).build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.oracleucp")
    public DataSource ucpDataSource() {
        return DataSourceBuilder.create().type(oracle.ucp.jdbc.PoolDataSourceImpl.class).build();
    }

    //=================DataSourceConfiguration.Generic=================

    @Bean
    @ConfigurationProperties("spring.datasource.druid")
    public DataSource druidDataSource() {
        return DataSourceBuilder.create().type(com.alibaba.druid.pool.DruidDataSource.class).build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.tomcat-dbcp2")
    public DataSource tomcatDbcp2DataSource() {
        return DataSourceBuilder.create().type(org.apache.tomcat.dbcp.dbcp2.BasicDataSource.class).build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.c3p0")
    public DataSource c3p0DataSource() {
        return DataSourceBuilder.create().type(com.mchange.v2.c3p0.ComboPooledDataSource.class).build();
    }

    //=================non-pooling DataSource=================
    @Bean
    public DataSource simpleDriverDataSource(DataSourceProperties properties) {
//        return DataSourceBuilder.create().type(SimpleDriverDataSource.class).build(); // java.lang.IllegalArgumentException: Driver must not be null
        return properties.initializeDataSourceBuilder().type(SimpleDriverDataSource.class).build();
    }

    @Bean
    public DataSource driverManagerDataSource(DataSourceProperties properties) {
//        return DataSourceBuilder.create().type(DriverManagerDataSource.class).build(); // java.lang.IllegalStateException: 'url' not set
        return properties.initializeDataSourceBuilder().type(DriverManagerDataSource.class).build();
    }

    /**
     * only for Oracle
     *
     * @return
     * @throws SQLException
     */
    @Bean
    @ConfigurationProperties("spring.datasource.oracle")
    public DataSource oracleDataSource(Environment env) throws SQLException {
        return DataSourceBuilder.create().type(oracle.jdbc.replay.OracleDataSourceImpl.class).build();
    }
}