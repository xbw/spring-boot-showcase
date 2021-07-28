package com.xbw.spring.boot.project.mail;

import org.apache.commons.mail.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class ApacheMail {
    private Properties properties;

    public void sendText() {
        SimpleEmail email = new SimpleEmail();
        setEmail(email);
        try {
            email.setMsg("This is a test mail send by Apache Mail!");
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }

    public void sendHtml() {
        HtmlEmail email = new HtmlEmail();
        setEmail(email);

        try {
            // embed the image and get the content id
            URL url = new URL("https://spring.io/images/spring-logo-9146a4d3298760c2e7e49595184e1975.svg");
            String cid = email.embed(url, "Spring logo");

            // set the html message
            email.setHtmlMsg("<html>Logo -> <img src=\"cid:" + cid + "\"></html>");
            // set the alternative message
            email.setTextMsg("Your email client does not support HTML messages");
            email.send();
        } catch (MalformedURLException | EmailException e) {
            e.printStackTrace();
        }
    }

    public void setEmail(Email email) {
        properties = new Properties();
        try {
            properties.load(ClassLoader.getSystemResourceAsStream("application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // email.setTLS(true);
        // email.setSSL(true);

        email.setDebug(Boolean.valueOf(properties.getProperty("spring.mail.properties.mail.debug")));
        email.setCharset(properties.getProperty("spring.mail.default-encoding"));
        email.setHostName(properties.getProperty("spring.mail.host"));
        email.setSmtpPort(Integer.valueOf(properties.getProperty("spring.mail.port")));
        email.setAuthenticator(new DefaultAuthenticator(properties.getProperty("spring.mail.username"), properties.getProperty("spring.mail.password")));
        email.setSSLOnConnect(true);
        try {
            email.setFrom(properties.getProperty("spring.mail.properties.from.address"));
            email.addTo(properties.getProperty("spring.mail.properties.to.address"));

            email.setSubject("Apache Mail");
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }

}
