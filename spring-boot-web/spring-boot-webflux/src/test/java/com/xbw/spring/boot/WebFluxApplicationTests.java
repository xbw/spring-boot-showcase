package com.xbw.spring.boot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest
class WebFluxApplicationTests {
    @Autowired
    WebTestClient client;

    @Test
    void contextLoads() {
    }

    @Test
    public void hello() {
        client.get().uri("/hello").exchange().expectStatus().isOk();
    }
}
