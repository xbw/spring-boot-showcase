package com.xbw.spring.boot.framework.config;

import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.apache.activemq.artemis.jms.client.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.artemis.ArtemisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.DelegatingConnectionFactory;

import javax.jms.Destination;

/**
 * @author xbw
 * @see org.springframework.boot.autoconfigure.jms.artemis.ArtemisAutoConfiguration
 * @see org.springframework.boot.autoconfigure.jms.artemis.ArtemisProperties
 */
@Configuration(proxyBeanMethods = false)
public class ArtemisConfig {

    @Autowired
    ArtemisProperties properties;
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
     * @see org.springframework.boot.autoconfigure.jms.artemis.ArtemisXAConnectionFactoryConfiguration#jmsConnectionFactory
     * @see org.springframework.boot.autoconfigure.jms.artemis.ArtemisConnectionFactoryConfiguration.PooledConnectionFactoryConfiguration#jmsConnectionFactory
     * @see org.springframework.boot.autoconfigure.jms.artemis.ArtemisConnectionFactoryConfiguration.SimpleConnectionFactoryConfiguration#jmsConnectionFactory
     */
    //@Bean
    public DelegatingConnectionFactory connectionFactory() {
        return new DelegatingConnectionFactory();
    }
}