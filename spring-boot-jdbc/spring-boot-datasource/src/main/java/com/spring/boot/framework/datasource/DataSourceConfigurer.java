package com.spring.boot.framework.datasource;


import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.JDBCType;

/**
 * https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/jdbc/DataSourceBuilder.html
 */

/**
 * Database initialized order
 * {@code org.springframework.boot.autoconfigure.jdbc.DataSourceInitializer#createSchema()}
 *
 * @see org.springframework.jdbc.datasource.init.DatabasePopulatorUtils#execute(org.springframework.jdbc.datasource.init.DatabasePopulator, DataSource)
 * @see org.springframework.jdbc.datasource.init.ResourceDatabasePopulator#populate(java.sql.Connection)
 * @see org.springframework.jdbc.datasource.init.ScriptUtils#executeSqlScript(java.sql.Connection, org.springframework.core.io.support.EncodedResource, boolean, boolean, String[], String, String, String)
 * @see org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
 */
@ConditionalOnMissingBean({DataSourceConfigurerByConfigurationProperties.class})
@Configuration
public class DataSourceConfigurer {

    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }
}