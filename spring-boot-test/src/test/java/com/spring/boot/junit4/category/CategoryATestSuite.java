package com.spring.boot.junit4.category;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(CategoryA.class)
@Categories.ExcludeCategory(CategoryB.class)
@Suite.SuiteClasses({CategoryATests.class, CategoryBTests.class})
public class CategoryATestSuite {
    // 将运行CategoryATests.b ，但不运行CategoryATests.a和CategoryBTests.c
}