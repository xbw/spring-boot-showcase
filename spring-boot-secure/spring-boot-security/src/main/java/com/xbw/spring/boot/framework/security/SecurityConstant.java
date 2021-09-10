package com.xbw.spring.boot.framework.security;

/**
 * @author xbw
 */
@SuppressWarnings("unused")
public class SecurityConstant {
    public static final String CONSTANT_ADMIN = "ADMIN";
    public static final String CONSTANT_USER = "USER";
    public static final String CONSTANT_ANONYMOUS = "ANONYMOUS";

    public static final String CONSTANT_ROLE_PREFIX = "ROLE_";
    public static final String CONSTANT_ROLE_ADMIN = CONSTANT_ROLE_PREFIX + CONSTANT_ADMIN;
    public static final String CONSTANT_ROLE_USER = CONSTANT_ROLE_PREFIX + CONSTANT_USER;
    public static final String CONSTANT_ROLE_ANONYMOUS = CONSTANT_ROLE_PREFIX + CONSTANT_ANONYMOUS;

    public static final String CONSTANT_USER_ADMIN = "admin";
    public static final String CONSTANT_USER_USER = "user";
    public static final String CONSTANT_USER_ANONYMOUS = "anonymousUser";
}

