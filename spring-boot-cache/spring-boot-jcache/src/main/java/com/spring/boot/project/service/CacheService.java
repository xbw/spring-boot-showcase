package com.spring.boot.project.service;

import com.spring.boot.project.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.cache.annotation.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * https://docs.spring.io/spring-framework/docs/current/reference/html/integration.html#cache-jsr-107-summary
 */
@CacheDefaults(cacheName = "spring:boot")
@Service
public class CacheService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @CacheResult //SimpleKey
    public Serializable get() {
        Person person = new Person();
        person.setBirthday(now("@CacheResult"));
        return person;
    }

    @CacheResult(/*, key = "#id"*/)//default key = "#id",Override by keyGenerator()
    public Serializable get(Long id) {
        Person person = new Person(id);
        person.setBirthday(now("@CacheResult"));
        return person;
    }

    @CachePut
    public void put(@CacheValue Person person) {
        person.setBirthday(now("@CachePut"));
    }

    /**
     * @param serializable String is inactive
     */
    @CachePut
    public void put(@CacheValue Serializable serializable) {
        serializable = now("@CachePut");
    }

    @CachePut
    public void put(Long id, @CacheValue Person person) {
        person.setBirthday(now("@CachePut"));
    }

    @CacheRemoveAll
    public String evict() {
        return now("@CacheRemoveAll");
    }

    @CacheRemove
    public String evict(Long id) {
        return now("@CacheRemove");
    }

    private String now(String type) {
        String now = type + " -> " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS"));
        logger.info(now);
        return now;
    }
}
