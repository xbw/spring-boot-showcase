package com.spring.boot.junit4.category;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CategoryATests {
    @Test
    public void a() {
        Assert.assertTrue(true);
    }

    @Category(CategoryA.class)
    @Test
    public void b() {
    }
}
 

