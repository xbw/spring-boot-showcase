package com.xbw.spring.boot.project.service;

import org.springframework.stereotype.Service;

/**
 * @author xbw
 */
@Service
public interface BaseService {

    String hello();

    void annotation();

    default void exception() {
        throw new RuntimeException();
    }
}
