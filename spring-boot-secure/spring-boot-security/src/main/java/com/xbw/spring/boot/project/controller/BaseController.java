package com.xbw.spring.boot.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author xbw
 */
@SuppressWarnings("unused")
public class BaseController {
    protected Logger logger = LoggerFactory.getLogger(BaseController.class);

    public List<String> getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> list = new ArrayList<>();
        for (GrantedAuthority grantedAuthority : authorities) {
            list.add(grantedAuthority.getAuthority());
        }
        logger.debug("GrantedAuthority List: {}", list);
        return list;
    }

    public String getPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        logger.debug("Principal: {}", authentication);
        return authentication.getPrincipal().toString();
    }
}
