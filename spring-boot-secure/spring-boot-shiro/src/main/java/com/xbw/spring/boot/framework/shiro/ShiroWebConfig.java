package com.xbw.spring.boot.framework.shiro;

import com.xbw.spring.boot.framework.shiro.realm.ShiroRealm;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.spring.web.config.AbstractShiroWebConfiguration;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.List;

/**
 * http://shiro.apache.org/spring-boot.html
 * @author xbw
 * @see org.apache.shiro.spring.boot.autoconfigure.ShiroAutoConfiguration
 * @see org.apache.shiro.spring.config.web.autoconfigure.ShiroWebAutoConfiguration
 * @see org.apache.shiro.spring.config.web.autoconfigure.ShiroWebMvcAutoConfiguration
 * @see org.apache.shiro.spring.config.web.autoconfigure.ShiroWebFilterConfiguration
 * <p>
 * Order by
 * @see org.apache.shiro.web.filter.authc.FormAuthenticationFilter#onAccessDenied(ServletRequest, ServletResponse)
 * @see org.apache.shiro.web.filter.authc.FormAuthenticationFilter#executeLogin(ServletRequest, ServletResponse)
 * @see org.apache.shiro.web.filter.authc.AuthenticatingFilter#createToken(ServletRequest, ServletResponse)
 * @see org.apache.shiro.web.filter.authc.FormAuthenticationFilter#createToken(ServletRequest, ServletResponse)
 * @see org.apache.shiro.subject.support.DelegatingSubject#login(AuthenticationToken)
 * @see org.apache.shiro.mgt.DefaultSecurityManager#login(Subject, AuthenticationToken)
 * @see org.apache.shiro.authc.AbstractAuthenticator#authenticate(AuthenticationToken)
 * @see org.apache.shiro.authc.pam.ModularRealmAuthenticator#doAuthenticate(AuthenticationToken)
 * @see org.apache.shiro.realm.AuthenticatingRealm#getAuthenticationInfo(AuthenticationToken)
 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(AuthenticationToken)
 * then Custom Realm {@link ShiroRealm#doGetAuthenticationInfo(AuthenticationToken)}
 * @see org.apache.shiro.realm.AuthenticatingRealm#assertCredentialsMatch(AuthenticationToken, AuthenticationInfo)
 * @see org.apache.shiro.authc.credential.CredentialsMatcher#doCredentialsMatch(AuthenticationToken, AuthenticationInfo)
 * @see org.apache.shiro.authc.credential.HashedCredentialsMatcher#doCredentialsMatch(AuthenticationToken, AuthenticationInfo)
 * @see org.apache.shiro.mgt.DefaultSecurityManager#onSuccessfulLogin(AuthenticationToken, AuthenticationInfo, Subject)
 * @see org.apache.shiro.mgt.DefaultSecurityManager#rememberMeSuccessfulLogin(AuthenticationToken, AuthenticationInfo, Subject)
 * @see org.apache.shiro.web.mgt.CookieRememberMeManager#rememberSerializedIdentity(Subject, byte[])
 * @see org.apache.shiro.mgt.AbstractRememberMeManager#onSuccessfulLogin(Subject, AuthenticationToken, AuthenticationInfo)
 * @see org.apache.shiro.subject.support.DelegatingSubject#isPermitted(String)
 * @see org.apache.shiro.realm.AuthorizingRealm#isPermitted(PrincipalCollection, String)
 * @see org.apache.shiro.realm.AuthorizingRealm#getAuthorizationInfo(PrincipalCollection)
 * then Custom Realm {@link ShiroRealm#doGetAuthorizationInfo(PrincipalCollection)}
 */
@Configuration
public class ShiroWebConfig extends AbstractShiroWebConfiguration {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * @return
     * @see org.apache.shiro.spring.config.web.autoconfigure.ShiroWebAutoConfiguration#shiroFilterChainDefinition()
     */
    @Bean
    @Override
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        // actuator
        chainDefinition.addPathDefinition("/actuator", "anon");

        // logged in users with the 'admin' role
        chainDefinition.addPathDefinition("/admin/**", "authc, roles[admin]");

        // logged in users with the 'document:read' permission
        chainDefinition.addPathDefinition("/docs/**", "authc, perms[document:read]");

        // logout
        chainDefinition.addPathDefinition("/logout", "logout");

        // all other paths require a logged in user
        // when use rememberMe,authc need change to user
        //chainDefinition.addPathDefinition("/**", "authc");
        chainDefinition.addPathDefinition("/**", "user");

        return chainDefinition;
    }

    @Bean
    @Override
    protected SessionsSecurityManager securityManager(List<Realm> realms) {
        return super.securityManager(realms);
    }

    @Bean
    public ShiroRealm shiroRealm() {
        ShiroRealm realm = new ShiroRealm();
        realm.setCredentialsMatcher(credentialsMatcher());
        logger.debug("ShiroRealm CredentialsMatcher: {}", realm.getCredentialsMatcher());
        logger.debug("ShiroRealm: {} ", realm);
        return realm;
    }

    /**
     * @return
     * @see org.apache.shiro.authc.credential.SimpleCredentialsMatcher
     */
    @Bean
    public SimpleAccountRealm simpleAccountRealm() {
        SimpleAccountRealm realm = new SimpleAccountRealm();
        realm.addAccount("xbw", "xbw", "admin");
        realm.addAccount("user", "user", "user");
        logger.debug("SimpleAccountRealm CredentialsMatcher: {}", realm.getCredentialsMatcher());
        logger.debug("SimpleAccountRealm: {} ", realm);
        return realm;
    }

    @Bean
    public CredentialsMatcher credentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(2);
        return matcher;
    }

    /**
     * Change to AllSuccessfulStrategy
     * @return
     */
    @Override
    protected Authenticator authenticator() {
        ModularRealmAuthenticator authenticator = (ModularRealmAuthenticator) super.authenticator();
        //authenticator.setAuthenticationStrategy(new AllSuccessfulStrategy());
        return authenticator;
    }

}
