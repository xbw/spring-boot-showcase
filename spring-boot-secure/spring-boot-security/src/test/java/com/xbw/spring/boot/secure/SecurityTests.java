package com.xbw.spring.boot.secure;

import com.xbw.spring.boot.project.service.SecurityService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xbw
 */
@SpringBootTest
public class SecurityTests {
    @Autowired
    SecurityService securityService;

    @Test
    void unauthenticated() {
        Throwable throwable = Assertions.assertThrows(AuthenticationCredentialsNotFoundException.class, () -> {
            securityService.preAuthorize();
        });
        Assertions.assertEquals("An Authentication object was not found in the SecurityContext", throwable.getMessage());
    }

    @Test
    @WithMockUser
    void authenticated() {
        printAuthenticated();
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void preAuthorize() {
        Assertions.assertEquals("@PreAuthorize", securityService.preAuthorize());
    }

    @Test
    @WithMockUser
    void postAuthorize() {
        Assertions.assertEquals("@PostAuthorize", securityService.postAuthorize());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void postFilter() {
        printAuthenticated();
        List list = securityService.postFilter(getList());
        list.forEach(System.out::println);
    }

    /**
     * Fail: need use 'authentication.principal.username'
     */
    @Test
    @WithMockUser
    void postFilterUser() {
        printAuthenticated();
        List list = securityService.postFilter(getList());
        list.forEach(System.out::println);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void secured() {
        Assertions.assertEquals("@Secured", securityService.secured());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void denyAll() {
        Throwable throwable = Assertions.assertThrows(AccessDeniedException.class, () -> {
            securityService.denyAll();
        });
        Assertions.assertEquals("不允许访问", throwable.getMessage());
    }

    @Test
    @WithMockUser
    void permitAll() {
        Assertions.assertEquals("@PermitAll", securityService.permitAll());
    }

    @Test
    @WithMockUser("Test")
    void rolesAllowed() {
        Assertions.assertEquals("@RolesAllowed", securityService.rolesAllowed());
    }


    private List<String> getList() {
        List<String> list = new ArrayList<>();
        list.add("@PreAuthorize");
        list.add("@PostAuthorize");
        list.add("@PreFilter");
        list.add("@PostFilter");
        list.add("xbw");
        list.add("user");
        return list;
    }

    /**
     * @see org.springframework.security.authentication.UsernamePasswordAuthenticationToken
     */
    private void printAuthenticated() {
        Authentication authentication = securityService.getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println("Authentication: " + authentication);
        System.out.println("Authenticated getPrincipal: " + userDetails);
    }
}
