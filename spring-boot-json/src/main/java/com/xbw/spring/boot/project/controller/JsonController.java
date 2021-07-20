package com.xbw.spring.boot.project.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("json")
public class JsonController {

    @RequestMapping("/get")
    public Object get() {
        return new Date();
    }

    @RequestMapping("/date")
    public Date date() {
        return new Date();
    }

}
