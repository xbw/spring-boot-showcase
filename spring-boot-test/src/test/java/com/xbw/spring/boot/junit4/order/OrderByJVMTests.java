package com.xbw.spring.boot.junit4.order;


import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;


@FixMethodOrder(MethodSorters.JVM)
public class OrderByJVMTests {
    @Test
    public void testA() {
        System.out.println("testA");
    }

    @Test
    public void testB() {
        System.out.println("testB");
    }

    @Test
    public void testC() {
        System.out.println("testC");
    }

    @Test
    public void testZ() {
        System.out.println("testZ");
    }

    @Test
    public void testY() {
        System.out.println("testY");
    }

    @Test
    public void testX() {
        System.out.println("testX");
    }
}
