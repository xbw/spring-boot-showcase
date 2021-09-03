package com.xbw.spring.boot.framework.security.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;

/**
 * @author xbw
 * @see org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider
 */
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public CustomAuthenticationProvider(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    /**
     * {@link org.springframework.security.authentication.AuthenticationManager#authenticate(Authentication)}
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserDetails userDetails = userDetailsService.loadUserByUsername(name);
        if (null != userDetails) {
            if (bCryptPasswordEncoder.matches(password, userDetails.getPassword())) {
                ArrayList<GrantedAuthority> authorities = new ArrayList<>();
                return new UsernamePasswordAuthenticationToken(name, password, authorities);
            } else {
                throw new BadCredentialsException("Error password");
            }
        } else {
            throw new UsernameNotFoundException("User is null");
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
