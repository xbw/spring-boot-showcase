package com.xbw.spring.boot.template;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xbw
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"thymeleaf"})
public class FreeMarkerTests {
    @Autowired
    FreeMarkerConfigurer configurer;
    @Autowired
    freemarker.template.Configuration configuration;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void index() {
        ResponseEntity<String> entity = this.restTemplate.getForEntity("/index", String.class);
        Assertions.assertEquals(entity.getStatusCode(), HttpStatus.OK);
        System.out.println(entity.getBody());
    }

    @Test
    void freeMarkerConfigurer() throws IOException, TemplateException {
        Configuration configuration = configurer.getConfiguration();
        Template template = configuration.getTemplate("template.ftl");
        writer(template);
    }

    @Test
    void configuration() throws IOException, TemplateException {
        Template template = configuration.getTemplate("template.ftl");
        writer(template);
    }

    @Test
    void template() throws IOException, TemplateException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_31);
        configuration.setClassForTemplateLoading(this.getClass(), "/");
        Template template = configuration.getTemplate("templates/template.ftl");
        writer(template);
    }

    private Map<String, String> initMap() {
        Map<String, String> map = new HashMap<>();
        map.put("firstname", "First Name");
        map.put("lastname", "Last Name");
        map.put("country", "Country");
        return map;
    }

    private void writer(Template template) throws TemplateException, IOException {
        StringWriter writer = new StringWriter();
        template.process(initMap(), writer);
        System.out.println(writer);
    }
}