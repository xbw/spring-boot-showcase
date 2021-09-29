package com.xbw.spring.boot.project.topic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author xbw
 * @see org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration
 */
@Component
public class TopicConsumer {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @JmsListener(destination = "default")
    public void receive(String message) {
        logger.info("{} default: {}", this.getClass().getSimpleName(), message);
    }

    @JmsListener(destination = "${activemq.topic.name}")
    public void convertAndSend(String message) {
        logger.info("{}-1: {}", this.getClass().getSimpleName(), message);
    }

    @JmsListener(destination = "${activemq.topic.name}")
    public void convertAndSend2(String message) {
        logger.info("{}-2: {}", this.getClass().getSimpleName(), message);
    }

    @JmsListener(destination = "${activemq.topic.name}", containerFactory = "topicJmsListenerContainerFactory")
    public void convertAndSend3(String message) {
        logger.info("{}-3: {}", this.getClass().getSimpleName(), message);
    }

    @JmsListener(destination = "${activemq.topic.name}", containerFactory = "simpleJmsListenerContainerFactory")
    public void convertAndSend4(String message) {
        logger.info("{}-4: {}", this.getClass().getSimpleName(), message);
    }

}