//package com.xbw.spring.boot.secure;
//
//import com.xbw.spring.boot.framework.shiro.realm.ShiroRealm;
//import org.apache.shiro.authc.AuthenticationInfo;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.apache.shiro.authc.SimpleAuthenticationInfo;
//import org.apache.shiro.authc.UsernamePasswordToken;
//import org.apache.shiro.authc.credential.CredentialsMatcher;
//import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
//import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
//import org.apache.shiro.crypto.hash.Md5Hash;
//import org.apache.shiro.crypto.hash.Sha1Hash;
//import org.apache.shiro.crypto.hash.Sha256Hash;
//import org.apache.shiro.crypto.hash.SimpleHash;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
///**
// * https://shiro.apache.org/testing.html
// * @author xbw
// */
//@SpringBootTest
//public class ShiroTests {
//    private final String user = "user";
//    private final String password = "password";
//    private final String name = "shiroRealm";
//    private final String salt = "salt";
//
//    @Autowired
//    ShiroRealm shiroRealm;
//
//    @Test
//    void getCredentialsMatcher() {
//        CredentialsMatcher matcher = shiroRealm.getCredentialsMatcher();
//        print(matcher, getToken(), getInfo());
//    }
//
//    @Test
//    void getHashedCredentialsMatcher() {
//        HashedCredentialsMatcher matcher = (HashedCredentialsMatcher) shiroRealm.getCredentialsMatcher();
//        hashedCredentialsMatcher(matcher, new Md5Hash(salt));
//    }
//
//    @Test
//    void simple() {
//        CredentialsMatcher matcher = new SimpleCredentialsMatcher();
//        print(matcher, getToken(), getInfo());
//    }
//
//    @Test
//    void md5() {
//        // storedCredentialsHexEncoded default is true
//        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(Md5Hash.ALGORITHM_NAME);
//        matcher.setHashIterations(2);
//        hashedCredentialsMatcher(matcher, new Md5Hash(salt));
//    }
//
//    @Test
//    void sha1() {
//        // storedCredentialsHexEncoded default is true
//        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(Sha1Hash.ALGORITHM_NAME);
//        matcher.setHashIterations(2);
//        hashedCredentialsMatcher(matcher, new Sha1Hash(salt));
//    }
//
//    @Test
//    void sha256() {
//        // storedCredentialsHexEncoded default is true
//        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(Sha256Hash.ALGORITHM_NAME);
//        matcher.setHashIterations(2);
//        hashedCredentialsMatcher(matcher, new Sha256Hash(salt));
//    }
//
//    private void hashedCredentialsMatcher(HashedCredentialsMatcher matcher, SimpleHash credentialsSalt) {
//        SimpleHash hashedCredentials = new SimpleHash(matcher.getHashAlgorithmName(), password.toCharArray(), credentialsSalt, matcher.getHashIterations());
//        AuthenticationInfo info = new SimpleAuthenticationInfo(user, hashedCredentials, credentialsSalt, name);
//        print(matcher, getToken(), info);
//    }
//
//    private AuthenticationToken getToken() {
//        return new UsernamePasswordToken(user, password);
//    }
//
//    private AuthenticationInfo getInfo() {
//        return new SimpleAuthenticationInfo(user, password, name);
//    }
//
//    private void print(CredentialsMatcher matcher, AuthenticationToken token, AuthenticationInfo info) {
//        System.out.printf("CredentialsMatcher: %s%n", matcher.getClass().getName());
//        Assertions.assertTrue(matcher.doCredentialsMatch(token, info));
//    }
//}
