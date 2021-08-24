package com.xbw.spring.boot.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xbw
 */
@Controller
public class IndexController {
    @Autowired
    SpringTemplateEngine templateEngine;

    @RequestMapping({"/index"})
    public String index(HttpServletRequest request, @RequestParam(value = "name", required = false, defaultValue = "springboot-thymeleaf") String name) {
        request.setAttribute("name", name);
        return "index";
    }

    @ResponseBody
    @RequestMapping(value = {"/xml"})
    public String xml() {
        Map<String, String> values = new HashMap<>(5);
        Context context = new Context();
        context.setVariable("values", values);
        values.put("firstname", "First Name");
        values.put("lastname", "Last Name");
        values.put("country", "Country");
        return templateEngine.process("template", context);
    }

}