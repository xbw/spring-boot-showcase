package com.xbw.spring.boot.framework.security.filter;

import com.xbw.spring.boot.framework.security.handler.CustomAuthenticationSuccessHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xbw
 */
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    private AuthenticationSuccessHandler successHandler = new CustomAuthenticationSuccessHandler();

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        logger.debug("AuthenticationManager -> {}", authenticationManager);
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        logger.debug("request.getMethod() -> {}", request.getMethod());
        return super.attemptAuthentication(request, response);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);

        String token = "xbw";
        response.addHeader("Authorization", "Token " + token);

        this.successHandler.onAuthenticationSuccess(request, response, authResult);
    }
}
