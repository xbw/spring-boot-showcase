package com.spring.boot.framework.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;

import javax.sql.DataSource;

/**
 * @see org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
 * @see org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration#sqlSessionFactory
 */
@Configuration
public class DataSourceConfigurer {
    @Autowired
    MybatisProperties mybatisProperties;

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);

        // Annotation use mybatis.configuration.*
        if (mybatisProperties.getConfigLocation() == null) {
            sessionFactory.setConfiguration(mybatisProperties.getConfiguration());
        } else {
            sessionFactory.setConfigLocation(new DefaultResourceLoader().getResource(mybatisProperties.getConfigLocation()));
            sessionFactory.setMapperLocations(mybatisProperties.resolveMapperLocations());
            sessionFactory.setTypeAliasesPackage(mybatisProperties.getTypeAliasesPackage());
        }

        return sessionFactory.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}