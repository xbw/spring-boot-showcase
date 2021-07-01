package com.spring.boot.framework.datasource;


import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiObjectFactoryBean;

import javax.naming.NamingException;
import javax.sql.DataSource;


@ConditionalOnProperty(name = "spring.profiles.active", havingValue = "jndi")
@Configuration
public class DataSourceJNDIConfigurer {

    @Bean
    public TomcatEmbeddedServletContainerFactory tomcatEmbeddedServletContainer() {
        return new TomcatEmbeddedServletContainerFactory() {
            @Override
            protected TomcatEmbeddedServletContainer getTomcatEmbeddedServletContainer(Tomcat tomcat) {
                tomcat.enableNaming(); // default is unable
                return super.getTomcatEmbeddedServletContainer(tomcat);
            }
        };
    }

    @Bean
    public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer() {
        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                if (container instanceof TomcatEmbeddedServletContainerFactory) {
                    TomcatEmbeddedServletContainerFactory tomcatEmbeddedServletContainerFactory = (TomcatEmbeddedServletContainerFactory) container;
                    tomcatEmbeddedServletContainerFactory.addContextCustomizers(new TomcatContextCustomizer() {
                        @Override
                        public void customize(Context context) {
                            ContextResource contextResource = new ContextResource();
                            contextResource.setName("jdbc/customJNDI");
                            contextResource.setType(DataSource.class.getName());
                            /**
                             * "factory" inactive, dataSource -> {@link org.apache.tomcat.dbcp.dbcp2.BasicDataSourceFactory}
                             */
                            contextResource.setProperty("factory", "org.apache.tomcat.jdbc.pool.DataSourceFactory");
                            contextResource.setProperty("driverClassName", "org.h2.Driver");
                            contextResource.setProperty("url", "jdbc:h2:mem:test");
                            contextResource.setProperty("username", "sa");
                            contextResource.setProperty("password", "");
                            context.getNamingResources().addResource(contextResource);
                        }
                    });
                }
            }
        };
    }

    /**
     * @return
     * @throws IllegalArgumentException
     * @throws NamingException
     */
    @Bean
    public DataSource jndiDataSource() throws IllegalArgumentException, NamingException {
        JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
        bean.setJndiName("java:/comp/env/jdbc/customJNDI");
        bean.setProxyInterface(DataSource.class);
        bean.setLookupOnStartup(false);
        bean.afterPropertiesSet();
        return (DataSource) bean.getObject();
    }
}