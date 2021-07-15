package com.spring.boot;

import org.junit.jupiter.api.Test;

import javax.cache.Caching;

//@SpringBootTest
class JCacheApplicationTests {

    @Test
    void contextLoads() {
        Caching.getCachingProviders().forEach(provider -> {
            System.out.printf("CachingProvider -> %s%n", provider.getClass());
        });
    }

}
