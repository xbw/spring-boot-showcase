package com.spring.boot.junit5.tag;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectPackages("com.spring.boot.junit5.tag")
public class TagBTestSuite {
    // 将运行A.b 和B.c，但不运行A.a
}