package com.xbw.spring.boot.framework.security;

import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.util.AssertionThreadLocalFilter;
import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * @author xbw
 */
@Configuration
public class SecurityCasConfig {

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
