package com.spring.boot.junit5.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.DisplayName.class)
class OrderByDisplayNameTests extends OrderByDefaultTests {
    @Test
    @DisplayName("Display C , Actual CategoryATests")
    void testA() {
        System.out.println("Display C , Actual CategoryATests");
    }

    @Test
    @DisplayName("Display CategoryBTests , Actual CategoryBTests")
    void testB() {
        System.out.println("Display CategoryBTests , Actual CategoryBTests");
    }

    @Test
    @DisplayName("Display CategoryATests , Actual C")
    void testC() {
        System.out.println("Display CategoryATests , Actual C");
    }

    @Test
    @DisplayName("Display X , Actual Z")
    void testZ() {
        System.out.println("Display X , Actual Z");
    }

    @Test
    @DisplayName("Display Y , Actual Y")
    void testY() {
        System.out.println("Display Y , Actual Y");
    }

    @Test
    @DisplayName("Display Z , Actual X")
    void testX() {
        System.out.println("Display Z , Actual X");
    }
}
