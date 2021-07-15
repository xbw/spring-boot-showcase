package com.xbw.spring.boot.junit5.params;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * https://www.baeldung.com/junit-assert-exception
 */
class ParamsTests {

    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource({
            "0,    1,   1",
            "1,    2,   3",
            "6,  24, 30",
            "49,  51, 100"
    })
    void testAdd(int x, int y, int expectedResult) {
        Assertions.assertEquals(expectedResult, testAdd(x, y), () -> x + " + " + y + " should equal " + expectedResult);
    }

    private int testAdd(int a, int b) {
        return a + b;
    }

}
