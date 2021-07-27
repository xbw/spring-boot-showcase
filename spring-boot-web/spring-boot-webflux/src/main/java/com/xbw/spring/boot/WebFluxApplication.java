package com.xbw.spring.boot;

import com.xbw.spring.boot.project.client.GreetingClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * https://spring.io/guides/gs/reactive-rest-service/
 * https://spring.io/guides/gs/spring-data-reactive-redis/
 */
@SpringBootApplication
public class WebFluxApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(WebFluxApplication.class, args);
        GreetingClient greetingClient = context.getBean(GreetingClient.class);
        // We need to block for the content here or the JVM might exit before the message is logged
        System.out.println("message = " + greetingClient.getMessage().block());
    }

}
