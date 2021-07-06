package com.spring.boot.framework.datasource;

import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;


/**
 * @see org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration#sqlSessionFactory(DataSource)
 * @see SqlSessionFactoryBean#buildSqlSessionFactory()
 */
@Configuration
public class DataSourceConfigurer {
    @Autowired
    Environment env;
    @Autowired
    MybatisProperties mybatisProperties;

    /**
     * TODO MapWrapper not work
     *
     * @return
     * @throws IOException
     */
    @Bean
    public org.apache.ibatis.session.Configuration mybatisConfiguration() throws IOException {
        Resource configLocation = new DefaultResourceLoader().getResource(env.getProperty("mybatis.config-location"));
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(configLocation.getInputStream());
        //这步只是拿到初始化 super(new Configuration()); Configuration这个对象  并没有用到解析classpath:mybatis-config.xml得到的Document对象
        org.apache.ibatis.session.Configuration configuration = xmlConfigBuilder.getConfiguration();
        //这步才开始利用到 上面解析mybatis-config.xml得到的Document对象
        configuration = xmlConfigBuilder.parse();
        return configuration;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource, Environment env) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);

        // XML use mybatis.config-location
        if (env.getProperty("mybatis.configuration") == null) {
            sessionFactory.setConfigLocation(new DefaultResourceLoader().getResource(env.getProperty("mybatis.config-location")));
        } else {
            sessionFactory.setConfiguration(mybatisProperties.getConfiguration());
        }
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapper-locations")));
        sessionFactory.setTypeAliasesPackage(env.getProperty("mybatis.type-aliases-package"));
        return sessionFactory.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}