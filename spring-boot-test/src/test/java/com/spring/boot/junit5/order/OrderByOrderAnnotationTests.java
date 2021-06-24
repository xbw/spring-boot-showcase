package com.spring.boot.junit5.order;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderByOrderAnnotationTests extends OrderByDefaultTests {
    @Test
    @Order(1)
    void testA() {
        System.out.println("testA @Order(1)");
    }

    @Test
    @Order(4)
    void testB() {
        System.out.println("testB @Order(4)");
    }

    @Test
    @Order(2)
    void testC() {
        System.out.println("testC @Order(2)");
    }

    @Test
    @Order(5)
    void testZ() {
        System.out.println("testZ @Order(5)");
    }

    @Test
    @Order(3)
    void testY() {
        System.out.println("testY @Order(3)");
    }

    @Test
    @Order(6)
    void testX() {
        System.out.println("testX @Order(6)");
    }
}
