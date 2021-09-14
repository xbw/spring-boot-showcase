package com.xbw.spring.boot.project.controller;

import com.xbw.spring.boot.framework.shiro.util.ShiroUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String index() {
        Object principal = ShiroUtils.getPrincipal();
        if (principal != null) {
            return "redirect:/";
        }
        return "login";
    }

    @PostMapping("/login")
    public String login() {
        Object principal = ShiroUtils.getPrincipal();
        if (principal != null) {
            return "redirect:/";
        }

        return "login";
    }

//    @RequestMapping("/logout")
//    public String logout() {
//        ShiroUtils.getSubject().logout();
//        return "login";
//    }
}
