package com.spring.boot.junit5.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class NestedTests {
    @BeforeEach
    void setUp() {
        System.out.println("NestedTests Class setUp");
    }

    @Test
    void test() {
        System.out.println("NestedTests Class Test");
    }

    @Nested
    class InnerNestedTests {
        @BeforeEach
        void setUp() {
            System.out.println("InnerNestedTests Class setUp");
        }

        @Test
        void test() {
            System.out.println("InnerNestedTests Class Test");
        }
    }

}