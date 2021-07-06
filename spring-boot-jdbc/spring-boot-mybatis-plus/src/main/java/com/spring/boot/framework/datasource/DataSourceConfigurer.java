package com.spring.boot.framework.datasource;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.MybatisMapWrapperFactory;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.spring.boot.framework.config.MybatisPlusConfig;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.List;

/**
 * @see com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration#sqlSessionFactory(DataSource)
 */
@Configuration
public class DataSourceConfigurer {
    private final List<ConfigurationCustomizer> configurationCustomizers;
    @Autowired
    MybatisPlusProperties mybatisPlusProperties;

    public DataSourceConfigurer(ObjectProvider<List<ConfigurationCustomizer>> configurationCustomizersProvider) {
        this.configurationCustomizers = configurationCustomizersProvider.getIfAvailable();
    }


    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean sessionFactory = new MybatisSqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);

        if (mybatisPlusProperties.getConfigLocation() == null) {
            sessionFactory.setConfiguration(mybatisPlusProperties.getConfiguration());

            applyConfiguration(sessionFactory);
        } else {
            sessionFactory.setConfigLocation(new DefaultResourceLoader().getResource(mybatisPlusProperties.getConfigLocation()));
        }
        sessionFactory.setMapperLocations(mybatisPlusProperties.resolveMapperLocations());
        sessionFactory.setTypeAliasesPackage(mybatisPlusProperties.getTypeAliasesPackage());
        return sessionFactory.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /**
     * @see com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration#applyConfiguration(MybatisSqlSessionFactoryBean)
     */
    private void applyConfiguration(MybatisSqlSessionFactoryBean factory) {
        // TODO 使用 MybatisConfiguration
        MybatisConfiguration configuration = mybatisPlusProperties.getConfiguration();
        if (configuration == null && !StringUtils.hasText(mybatisPlusProperties.getConfigLocation())) {
            configuration = new MybatisConfiguration();
        }
        if (configuration != null && !CollectionUtils.isEmpty(this.configurationCustomizers)) {
            for (ConfigurationCustomizer customizer : this.configurationCustomizers) {
                customizer.customize(configuration);
            }
        }
        factory.setConfiguration(configuration);
    }
}