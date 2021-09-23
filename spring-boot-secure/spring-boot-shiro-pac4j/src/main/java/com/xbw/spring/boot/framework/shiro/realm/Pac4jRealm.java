package com.xbw.spring.boot.framework.shiro.realm;

import com.xbw.spring.boot.framework.shiro.ShiroConstant;
import io.buji.pac4j.subject.Pac4jPrincipal;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @author xbw
 */
public class Pac4jRealm extends io.buji.pac4j.realm.Pac4jRealm {

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        AuthenticationInfo info = super.doGetAuthenticationInfo(authenticationToken);
        return info;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Pac4jPrincipal principal = (Pac4jPrincipal) getAvailablePrincipal(principals);
        // TODO
        System.out.println(principal.getName());
        System.out.println(principal.getProfiles());
        if (ShiroConstant.CONSTANT_USER_XBW.equals(principal.getName())
                || ShiroConstant.CONSTANT_USER_ADMIN.equals(principal)) {
            info.addRole(ShiroConstant.CONSTANT_ROLE_ADMIN);
            info.addStringPermission(ShiroConstant.CONSTANT_PERMISSION_ADMIN);
        } else {
            info.addRole(ShiroConstant.CONSTANT_ROLE_USER);
            info.addStringPermission(ShiroConstant.CONSTANT_PERMISSION_USER);
//            info.setRoles();
//            info.setStringPermissions();
        }
        return info;
//        return super.doGetAuthorizationInfo(principals);
    }
}
