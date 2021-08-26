package com.xbw.spring.boot.template;

import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import groovy.lang.Script;
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author xbw
 */
public class GroovyTests {
    final static String GROOVY = "src/test/resources/groovy/";

    @Test
    void classLoader() throws IOException, InstantiationException, IllegalAccessException {
        ClassLoader parent = this.getClass().getClassLoader();
        GroovyClassLoader loader = new GroovyClassLoader(parent);
        Class clazz = loader.parseClass(
                new File(GROOVY + "classLoader.groovy")
        );
        GroovyObject groovyObject = (GroovyObject) clazz.newInstance();
        String result = (String) groovyObject.invokeMethod("test", "1");
        Assertions.assertEquals("test id: 1", result);
    }

    @Test
    void scriptEngine() throws IOException, ScriptException, ResourceException {
        GroovyScriptEngine engine = new GroovyScriptEngine(GROOVY);
        Script script = engine.createScript("scriptEngine.groovy", new Binding());
        String result = (String) script.invokeMethod("test", "1");
        Assertions.assertEquals("test id: 1", result);
    }

    @Test
    void binding() throws IOException, ScriptException, ResourceException {
        GroovyScriptEngine engine = new GroovyScriptEngine(GROOVY);
        Binding binding = new Binding();
        binding.setVariable("id", 1);
        engine.run("binding.groovy", binding);
        String result = binding.getVariable("output").toString();
        Assertions.assertEquals("test id: 1", result);
    }
}
