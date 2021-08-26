package com.xbw.spring.boot.template;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xbw
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VelocityTests {
    @Autowired
    VelocityEngine engine;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void index() {
        ResponseEntity<String> entity = this.restTemplate.getForEntity("/index", String.class);
        Assertions.assertEquals(entity.getStatusCode(), HttpStatus.OK);
        System.out.println(entity.getBody());
    }

    @Test
    void template() {
        //Velocity.init("src/main/resources/velocity.properties");
        Template template = engine.getTemplate("templates/template.vm");
        writer(template);
    }

    @Test
    void velocity() {
        Template template = Velocity.getTemplate("src/main/resources/templates/template.vm");
        writer(template);
    }

    private Map<String, Object> initMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("firstname", "First Name");
        map.put("lastname", "Last Name");
        map.put("country", "Country");
        return map;
    }

    private void writer(Template template) {
        VelocityContext context = new VelocityContext(initMap());
        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        System.out.println(writer);
    }
}