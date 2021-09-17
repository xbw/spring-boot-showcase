package com.xbw.spring.boot.framework.shiro.filter;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author xbw
 */
@SuppressWarnings("deprecation")
public class CasFilter extends org.apache.shiro.cas.CasFilter {
    /**
     * {@code SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(request);} Disabled
     * @param token
     * @param subject
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
                                     ServletResponse response) throws Exception {
        if (!StringUtils.hasLength(getSuccessUrl())) {
            Session session = subject.getSession(false);
            if (session != null) {
                session.removeAttribute(WebUtils.SAVED_REQUEST_KEY);
            }
        }
        return super.onLoginSuccess(token, subject, request, response);
    }

//    /**
//     * use custom successUrl
//     * @param request
//     * @param response
//     * @throws Exception
//     */
//    @Override
//    protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
//        //WebUtils.redirectToSavedRequest(request, response, getSuccessUrl());
//        WebUtils.issueRedirect(request, response, getSuccessUrl());
//    }

}
