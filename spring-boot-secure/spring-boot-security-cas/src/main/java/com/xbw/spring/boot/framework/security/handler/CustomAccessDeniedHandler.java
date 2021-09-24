package com.xbw.spring.boot.framework.security.handler;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("application/json;charset=utf-8");
        String json = "{\"status\":403,\"message\":\"403 Forbidden\"}";
        response.setContentLength(json.getBytes(StandardCharsets.UTF_8).length);
        response.getWriter().write(json);
    }
}