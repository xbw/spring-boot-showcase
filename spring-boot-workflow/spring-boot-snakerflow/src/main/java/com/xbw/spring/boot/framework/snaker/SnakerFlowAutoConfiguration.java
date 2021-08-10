package com.xbw.spring.boot.framework.snaker;

import org.snaker.engine.*;
import org.snaker.engine.access.jdbc.JdbcAccess;
import org.snaker.engine.cache.CacheManager;
import org.snaker.engine.core.*;
import org.snaker.engine.impl.LogInterceptor;
import org.snaker.engine.impl.SurrogateInterceptor;
import org.snaker.engine.spring.SpringConfiguration;
import org.snaker.engine.spring.SpringJdbcAccess;
import org.snaker.engine.spring.SpringSnakerEngine;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;

import javax.sql.DataSource;

/**
 * @author xbw
 */
@Order
@Configuration
@ConditionalOnClass({DataSource.class, EmbeddedDatabaseType.class})
@AutoConfigureAfter({DataSourceAutoConfiguration.class, RedisAutoConfiguration.class})
public class SnakerFlowAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public SpringSnakerEngine snakerEngine(ApplicationContext context) {
        SpringSnakerEngine snakerEngine = new SpringSnakerEngine();
        SpringConfiguration configuration = new SpringConfiguration(context);
        snakerEngine.configure(configuration);
        return snakerEngine;
    }

    @Bean
    @ConditionalOnMissingBean
    public LobHandler lobHandler() {
        return new DefaultLobHandler();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "snaker.flow", name = "db-access-type", havingValue = "spring")
    public DBAccess dbAccess(DataSource dataSource,
                             LobHandler lobHandler) {
        SpringJdbcAccess dbAccess = new SpringJdbcAccess();
        dbAccess.setDataSource(dataSource);
        dbAccess.setLobHandler(lobHandler);
        return dbAccess;
    }

    @Bean
    @ConditionalOnProperty(prefix = "snaker.flow", name = "db-access-type", havingValue = "jdbc", matchIfMissing = true)
    public DBAccess jdbcDBAccess(DataSource dataSource) {
        JdbcAccess jdbcAccess = new JdbcAccess();
        jdbcAccess.setDataSource(dataSource);
        return jdbcAccess;
    }

    @Bean
    @ConditionalOnMissingBean
    public CacheManager cacheManager() {
        return new org.snaker.engine.cache.memory.MemoryCacheManager();
    }

    @Bean
    @ConditionalOnMissingBean
    public IProcessService processService(DBAccess dbAccess, CacheManager cacheManager) {
        ProcessService processService = new ProcessService();
        processService.setAccess(dbAccess);
        processService.setCacheManager(cacheManager);
        return processService;
    }

    @Bean
    @ConditionalOnMissingBean
    public IOrderService orderService(DBAccess dbAccess) {
        OrderService orderService = new OrderService();
        orderService.setAccess(dbAccess);
        return orderService;
    }

    @Bean
    @ConditionalOnMissingBean
    public ITaskService taskService(DBAccess dbAccess) {
        TaskService taskService = new TaskService();
        taskService.setAccess(dbAccess);
        return taskService;
    }

    @Bean
    @ConditionalOnMissingBean
    public IQueryService queryService(DBAccess dbAccess) {
        QueryService queryService = new QueryService();
        queryService.setAccess(dbAccess);
        return queryService;
    }

    @Bean
    @ConditionalOnMissingBean
    public IManagerService managerService(DBAccess dbAccess) {
        ManagerService managerService = new ManagerService();
        managerService.setAccess(dbAccess);
        return managerService;
    }

    @Bean
    @ConditionalOnMissingBean
    public LogInterceptor logInterceptor() {
        return new LogInterceptor();
    }


    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "snaker.flow", name = "enable-surrogate", havingValue = "true")
    public SurrogateInterceptor surrogateInterceptor() {
        return new SurrogateInterceptor();
    }

}
