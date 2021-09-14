package com.xbw.spring.boot.framework.shiro.filter;

import com.xbw.spring.boot.framework.shiro.realm.ShiroRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * There are 3 ways to logout
 * 1. use chainDefinition.addPathDefinition("/logout", "logout");
 * 2. use Controller /logout
 * 3. use LogoutFilter
 * @author: xbw
 */
public class LogoutFilter extends org.apache.shiro.web.filter.authc.LogoutFilter {

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {

        Subject subject = getSubject(request, response);
        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
        ShiroRealm shiroRealm = (ShiroRealm) securityManager.getRealms().iterator().next();
        PrincipalCollection principals = subject.getPrincipals();
        shiroRealm.onLogout(principals);

        subject.logout();

        String redirectUrl = getRedirectUrl(request, response, subject);
        issueRedirect(request, response, redirectUrl);
        return false;
    }
}