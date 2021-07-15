package com.xbw.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class InfinispanRemoteApplication {

    public static void main(String[] args) {
        SpringApplication.run(InfinispanRemoteApplication.class, args);
    }

}
