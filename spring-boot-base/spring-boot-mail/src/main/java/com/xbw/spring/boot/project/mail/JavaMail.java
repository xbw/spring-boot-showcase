package com.xbw.spring.boot.project.mail;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

public class JavaMail {
    private Properties properties;

    public void sendText() {
        try {
            Session session = getSession();
            Transport transport = getTransport(session);
            Message message = getMessage(session);
            message.setText("This is a test mail send by Java Mail!");

            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendHtml() {
        try {
            Session session = getSession();
            Transport transport = getTransport(session);
            Message message = getMessage(session);
            message.setContent("<h1>This is a test mail send by Java Mail!</h1>", "text/html"
            );
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private Session getSession() {
        properties = new Properties();
        try {
            properties.load(ClassLoader.getSystemResourceAsStream("application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        properties.setProperty("mail.debug", properties.getProperty("spring.mail.properties.mail.debug"));
        properties.setProperty("mail.smtp.auth", properties.getProperty("spring.mail.properties.mail.smtp.auth"));
        properties.setProperty("mail.host", properties.getProperty("spring.mail.host"));
        properties.setProperty("mail.port", properties.getProperty("spring.mail.port"));
        properties.setProperty("mail.transport.protocol", properties.getProperty("spring.mail.protocol"));

        Session session = Session.getInstance(properties);
        session = Session.getDefaultInstance(properties);
        session.setDebug(true);
        return session;
    }

    private Transport getTransport(Session session) {
        Transport transport = null;
        try {
            transport = session.getTransport();
            transport.connect(properties.getProperty("spring.mail.username"), properties.getProperty("spring.mail.password"));
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return transport;
    }

    private Message getMessage(Session session) {
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(properties.getProperty("spring.mail.properties.from.address")));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(properties.getProperty("spring.mail.properties.to.address")));

            message.setSubject("Java Mail");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return message;
    }
}