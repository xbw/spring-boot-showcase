package com.spring.boot.testng.group;

import org.testng.annotations.Test;

//@Test(groups = "group")
public class GroupsTests {
    @Test
    public void test() {
    }

    @Test(enabled = false)
    void testEnabled() {
    }

    @Test(groups = {"group", "default"})
    void testGroup() {
    }

    @Test(groups = "default")
    void testGroupDefault() {
    }

    @Test(groups = "custom")
    void testGroupCustom() {
    }

}