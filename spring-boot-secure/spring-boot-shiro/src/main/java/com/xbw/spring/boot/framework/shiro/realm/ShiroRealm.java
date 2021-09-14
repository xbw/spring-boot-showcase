package com.xbw.spring.boot.framework.shiro.realm;

import com.xbw.spring.boot.framework.shiro.ShiroConstant;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * @author xbw
 * @see org.apache.shiro.authc.pam.ModularRealmAuthenticator#doSingleRealmAuthentication(Realm, AuthenticationToken)
 * @see org.apache.shiro.authc.pam.ModularRealmAuthenticator#doMultiRealmAuthentication(Collection, AuthenticationToken)
 */
public class ShiroRealm extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory.getLogger(ShiroRealm.class);

    /**
     * @param token
     * @return
     * @throws AuthenticationException
     * @see org.apache.shiro.realm.AuthenticatingRealm#getAuthenticationInfo(AuthenticationToken)
     * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(AuthenticationToken)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        String password = "";
        if (upToken.getPassword() != null) {
            password = new String(upToken.getPassword());
        }
        Object user = null;
        try {
            // TODO query User info
            user = username;
        } catch (UnknownAccountException e) {
            throw new UnknownAccountException(e.getMessage(), e);
        } catch (ExcessiveAttemptsException e) {
            throw new ExcessiveAttemptsException(e.getMessage(), e);
        } catch (LockedAccountException e) {
            throw new LockedAccountException(e.getMessage(), e);
        } catch (DisabledAccountException e) {
            throw new DisabledAccountException(e.getMessage(), e);
        } catch (AccountException e) {
            throw new AccountException(e.getMessage(), e);
        } catch (ExpiredCredentialsException e) {
            throw new ExpiredCredentialsException(e.getMessage(), e);
        } catch (IncorrectCredentialsException e) {
            throw new IncorrectCredentialsException(e.getMessage(), e);
        } catch (AuthenticationException e) {
            throw new AuthenticationException(e.getMessage(), e);
        } catch (Exception e) {
            throw new AuthenticationException(e.getMessage(), e);
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        logger.info("doGetAuthenticationInfo SimpleAuthenticationInfo: {}", info);
        return info;
    }

    @Override
    protected AuthorizationInfo getAuthorizationInfo(PrincipalCollection principals) {
        return super.getAuthorizationInfo(principals);
    }

    /**
     * @param principals
     * @return
     * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(PrincipalCollection)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //PrincipalCollection principals = ShiroUtils.getSubject().getPrincipals();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Object principal = principals.getPrimaryPrincipal();
        if (ShiroConstant.CONSTANT_USER_XBW.equals(principal)
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
    }


}
