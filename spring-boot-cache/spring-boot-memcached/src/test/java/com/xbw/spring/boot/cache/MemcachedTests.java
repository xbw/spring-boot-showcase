package com.xbw.spring.boot.cache;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeoutException;


@SpringBootTest
class MemcachedTests {
    @Autowired
    com.whalin.MemCached.MemCachedClient memcached;
    @Autowired
    net.spy.memcached.MemcachedClient spymemcached;
    @Autowired
    net.rubyeye.xmemcached.MemcachedClient xMemcached;

    @Test
    void memcached() {
        memcached.set("memcached", "Memcached Client for Java");
        Assertions.assertEquals("Memcached Client for Java", memcached.get("memcached"));
    }


    @Test
    void memcachedList() {
        List list = new ArrayList<Character>();
        for (int i = 65; i < 65 + new Random().nextInt(26); i++) {
            list.add((char) i);
        }
        System.out.println(list);

        memcached.set("abc", list);
        Assertions.assertEquals(list, memcached.get("abc"));
    }


    @Test
    void spymemcached() throws IOException {
        spymemcached.set("spy", 0, "Spymemcached");
        Assertions.assertEquals("Spymemcached", spymemcached.get("spy"));
    }

    @Test
    void xMemcached() {
        try {
            xMemcached.set("x", 0, "XMemcached");
            Assertions.assertEquals("XMemcached", xMemcached.get("x"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (net.rubyeye.xmemcached.exception.MemcachedException e) {
            e.printStackTrace();
        }
    }

}