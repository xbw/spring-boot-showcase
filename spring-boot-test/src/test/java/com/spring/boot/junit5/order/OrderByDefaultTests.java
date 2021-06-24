package com.spring.boot.junit5.order;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * like {@code @TestMethodOrder(MethodOrderer.MethodName.class)}
 *
 * @see TestMethodOrder
 * @see MethodOrderer
 */
class OrderByDefaultTests {
    @Test
    void testA() {
        System.out.println("testA");
    }

    @Test
    void testB() {
        System.out.println("testB");
    }

    @Test
    void testC() {
        System.out.println("testC");
    }

    @Test
    void testZ() {
        System.out.println("testZ");
    }

    @Test
    void testY() {
        System.out.println("testY");
    }

    @Test
    void testX() {
        System.out.println("testX");
    }
}
