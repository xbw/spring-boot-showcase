package com.spring.boot.junit4.category;

import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category({CategoryA.class, CategoryB.class})
public class CategoryBTests {
  @Test
  public void c() {
  }
}