package com.xbw.spring.boot.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

/**
 * @author xbw
 */
@Controller
public class IndexController {

    @RequestMapping({"/index"})
    public String index(@RequestParam(value = "name", required = false, defaultValue = "springboot-groovy") String name, ModelMap map) {
        map.addAttribute("time", new Date());
        map.addAttribute("name", name);
        return "index";
    }

}