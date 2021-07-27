package com.xbw.spring.boot.framework.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.List;

/**
 * @see org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration#sqlSessionFactory(DataSource)
 * @see SqlSessionFactoryBean#buildSqlSessionFactory()
 */
@Configuration
public class DataSourceConfigurer {
    private final List<ConfigurationCustomizer> configurationCustomizers;
    @Autowired
    MybatisProperties mybatisProperties;

    public DataSourceConfigurer(ObjectProvider<List<ConfigurationCustomizer>> configurationCustomizersProvider) {
        this.configurationCustomizers = configurationCustomizersProvider.getIfAvailable();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);

        // Annotation use mybatis.configuration.*
        if (mybatisProperties.getConfigLocation() == null) {
            sessionFactory.setConfiguration(mybatisProperties.getConfiguration());

            applyConfiguration(sessionFactory);
        } else {
            sessionFactory.setConfigLocation(new DefaultResourceLoader().getResource(mybatisProperties.getConfigLocation()));
        }
        sessionFactory.setMapperLocations(mybatisProperties.resolveMapperLocations()); //Property 'mapperLocations' was specified but matching resources are not found.
        sessionFactory.setTypeAliasesPackage(mybatisProperties.getTypeAliasesPackage());
        return sessionFactory.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /**
     * @see org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration#applyConfiguration(SqlSessionFactoryBean)
     */
    private void applyConfiguration(SqlSessionFactoryBean factory) {
        org.apache.ibatis.session.Configuration configuration = mybatisProperties.getConfiguration();
        if (configuration == null && !StringUtils.hasText(mybatisProperties.getConfigLocation())) {
            configuration = new org.apache.ibatis.session.Configuration();
        }
        if (configuration != null && !CollectionUtils.isEmpty(this.configurationCustomizers)) {
            for (ConfigurationCustomizer customizer : this.configurationCustomizers) {
                customizer.customize(configuration);
            }
        }
        factory.setConfiguration(configuration);
    }
}