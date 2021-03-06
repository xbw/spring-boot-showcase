package com.xbw.spring.boot.junit5;


import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.ExcludePackages;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectPackages("com.xbw.spring.boot.junit5")
@ExcludePackages({"com.xbw.spring.boot.junit5.order","com.xbw.spring.boot.junit5.tag"})
class JUnit5TestsSuite {

}
