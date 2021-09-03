package com.xbw.spring.boot.framework.security.encoder;

import com.xbw.spring.boot.framework.security.SecurityDefaultConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.util.StringUtils;

/**
 * @author xbw
 */
@ConditionalOnMissingBean(SecurityDefaultConfig.class)
@Configuration
public class CustomMemoryConfig {

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth, SecurityProperties properties) throws Exception {
        SecurityProperties.User user = properties.getUser();
        auth.inMemoryAuthentication()
                .withUser(user.getName()).password(user.getPassword()).roles(StringUtils.toStringArray(user.getRoles()))
                .and()
                .withUser("root").password("root").roles("ROOT")
                .and()
                .withUser("admin").password("admin").roles("ADMIN")
                .and()
                .withUser("user").password("user").roles("USER")
                .and()
                .passwordEncoder(new CustomPasswordEncoder());
    }

    private static class CustomPasswordEncoder implements org.springframework.security.crypto.password.PasswordEncoder {
        @Override
        public String encode(CharSequence rawPassword) {
            return String.valueOf(rawPassword);
        }

        @Override
        public boolean matches(CharSequence rawPassword, String encodedPassword) {
            boolean flag = encodedPassword.equals(String.valueOf(rawPassword));
            System.out.println("encodedPassword: " + encodedPassword + ", rawPassword: " + rawPassword + ", " + flag);
            return flag;
        }
    }
}
