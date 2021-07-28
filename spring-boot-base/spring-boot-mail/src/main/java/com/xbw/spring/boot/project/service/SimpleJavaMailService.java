package com.xbw.spring.boot.project.service;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.email.EmailBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SimpleJavaMailService {

    @Autowired // or roll your own, as long as SimpleJavaMailSpringSupport is processed first
    private Mailer mailer;

    public void sendMail(String subject,String text) {
        Email email = EmailBuilder.startingBlank()
                .withSubject(subject)
                .withPlainText(text)
                .buildEmail();
        mailer.sendMail(email);
    }

}