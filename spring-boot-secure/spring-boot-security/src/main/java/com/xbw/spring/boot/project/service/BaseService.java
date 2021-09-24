package com.xbw.spring.boot.project.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author xbw
 */
@Service
public class BaseService {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public List<String> getGrantedAuthority() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> list = new ArrayList<>();
        for (GrantedAuthority grantedAuthority : authorities) {
            list.add(grantedAuthority.getAuthority());
        }
        logger.debug("GrantedAuthority List: {}", list);
        return list;
    }

    public Object getPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        logger.debug("Principal: {}", authentication);
        return authentication.getPrincipal();
    }
}
