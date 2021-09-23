package com.xbw.spring.boot.framework.exception;

import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ShiroExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(UnauthorizedException.class)
    public String handleUnauthorizedException(Exception e) {
        logger.error("UnauthorizedException:", e);
        return "UnauthorizedException";
    }

    @ExceptionHandler(AuthorizationException.class)
    public String handleAuthorizationException(Exception e) {
        logger.error("AuthorizationException:", e);
        return "AuthorizationException";
    }

    @ExceptionHandler(ShiroException.class)
    public String handleShiroException(Exception e) {
        logger.error("ShiroException:", e);
        return "ShiroException";
    }

}