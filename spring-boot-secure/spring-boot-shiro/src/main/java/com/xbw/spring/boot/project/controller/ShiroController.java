package com.xbw.spring.boot.project.controller;

import com.xbw.spring.boot.framework.shiro.util.ShiroUtils;
import com.xbw.spring.boot.project.service.ShiroService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.Filter;

/**
 * @author xbw
 */
@RestController
public class ShiroController extends ShiroUtils {

    @Autowired
    ShiroService shiroService;

    /**
     * @return
     * @see org.apache.shiro.spring.web.ShiroFilterFactoryBean#applyUnauthorizedUrlIfNecessary(Filter)
     */
    @RequiresRoles("admin")
    @RequestMapping("roles")
    public String roles() {
        return shiroService.roles();
    }

    @RequiresPermissions("user:*:*")
    @RequestMapping("permissions")
    public String permissions() {
        return shiroService.permissions();
    }

    @RequiresUser
    @RequestMapping("user")
    public String user() {
        return shiroService.user();
    }
}
