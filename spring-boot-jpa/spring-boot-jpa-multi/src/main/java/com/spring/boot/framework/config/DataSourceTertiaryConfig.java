package com.spring.boot.framework.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

@Configuration
@ConditionalOnProperty(name = "spring.datasource.multi.enable", havingValue = "true")
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef="entityManagerFactoryTertiary",
        transactionManagerRef="transactionManagerTertiary",
        basePackages= {"com.spring.boot.project.repository.tertiary"}) //设置Repository所在位置
public class DataSourceTertiaryConfig {

    @Autowired
    @Qualifier("dataSourceTertiary")
    private DataSource dataSource;

    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    private HibernateProperties hibernateProperties;

    @Bean
    @ConfigurationProperties("spring.datasource.tertiary")
    public DataSource dataSourceTertiary() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryTertiary(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .properties(getVendorProperties())
                .packages("com.spring.boot.project.model") //设置实体类所在位置
                .persistenceUnit("persistenceUnitTertiary")
                .build();
    }

    @Bean
    public EntityManager entityManagerTertiary(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryTertiary(builder).getObject().createEntityManager();
    }

    @Bean
    public PlatformTransactionManager transactionManagerTertiary(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryTertiary(builder).getObject());
    }

    private Map<String, Object> getVendorProperties() {
        return hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings());
    }

}
