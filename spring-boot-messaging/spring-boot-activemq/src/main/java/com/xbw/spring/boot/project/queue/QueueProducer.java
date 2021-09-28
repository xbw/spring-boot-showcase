package com.xbw.spring.boot.project.queue;

import com.xbw.spring.boot.project.bean.JmsBean;
import com.xbw.spring.boot.project.topic.TopicProducer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.support.GenericMessage;

import javax.jms.Destination;

/**
 * @author xbw
 * @see org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration
 */
@ConditionalOnMissingBean(TopicProducer.class)
@Configuration
public class QueueProducer implements InitializingBean {
    @Autowired
    JmsTemplate jmsTemplate;
    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private Destination queue;

    @Override
    public void afterPropertiesSet() throws Exception {
        jmsTemplate.send("default", session -> session.createTextMessage("ActiveMQ!"));

        jmsTemplate.send("send", session -> session.createTextMessage("JmsTemplate!"));
        jmsTemplate.convertAndSend("convertAndSend", "JmsTemplate use MessageConverter!");
        jmsTemplate.convertAndSend(queue, "JmsTemplate use Queue and MessageConverter!");

        jmsMessagingTemplate.send("sendMessaging", new GenericMessage<>("JmsMessagingTemplate!"));
        jmsMessagingTemplate.convertAndSend("convertAndSendMessaging", new JmsBean("JmsMessagingTemplate use MessageConverter!"));
        jmsMessagingTemplate.convertAndSend(queue, new JmsBean("JmsMessagingTemplate use Queue and MessageConverter!"));
    }
}