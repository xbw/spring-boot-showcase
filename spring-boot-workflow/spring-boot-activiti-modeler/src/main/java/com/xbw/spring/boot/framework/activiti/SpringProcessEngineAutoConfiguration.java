package com.xbw.spring.boot.framework.activiti;

import org.activiti.engine.*;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author xbw
 * @see org.activiti.spring.boot.AbstractProcessEngineAutoConfiguration
 */
@EnableConfigurationProperties(ActivitiProperties.class)
@Configuration
public class SpringProcessEngineAutoConfiguration {
    private static final String DB_ORACLE = "Oracle";
    private static final String DB_MYSQL = "MySQL";

    @Bean
    public SpringProcessEngineConfiguration springProcessEngineConfiguration(DataSource dataSource,
                                                                             PlatformTransactionManager transactionManager,
                                                                             ActivitiProperties activitiProperties) {
        SpringProcessEngineConfiguration configuration = new SpringProcessEngineConfiguration();
        configuration.setDataSource(dataSource);
        try {
            Connection connection = dataSource.getConnection();
            System.out.printf("DatabaseProductName: %s, Schema: %s, Catalog: %s%n",
                    connection.getMetaData().getDatabaseProductName(),
                    connection.getSchema(),
                    connection.getCatalog());
            if (DB_ORACLE.equals(connection.getMetaData().getDatabaseProductName())) {
                configuration.setDatabaseSchema(connection.getSchema());
            } else if (DB_MYSQL.equals(connection.getMetaData().getDatabaseProductName())) {
                configuration.setDatabaseSchema(connection.getCatalog());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        configuration.setTransactionManager(transactionManager);

        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_FALSE);
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_CREATE_DROP);
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfigurationImpl.DB_SCHEMA_UPDATE_CREATE);
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfigurationImpl.DB_SCHEMA_UPDATE_DROP_CREATE);
        configuration.setDatabaseSchemaUpdate(activitiProperties.getDatabaseSchemaUpdate());

        return configuration;
    }

    @Bean
    public ProcessEngineFactoryBean processEngine(SpringProcessEngineConfiguration configuration) throws Exception {
        ProcessEngineFactoryBean processEngineFactoryBean = new ProcessEngineFactoryBean();
        processEngineFactoryBean.setProcessEngineConfiguration(configuration);
        return processEngineFactoryBean;
    }

//    @Bean
//    public ProcessEngine processEngine(ProcessEngineFactoryBean factory) throws Exception {
//        return factory.getObject();
//    }

    @Bean
    public RepositoryService repositoryService(ProcessEngine processEngine) {
        return processEngine.getRepositoryService();
    }

    @Bean
    public RuntimeService runtimeService(ProcessEngine processEngine) {
        return processEngine.getRuntimeService();
    }

    @Bean
    public IdentityService identityService(ProcessEngine processEngine) {
        return processEngine.getIdentityService();
    }

    @Bean
    public FormService formService(ProcessEngine processEngine) {
        return processEngine.getFormService();
    }

    @Bean
    public TaskService taskService(ProcessEngine processEngine) {
        return processEngine.getTaskService();
    }

    @Bean
    public HistoryService historyService(ProcessEngine processEngine) {
        return processEngine.getHistoryService();
    }

    @Bean
    public ManagementService managementService(ProcessEngine processEngine) {
        return processEngine.getManagementService();
    }

    @Bean
    public DynamicBpmnService dynamicBpmnService(ProcessEngine processEngine) {
        return processEngine.getDynamicBpmnService();
    }
}
