package com.xbw.spring.boot.template;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mustache.MustacheResourceTemplateLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xbw
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MustacheTests {

    @Autowired
    Mustache.Compiler compiler;
    @Autowired
    MustacheResourceTemplateLoader templateLoader;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void index() {
        ResponseEntity<String> entity = this.restTemplate.getForEntity("/index", String.class);
        Assertions.assertEquals(entity.getStatusCode(), HttpStatus.OK);
        System.out.println(entity.getBody());
    }

    @Test
    void templateClassPath() throws IOException {
        ClassPathResource resource = new ClassPathResource("templates/template.mustache");
        try (InputStream is = resource.getInputStream();
             Reader reader = new InputStreamReader(is)) {
            Template template = compiler.compile(reader);
            writer(template);
        }
    }

    @Test
    void templateLoader() throws Exception {
        Template template = compiler.compile(templateLoader.getTemplate("template"));
        writer(template);
    }

    @Test
    void compile() {
        String text = "Hello {{mustache}}!";
        Template template = Mustache.compiler().compile(text);
        Map<String, String> map = new HashMap<>();
        map.put("mustache", "Mustache");
        Assertions.assertEquals("Hello Mustache!", template.execute(map));
    }

    private Map<String, Object> initMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("firstname", "First Name");
        map.put("lastname", "Last Name");
        map.put("country", "Country");
        return map;
    }

    private void writer(Template template) {
        StringWriter writer = new StringWriter();
        template.execute(initMap(), writer);
        System.out.println(writer);
    }
}