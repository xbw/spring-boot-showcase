package com.xbw.spring.boot.el;


import org.apache.commons.jexl3.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * https://commons.apache.org/proper/commons-jexl/reference/examples.html
 * JEXL is not a product of the Java Community Process (JCP),
 * but it provides a similar expression syntax. For more information about JSP 2.0 EL and JSTL 1.1 EL:
 * @author xbw
 */
public class JEXLTests {
    private static final JexlEngine jexl = new JexlBuilder().cache(512).strict(true).silent(false).create();

    @Test
    void calculate() {
        // Assuming we have a JexlEngine instance initialized in our class named 'jexl':
        // Create an expression object for our calculation
        String calculateTax = "((G1 + G2 + G3) * 0.1) + G4"; //e.g. "((G1 + G2 + G3) * 0.1) + G4";
        JexlExpression expression = jexl.createExpression(calculateTax);

        // populate the context
        JexlContext context = new MapContext();
        context.set("G1", 1);
        context.set("G2", 2);
        context.set("G3", 3);
        context.set("G4", 4);

        // work it out
        Number result = (Number) expression.evaluate(context);
        Assertions.assertEquals(4.6, result);
    }

    @Test
    void arrayList() {
        /*
         * First step is to retrieve an instance of a JexlEngine;
         * it might be already existing and shared or created anew.
         */
        final JexlEngine jexl = new JexlBuilder().create();
        /*
         *  Second make a jexlContext and put stuff in it
         */
        final JexlContext jc = new MapContext();

        final List<Object> l = new ArrayList<>();
        l.add("Hello from location 0");
        final Integer two = 2;
        l.add(two);
        jc.set("array", l);

        JexlExpression e = jexl.createExpression("array[1]");
        Object o = e.evaluate(jc);
        Assertions.assertEquals(o, two);

        e = jexl.createExpression("array[0].length()");
        o = e.evaluate(jc);
        Assertions.assertEquals(o, 21);
    }

    @Test
    void methodProperty() {
        /*
         * First step is to retrieve an instance of a JexlEngine;
         * it might be already existing and shared or created anew.
         */
        final JexlEngine jexl = new JexlBuilder().create();
        /*
         *  Second make a jexlContext and put stuff in it
         */
        final JexlContext jc = new MapContext();

        /*
         * The Java equivalents of foo and number for comparison and checking
         */
        final Foo foo = new Foo();
        final Integer number = 10;

        jc.set("foo", foo);
        jc.set("number", number);

        /*
         *  access a method w/o args
         */
        JexlExpression e = jexl.createExpression("foo.getFoo()");
        Object o = e.evaluate(jc);
        Assertions.assertEquals(o, foo.getFoo());

        /*
         *  access a method w/ args
         */
        e = jexl.createExpression("foo.convert(1)");
        o = e.evaluate(jc);
        Assertions.assertEquals(o, foo.convert(1));

        e = jexl.createExpression("foo.convert(1+7)");
        o = e.evaluate(jc);
        Assertions.assertEquals(o, foo.convert(1 + 7));

        e = jexl.createExpression("foo.convert(1+number)");
        o = e.evaluate(jc);
        Assertions.assertEquals(o, foo.convert(1 + number));

        /*
         * access a property
         */
        e = jexl.createExpression("foo.bar");
        o = e.evaluate(jc);
        Assertions.assertEquals(o, foo.get("bar"));
    }

    @Test
    void testBoolean() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);

        assertExpression(jc, "foo.convertBoolean(a==b)", "Boolean : false");
        assertExpression(jc, "foo.convertBoolean(a==true)", "Boolean : true");
        assertExpression(jc, "foo.convertBoolean(a==false)", "Boolean : false");
        assertExpression(jc, "foo.convertBoolean(true==false)", "Boolean : false");
        assertExpression(jc, "true eq false", Boolean.FALSE);
        assertExpression(jc, "true ne false", Boolean.TRUE);
    }

    @Test
    void testClass() {
        JexlContext context = new MapContext();
        String jexlTest = "StringUtils.isEmpty(value)";
        String test = "Test";
        context.set("value", test);
        context.set("StringUtils", StringUtils.class);
        boolean evaluate = (boolean) jexl.createExpression(jexlTest).evaluate(context);
        System.out.println(evaluate);
    }

    /**
     * Asserts that the given expression returns the given value when applied to the
     * given context
     */
    protected void assertExpression(final JexlContext jc, final String expression, final Object expected) throws Exception {
        final JexlExpression e = jexl.createExpression(expression);
        final Object actual = e.evaluate(jc);
        Assertions.assertEquals(expected.toString(), actual.toString(), expression);
    }

    /**
     * Helper example class.
     */
    public static class Foo {
        /**
         * Gets foo.
         * @return a string.
         */
        public String getFoo() {
            return "This is from getFoo()";
        }

        /**
         * Gets an arbitrary property.
         * @param arg property name.
         * @return arg prefixed with 'This is the property '.
         */
        public String get(final String arg) {
            return "This is the property " + arg;
        }

        /**
         * Gets a string from the argument.
         * @param i a long.
         * @return The argument prefixed with 'The value is : '
         */
        public String convert(final long i) {
            return "The value is : " + i;
        }

        public String convertBoolean(final boolean b) {
            return "Boolean : " + b;
        }
    }
}
