package com.spring.boot.framework.datasource;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * 使用的spring-boot的缺省dataSource，
 * beetlsql3在spring-boot下无法实例化SQLManager
 * https://gitee.com/xiandafu/beetlsql/issues/I2AYJ5
 * https://www.kancloud.cn/xiandafu/beetlsql3_guide/1967902
 *
 * @see BeetlSqlDataSource
 * @see BeetlSqlProperties
 */
@ConditionalOnProperty(name = "spring.profiles.active", havingValue = "dynamic")
@Configuration
public class DataSourceDynamicConfigurer {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.dynamic")
    public DataSource dynamicDs() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.dynamic2")
    public DataSource dynamic2Ds() {
        return DataSourceBuilder.create().build();
    }

}