package com.xbw.spring.boot.mail;

import com.xbw.spring.boot.project.mail.ApacheMail;
import com.xbw.spring.boot.project.mail.JavaMail;
import com.xbw.spring.boot.project.mail.SimpleMail;
import org.junit.jupiter.api.Test;

class MainTests {
    @Test
    void javaMailText() {
        new JavaMail().sendText();
    }

    @Test
    void javaMailHtml() {
        new JavaMail().sendHtml();
    }

    @Test
    void apacheMailText() {
        new ApacheMail().sendText();
    }

    @Test
    void apacheMailHtml() {
        new ApacheMail().sendHtml();
    }

    @Test
    void simpleMail() {
        new SimpleMail().sendMail();
    }

}
