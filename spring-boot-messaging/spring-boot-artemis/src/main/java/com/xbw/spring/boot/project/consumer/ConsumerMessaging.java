package com.xbw.spring.boot.project.consumer;

import com.xbw.spring.boot.project.bean.JmsBean;
import org.apache.activemq.artemis.jms.client.ActiveMQTextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;

/**
 * @author xbw
 * @see org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration
 */
@Component
public class ConsumerMessaging {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @JmsListener(destination = "default")
    public void receive(String message) {
        logger.info("{} default: {}", this.getClass().getSimpleName(), message);
    }

    @JmsListener(destination = "sendMessaging")
    public void send(Object message) throws JMSException {
        ActiveMQTextMessage textMessage = (ActiveMQTextMessage) message;
        logger.info("{} send: {}", this.getClass().getSimpleName(), textMessage.getText());
    }

    @JmsListener(destination = "convertAndSendMessaging")
    public void convertAndSend(JmsBean message) {
        logger.info("{} convertAndSend: {}", this.getClass().getSimpleName(), message);
    }
}