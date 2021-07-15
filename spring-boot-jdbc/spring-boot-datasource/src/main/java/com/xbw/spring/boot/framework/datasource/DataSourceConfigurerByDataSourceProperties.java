package com.xbw.spring.boot.framework.datasource;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.SQLException;

/**
 * use spring.datasource properties
 * <p>
 * https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/jdbc/DataSourceBuilder.html
 *
 * @see org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
 */
@ConditionalOnMissingBean({DataSourceConfigurer.class, DataSourceConfigurerByConfigurationProperties.class})
@Configuration
public class DataSourceConfigurerByDataSourceProperties {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private DataSourceProperties properties;
    private String url;
    private String username;
    private String password;
    private String driverClassName;

    @PostConstruct
    private void print() {
        this.url = properties.determineUrl();
        this.username = properties.determineUsername();
        this.password = properties.determinePassword();
        this.driverClassName = properties.determineDriverClassName();

        logger.debug("DataSourceConfigurerByDataSourceProperties: url -> {}, username -> {}, password -> {}, driverClassName -> {}", url, username, password, driverClassName);
    }

    private <T> T createDataSource(DataSourceProperties properties, Class<? extends DataSource> type) {
        return (T) properties.initializeDataSourceBuilder().type(type).build();
    }

    /**
     * like this.dataSource() by default
     *
     * @param properties
     * @return
     */
    @Bean
    public DataSource dataSource(DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().build();
    }

    @Bean
    public DataSource dataSource() {
        com.zaxxer.hikari.HikariDataSource dataSource = createDataSource(properties, com.zaxxer.hikari.HikariDataSource.class);
        if (StringUtils.hasText(properties.getName())) {
            dataSource.setPoolName(properties.getName());
        }
        return dataSource;
    }

    @Bean
    public DataSource tomcatDataSource() {
        org.apache.tomcat.jdbc.pool.DataSource dataSource = createDataSource(properties, org.apache.tomcat.jdbc.pool.DataSource.class);
        DatabaseDriver databaseDriver = DatabaseDriver.fromJdbcUrl(properties.determineUrl());
        String validationQuery = databaseDriver.getValidationQuery();
        if (validationQuery != null) {
            dataSource.setTestOnBorrow(true);
            dataSource.setValidationQuery(validationQuery);
        }
        return dataSource;
    }

    @Bean
    public DataSource dbcp2DataSource() {
        return createDataSource(properties, org.apache.commons.dbcp2.BasicDataSource.class);
    }

    @Bean
    public DataSource ucpDataSource() throws SQLException {
//        oracle.ucp.jdbc.PoolDataSource dataSource = createDataSource(properties, oracle.ucp.jdbc.PoolDataSourceImpl.class);
        oracle.ucp.jdbc.PoolDataSource dataSource = createDataSource(properties, oracle.ucp.jdbc.PoolDataSourceFactory.getPoolDataSource().getClass());
        dataSource.setValidateConnectionOnBorrow(true);
        if (StringUtils.hasText(properties.getName())) {
            dataSource.setConnectionPoolName(properties.getName());
        }
        return dataSource;
    }

    //=================DataSourceConfiguration.Generic=================

    @Bean
    public DataSource druidDataSource() {
        return createDataSource(properties, com.alibaba.druid.pool.DruidDataSource.class);
    }

    @Bean
    public DataSource tomcatDbcp2DataSource() {
        return createDataSource(properties, org.apache.tomcat.dbcp.dbcp2.BasicDataSource.class);
    }

    @Bean
    public DataSource c3p0DataSource() throws PropertyVetoException {
        com.mchange.v2.c3p0.ComboPooledDataSource dataSource = createDataSource(properties, com.mchange.v2.c3p0.ComboPooledDataSource.class);
        dataSource.setJdbcUrl(properties.determineUrl());
        dataSource.setUser(properties.determineUsername());
//        dataSource.setPassword(properties.determinePassword());
        dataSource.setDriverClass(properties.determineDriverClassName());
        return dataSource;
    }

    //=================non-pooling DataSource=================
    @Bean
    public DataSource simpleDriverDataSource() {
        return createDataSource(properties, SimpleDriverDataSource.class);
    }

    @Bean
    public DataSource driverManagerDataSource() {
        return createDataSource(properties, DriverManagerDataSource.class);
    }

    /**
     * only for Oracle
     *
     * @return
     * @throws SQLException
     */
    @ConditionalOnProperty(name = "spring.profiles.active", havingValue = "oracle")
    @Bean
    public DataSource oracleDataSource() throws SQLException {
        oracle.jdbc.datasource.OracleDataSource dataSource = createDataSource(properties, oracle.jdbc.replay.OracleDataSourceImpl.class);
        dataSource.setURL(properties.determineUrl());
        dataSource.setUser(properties.determineUsername());
//        dataSource.setPassword(properties.determinePassword());
        dataSource.setServerName(properties.determineDriverClassName());
        return dataSource;
    }
}