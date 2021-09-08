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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

/**
 * @author xbw
 * @see org.springframework.security.authentication.AuthenticationProvider
 * @see org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider
 */
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public CustomAuthenticationProvider(UserDetailsService userDetailsService, BCryptPasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
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
            if (passwordEncoder.matches(password, userDetails.getPassword())) {
                ArrayList<GrantedAuthority> authorities = new ArrayList<>();
                if (SecurityConstant.CONSTANT_USER_ADMIN.equals(name)) {
                    authorities.add(getAuthority(SecurityConstant.CONSTANT_ROLE_ADMIN));
                } else {
                    authorities.add(getAuthority(SecurityConstant.CONSTANT_ROLE_USER));
                }
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

    private GrantedAuthority getAuthority(String role) {
        return new SimpleGrantedAuthority(role);
    }
}
