package com.xbw.spring.boot.project.controller;

import com.samskivert.mustache.Mustache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mustache.MustacheResourceTemplateLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xbw
 */
@Controller
public class IndexController {

    @Autowired
    Mustache.Compiler compiler;
    @Autowired
    MustacheResourceTemplateLoader templateLoader;

    @RequestMapping({"/index"})
    public String index(@RequestParam(value = "name", required = false, defaultValue = "springboot-mustache") String name, ModelMap map) {
        map.addAttribute("time", new Date());
        map.addAttribute("name", name);
        return "index";
    }

    @ResponseBody
    @RequestMapping(value = {"/xml"})
    public String xml() throws Exception {
        Map<String, String> values = new HashMap<>(5);
        values.put("firstname", "First Name");
        values.put("lastname", "Last Name");
        values.put("country", "Country");
        return compiler.compile(templateLoader.getTemplate("template")).execute(values);
    }

}