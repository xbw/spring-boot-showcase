package com.xbw.spring.boot.project.controller;

import com.xbw.spring.boot.project.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;

/**
 * https://docs.spring.io/spring-security/site/migrate/current/3-to-4/html5/migrate-3-to-4-jc.html#m3to4-role-prefixing
 * https://docs.spring.io/spring-security/site/docs/current/reference/html5/#el-access
 * @author xbw
 * need {@link org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity} active
 */
@RestController
public class SecurityController extends BaseController {

    @Autowired
    SecurityService securityService;

    @RequestMapping("pre-authorize")
    public String preAuthorize() {
        return securityService.preAuthorize();
    }

    @RequestMapping("has-permission")
    public String hasPermission() {
        return securityService.hasPermission();
    }

    @RequestMapping("post-authorize")
    public String postAuthorize() {
        return securityService.postAuthorize();
    }

    @RequestMapping("post-authorize-list")
    public List<String> postAuthorizeList() {
        return securityService.postAuthorizeList(getList());
    }

    @RequestMapping("pre-filter")
    public List<String> preFilter() {
        return securityService.preFilter(getList());
    }

    @RequestMapping("post-filter")
    public List<String> postFilter() {
        return securityService.postFilter(getList());
    }

    @RequestMapping("secured")
    public String secured() {
        return securityService.secured();
    }

    @RequestMapping("deny-all")
    public String denyAll() {
        return securityService.denyAll();
    }

    @RequestMapping("permit-all")
    public String permitAll() {
        return securityService.permitAll();
    }

    @RequestMapping("roles-allowed")
    public String rolesAllowed() {
        return securityService.rolesAllowed();
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
}
