package com.xbw.spring.boot.project.controller;

import com.xbw.spring.boot.framework.security.SecurityConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LoginController extends BaseController {

    @GetMapping("/login")
    public String getLogin() {
        List<String> list = getGrantedAuthority();
        if (list.contains(SecurityConstant.CONSTANT_ROLE_ANONYMOUS)) {
            return "cas";
        } else if (list.contains(SecurityConstant.CONSTANT_ROLE_ADMIN)) {
            return "admin/home";
        } else {
            return "home";
        }
    }

    @GetMapping("/cas")
    public String cas() {
        List<String> list = getGrantedAuthority();

        if (list.contains(SecurityConstant.CONSTANT_ROLE_ANONYMOUS)) {
            return "redirect:error";
        } else if (list.contains(SecurityConstant.CONSTANT_ROLE_ADMIN)) {
            return "redirect:admin/home";
        } else {
            return "redirect:home";
        }
    }
}
