package com.xbw.spring.boot.framework.datasource;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@ConditionalOnProperty(name = "spring.profiles.active", havingValue = "unmaintained")
@Configuration
@Deprecated
public class DataSourceUnmaintainedConfigurer {

    @Bean
    @ConfigurationProperties("spring.datasource.proxool")
    public DataSource proxoolDataSource() {
        return DataSourceBuilder.create().type(org.logicalcobwebs.proxool.ProxoolDataSource.class).build();
    }


    @Bean
    @ConfigurationProperties("spring.datasource.bonecp")
    public DataSource bonecpDataSource() {
        return DataSourceBuilder.create().type(com.jolbox.bonecp.BoneCPDataSource.class).build();
    }


}