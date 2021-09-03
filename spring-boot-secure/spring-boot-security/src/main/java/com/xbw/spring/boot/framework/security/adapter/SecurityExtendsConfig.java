package com.xbw.spring.boot.framework.security.adapter;

import com.xbw.spring.boot.framework.security.provider.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * https://blog.csdn.net/qq_22172133/article/details/86503223
 * a) @Configuration + {@link org.springframework.security.config.annotation.web.WebSecurityConfigurer}
 * b) @EnableWebSecurity + extends {@link WebSecurityConfigurerAdapter)
 * @author xbw
 * @see org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration
 */
@EnableWebSecurity
public class SecurityExtendsConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(new CustomAuthenticationProvider(userDetailsService, new BCryptPasswordEncoder()));
    }

}
