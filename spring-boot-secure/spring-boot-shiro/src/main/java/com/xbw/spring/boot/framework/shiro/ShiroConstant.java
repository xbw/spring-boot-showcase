package com.xbw.spring.boot.framework.shiro;

/**
 * @author xbw
 */
@SuppressWarnings("unused")
public class ShiroConstant {
    public static final String CONSTANT_ADMIN = "admin";
    public static final String CONSTANT_USER = "user";

    public static final String CONSTANT_ROLE_ADMIN = CONSTANT_ADMIN;
    public static final String CONSTANT_ROLE_USER = CONSTANT_USER;

    public static final String CONSTANT_PERMISSION_ADMIN = "*:*:*";
    public static final String CONSTANT_PERMISSION_USER = "user:*:*";

    public static final String CONSTANT_USER_ADMIN = CONSTANT_ADMIN;
    public static final String CONSTANT_USER_USER = CONSTANT_USER;

    public static final String CONSTANT_USER_XBW = "xbw";
}

