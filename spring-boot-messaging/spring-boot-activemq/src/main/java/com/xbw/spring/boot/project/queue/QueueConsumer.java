package com.xbw.spring.boot.project.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author xbw
 * @see org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration
 */
@Component
public class QueueConsumer {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @JmsListener(destination = "${activemq.queue.name}")
    public void convertAndSend(String message) {
        logger.info("{}-1: {}", this.getClass().getSimpleName(), message);
    }

    @JmsListener(destination = "${activemq.queue.name}")
    public void convertAndSend2(String message) {
        logger.info("{}-2: {}", this.getClass().getSimpleName(), message);
    }
}