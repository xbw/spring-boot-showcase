package com.xbw.spring.boot.junit4.runners;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ParameterizedTests {

    private int x;
    private int y;
    private int expected;

    public ParameterizedTests(int x, int y, int expected) {
        this.x = x;
        this.y = y;
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "{index}: {0} + {1} = {2}")
    public static Collection<Integer[]> data() {
        return Arrays.asList(new Integer[][]{
                {0, 1, 1}, {1, 2, 3}, {6, 24, 30}, {49, 51, 100}
        });
    }

    @Test
    public void testAdd() {
        Assert.assertEquals(expected, (x + y));
    }
}
