package com.xbw.spring.boot.junit4.category;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory({CategoryA.class})
@Suite.SuiteClasses({CategoryATests.class, CategoryBTests.class})
public class CategoryBTestSuite {
    // 将运行CategoryATests.b 和CategoryBTests.c，但不运行CategoryATests.a
}