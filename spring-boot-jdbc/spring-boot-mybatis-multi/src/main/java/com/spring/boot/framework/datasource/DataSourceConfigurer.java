package com.spring.boot.framework.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * Another option is to use AbstractRoutingDataSource
 */
@Configuration
public class DataSourceConfigurer {

    @Autowired
    MybatisProperties mybatisProperties;
    @Autowired
    Environment env;

    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.primary")
    public DataSource primaryDs() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.secondary")
    public DataSource secondaryDs() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        return this.ssf(dataSource).getObject();
    }

    @Primary
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public SqlSessionFactory primarySSF(@Qualifier("primaryDs") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = this.ssf(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.primary.mapper-locations")));
        return sessionFactory.getObject();
    }

    @Bean
    public SqlSessionTemplate primarySST(@Qualifier("primarySSF") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public SqlSessionFactory secondarySSF(@Qualifier("secondaryDs") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = this.ssf(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.secondary.mapper-locations")));
        return sessionFactory.getObject();
    }

    @Bean
    public SqlSessionTemplate secondarySST(@Qualifier("secondarySSF") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    private SqlSessionFactoryBean ssf(DataSource dataSource) {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        if (mybatisProperties.getConfigLocation() == null) {
            sessionFactory.setConfiguration(mybatisProperties.getConfiguration());
        } else {
            sessionFactory.setConfigLocation(new DefaultResourceLoader().getResource(mybatisProperties.getConfigLocation()));
        }
        sessionFactory.setMapperLocations(mybatisProperties.resolveMapperLocations());
        sessionFactory.setTypeAliasesPackage(mybatisProperties.getTypeAliasesPackage());
        return sessionFactory;
    }
}