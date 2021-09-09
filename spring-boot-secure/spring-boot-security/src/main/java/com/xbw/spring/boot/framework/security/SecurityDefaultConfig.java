package com.xbw.spring.boot.framework.security;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * https://docs.spring.io/spring-security/site/docs/current/reference/html5/
 * @author xbw
 * Default
 * @see org.springframework.boot.autoconfigure.security.servlet.SpringBootWebSecurityConfiguration
 * @see org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration#inMemoryUserDetailsManager(SecurityProperties, ObjectProvider)
 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration#springSecurityFilterChain()
 * @see org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter#generateLoginPageHtml(HttpServletRequest, boolean, boolean)
 * @see org.springframework.security.web.authentication.ui.DefaultLogoutPageGeneratingFilter#renderLogout(HttpServletRequest, HttpServletResponse)
 * @see org.springframework.security.web.DefaultSecurityFilterChain
 */
@Configuration
public class SecurityDefaultConfig {

}
