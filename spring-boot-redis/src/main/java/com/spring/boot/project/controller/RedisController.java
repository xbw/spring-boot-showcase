package com.spring.boot.project.controller;

import com.spring.boot.project.model.Person;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

    @RequestMapping("/cache")
    @Cacheable(value = "spring:cache") //SimpleKey
    public Person getCache() {
        return new Person(1l, "name", "n", "1970-01-01");
    }

    @RequestMapping("/cache/{id}")
    @Cacheable(value = "spring:cache"/*, key = "#id"*/) //default key = "#id",Override by keyGenerator()
    public Person getCache(@PathVariable Long id) {
        return new Person(id, "name" + id, "n" + id, "1970-01-01");
    }

}
