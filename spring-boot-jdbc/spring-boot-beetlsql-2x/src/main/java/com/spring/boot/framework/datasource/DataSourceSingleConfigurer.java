package com.spring.boot.framework.datasource;

import com.ibeetl.starter.BeetlSqlProperties;
import com.ibeetl.starter.BeetlSqlSingleConfig;
import com.spring.boot.framework.interceptor.BeetlSqlDebugInterceptor;
import com.zaxxer.hikari.HikariDataSource;
import org.beetl.sql.core.Interceptor;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.ext.spring4.SqlManagerFactoryBean;
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
 * 使用的spring-boot的缺省dataSource无法实例化SQLManager
 * <p>
 * https://www.bookstack.cn/read/beetlsql-v2.11/d940fb64eb08bad3.md
 * https://www.bookstack.cn/read/BeetlSQL-2.12/bdd10a767c756c08.md
 *
 * @see BeetlSqlSingleConfig
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
    public SQLManager sqlManager(SqlManagerFactoryBean sqlManagerFactoryBean) {
        SQLManager sqlManager = null;
        try {
            sqlManager = sqlManagerFactoryBean.getObject();
            assert sqlManager != null;
            sqlManager.setInters(new Interceptor[]{new BeetlSqlDebugInterceptor(isSimple)});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sqlManager;
    }

}