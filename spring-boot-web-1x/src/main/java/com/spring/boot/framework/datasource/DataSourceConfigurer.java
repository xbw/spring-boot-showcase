package com.spring.boot.framework.datasource;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Database initialized order
 * {@code org.springframework.boot.autoconfigure.jdbc.DataSourceInitializer#runSchemaScripts()) }
 * {@code org.springframework.boot.autoconfigure.jdbc.DataSourceInitializer#runScripts(List, String, String)}
 *
 * @see org.springframework.jdbc.datasource.init.DatabasePopulatorUtils#execute(org.springframework.jdbc.datasource.init.DatabasePopulator, DataSource)
 * @see org.springframework.jdbc.datasource.init.ResourceDatabasePopulator#populate(java.sql.Connection)
 * @see org.springframework.jdbc.datasource.init.ScriptUtils#executeSqlScript(java.sql.Connection, org.springframework.core.io.support.EncodedResource, boolean, boolean, String, String, String, String)
 */
//@Configuration
public class DataSourceConfigurer {

    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }
}