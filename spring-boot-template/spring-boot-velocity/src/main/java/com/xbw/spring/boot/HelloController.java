package com.xbw.spring.boot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

@Controller
public class HelloController {

    @ResponseBody
    @RequestMapping({"", "/"})
    public String hello(Locale locale, Model model) {
        return "Hello Spring Boot Velocity!";
    }

    @RequestMapping({"/hello"})
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("redirect:index.html");
        return modelAndView;
    }

}