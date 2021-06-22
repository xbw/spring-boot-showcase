package com.spring.boot.redis;

import com.spring.boot.project.controller.RedisController;
import com.spring.boot.project.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisController personController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
    }

    @Test
    void testMvc() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/person/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        Assertions.assertEquals("{\"id\":1,\"userName\":\"name1\",\"nickName\":\"n1\",\"birthday\":\"1970-01-01\"}", mvcResult.getResponse().getContentAsString());
    }

    @Test
    void testStr() throws Exception {
        stringRedisTemplate.opsForValue().set("test", "test");
        Assertions.assertEquals("test", stringRedisTemplate.opsForValue().get("test"));
    }

    @Test
    void testObj() throws Exception {
        Person person = new Person("aa", "a", "1970-01-01");
        ValueOperations<String, Person> operations = redisTemplate.opsForValue();
        String basePackage = "com.spring.boot.project.model";
        operations.set(basePackage, person);
        //redisTemplate.delete(basePackage);
//        Assertions.assertEquals(true, redisTemplate.hasKey(basePackage));
        Assertions.assertTrue(redisTemplate.hasKey(basePackage));
        Assertions.assertEquals("aa", operations.get(basePackage).getUserName());
    }

    @Test
    void testObjTime() throws Exception {
        Person person = new Person("bb", "b", "1970-01-01");
        ValueOperations<String, Person> operations = redisTemplate.opsForValue();
        String basePackage = "com.spring.boot.project.model";
        operations.set(basePackage, person, 2, TimeUnit.SECONDS);
        //redisTemplate.delete(basePackage);
//        Assertions.assertEquals(true, redisTemplate.hasKey(basePackage));
        Assertions.assertTrue(redisTemplate.hasKey(basePackage));
        Assertions.assertEquals("bb", operations.get(basePackage).getUserName());

        Thread.sleep(2000);
//        Assertions.assertEquals(false, redisTemplate.hasKey(basePackage));
        Assertions.assertFalse(redisTemplate.hasKey(basePackage));
    }
}