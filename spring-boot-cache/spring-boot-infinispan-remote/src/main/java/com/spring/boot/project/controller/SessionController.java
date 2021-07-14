package com.spring.boot.project.controller;

import org.infinispan.spring.remote.provider.SpringRemoteCacheManager;
import org.infinispan.spring.remote.session.configuration.EnableInfinispanRemoteHttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.session.MapSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Set;

@RequestMapping("/session")
@RestController
public class SessionController {
    public static final String SESSION_CACHE_NAME = EnableInfinispanRemoteHttpSession.DEFAULT_CACHE_NAME;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SpringRemoteCacheManager cacheManager;

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
        Set<?> keySet = cacheManager.getCache(SESSION_CACHE_NAME).getNativeCache().keySet();
        logger.info("Session keySet() -> {}", keySet);
        return String.format("Session keySet() -> %s", keySet);
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
