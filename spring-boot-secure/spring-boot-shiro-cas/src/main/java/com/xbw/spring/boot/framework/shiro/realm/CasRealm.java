package com.xbw.spring.boot.framework.shiro.realm;

import com.xbw.spring.boot.framework.shiro.ShiroConstant;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasAuthenticationException;
import org.apache.shiro.cas.CasToken;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.TicketValidationException;
import org.jasig.cas.client.validation.TicketValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("deprecation")
public class CasRealm extends org.apache.shiro.cas.CasRealm {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * @param token
     * @return
     * @throws AuthenticationException
     * @see org.apache.shiro.realm.AuthenticatingRealm#getAuthenticationInfo(AuthenticationToken)
     * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(AuthenticationToken)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // return super.doGetAuthenticationInfo(token);
        CasToken casToken = (CasToken) token;
        if (token == null) {
            return null;
        }

        String ticket = (String) casToken.getCredentials();
        if (!org.apache.shiro.util.StringUtils.hasText(ticket)) {
            return null;
        }
        logger.info("ticket -> {}", ticket);
        TicketValidator ticketValidator = ensureTicketValidator();
        try {
            Assertion casAssertion = ticketValidator.validate(ticket, getCasService());
            AttributePrincipal casPrincipal = casAssertion.getPrincipal();

            String username = casPrincipal.getName();
            Object user;
            try {
                // TODO query User info
                user = username;
                PrincipalCollection principalCollection = new SimplePrincipalCollection(user, getName());
                SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principalCollection, ticket);
                logger.info("doGetAuthenticationInfo SimpleAuthenticationInfo: {}", info);
                return info;
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
        } catch (TicketValidationException e) {
            throw new CasAuthenticationException("Unable to validate ticket [" + ticket + "]", e);
        }
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
        Object principal = getAvailablePrincipal(principals);
        // TODO
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