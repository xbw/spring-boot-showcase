package com.spring.boot.framework.datasource;


import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiObjectFactoryBean;

import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * https://stackoverflow.com/questions/22904888/tomcat-dbcp-and-namingexception
 * https://stackoverflow.com/questions/24941829/how-to-create-jndi-context-in-spring-boot-with-embedded-tomcat-container/53457808
 * <p>
 * https://blog.roncoo.com/article/133919
 */
@ConditionalOnProperty(name = "spring.profiles.active", havingValue = "jndi")
@Configuration
public class DataSourceJNDIConfigurer {

    @Bean
    public ServletWebServerFactory servletWebServer() {
        return new TomcatServletWebServerFactory() {
            @Override
            protected TomcatWebServer getTomcatWebServer(Tomcat tomcat) {
                tomcat.enableNaming(); // default is unable
                return super.getTomcatWebServer(tomcat);
            }

            @Override
            protected void postProcessContext(Context context) {
                ContextResource resource = new ContextResource();
                resource.setName("jdbc/customJNDI");
                resource.setType(DataSource.class.getName());
                /**
                 * "factory" inactive, dataSource -> {@link org.apache.tomcat.dbcp.dbcp2.BasicDataSourceFactory}
                 */
                resource.setProperty("factory", "org.apache.tomcat.jdbc.pool.DataSourceFactory");
                resource.setProperty("driverClassName", "org.h2.Driver");
                resource.setProperty("url", "jdbc:h2:mem:test");
                resource.setProperty("username", "sa");
                resource.setProperty("password", "");
                context.getNamingResources().addResource(resource);
                super.postProcessContext(context);
            }
        };
    }

    /**
     * This is not necessary in Spring Boot 2.x , use 'spring.datasource.jndi-name' instead.
     *
     * @return
     * @throws NamingException
     * @Bean active, dataSource -> com.sun.proxy.$Proxy61
     * @Bean inactive, dataSource -> {@link DataSourceJNDIConfigurer#servletWebServer()} {@code TomcatServletWebServerFactory#postProcessContext(Context)}
     */
    @ConditionalOnMissingClass("org.springframework.boot.web.servlet.support.SpringBootServletInitializer")
    @Bean
    public DataSource jndiDataSource() throws NamingException {
        JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
        bean.setJndiName("java:/comp/env/jdbc/customJNDI");

        bean.setProxyInterface(DataSource.class);
        bean.setLookupOnStartup(false);
        bean.afterPropertiesSet();

        return (DataSource) bean.getObject();
    }
}