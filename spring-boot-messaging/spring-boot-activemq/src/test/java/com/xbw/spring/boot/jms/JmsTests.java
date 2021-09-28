package com.xbw.spring.boot.jms;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;

import java.time.Instant;
import java.util.Random;

/**
 * @author xbw
 */
@SpringBootTest
class JmsTests {
    private final static int NUM = 10;
    @Autowired
    JmsTemplate jmsTemplate;
    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;

    @Test
    void activeMQ() {
        for (int i = 0; i < NUM; i++) {
            jmsTemplate.send("default", session -> session.createTextMessage("ActiveMQ! " + Instant.now()));
            try {
                Thread.sleep(new Random().nextInt(5) * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
