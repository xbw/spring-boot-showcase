package com.xbw.spring.boot.junit4;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Spring via {@code @RunWith(SpringRunner.class)}
 * Springboot via {@code @SpringBootTest}
 *
 * @see RunWith
 * @see SpringRunner
 * @see SpringBootTest
 */
public class JUnit4Tests {
    @BeforeClass // the method should be static
    public static void testBeforeClass() {
        System.out.println("print @BeforeClass");
    }

    @AfterClass // the method should be static
    public static void testAfterClass() {
        System.out.println("print @AfterClass");
    }

    @Before
    public void testBefore() {
        System.out.println("print @Before");
    }

    @Test
    public void test() {
        System.out.println("print @Test");
    }

    @Test
    @Ignore
    public void testIgnore() {
        System.out.println("print @Ignore");
    }

    @After
    public void testAfter() {
        System.out.println("print @After");
    }

}
