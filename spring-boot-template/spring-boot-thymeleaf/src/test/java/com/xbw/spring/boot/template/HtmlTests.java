package com.xbw.spring.boot.template;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

/**
 * @author xbw
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"html"})
public class HtmlTests {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void index() {
        ResponseEntity<String> entity = this.restTemplate.getForEntity("/index", String.class);
        Assertions.assertEquals(entity.getStatusCode(), HttpStatus.OK);
        System.out.println(entity.getBody());
    }
}