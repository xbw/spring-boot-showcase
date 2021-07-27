package com.xbw.spring.boot.webflux;

import com.xbw.spring.boot.project.model.Greeting;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author xbw
 */
//@WebFluxTest
//  We create a `@SpringBootTest`, starting an actual server on a `RANDOM_PORT`
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebFluxTests {

    // Spring Boot will create a `WebTestClient` for you,
    // already configure and ready to issue requests against "localhost:RANDOM_PORT"
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void hello() {
        webTestClient
                // Create a GET request to test an endpoint
                .get().uri("/hello")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                // and use the dedicated DSL to test assertions against the response
                .expectStatus().isOk()
                .expectBody(Greeting.class).value(greeting -> {
            assertThat(greeting.getMessage()).isEqualTo("Hello, WebFlux!");
        });
    }

    @Test
    void flux() {
        Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5, 6);
        flux
                .map(i -> {
                    System.out.println(Thread.currentThread().getName() + "-map1" + "-" + i);
                    return i * 3;
                })
                .publishOn(Schedulers.boundedElastic())
                .map(i -> {
                    System.out.println(Thread.currentThread().getName() + "-map2" + "-" + i);
                    return i / 3;
                }).subscribeOn(Schedulers.parallel())
                .subscribe(i -> System.out.println(Thread.currentThread().getName() + i));
    }
}
