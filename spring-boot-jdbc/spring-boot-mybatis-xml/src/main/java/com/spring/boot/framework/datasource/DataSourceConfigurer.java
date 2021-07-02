package com.spring.boot.framework.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;


/**
 * @see org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
 * @see org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration#sqlSessionFactory
 */
@Configuration
public class DataSourceConfigurer {
    @Autowired
    Environment env;
    @Autowired
    MybatisProperties mybatisProperties;

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource, Environment env) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);

        // XML use mybatis.config-location
        if (env.getProperty("mybatis.configuration") == null) {
            sessionFactory.setConfigLocation(new DefaultResourceLoader().getResource(env.getProperty("mybatis.config-location")));
            sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapper-locations")));
            sessionFactory.setTypeAliasesPackage(env.getProperty("mybatis.type-aliases-package"));
        } else {
            sessionFactory.setConfiguration(mybatisProperties.getConfiguration());
        }

        return sessionFactory.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}