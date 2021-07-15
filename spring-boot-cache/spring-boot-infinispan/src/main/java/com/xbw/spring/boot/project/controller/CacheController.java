package com.xbw.spring.boot.project.controller;

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
}
