package com.xbw.spring.boot.framework.security.provider;

import com.xbw.spring.boot.framework.security.SecurityConstant;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;

/**
 * @author xbw
 * @see org.springframework.security.authentication.AuthenticationProvider
 * @see org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider
 */
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public CustomAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * {@link org.springframework.security.authentication.AuthenticationManager#authenticate(Authentication)}
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        if (!StringUtils.hasLength(username)) {
            //Assert.notNull(username, "Username must not be null");
            throw new UsernameNotFoundException("Username must not be null");
        }
        String password = (String) authentication.getCredentials();
        if (!StringUtils.hasLength(password)) {
            //Assert.notNull(password, "Password must not be null");
            throw new BadCredentialsException("Password must not be null");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (!ObjectUtils.isEmpty(userDetails)) {
            if (passwordEncoder.matches(password, userDetails.getPassword())) {
                return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
            } else {
                throw new BadCredentialsException("Incorrect password");
            }
        } else {
            throw new UsernameNotFoundException("UserDetails is null");
        }
    }

    /**
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
