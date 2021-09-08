package com.xbw.spring.boot.framework.security.adapter;

import com.xbw.spring.boot.framework.security.filter.CustomAuthenticationFilter;
import com.xbw.spring.boot.framework.security.provider.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
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

    private static final String[] AUTH_WHITELIST = {
            "/public/**",
            "/resources/**",
            "/static/**",
            "/webjars/**",
            "/login"
    };

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * @param http
     * @throws Exception
     * @see org.springframework.security.config.annotation.web.configurers.CorsConfigurer
     * @see org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
     * @see org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer
     * @see org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer
     * @see org.springframework.security.config.annotation.web.configurers.LogoutConfigurer
     * @see org.springframework.security.config.annotation.web.configurers.RememberMeConfigurer
     * @see org.springframework.security.config.annotation.web.configurers.RememberMeConfigurer
     * @see org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors();

        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated();

        http.formLogin()
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .failureForwardUrl("/login?error")
                .loginPage("/login")
//                .failureUrl("/login-error.html")
                .permitAll();

        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .permitAll();

//        http.rememberMe();

//        http.sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilter(new CustomAuthenticationFilter(authenticationManager()));
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(new CustomAuthenticationProvider(userDetailsService, new BCryptPasswordEncoder()));
    }

}
