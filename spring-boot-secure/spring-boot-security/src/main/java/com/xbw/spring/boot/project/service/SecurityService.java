package com.xbw.spring.boot.project.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * @author xbw
 */
@Service
public class SecurityService extends BaseService {

    @Override
    @PreAuthorize("authenticated")
    public Authentication getAuthentication() {
        return super.getAuthentication();
    }

    /**
     * The most obviously useful annotation is @PreAuthorize which decides whether a method can actually be invoked or not.
     * @return
     */
    @PreAuthorize("hasRole('ADMIN')")
    public String preAuthorize() {
        return "@PreAuthorize";
    }

    @PreAuthorize("hasPermission(authentication.principal,'xbw')")
    public String hasPermission() {
        return "hasPermission()";
    }

    @PostAuthorize("hasAnyRole('ADMIN','USER')")
    public String postAuthorize() {
        return "@PostAuthorize";
    }

    @PostAuthorize("returnObject.contains(authentication.principal)")
    public List<String> postAuthorizeList(List<String> list) {
        return list;
    }

    /**
     * Pre-filtering on array types is not supported. Using a Collection will solve this problem
     * https://www.baeldung.com/spring-security-prefilter-postfilter
     * https://stackoverflow.com/questions/33526215/nullpointerexception-in-spring-security-prefilter-with-two-argument-method
     * @return
     */
    @PreFilter("hasAnyAuthority('ROLE_ADMIN','ROLE_USER') and filterObject == authentication.principal")
    public List<String> preFilter(List<String> list) {
        return list;
    }

    /**
     * Filter target must be a collection or array type, but was Optional
     * <p>
     * When use 'authentication.principal.username'
     * EL1008E: Property or field 'username' cannot be found on object of type 'java.lang.String' - maybe not public or not valid?
     * Because in {@link com.xbw.spring.boot.framework.security.provider.CustomAuthenticationProvider#authenticate(Authentication)} principal use 'java.lang.String'
     * @return
     */
    @PostFilter("hasAuthority('ROLE_ADMIN') or filterObject == authentication.principal")
    public List<String> postFilter(List<String> list) {
        return list;
    }

    /**
     * Java 5 annotation for describing service layer security attributes.
     * @return
     */
    @Secured({"ROLE_ADMIN"})
    public String secured() {
        return "@Secured";
    }

    @DenyAll
    public String denyAll() {
        return "@DenyAll";
    }

    @PermitAll
    public String permitAll() {
        return "@PermitAll";
    }

    /**
     * JSR-250
     * @return
     */
    @RolesAllowed({})
    public String rolesAllowed() {
        return "@RolesAllowed";
    }

}
