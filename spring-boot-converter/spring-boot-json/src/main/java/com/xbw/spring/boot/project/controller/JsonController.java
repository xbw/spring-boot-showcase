package com.xbw.spring.boot.project.controller;

import com.xbw.spring.boot.project.model.Person;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class JsonController {

    @RequestMapping("/get")
    public Object get() {
        return new Date();
    }

    @RequestMapping("/date")
    public Date date() {
        return new Date();
    }

    @RequestMapping("/person")
    public Person person() {
        return new Person(1L, "JSON", "j", "1970-01-01");
    }

}
