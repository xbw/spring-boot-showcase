package com.xbw.spring.boot.junit5;


import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Spring via {@code @ExtendWith(SpringExtension.class)}
 * SpringBoot via {@code @SpringBootTest}
 *
 * @see ExtendWith
 * @see SpringExtension
 * @see SpringBootTest
 */
@DisabledOnOs(OS.LINUX)
class JUnit5Tests {

    /**
     * the method must be static unless the test class is annotated with {@code @TestInstance(TestInstance.Lifecycle.PER_CLASS)}
     *
     * @see TestInstance
     */
    @BeforeAll
    static void testBeforeAll() {
        System.out.println("print @BeforeAll");
    }

    /**
     * the method must be static unless the test class is annotated with {@code @TestInstance(TestInstance.Lifecycle.PER_CLASS)}
     *
     * @see TestInstance
     */
    @AfterAll
    static void testAfterAll() {
        System.out.println("print @AfterAll");
    }

    @BeforeEach
    void testBeforeEach() {
        System.out.println("print @BeforeEach");
    }

    @Test
    void test() {
        System.out.println("print @Test");
    }

    @RepeatedTest(5)
    void testRepeated() {
        System.out.println("print @RepeatedTest");
    }

    @Test
    @Disabled
    void testDisabled() {
        System.out.println("print @Disabled");
    }

    @Test
    void testException() {//
        Throwable throwable = Assertions.assertThrows(ArithmeticException.class, () -> {
            int i = 1 / 0;
        });
        Assertions.assertEquals("/ by zero", throwable.getMessage());
    }

    @AfterEach
    void testAfterEach() {
        System.out.println("print @AfterEach");
    }

}
