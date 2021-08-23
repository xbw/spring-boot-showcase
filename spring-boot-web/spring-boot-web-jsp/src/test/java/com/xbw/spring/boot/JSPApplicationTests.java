package com.xbw.spring.boot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.net.URI;
import java.util.Arrays;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JSPApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    void jspWithEl() {
        ResponseEntity<String> entity = this.restTemplate.getForEntity("/index", String.class);
        Assertions.assertEquals(entity.getStatusCode(), HttpStatus.OK);
        Assertions.assertTrue(entity.getBody().contains("/resources/text.txt"));
    }

    @Test
    void customErrorPage() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.TEXT_HTML));
        RequestEntity<Void> request = new RequestEntity<>(headers, HttpMethod.GET, URI.create("/foo"));
        ResponseEntity<String> entity = this.restTemplate.exchange(request, String.class);
        Assertions.assertEquals(entity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        Assertions.assertTrue(entity.getBody().contains("Something went wrong: 500 Internal Server Error"));
    }

}
