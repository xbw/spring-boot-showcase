package com.xbw.spring.boot.framework.json.gson;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>New applications should prefer {@link TypeAdapter}, whose streaming API
 * is more efficient than this interface's tree API.
 *
 * @author xbw
 * @version 1.0.0
 * @date 2021/7/22
 */
public class LocalDateTimeSerializers implements JsonDeserializer<LocalDateTime>, JsonSerializer<LocalDateTime> {
    private DateTimeFormatter formatter;

    public LocalDateTimeSerializers() {
        this.formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    }

    public LocalDateTimeSerializers(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return LocalDateTime.parse(json.getAsString(), formatter);
    }

    @Override
    public JsonElement serialize(LocalDateTime src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(formatter.format(src));
    }

    public DateTimeFormatter getFormatter() {
        return formatter;
    }

    public void setFormatter(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }
}