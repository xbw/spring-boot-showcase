package com.xbw.spring.boot.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * @author xbw
 */
@Controller
public class IndexController {
    @RequestMapping("index")
    public String index(Model model) {
        model.addAttribute("now", DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDateTime.now()));
        return "index";
    }

    @RequestMapping("/foo")
    public String foo(Map<String, Object> model) {
        throw new RuntimeException("Foo");
    }

}