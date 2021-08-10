package com.xbw.spring.boot.snaker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.snaker.engine.Expression;
import org.snaker.engine.impl.JuelExpression;
import org.snaker.engine.spring.SpelExpression;

import java.util.HashMap;
import java.util.Map;

/**
 * https://zc-libre.gitee.io/snaker-doc/guide/decision.html
 * @author xbw
 */
public class ExpressionTests {
    final static String agree = "agree";
    final static Integer day_int = 5;
    final Map<String, Object> params = new HashMap<>();
    Expression expression;

    @BeforeEach
    void setUp() {
        params.put("result", agree);
        params.put("day", day_int);
    }

    @Test
    void juel() {
        expression = new JuelExpression();
        Assertions.assertEquals(agree, expression.eval(String.class, "#{result}", params));
        Assertions.assertEquals(agree, expression.eval(String.class, "${result}", params));

        Assertions.assertEquals(day_int, expression.eval(Integer.class, "#{day}", params));
        Assertions.assertEquals(day_int, expression.eval(Integer.class, "${day}", params));
    }

    @Test
    void juelFail() {
        expression = new JuelExpression();
        Assertions.assertEquals("#result", expression.eval(String.class, "#result", params));
        Assertions.assertEquals("$result", expression.eval(String.class, "$result", params));

        Assertions.assertEquals("#day", expression.eval(String.class, "#day", params));
        Assertions.assertEquals("$day", expression.eval(String.class, "$day", params));
    }

    @Test
    void spel() {
        expression = new SpelExpression();
        String result = "#result";
        System.out.println(result + ": " + expression.eval(String.class, result, params));
        String day = "#day";
        System.out.println(day + ": " + expression.eval(String.class, day, params));
    }
}
