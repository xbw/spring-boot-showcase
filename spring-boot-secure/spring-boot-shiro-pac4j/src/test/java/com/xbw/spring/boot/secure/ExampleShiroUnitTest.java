package com.xbw.spring.boot.secure;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


/**
 * Simple example test class showing how one may perform unit tests for
 * code that requires Shiro APIs.
 */
class ExampleShiroUnitTest extends AbstractShiroTest {

    @BeforeEach
    void setUp() {
        SecurityManager securityManger = Mockito.mock(SecurityManager.class, Mockito.RETURNS_DEEP_STUBS);
        Subject subject = Mockito.mock(Subject.class, Mockito.RETURNS_DEEP_STUBS);
        AuthenticationToken authenticationToken = Mockito.mock(AuthenticationToken.class, Mockito.RETURNS_DEEP_STUBS);
        securityManger.login(subject, authenticationToken);
        ThreadContext.bind(securityManger);
    }

    @Test
    void testSimple() {
        //1.  Create a mock authenticated Subject instance for the test to run:
        Subject subjectUnderTest = getSubject();
        Assertions.assertFalse(subjectUnderTest.isAuthenticated());

        //2. Bind the subject to the current thread:
        setSubject(subjectUnderTest);

        //perform test logic here.  Any call to
        //SecurityUtils.getSubject() directly (or nested in the
        //call stack) will work properly.
    }

    @Test
    void tearDownSubject() {
        //3. Unbind the subject from the current thread:
        clearSubject();
    }

}