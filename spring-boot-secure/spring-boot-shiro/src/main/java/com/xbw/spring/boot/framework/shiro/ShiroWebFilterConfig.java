package com.xbw.spring.boot.framework.shiro;

import com.xbw.spring.boot.framework.shiro.filter.FormAuthenticationFilter;
import com.xbw.spring.boot.framework.shiro.filter.LogoutFilter;
import org.apache.shiro.spring.web.config.AbstractShiroWebFilterConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xbw
 * @see org.apache.shiro.web.filter.mgt.DefaultFilter#authc
 * @see org.apache.shiro.spring.config.web.autoconfigure.ShiroWebFilterConfiguration#filterShiroFilterRegistrationBean()
 */
@Configuration
public class ShiroWebFilterConfig extends AbstractShiroWebFilterConfiguration {

    @Bean("authc")
    public FormAuthenticationFilter formAuthenticationFilter() {
        return new FormAuthenticationFilter();
    }

//    @Bean("logout")
//    public LogoutFilter logoutFilter() {
//        LogoutFilter filter = new LogoutFilter();
//        //filter.setRedirectUrl("/login");
//        return filter;
//    }
}
