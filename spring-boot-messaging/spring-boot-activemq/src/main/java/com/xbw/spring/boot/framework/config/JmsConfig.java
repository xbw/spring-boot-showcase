package com.xbw.spring.boot.framework.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.jms.JmsProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.util.Assert;

import javax.jms.ConnectionFactory;

/**
 * @author xbw
 * @see org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration
 * @see org.springframework.boot.autoconfigure.jms.JmsProperties
 */
@Configuration(proxyBeanMethods = false)
public class JmsConfig {

    @Autowired
    JmsProperties properties;

    /**
     * @param connectionFactory
     * @return
     * @see org.springframework.boot.autoconfigure.jms.JmsAnnotationDrivenConfiguration#jmsListenerContainerFactoryConfigurer()
     * @see org.springframework.boot.autoconfigure.jms.JmsAnnotationDrivenConfiguration#jmsListenerContainerFactory
     */

    @Bean("topicJmsListenerContainerFactory")
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(
            DefaultJmsListenerContainerFactoryConfigurer configurer, ConnectionFactory connectionFactory) {
        Assert.notNull(connectionFactory, "ConnectionFactory must not be null");
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        // Set 'spring.jms.pub-sub-domain=false' when both use Queue and Topic.
        factory.setPubSubDomain(true);
        return factory;
    }

    @Bean
    public SimpleJmsListenerContainerFactory simpleJmsListenerContainerFactory(ConnectionFactory connectionFactory) {
        Assert.notNull(connectionFactory, "ConnectionFactory must not be null");
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        // Set 'spring.jms.pub-sub-domain=false' when both use Queue and Topic.
        factory.setPubSubDomain(true);
        //factory.setPubSubDomain(properties.isPubSubDomain());
        JmsProperties.Listener listener = properties.getListener();
        factory.setAutoStartup(listener.isAutoStartup());
        if (listener.getAcknowledgeMode() != null) {
            factory.setSessionAcknowledgeMode(listener.getAcknowledgeMode().getMode());
        }
        return factory;
    }

}