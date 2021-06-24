package com.spring.boot.junit4.order;


import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderByNameAscendingTests {
    @Test
    public void testA() {
    }

    @Test
    public void testB() {
    }

    @Test
    public void testC() {
    }

    @Test
    public void testZ() {
    }

    @Test
    public void testY() {
    }

    @Test
    public void testX() {
    }
}
