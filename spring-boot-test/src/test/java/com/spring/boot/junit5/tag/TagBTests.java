package com.spring.boot.junit5.tag;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

@Tags({@Tag("TagA")})
@TagB
public class TagBTests extends TagTests {
    @Test
    public void c() {
    }
}