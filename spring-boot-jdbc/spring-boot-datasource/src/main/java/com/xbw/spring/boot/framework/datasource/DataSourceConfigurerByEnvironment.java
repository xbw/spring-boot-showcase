package com.xbw.spring.boot.framework.datasource;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Driver;
import java.sql.SQLException;

/**
 * @see org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
 */

@ConditionalOnMissingBean({DataSourceConfigurer.class, DataSourceConfigurerByConfigurationProperties.class,
        DataSourceConfigurerByDataSourceProperties.class})
@Configuration
public class DataSourceConfigurerByEnvironment {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private Environment env;
    private String url;
    private String username;
    private String password;
    private String driverClassName;


    @PostConstruct
    private void init() {
        this.url = env.getProperty("spring.datasource.url");
        this.username = env.getProperty("spring.datasource.username");
        this.password = env.getProperty("spring.datasource.password");
        this.driverClassName = env.getProperty("spring.datasource.driver-class-name");

        logger.debug("DataSourceConfigurerByEnvironment: url -> {}, username -> {}, password -> {}, driverClassName -> {}", url, username, password, driverClassName);
    }

    public DataSource dataSource(Environment env) {
        com.zaxxer.hikari.HikariDataSource dataSource = new com.zaxxer.hikari.HikariDataSource();
        dataSource.setJdbcUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        return dataSource;
    }

    @Bean
    public DataSource dataSource() {
        com.zaxxer.hikari.HikariDataSource dataSource = new com.zaxxer.hikari.HikariDataSource();
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
        return dataSource;
    }

    @Bean
    public DataSource tomcatDataSource(Environment env) {
        org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
        return dataSource;
    }

    @Bean
    public DataSource dbcp2DataSource(Environment env) {
        org.apache.commons.dbcp2.BasicDataSource dataSource = new org.apache.commons.dbcp2.BasicDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
        return dataSource;
    }

    public DataSource ucpDataSource() throws SQLException {
//        oracle.ucp.jdbc.PoolDataSource dataSource = new oracle.ucp.jdbc.PoolDataSourceImpl();
        oracle.ucp.jdbc.PoolDataSource dataSource = oracle.ucp.jdbc.PoolDataSourceFactory.getPoolDataSource();

        dataSource.setURL(url);
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setServerName(driverClassName);

        dataSource.setValidateConnectionOnBorrow(true);
        if (url.contains("jdbc:mysql:")) {
            dataSource.setConnectionFactoryClassName("com.mysql.cj.jdbc.MysqlDataSource");
        } else {
            dataSource.setConnectionFactoryClassName("oracle.jdbc.pool.OracleDataSource");
        }

        return dataSource;
    }

    @Bean
    public DataSource ucpDataSource(Environment env) throws SQLException {
//        oracle.ucp.jdbc.PoolDataSource dataSource = new oracle.ucp.jdbc.PoolDataSourceImpl();
        oracle.ucp.jdbc.PoolDataSource dataSource = oracle.ucp.jdbc.PoolDataSourceFactory.getPoolDataSource();

        dataSource.setURL(url);
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setServerName(driverClassName);

        dataSource.setValidateConnectionOnBorrow(true);

        // https://stackoverflow.com/questions/39720350/jdbc-oracle-universal-connection-pool-error-ucp-45060-invalid-life-cycle-state
        // https://docs.oracle.com/en/database/oracle/oracle-database/12.2/jjucp/validating-ucp-connections.html#GUID-139508B9-1456-41CA-A860-2AFD9352C1E6
        // https://docs.oracle.com/cd/E11882_01/java.112/e12265/connect.htm#CHDGEJJF
        dataSource.setConnectionFactoryClassName(env.getProperty("spring.datasource.oracleucp.connection-factory-class-name")); // custom config

        return dataSource;
    }


    //=================DataSourceConfiguration.Generic=================

    @Bean
    public DataSource druidDataSource(Environment env) {
        com.alibaba.druid.pool.DruidDataSource dataSource = new com.alibaba.druid.pool.DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
        return dataSource;
    }

    @Bean
    public DataSource tomcatDbcp2DataSource(Environment env) {
        org.apache.tomcat.dbcp.dbcp2.BasicDataSource dataSource = new org.apache.tomcat.dbcp.dbcp2.BasicDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
        return dataSource;
    }

    @Bean
    public DataSource c3p0DataSource(Environment env) throws PropertyVetoException {
        com.mchange.v2.c3p0.ComboPooledDataSource dataSource = new com.mchange.v2.c3p0.ComboPooledDataSource();
        dataSource.setJdbcUrl(url);
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setDriverClass(driverClassName);
        return dataSource;
    }


    //=================non-pooling DataSource=================
    @Bean
    public DataSource simpleDriverDataSource() throws ClassNotFoundException {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClass((Class<Driver>) Class.forName(driverClassName));
        return dataSource;
    }

    @Bean
    public DataSource driverManagerDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
        return dataSource;
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
//        oracle.jdbc.datasource.OracleDataSource dataSource = new oracle.jdbc.pool.OracleDataSource();
        oracle.jdbc.replay.OracleDataSource dataSource = new oracle.jdbc.replay.OracleDataSourceImpl();
        dataSource.setURL(url);
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setServerName(driverClassName);
        return dataSource;
    }
}