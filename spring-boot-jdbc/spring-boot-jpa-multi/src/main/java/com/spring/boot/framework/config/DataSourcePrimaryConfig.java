package com.spring.boot.framework.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
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
//        entityManagerFactoryRef = "entityManagerFactoryPrimary",
//        transactionManagerRef = "transactionManagerPrimary",
        basePackages = {"com.spring.boot.project.repository.primary"}) //设置Repository所在位置
public class DataSourcePrimaryConfig {

//    @Autowired
//    private DataSource dataSource;

    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    private HibernateProperties hibernateProperties;

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.primary")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource())
                .properties(getVendorProperties())
                .packages("com.spring.boot.project.model.primary") //设置实体类所在位置
                .persistenceUnit("persistenceUnitPrimary")
                .build();
    }

    @Primary
    @Bean
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactory(builder).getObject().createEntityManager();
    }

    @Primary
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactory(builder).getObject());
    }

    private Map<String, Object> getVendorProperties() {
        return hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings());
    }

}
