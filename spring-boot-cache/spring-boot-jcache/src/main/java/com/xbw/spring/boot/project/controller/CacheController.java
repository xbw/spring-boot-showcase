package com.xbw.spring.boot.project.controller;

import com.xbw.spring.boot.project.model.Person;
import com.xbw.spring.boot.project.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/cache")
@RestController
public class CacheController {
    @Autowired
    CacheService cacheService;

    @RequestMapping("/get")
    public String get() {
        return cacheService.get().toString();
    }

    @RequestMapping("/get/{id}")
    public String get(@PathVariable Long id) {
        return cacheService.get(id).toString();
    }

    @RequestMapping("/put")
    public String put() {
        Person person = new Person();
        cacheService.put(person);
        return person.toString();
    }

    @RequestMapping("/put/{id}")
    public String put(@PathVariable Long id) {
        Person person = new Person(id);
        cacheService.put(id, person);
        return person.toString();
    }

    @RequestMapping("/evict")
    public String evict() {
        return cacheService.evict();
    }

    @RequestMapping("/evict/{id}")
    public String evict(@PathVariable Long id) {
        return cacheService.evict(id);
    }
}
