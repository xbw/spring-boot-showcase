package com.xbw.spring.boot.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import jakarta.json.bind.Jsonb;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


@SpringBootTest
public class JsonTests {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    Jsonb jsonb;
    @Autowired
    Gson gson;
    Map<Integer, Integer> map;

    @BeforeEach
    void beforeAll() {
        map = new HashMap<>();
        for (int i = 0; i < new Random().nextInt(10); i++) {
            map.put(i, i * 2);
        }
    }

    @Test
    void jackson() throws JsonProcessingException {
        logger.info("Map -> {}", objectMapper.writeValueAsString(map));
        logger.info("Date -> {}", objectMapper.writeValueAsString(new Date()));
    }

    @Test
    void gson() {
        logger.info("Map -> {}", gson.toJson(map));
        logger.info("Date -> {}", gson.toJson(new Date()));
    }

    @Test
    void map() {
        logger.info("Map -> {}", jsonb.toJson(map));
        logger.info("Date -> {}", jsonb.toJson(new Date()));
    }

    @Test
    void fastjson() {
        logger.info("Map -> {}", JSON.toJSONString(map));
        logger.info("Date -> {}", JSON.toJSONString(new Date()));
        logger.info("Date -> {}", JSON.toJSONString(new Date(), SerializerFeature.WriteDateUseDateFormat));
    }

    @Test
    void json() {
        org.json.JSONObject jsonObject = new org.json.JSONObject(map);
        logger.info("Map -> {}", jsonObject);
        jsonObject.put("date", new Date());
        logger.info("Map -> {}", jsonObject);
    }
}
