package com.xbw.spring.boot.json;

import com.xbw.spring.boot.framework.json.gson.InstantTypeAdapter;
import com.xbw.spring.boot.framework.json.gson.LocalDateTimeTypeAdapter;
import com.xbw.spring.boot.framework.json.gson.LocalDateTypeAdapter;
import com.xbw.spring.boot.framework.json.gson.LocalTimeTypeAdapter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class GsonTests {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    void instant() throws IOException {
        InstantTypeAdapter typeAdapter = new InstantTypeAdapter();
        Instant instant = Instant.now();
        logger.info("{} -> {}", instant.getClass(), instant);
        Assertions.assertEquals(instant, typeAdapter.fromJson(typeAdapter.toJson(instant)));
    }

    @Test
    void localDate() throws IOException {
        LocalDateTypeAdapter typeAdapter = new LocalDateTypeAdapter();
        LocalDate localDate = LocalDate.now();
        logger.info("{} -> {}", localDate.getClass(), localDate);
        Assertions.assertEquals(localDate, typeAdapter.fromJson(typeAdapter.toJson(localDate)));
    }

    @Test
    void localDateTime() throws IOException {
        LocalDateTimeTypeAdapter typeAdapter = new LocalDateTimeTypeAdapter();
        LocalDateTime localDateTime = LocalDateTime.now();
        logger.info("{} -> {}", localDateTime.getClass(), localDateTime);
        Assertions.assertEquals(localDateTime, typeAdapter.fromJson(typeAdapter.toJson(localDateTime)));
    }

    @Test
    void localTime() throws IOException {
        LocalTimeTypeAdapter typeAdapter = new LocalTimeTypeAdapter();
        LocalTime localTime = LocalTime.now();
        logger.info("{} -> {}", localTime.getClass(), localTime);
        Assertions.assertEquals(localTime, typeAdapter.fromJson(typeAdapter.toJson(localTime)));
    }
}
