package com.xbw.spring.boot.project.mail;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.config.ConfigLoader;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

public class SimpleMail {
    public void sendMail() {
        ConfigLoader.loadProperties("simplejavamail.properties", false);
        Mailer mailer = MailerBuilder.buildMailer();
        Email email = EmailBuilder.startingBlank()
                .withSubject("Simple Java Mail")
                .withPlainText("This is a test mail send by Simple Java Mail！")
                .buildEmail();
        mailer.sendMail(email);
    }
}
