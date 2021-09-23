package com.xbw.spring.boot.project.controller;

import com.xbw.spring.boot.framework.shiro.util.ShiroUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @Value("${shiro.successUrl:/}")
    private String successUrl;

    @GetMapping("/cas")
    public String cas() {
        Object principal = ShiroUtils.getPrincipal();
        if (principal != null) {
            return "redirect:" + successUrl;
        }
        return null;
    }
}
