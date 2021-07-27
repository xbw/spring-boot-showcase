package com.xbw.spring.boot.framework.datasource;


import com.xbw.spring.boot.framework.dynamic.DynamicDataSource;
import com.xbw.spring.boot.framework.dynamic.enums.DynamicDataSourceEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/jdbc/DataSourceBuilder.html
 *
 * @see org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
 */
@ConditionalOnProperty(name = "spring.profiles.active", havingValue = "dynamic")
@Configuration
public class DataSourceDynamicConfigurer {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean
    @ConfigurationProperties("spring.datasource.primary")
    public DataSource primaryDS() {
        return DataSourceBuilder.create().type(org.apache.tomcat.jdbc.pool.DataSource.class).build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.secondary")
    public DataSource secondaryDS() {
        return DataSourceBuilder.create().type(org.apache.commons.dbcp2.BasicDataSource.class).build();
    }

    @Bean
    @Primary
    public DynamicDataSource dynamicDataSource(DataSource dataSource, DataSource primaryDS, DataSource secondaryDS) {
        Map<Object, Object> targetDataSources = new HashMap<>(5);
        targetDataSources.put(DynamicDataSourceEnum.DEFAULT.name(), dataSource);
        targetDataSources.put(DynamicDataSourceEnum.PRIMARY.name(), primaryDS);
        targetDataSources.put(DynamicDataSourceEnum.SECONDARY.name(), secondaryDS);

        printDS(dataSource);
        printDS(primaryDS);
        printDS(secondaryDS);

        return new DynamicDataSource(dataSource, targetDataSources);
    }

    void printDS(DataSource dataSource) {
        try {
            logger.debug("dataSource -> {}, connection -> {}", dataSource.getClass().getName(), dataSource.getConnection());
        } catch (SQLException e) {
            logger.error("SQLException -> ", e);
        }
    }
}