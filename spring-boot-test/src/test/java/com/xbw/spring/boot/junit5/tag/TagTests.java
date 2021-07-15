package com.xbw.spring.boot.junit5.tag;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectPackages("com.xbw.spring.boot.junit5")
public class TagTests {
}
 

