package com.spring.boot.junit5.tag;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.ExcludeTags;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@IncludeTags("TagA")
@ExcludeTags("TagB")
@SelectClasses({TagATests.class, TagBTests.class})
public class TagATestSuite {
    // 将运行TagATests.b ，但不运行TagATests.a和TagBTests.c
}