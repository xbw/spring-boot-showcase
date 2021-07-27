package com.xbw.spring.boot.framework.datasource;

import com.ibeetl.starter.BeetlSqlMutipleDataSourceConfig;
import com.ibeetl.starter.BeetlSqlProperties;
import org.beetl.sql.ext.spring4.BeetlSqlDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * 使用的spring-boot的缺省dataSource无法实例化SQLManager
 * <p>
 * https://www.bookstack.cn/read/beetlsql-v2.11/d940fb64eb08bad3.md
 * https://www.bookstack.cn/read/BeetlSQL-2.12/bdd10a767c756c08.md
 *
 * @see BeetlSqlMutipleDataSourceConfig
 * @see BeetlSqlProperties
 */
@ConditionalOnProperty(name = "spring.profiles.active", havingValue = "master-slave")
@Configuration
public class DataSourceMaterSlaveConfigurer {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.master")
    public DataSource masterDs() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.slave")
    public DataSource slaveDs() {
        return DataSourceBuilder.create().build();
    }

    /**
     * https://www.bookstack.cn/read/BeetlSQL-2.12/e764f7fa53266eec.md
     * BeetlSql管理数据源，如果只提供一个数据源，则认为读写均操作此数据源，
     * 如果提供多个，则默认第一个为写库，其他为读库。
     *
     * @param masterDs
     * @param slaveDs
     * @return
     * @see
     */
    @Bean
    public BeetlSqlDataSource beetlSqlDataSource(@Qualifier("masterDs") DataSource masterDs,
                                                 @Qualifier("slaveDs") DataSource slaveDs) {
        BeetlSqlDataSource source = new BeetlSqlDataSource();
        source.setMasterSource(masterDs);
        source.setSlaves(new DataSource[]{slaveDs});
        return source;
    }
}