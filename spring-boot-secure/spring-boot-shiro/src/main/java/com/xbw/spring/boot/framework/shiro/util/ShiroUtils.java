package com.xbw.spring.boot.framework.shiro.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;

/**
 * @author xbw
 */
@SuppressWarnings("unused")
public class ShiroUtils {
    private static final Logger logger = LoggerFactory.getLogger(ShiroUtils.class);

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static Object getPrincipal() {
        Object principal = null;
        try {
            Subject subject = SecurityUtils.getSubject();
            principal = subject.getPrincipal();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        logger.debug("Principal: {}", principal);
        return principal;
    }

}
