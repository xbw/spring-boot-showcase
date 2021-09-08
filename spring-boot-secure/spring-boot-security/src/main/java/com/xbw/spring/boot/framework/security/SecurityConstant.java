package com.xbw.spring.boot.framework.security;

/**
 * @author xbw
 */
@SuppressWarnings("unused")
public class SecurityConstant {
    public static final String CONSTANT_ROOT = "ROOT";
    public static final String CONSTANT_ADMIN = "ADMIN";
    public static final String CONSTANT_USER = "USER";
    public static final String CONSTANT_ANONYMOUS = "ANONYMOUS";

    public static final String CONSTANT_ROLE_PREFIX = "ROLE_";
    public static final String CONSTANT_ROLE_ROOT = CONSTANT_ROLE_PREFIX + "ROOT";
    public static final String CONSTANT_ROLE_ADMIN = CONSTANT_ROLE_PREFIX + "ADMIN";
    public static final String CONSTANT_ROLE_USER = CONSTANT_ROLE_PREFIX + "USER";
    public static final String CONSTANT_ROLE_ANONYMOUS = CONSTANT_ROLE_PREFIX + "ANONYMOUS";

    public static final String CONSTANT_USER_ADMIN = "xbw";
    public static final String CONSTANT_USER_ANONYMOUS = "anonymousUser";
}
