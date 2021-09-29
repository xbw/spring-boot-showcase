package com.xbw.spring.boot.project.topic;

import com.xbw.spring.boot.project.bean.JmsBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.support.GenericMessage;

import javax.jms.Destination;

/**
 * @author xbw
 * @see org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration
 */
@ConditionalOnProperty(name = "spring.jms.pub-sub-domain", havingValue = "true")
@Configuration
public class TopicProducer implements InitializingBean {
    @Autowired
    JmsTemplate jmsTemplate;
    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private Destination topic;


    @Override
    public void afterPropertiesSet() throws Exception {
        // inactive
        jmsTemplate.send("default", session -> session.createTextMessage("ActiveMQ!"));

        jmsTemplate.send("send", session -> session.createTextMessage("JmsTemplate!"));
        jmsTemplate.convertAndSend("convertAndSend", "JmsTemplate use MessageConverter!");
        jmsTemplate.convertAndSend(topic, "JmsTemplate use Topic and MessageConverter!");

        jmsMessagingTemplate.send("sendMessaging", new GenericMessage<>("JmsMessagingTemplate!"));
        jmsMessagingTemplate.convertAndSend("convertAndSendMessaging", new JmsBean("JmsMessagingTemplate use MessageConverter!"));
        jmsMessagingTemplate.convertAndSend(topic, new JmsBean("JmsMessagingTemplate use Topic and MessageConverter!"));
    }
}