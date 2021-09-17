package com.xbw.spring.boot.project.service;

import org.springframework.stereotype.Service;

/**
 * @author xbw
 */
@Service
public class ShiroService {

    public String roles() {
        return "@RequiresRoles(\"admin\")";
    }

    public String permissions() {
        return "@RequiresPermissions(\"user:*:*\")";
    }

    public String user() {
        return "@RequiresUser";
    }
}
