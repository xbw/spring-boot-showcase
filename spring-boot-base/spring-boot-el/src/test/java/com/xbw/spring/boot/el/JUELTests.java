package com.xbw.spring.boot.el;

import de.odysseus.el.ExpressionFactoryImpl;
import de.odysseus.el.ObjectValueExpression;
import de.odysseus.el.TreeMethodExpression;
import de.odysseus.el.TreeValueExpression;
import de.odysseus.el.util.SimpleContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
 * http://juel.sourceforge.net/guide/start.html
 * @author xbw
 */
class JUELTests {
    // the ExpressionFactory implementation is de.odysseus.el.ExpressionFactoryImpl
    ExpressionFactory factory = new ExpressionFactoryImpl();
    // package de.odysseus.el.util provides a ready-to-use subclass of ELContext
    SimpleContext context = new SimpleContext();

    /**
     * You will need juel-spi-2.2.x.jar if you have several expression language implementations on your classpath
     * and want to force JUELs implementation to be chosen by ExpressionFactory.newInstance()
     */
    @Test
    void spi() {
        factory = ExpressionFactory.newInstance();
        System.out.println(factory);
    }

    @Test
    void properties() {
        Properties properties = new Properties();
        properties.put("javax.el.cacheSize", "5000");
        factory = new ExpressionFactoryImpl(properties);
    }

    @Test
    void guide() throws NoSuchMethodException {
        // map function math:max(int, int) to java.lang.Math.max(int, int)
        context.setFunction("math", "max", Math.class.getMethod("max", int.class, int.class));

        // map variable foo to 0
        context.setVariable("foo", factory.createValueExpression(0, int.class));

        // parse our expression
        ValueExpression expression = factory.createValueExpression(context, "${math:max(foo,bar)}", int.class);

        // set value for top-level property "bar" to 1
        factory.createValueExpression(context, "${bar}", int.class).setValue(context, 1);

        // get value for our expression
        Assertions.assertEquals(1, expression.getValue(context));
    }

    @Test
    void valueExpression() {
        ObjectValueExpression objectExpr = (ObjectValueExpression) factory.createValueExpression(Math.PI, Double.class);
        context.setVariable("pi", objectExpr);
        print(objectExpr);

        TreeValueExpression treeExpr = (TreeValueExpression) factory.createValueExpression(context, "#{pi/2}", Object.class);
        PrintWriter out = new PrintWriter(System.out);
        treeExpr.dump(out);
        out.flush();
        Assertions.assertTrue(treeExpr.isDeferred());
        Assertions.assertFalse(treeExpr.isLeftValue());
        print(treeExpr);
    }

    @Test
    void treeMethodExpression() {
        context.setVariable("x", factory.createValueExpression(Math.PI, Double.class));

        TreeMethodExpression expr = (TreeMethodExpression) factory.createMethodExpression(context, "#{x.toString}", String.class, new Class[]{});
        PrintWriter out = new PrintWriter(System.out);
        expr.dump(out);
        out.flush();
        Assertions.assertTrue(expr.isDeferred());
        System.out.printf("%s -> %s: %s%n",
                expr.getExpressionString(),
                expr.getMethodInfo(context).getName(),
                expr.invoke(context, null));
    }

    @Test
    void simpleContext() throws NoSuchMethodException {
        context.setFunction("math", "sin", Math.class.getMethod("sin", double.class));
        context.setVariable("pi", factory.createValueExpression(Math.PI, double.class));
        ValueExpression expr = factory.createValueExpression(context, "${math:sin(pi/2)}", double.class);
        System.out.println(expr.getExpressionString() + " = " + expr.getValue(context)); // 1.0
    }

    @Test
    void simpleResolver() {
        // resolve top-level property
        factory.createValueExpression(context, "#{pi}", double.class).setValue(context, Math.PI);
        ValueExpression expr1 = factory.createValueExpression(context, "${pi/2}", double.class);
        System.out.println("pi/2 = " + expr1.getValue(context)); // 1.5707963...

        // resolve bean property
        factory.createValueExpression(context, "#{current}", Date.class).setValue(context, new Date());
        ValueExpression expr2 = factory.createValueExpression(context, "${current.time}", long.class);
        System.out.println("current.time = " + expr2.getValue(context));
    }

    @Test
    void eval() {
        Map<String, Object> params = new HashMap<>();
        params.put("Integer", 0);
        params.put("Long", 0.0);
        params.put("Boolean", true);
        params.put("String", "String");

        params.forEach((k, v) -> {
            context.setVariable(k, factory.createValueExpression(v, Object.class));
            ValueExpression expr = factory.createValueExpression(context, "#{" + k + "}", Object.class);
            print(expr);
        });

        System.out.println();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            ValueExpression expr = factory.createValueExpression(context, "${" + entry.getKey() + "}", Object.class);
            context.setVariable(entry.getKey(), factory.createValueExpression(entry.getValue(), Object.class));
            print(expr);
        }

        System.out.println();
        context.setVariable("x", factory.createValueExpression(params, Map.class));
        TreeMethodExpression expr = (TreeMethodExpression) factory.createMethodExpression(context, "#{x.get}", Object.class, new Class[]{Object.class});
        PrintWriter out = new PrintWriter(System.out);
        expr.dump(out);
        out.flush();
        Assertions.assertTrue(expr.isDeferred());
        System.out.printf("%s -> %s: %s%n",
                expr.getExpressionString(),
                expr.getMethodInfo(context).getName(),
                expr.invoke(context, new Object[]{"Boolean"}));
    }

    @Test
    void evalSet() {
    }

    private void print(ValueExpression expr) {
        System.out.printf("%s -> %s: %s%n",
                expr.getExpressionString(),
                expr.getValue(context).getClass().getName(),
                expr.getValue(context));
    }
}
