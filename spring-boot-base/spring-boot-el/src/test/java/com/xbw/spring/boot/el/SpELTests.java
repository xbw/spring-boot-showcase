package com.xbw.spring.boot.el;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://www.baeldung.com/spring-expression-language
 * https://docs.spring.io/spring-framework/docs/4.3.x/spring-framework-reference/html/expressions.html
 * @author xbw
 */
public class SpELTests {
    final ExpressionParser parser = new SpelExpressionParser();
    final EvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();
    String hello = "'Hello World'";

    @Test
    void hello() {
        Expression exp = parser.parseExpression(hello);
        String message = (String) exp.getValue();
        Assertions.assertEquals("Hello World", message);

        exp = parser.parseExpression(hello + ".concat('!')");
        message = exp.getValue(String.class);
        Assertions.assertEquals("Hello World!", message);

        exp = parser.parseExpression(hello + ".bytes");
        byte[] bytes = (byte[]) exp.getValue();
        Assertions.assertEquals("Hello World".getBytes().length, bytes.length);

        exp = parser.parseExpression("new String('hello world').toUpperCase()");
        message = exp.getValue(String.class);
        Assertions.assertEquals("Hello World".toUpperCase(), message);
    }


    @Test
    void typeConversion() {
        Simple simple = new Simple();
        simple.booleanList.add(true);

        // "false" is passed in here as a String. SpEL and the conversion service
        // will recognize that it needs to be a Boolean and convert it accordingly.
        parser.parseExpression("booleanList[0]").setValue(context, simple, "false");
        // b will be false
        Assertions.assertFalse(simple.booleanList.get(0));
    }

    @Test
    void parserConfiguration() {
        // Turn on:
        // - auto null reference initialization
        // - auto collection growing
        SpelParserConfiguration config = new SpelParserConfiguration(true, true);
        ExpressionParser parser = new SpelExpressionParser(config);
        Expression expression = parser.parseExpression("list[3]");

        Simple simple = new Simple();
        Assertions.assertEquals(null, simple.list);
        Object o = expression.getValue(simple);
        // simple.list will now be a real collection of 4 entries
        // Each entry is a new empty String
        Assertions.assertEquals(4, simple.list.size());
    }

    @Test
    void expressions() {
        // evaluates to a Java list containing the four numbers
        List numbers = (List) parser.parseExpression("{1,2,3,4}").getValue(context);
        List listOfLists = (List) parser.parseExpression("{{'a','b'},{'x','y'}}").getValue(context);
        System.out.println(numbers);
        System.out.println(listOfLists);

        // evaluates to a Java map containing the two entries
        Map inventorInfo = (Map) parser.parseExpression("{name:'Nikola',dob:'10-July-1856'}").getValue(context);
        Map mapOfMaps = (Map) parser.parseExpression("{name:{first:'Nikola',last:'Tesla'},dob:{day:10,month:'July',year:1856}}").getValue(context);
        System.out.println(inventorInfo);
        System.out.println(mapOfMaps);

        // string literal, evaluates to "bc"
        String bc = parser.parseExpression("'abc'.substring(1, 3)").getValue(String.class);
        Assertions.assertEquals("bc", bc);
    }

    @Test
    void templating() {
        String randomPhrase = parser.parseExpression("random number is #{T(java.lang.Math).random()}",
                new TemplateParserContext()).getValue(String.class);
        System.out.println(randomPhrase);

        context.setVariable("abc", "xyz");
        System.out.println(parser.parseExpression("#abc").getValue(context, String.class));
    }

    @Test
    void eval() {

        EvaluationContext context = new StandardEvaluationContext();

        Map<String, Object> params = new HashMap<>();
        params.put("Integer", 0);
        params.put("Long", 0.0);
        params.put("Boolean", true);
        params.put("String", "String");

        params.forEach(context::setVariable);
        params.keySet().forEach(k -> {
            Expression expression = parser.parseExpression("#" + k);
            Object obj = expression.getValue(context, Object.class);

            System.out.printf("%s -> %s: %s%n",
                    expression.getExpressionString(),
                    obj.getClass().getName(),
                    obj);
        });

    }

    class Simple {
        public List<String> list;
        public List<Boolean> booleanList = new ArrayList<>();
    }

}
