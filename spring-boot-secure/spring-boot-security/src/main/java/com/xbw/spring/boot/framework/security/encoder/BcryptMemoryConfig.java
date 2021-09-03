package com.xbw.spring.boot.framework.security.encoder;

import com.xbw.spring.boot.framework.security.SecurityDefaultConfig;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author xbw
 */
@ConditionalOnMissingBean({SecurityDefaultConfig.class, CustomMemoryConfig.class})
@Configuration
public class BcryptMemoryConfig {

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(SecurityProperties properties,
                                                                 ObjectProvider<PasswordEncoder> passwordEncoder) {
        SecurityProperties.User user = properties.getUser();
        System.out.println("Encoded Password：" + passwordEncoder.getIfAvailable().encode(user.getPassword()));
        List<String> roles = user.getRoles();
        return new InMemoryUserDetailsManager(
                User.withUsername(user.getName())
                        .password(passwordEncoder.getIfAvailable().encode(user.getPassword()))
                        .roles(StringUtils.toStringArray(roles))
                        .build());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public InMemoryUserDetailsManager inMemoryUserDetailsManager(SecurityProperties properties) {
//        SecurityProperties.User user = properties.getUser();
//        System.out.println("Encoded Password：" + passwordEncoder().encode(user.getPassword()));
//        List<String> roles = user.getRoles();
//        return new InMemoryUserDetailsManager(
//                User.withUsername(user.getName()).password(passwordEncoder().encode(user.getPassword()))
//                        .roles(StringUtils.toStringArray(roles)).build());
//    }
//
//    private PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }
}
