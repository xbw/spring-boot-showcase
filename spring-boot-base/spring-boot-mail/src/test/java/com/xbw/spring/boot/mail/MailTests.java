package com.xbw.spring.boot.mail;

import com.xbw.spring.boot.project.service.SimpleJavaMailService;
import com.xbw.spring.boot.project.service.SpringMailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@SpringBootTest
class MailTests {
    @Autowired
    SimpleJavaMailService simpleJavaMailService;
    @Autowired
    SpringMailService springMailService;

    @Autowired
    private MailProperties mailProperties;

    @Test
    void simpleJavaMail() {
        simpleJavaMailService.sendMail("Simple Java Mail", "This is a test mail send by Simple Java Mail Spring support!");
    }

    @Test
    void springMailText() {
        String to = mailProperties.getProperties().get("to.address");
        String subject = "Spring Mail";
        String text = "This is a test mail send by Spring Mail!";
        springMailService.sendText(to, subject, text);
    }

    @Test
    void springMailHtml() {
        String to = mailProperties.getProperties().get("to.address");
        String subject = "Spring Mail";
        String text = "<h1>This is a test mail send by Spring Mail!</h1>";
        springMailService.sendHtml(to, subject, text);
    }

    @Test
    void springMailAttachments() throws Exception {
        String to = mailProperties.getProperties().get("to.address");
        String subject = "Spring Mail";
        String text = "<h1>This is a test mail send by Spring Mail!</h1>";

        URL url = new URL("https://spring.io/images/spring-logo-9146a4d3298760c2e7e49595184e1975.svg");
        String temp = System.getProperty("java.io.tmpdir") + "spring-logo.svg";
        File file = new File(temp);
        if (!file.exists()) {
            writeToLocal(temp, url.openStream());
        }
        Thread.sleep(2000);
        springMailService.sendAttachments(to, subject, text, new File[]{file});
    }

    private void writeToLocal(String destination, InputStream input) throws IOException {
        int index;
        byte[] bytes = new byte[1024];
        FileOutputStream downloadFile = new FileOutputStream(destination);
        while ((index = input.read(bytes)) != -1) {
            downloadFile.write(bytes, 0, index);
            downloadFile.flush();
        }
        input.close();
        downloadFile.close();
    }

}
