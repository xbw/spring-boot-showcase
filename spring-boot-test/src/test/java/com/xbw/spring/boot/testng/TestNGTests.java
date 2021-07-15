package com.xbw.spring.boot.testng;

import org.testng.annotations.*;

public class TestNGTests {

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("print @BeforeSuite");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("print @AfterSuite");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("print @BeforeTest");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("print @AfterTest");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("print @BeforeClass");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("print @AfterClass");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("print @BeforeMethod");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("print @AfterMethod");
    }

    @Test
    void test() {
        System.out.println("print @Test");
    }

}