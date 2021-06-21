package com.spring.boot.framework.datasource;


import com.spring.boot.framework.interceptor.BeetlSqlDebugInterceptor;
import com.zaxxer.hikari.HikariDataSource;
import org.beetl.sql.core.Interceptor;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.ext.DBInitHelper;
import org.beetl.sql.starter.SQLManagerCustomize;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

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
@ConditionalOnProperty(name = "spring.profiles.active", havingValue = "default")
@Configuration
public class DataSourceSingleConfigurer {

    @Value("${beetlsql.custom.sql-simple:false}")
    private boolean isSimple;

    /**
     * 与下面同数据源
     *
     * @param env
     * @return
     */
    @Bean
    public DataSource dataSource(Environment env) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        return dataSource;
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public SQLManagerCustomize sqlManagerCustomize() {
        return new SQLManagerCustomize() {
            @Override
            public void customize(String sqlMangerName, SQLManager sqlManager) {
                /**
                 * 初始化sql，这里也可以对sqlManager进行修改
                 * @see DBInitHelper
                 * @see NullPointerException
                 */
//                DBInitHelper.executeSqlScript(sqlManager, "static/init.sql");
                sqlManager.setInters(new Interceptor[]{new BeetlSqlDebugInterceptor(isSimple)});
            }
        };
    }
}