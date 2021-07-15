package com.xbw.spring.boot.testng.param;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParamsTests {

    @Parameters({"test-param"})
    @Test
    public void testParameters(@Optional("TestNG") String testParam) {
        System.out.println("Invoked testParameters , print args: " + testParam);
        assert "Springboot".equals(testParam) | "TestNG".equals(testParam);
    }

}