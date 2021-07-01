package com.spring.boot.framework.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

/**
 * @see org.springframework.transaction.PlatformTransactionManager
 * @see org.springframework.transaction.support.AbstractPlatformTransactionManager
 * @see org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration
 * @see org.springframework.jdbc.datasource.DataSourceTransactionManager
 * @see org.springframework.jdbc.support.JdbcTransactionManager
 * @see org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration
 * @see org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration
 * @see org.springframework.orm.jpa.JpaTransactionManager
 */
@Configuration
@EnableTransactionManagement
public class TransactionManagerConfigurer implements TransactionManagementConfigurer {
    // TODO
    @Autowired
    DataSource dataSource;

    @Autowired
    PlatformTransactionManager dataSourceTransactionManager;

    public PlatformTransactionManager dataSourceTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    public PlatformTransactionManager jdbcTransactionManager(DataSource dataSource) {
        return new JdbcTransactionManager(dataSource);
    }

    @Override
    public TransactionManager annotationDrivenTransactionManager() {
        return dataSourceTransactionManager;
    }
}