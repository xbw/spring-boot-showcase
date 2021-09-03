package com.xbw.spring.boot.framework.security.encoder;

import com.xbw.spring.boot.framework.security.SecurityDefaultConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xbw
 * @see org.springframework.security.config.annotation.web.WebSecurityConfigurer
 * @see org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication
 */
@ConditionalOnMissingBean({SecurityDefaultConfig.class, BcryptMemoryConfig.class})
@EnableWebSecurity
public class Pbkdf2MemoryConfig {

    @Autowired
    public void configure(AuthenticationManagerBuilder auth, SecurityProperties properties) throws Exception {
        SecurityProperties.User user = properties.getUser();
        System.out.println("Encoded Password：" + passwordEncoder().encode(user.getPassword()));
        auth
                .inMemoryAuthentication()
                .withUser(user.getName())
                .password(passwordEncoder().encode(user.getPassword()))
                .roles(StringUtils.toStringArray(user.getRoles()));
    }

    private PasswordEncoder passwordEncoder() {
        String encodingId = "pbkdf2";
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put(encodingId, new Pbkdf2PasswordEncoder());
        return new DelegatingPasswordEncoder(encodingId, encoders);
    }
}
