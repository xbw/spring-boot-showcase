package com.xbw.spring.boot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebApplicationTests {
    @LocalServerPort
    int port;

    @Test
    void contextLoads() {
        System.out.printf("LocalServerPort -> %s", port);
    }

}
