package com.xbw.spring.boot.project.service;

import com.xbw.spring.boot.framework.security.SecurityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xbw
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final Map<String, UserDetails> users = new ConcurrentHashMap<>();
    private boolean isTest = true;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SecurityProperties properties;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUser(username);
    }

    private UserDetails getUser(String username) {
        List<GrantedAuthority> authorities;
        if (isTest) {
            authorities = getTestAuthority(username);
        } else {
            authorities = Collections.emptyList();
        }

        UserDetails user = this.users.get(username.toLowerCase());
        if (user == null) {
            user = new User(username, passwordEncoder.encode(username), authorities);
            users.put(user.getUsername().toLowerCase(), user);
        }

        /**
         * For {@link org.springframework.security.authentication.ProviderManager#authenticate(Authentication)}
         *
         * {@code ((CredentialsContainer) result).eraseCredentials();}
         */
        return new User(user.getUsername(), user.getPassword(), user.getAuthorities());
    }

    /**
     * For test
     * @param username
     * @return
     */
    private List<GrantedAuthority> getTestAuthority(String username) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (username.equals(properties.getUser().getName())) {
            authorities.addAll(getAuthority(StringUtils.toStringArray(properties.getUser().getRoles())));
        } else if (SecurityConstant.CONSTANT_USER_ADMIN.equals(username)) {
            authorities.add(getAuthority(SecurityConstant.CONSTANT_ROLE_ADMIN));
        } else {
            authorities.add(getAuthority(SecurityConstant.CONSTANT_ROLE_USER));
        }
        return authorities;
    }

    private GrantedAuthority getAuthority(String authorities) {
        return new SimpleGrantedAuthority(authorities);
    }

    private List<GrantedAuthority> getAuthority(String... authorities) {
        return AuthorityUtils.createAuthorityList(authorities);
    }

    public void setTest(boolean test) {
        isTest = test;
    }
}