package com.xbw.spring.boot.framework.security.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xbw
 */
public class CasAuthenticationFilter extends org.springframework.security.cas.web.CasAuthenticationFilter {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException {
        logger.debug("request.getMethod() -> {}", request.getMethod());
        return super.attemptAuthentication(request, response);
    }
}
