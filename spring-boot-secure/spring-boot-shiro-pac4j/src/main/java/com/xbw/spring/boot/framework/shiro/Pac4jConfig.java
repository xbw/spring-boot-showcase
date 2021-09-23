package com.xbw.spring.boot.framework.shiro;

import com.xbw.spring.boot.framework.shiro.realm.Pac4jRealm;
import io.buji.pac4j.context.ShiroSessionStore;
import io.buji.pac4j.filter.CallbackFilter;
import io.buji.pac4j.filter.LogoutFilter;
import io.buji.pac4j.filter.SecurityFilter;
import io.buji.pac4j.subject.Pac4jSubjectFactory;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.config.web.autoconfigure.ShiroWebFilterConfiguration;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.pac4j.cas.client.CasClient;
import org.pac4j.cas.config.CasConfiguration;
import org.pac4j.cas.config.CasProtocol;
import org.pac4j.core.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.Map;

/**
 * @author xbw
 * Order by
 * 1) http://localhost:8080/cas
 * 2) {@link SecurityFilter#doFilter}
 * 3) {@link org.pac4j.core.engine.DefaultSecurityLogic#perform}
 * 4) {@link org.pac4j.cas.redirect.CasRedirectionActionBuilder#getRedirectionAction}
 * 5) Cas Login
 * 6) {@link CallbackFilter#doFilter}
 * 7) {@link org.pac4j.core.engine.DefaultCallbackLogic#perform}
 * 8) {@link org.pac4j.core.client.BaseClient#retrieveCredentials}
 * 9) {@link org.pac4j.cas.credentials.authenticator.CasAuthenticator#validate}
 * 10) {@link org.apache.shiro.realm.AuthenticatingRealm#getAuthenticationInfo}
 */
@Configuration
public class Pac4jConfig extends ShiroWebFilterConfiguration {
    @Autowired
    ShiroFilterChainDefinition shiroFilterChainDefinition;
    @Value("${shiro.cas.server-url-prefix}")
    private String casServerUrlPrefix;
    @Value("${shiro.cas.server-login-url}")
    private String casServerLoginUrl;
    @Value("${shiro.cas.client-url-prefix}")
    private String casClientUrlPrefix;
    @Value("${shiro.cas.client-login-url}")
    private String casClientLoginUrl;
    private String clientName = "CasClient";

    @Override
    @Bean
    protected ShiroFilterFactoryBean shiroFilterFactoryBean() {
        addFilterMap(filterMap);
        return super.shiroFilterFactoryBean();
    }

    private void addFilterMap(Map<String, Filter> filterMap) {
        Config config = config();
        SecurityFilter securityFilter = new SecurityFilter();
        securityFilter.setConfig(config);
        securityFilter.setClients(clientName);
        filterMap.put("cas", securityFilter);

        CallbackFilter callbackFilter = new CallbackFilter();
        callbackFilter.setConfig(config);
        filterMap.put("callback", callbackFilter);

        LogoutFilter logoutFilter = new LogoutFilter();
        logoutFilter.setConfig(config);
        logoutFilter.setCentralLogout(true);
        logoutFilter.setLocalLogout(true);
        /**
         * when use chainDefinition.addPathDefinition("/logout", "logout");
         * logoutFilter.setDefaultUrl(casClientLoginUrl);
         */
        logoutFilter.setDefaultUrl(casClientLoginUrl);
        filterMap.put("logout", logoutFilter);
    }

//    @Bean
//    public DefaultWebSecurityManager securityManager() {
//        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        securityManager.setRealm(pac4jRealm());
//        securityManager.setSubjectFactory(subjectFactory());
//        return securityManager;
//    }

    @Bean("securityManager")
    public DefaultSecurityManager securityManager(Pac4jSubjectFactory subjectFactory, Pac4jRealm casRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(casRealm);
        securityManager.setSubjectFactory(subjectFactory);
        return securityManager;
    }

    @Bean(name = "casRealm")
    public Pac4jRealm pac4jRealm() {
        Pac4jRealm realm = new Pac4jRealm();
        return realm;
    }

    @Bean
    public Pac4jSubjectFactory subjectFactory() {
        Pac4jSubjectFactory subjectFactory = new Pac4jSubjectFactory();
        return subjectFactory;
    }

    @Bean
    public Config config() {
        Config config = new Config(casClient());
        config.setSessionStore(shiroSessionStore());
        return config;
    }

    @Bean
    CasClient casClient() {
        CasConfiguration casConfiguration = new CasConfiguration(casServerLoginUrl, casServerUrlPrefix);
        casConfiguration.setProtocol(CasProtocol.CAS20);
        casConfiguration.setAcceptAnyProxy(true);
        casConfiguration.setPrefixUrl(casServerUrlPrefix + "/");

        CasClient casClient = new CasClient(casConfiguration);
        casClient.setCallbackUrl(casClientUrlPrefix + "/callback?client_name=" + clientName);
        casClient.setName(clientName);
        return casClient;
    }

    @Bean
    public ShiroSessionStore shiroSessionStore() {
        return new ShiroSessionStore();
    }
}
