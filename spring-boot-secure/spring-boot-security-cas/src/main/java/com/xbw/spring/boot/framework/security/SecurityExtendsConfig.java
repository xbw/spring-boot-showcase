package com.xbw.spring.boot.framework.security;

import com.xbw.spring.boot.framework.security.cas.CasProperties;
import com.xbw.spring.boot.framework.security.filter.CasAuthenticationFilter;
import com.xbw.spring.boot.framework.security.handler.CustomAccessDeniedHandler;
import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * https://blog.csdn.net/qq_22172133/article/details/86503223
 * a) @Configuration + {@link org.springframework.security.config.annotation.web.WebSecurityConfigurer}
 * b) @EnableWebSecurity + extends {@link WebSecurityConfigurerAdapter)
 * @author xbw
 * @see org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration
 * Order by 
 * 1) {@link CasAuthenticationProvider#authenticate}}
 */
@EnableConfigurationProperties(CasProperties.class)
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
    @Autowired
    private CasProperties casProperties;

    /**
     * @param http
     * @throws Exception
     * @see org.springframework.security.config.annotation.web.configurers.CorsConfigurer
     * @see org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
     * @see org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer
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

//        http.exceptionHandling().accessDeniedPage("/403");
        http.exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler());

        http.authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated();

        http.formLogin()
                .loginPage(casProperties.getLoginUrl())
                .permitAll();

        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl(casProperties.getServerLogoutUrl())
                .permitAll();

        http.addFilterAt(casAuthenticationFilter(), CasAuthenticationFilter.class);

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(casAuthenticationProvider());
    }

    @Bean
    public CasAuthenticationProvider casAuthenticationProvider() {
        CasAuthenticationProvider provider = new CasAuthenticationProvider();
        provider.setAuthenticationUserDetailsService((AuthenticationUserDetailsService<CasAssertionAuthenticationToken>) userDetailsService);
        provider.setServiceProperties(serviceProperties());
        provider.setTicketValidator(new Cas20ServiceTicketValidator(casProperties.getServerUrlPrefix()));
        provider.setKey("casAuthenticationProviderKey");
        return provider;
    }

    @Bean
    public CasAuthenticationFilter casAuthenticationFilter() throws Exception {
        CasAuthenticationFilter filter = new CasAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManager());
        filter.setServiceProperties(serviceProperties());
        filter.setFilterProcessesUrl(casProperties.getClientLoginUrl());
        return filter;
    }

    @Bean
    public ServiceProperties serviceProperties() {
        ServiceProperties properties = new ServiceProperties();
        properties.setService(casProperties.getClientLoginUrl());
        properties.setAuthenticateAllArtifacts(true);
        return properties;
    }
}
