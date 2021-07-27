package com.xbw.spring.boot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class HelloController {

    @RequestMapping({"", "/"})
    public String web() {
        return "Hello Spring Boot WebFlux!";
    }

    @RequestMapping("/hello/mono")
    public Mono<String> mono() {
        return Mono.just("Hello Spring Boot WebFlux! \nby Mono");
    }

    @RequestMapping("/hello/flux")
    public Flux<String> flux() {
        return Flux.just("Hello Spring Boot WebFlux!", "\nby Flux");
    }

}