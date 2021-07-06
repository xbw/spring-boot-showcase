package com.spring.boot.framework.config;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.MybatisMapWrapperFactory;
import com.spring.boot.framework.mybatis.ObjectWrapperFactoryConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
//@MapperScan("com.spring.boot.**.mapper")
public class MybatisPlusConfig {

    @Autowired
    MybatisPlusProperties mybatisPlusProperties;

    @ConditionalOnMissingBean(ObjectWrapperFactoryConverter.class)
    @Bean
    public ConfigurationCustomizer mybatisConfigurationCustomizer() {
        return configuration -> configuration.setObjectWrapperFactory(new MybatisMapWrapperFactory());
    }

    @ConditionalOnMissingBean(ConfigurationCustomizer.class)
    @Bean
    @ConfigurationProperties(prefix = "mybatis-plus.configuration")
    public MybatisConfiguration mybatisConfiguration() {
        MybatisConfiguration configuration = mybatisPlusProperties.getConfiguration();
        if (configuration == null) {
            configuration = new MybatisConfiguration();
        }
        configuration.setObjectWrapperFactory(new MybatisMapWrapperFactory());
        return configuration;
    }

}
