package com.xbw.spring.boot.junit5.tag;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class TagATests extends TagTests{
    @Test
    public void a() {
        Assertions.assertTrue(true);
    }

    @Tag("TagA")
    @Test
    public void b() {
    }
}
 

