package com.xbw.spring.boot.framework.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.DelegatingConnectionFactory;

import javax.jms.Destination;

/**
 * @author xbw
 * @see org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration
 * @see org.springframework.boot.autoconfigure.jms.activemq.ActiveMQProperties
 */
@Configuration(proxyBeanMethods = false)
public class ActiveMQConfig {

    @Autowired
    ActiveMQProperties properties;
    @Value("${activemq.queue.name}")
    private String queue;
    @Value("${activemq.topic.name}")
    private String topic;

    @Bean
    public Destination queue() {
        return new ActiveMQQueue(queue);
    }

    @Bean
    public Destination topic() {
        return new ActiveMQTopic(topic);
    }

    /**
     * @return
     * @see javax.jms.ConnectionFactory
     * @see org.springframework.boot.autoconfigure.jms.JndiConnectionFactoryAutoConfiguration#jmsConnectionFactory
     * @see org.springframework.boot.autoconfigure.jms.activemq.ActiveMQXAConnectionFactoryConfiguration#jmsConnectionFactory
     * @see org.springframework.boot.autoconfigure.jms.activemq.ActiveMQConnectionFactoryConfiguration.PooledConnectionFactoryConfiguration#jmsConnectionFactory
     * @see org.springframework.boot.autoconfigure.jms.activemq.ActiveMQConnectionFactoryConfiguration.SimpleConnectionFactoryConfiguration#jmsConnectionFactory
     * @see org.springframework.boot.autoconfigure.jms.activemq.ActiveMQConnectionFactoryConfiguration.SimpleConnectionFactoryConfiguration.CachingConnectionFactoryConfiguration#jmsConnectionFactory
     */
    //@Bean
    public DelegatingConnectionFactory connectionFactory() {
        return new DelegatingConnectionFactory();
    }

}