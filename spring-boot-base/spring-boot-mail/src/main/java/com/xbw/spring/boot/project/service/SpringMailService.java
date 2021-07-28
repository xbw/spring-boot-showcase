package com.xbw.spring.boot.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Map;

@Service
public class SpringMailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private MailProperties mailProperties;

    public void sendText(String to, String subject, String text) {
        Map<String, String> properties = mailProperties.getProperties();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(properties.get("from.address"));
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }

    public void sendHtml(String to, String subject, String text) {
        Map<String, String> properties = mailProperties.getProperties();
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(properties.get("from.address"));
            helper.setTo(to);

            helper.setSubject(subject);
            helper.setText(text, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        javaMailSender.send(message);
    }

    public void sendAttachments(String to, String subject, String text, File[] files) {
        Map<String, String> properties = mailProperties.getProperties();
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(properties.get("from.address"));
            helper.setTo(to);

            helper.setSubject(subject);
            helper.setText(text, true);

            // let's attach the infamous windows Sample file (this time copied to c:/)
            for (File file : files) {
                FileSystemResource fileSystemResource = new FileSystemResource(file);
                helper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        javaMailSender.send(message);
    }
}
