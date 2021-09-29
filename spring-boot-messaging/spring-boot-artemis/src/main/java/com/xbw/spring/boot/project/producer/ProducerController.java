package com.xbw.spring.boot.project.producer;

import com.xbw.spring.boot.project.bean.JmsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Destination;

/**
 * @author xbw
 * @see org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration
 */
@RestController
public class ProducerController {
    @Autowired
    JmsTemplate jmsTemplate;
    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private Destination queue;
    @Autowired
    private Destination topic;

    @GetMapping("default")
    public void activeMQ() {
        jmsTemplate.send("default", session -> session.createTextMessage("ActiveMQ!"));
    }

    @GetMapping("send")
    public void send() {
        jmsTemplate.send("send", session -> session.createTextMessage("JmsTemplate!"));
        jmsMessagingTemplate.send("sendMessaging", new GenericMessage<Object>("JmsMessagingTemplate!"));
    }


    @GetMapping("convert-and-send")
    public void convertAndSend() {
        jmsTemplate.convertAndSend("convertAndSend", "JmsTemplate use MessageConverter!");
        jmsTemplate.convertAndSend(queue, "JmsTemplate use Queue and MessageConverter!");
        jmsTemplate.convertAndSend(topic, "JmsTemplate use Topic and MessageConverter!");

        jmsMessagingTemplate.convertAndSend("convertAndSendMessaging", new JmsBean("JmsMessagingTemplate use MessageConverter!"));
        jmsMessagingTemplate.convertAndSend(queue, new JmsBean("JmsMessagingTemplate use Queue and MessageConverter!"));
        jmsMessagingTemplate.convertAndSend(topic, new JmsBean("JmsMessagingTemplate use Topic and MessageConverter!"));
    }

    @GetMapping("/queue")
    public void queue(@RequestParam String text) {
        jmsMessagingTemplate.convertAndSend(queue, text);
    }

    @GetMapping("/topic")
    public void topic(@RequestParam String text) {
        jmsMessagingTemplate.convertAndSend(topic, text);
    }
}