package com.xbw.spring.boot.project.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author xbw
 * @see org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration
 */
@Component
public class Consumer {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @JmsListener(destination = "default")
    public void receive(String message) {
        logger.info("{} default: {}", this.getClass().getSimpleName(), message);
    }

    @JmsListener(destination = "send")
    public void send(String message) {
        logger.info("{} send: {}", this.getClass().getSimpleName(), message);
    }

    @JmsListener(destination = "convertAndSend")
    public void convertAndSend(String message) {
        logger.info("{} convertAndSend: {}", this.getClass().getSimpleName(), message);
    }
}