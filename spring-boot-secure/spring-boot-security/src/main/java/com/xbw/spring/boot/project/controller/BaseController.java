package com.xbw.spring.boot.project.controller;

import com.xbw.spring.boot.project.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import java.util.List;

/**
 * @author xbw
 */
@SuppressWarnings("unused")
public class BaseController {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    BaseService baseService;

    public Authentication getAuthentication() {
        return baseService.getAuthentication();
    }

    public List<String> getGrantedAuthority() {
        return baseService.getGrantedAuthority();
    }

    public Object getPrincipal() {
        return baseService.getPrincipal();
    }
}
