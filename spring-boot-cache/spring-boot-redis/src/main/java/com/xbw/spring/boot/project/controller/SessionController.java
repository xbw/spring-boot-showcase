package com.xbw.spring.boot.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.session.MapSession;
import org.springframework.session.data.redis.RedisIndexedSessionRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequestMapping("/session")
@RestController
public class SessionController {
    public static final String SESSION_CACHE_NAME = RedisIndexedSessionRepository.DEFAULT_NAMESPACE;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CacheManager cacheManager;

    @GetMapping({"", "/"})
    public String init(HttpSession session) {
        session.setAttribute(SESSION_CACHE_NAME, SESSION_CACHE_NAME);
        return format(session.getAttribute(SESSION_CACHE_NAME));
    }

    @RequestMapping("/set")
    String setSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(SESSION_CACHE_NAME, SESSION_CACHE_NAME + ":set");
        return format(session.getAttribute(SESSION_CACHE_NAME));
    }

    @RequestMapping("/get")
    String getSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return format(session.getAttribute(SESSION_CACHE_NAME));
    }

    @RequestMapping("/get/{sessionId}")
    String getSession(@PathVariable String sessionId, HttpServletRequest request) {
        Cache.ValueWrapper valueWrapper = cacheManager.getCache(SESSION_CACHE_NAME).get(sessionId);
        MapSession mapSession = (MapSession) valueWrapper.get();
        return format(mapSession.getAttribute(SESSION_CACHE_NAME));
    }

    private String format(Object attribute) {
        logger.info("Session attribute: {} -> {}", SESSION_CACHE_NAME, attribute);
        return String.format("Session attribute: %s -> %s", SESSION_CACHE_NAME, attribute);
    }
}
