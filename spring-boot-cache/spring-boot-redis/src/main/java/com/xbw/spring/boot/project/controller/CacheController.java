package com.xbw.spring.boot.project.controller;

import com.xbw.spring.boot.project.model.Person;
import com.xbw.spring.boot.project.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CacheController {
    @Autowired
    CacheService cacheService;

    @RequestMapping("/get")
    public String get() {
        return cacheService.get();
    }

    @RequestMapping("/get/{id}")
    public String get(@PathVariable Long id) {
        return cacheService.get(id);
    }

    @RequestMapping("/put")
    public String put() {
        return cacheService.put();
    }

    @RequestMapping("/put/{id}")
    public String put(@PathVariable Long id) {
        return cacheService.put(id);
    }

    @RequestMapping("/evict")
    public String evict() {
        return cacheService.evict();
    }

    @RequestMapping("/evict/{id}")
    public String evict(@PathVariable Long id) {
        return cacheService.evict(id);
    }

    @RequestMapping("/person")
    @Cacheable(value = "spring:cache") //SimpleKey
    public Person getCache() {
        return new Person(1l, "name", "n", "1970-01-01");
    }

    @RequestMapping("/person/{id}")
    @Cacheable(value = "spring:cache"/*, key = "#id"*/) //default key = "#id",Override by keyGenerator()
    public Person getCache(@PathVariable Long id) {
        return new Person(id, "name" + id, "n" + id, "1970-01-01");
    }
}
