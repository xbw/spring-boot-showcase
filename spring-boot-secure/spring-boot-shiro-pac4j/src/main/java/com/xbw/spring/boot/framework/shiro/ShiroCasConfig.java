package com.xbw.spring.boot.framework.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.spring.web.config.AbstractShiroWebConfiguration;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.subject.Subject;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.util.AssertionThreadLocalFilter;
import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * http://shiro.apache.org/spring-boot.html
 * @author xbw
 * @see org.apache.shiro.web.filter.authc.FormAuthenticationFilter#onAccessDenied(ServletRequest, ServletResponse)
 * @see org.apache.shiro.web.filter.authc.FormAuthenticationFilter#executeLogin(ServletRequest, ServletResponse)
 * @see org.apache.shiro.subject.support.DelegatingSubject#login(AuthenticationToken)
 * @see org.apache.shiro.mgt.AbstractRememberMeManager#onSuccessfulLogin(Subject, AuthenticationToken, AuthenticationInfo)
 * @see org.apache.shiro.web.mgt.CookieRememberMeManager#rememberSerializedIdentity(Subject, byte[])
 * @see org.apache.shiro.spring.boot.autoconfigure.ShiroAutoConfiguration
 * @see org.apache.shiro.spring.config.web.autoconfigure.ShiroWebAutoConfiguration
 * @see org.apache.shiro.spring.config.web.autoconfigure.ShiroWebMvcAutoConfiguration
 * @see org.apache.shiro.spring.config.web.autoconfigure.ShiroWebFilterConfiguration
 */
@Configuration
public class ShiroCasConfig extends AbstractShiroWebConfiguration {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * @return
     * @see org.apache.shiro.spring.config.web.autoconfigure.ShiroWebAutoConfiguration#shiroFilterChainDefinition()
     */
    @Bean
    @Override
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();

        // actuator
        chainDefinition.addPathDefinition("/actuator", "anon");

        // logged in users with the 'admin' role
        chainDefinition.addPathDefinition("/admin/**", "authc, roles[admin]");

        // logged in users with the 'document:read' permission
        chainDefinition.addPathDefinition("/docs/**", "authc, perms[document:read]");

        // cas
        chainDefinition.addPathDefinition("/cas", "cas");
        chainDefinition.addPathDefinition("/callback", "callback");
        chainDefinition.addPathDefinition("/logout", "logout");

        chainDefinition.addPathDefinition("/**", "authc");

        return chainDefinition;
    }

    @Bean
    public ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> singleSignOutHttpSessionListener() {
        ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> listenerRegistrationBean = new ServletListenerRegistrationBean<>();
        listenerRegistrationBean.setListener(new SingleSignOutHttpSessionListener());
        listenerRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return listenerRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<SingleSignOutFilter> singleSignOutFilter() {
        FilterRegistrationBean<SingleSignOutFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new SingleSignOutFilter());
        filterRegistrationBean.setName("CAS Single Sign Out Filter");
        filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

    /**
     * {@link javax.servlet.http.HttpServletRequest#getUserPrincipal()}
     * {@link javax.servlet.http.HttpServletRequest#getRemoteUser()}
     * {@link javax.servlet.http.HttpServletRequest#isUserInRole(String)}
     * @return
     */
    @Bean
    public FilterRegistrationBean<HttpServletRequestWrapperFilter> httpServletRequestWrapperFilter() {
        FilterRegistrationBean<HttpServletRequestWrapperFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new HttpServletRequestWrapperFilter());
        filterRegistrationBean.setName("CAS HttpServletRequest Wrapper Filter");
        filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE + 2);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<AssertionThreadLocalFilter> assertionThreadLocalFilter() {
        FilterRegistrationBean<AssertionThreadLocalFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new AssertionThreadLocalFilter());
        filterRegistrationBean.setName("CAS Assertion Thread Local Filter");
        filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE + 3);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
}
