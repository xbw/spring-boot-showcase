package com.xbw.spring.boot.framework.security.access;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;

/**
 * @author xbw
 * @see org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler
 * @see org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler
 */
public class CustomPermissionEvaluator implements PermissionEvaluator {

    public CustomPermissionEvaluator() {
    }

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        } else {
            return targetDomainObject.equals(permission);
//            return authentication.getAuthorities().contains(targetDomainObject);
        }
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        throw new RuntimeException("Id-based permission evaluation not currently supported.");
    }

}