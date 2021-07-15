package com.xbw.spring.boot.junit4.rules;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * https://stackoverflow.com/a/31826781/2986984
 */
public class RulesTests {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testException() {
        thrown.expect(ArithmeticException.class);
        thrown.expectMessage("/ by zero");
        int i = 1 / 0;
    }

    @Test(expected = ArithmeticException.class)
    public void testExceptionByExpected() {
        int i = 1 / 0;
    }

    @Test
    public void testExceptionByCatch() {
        try {
            int i = 1 / 0;
        } catch (ArithmeticException e) {
            e.printStackTrace();
        }
    }

}
