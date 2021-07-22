package com.xbw.spring.boot.framework.json.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import org.springframework.util.Assert;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Type adapter for LocalDateTime.
 *
 * @author xbw
 */
public class LocalDateTimeTypeAdapter extends AbstractTemporalTypeAdapter<LocalDateTime> {

    public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() {
        @SuppressWarnings("unchecked") // we use a runtime check to make sure the 'T's equal
        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            return typeToken.getRawType() == LocalDateTime.class ? (TypeAdapter<T>) new LocalDateTimeTypeAdapter() : null;
        }
    };

    public LocalDateTimeTypeAdapter() {
        this(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public LocalDateTimeTypeAdapter(DateTimeFormatter formatter) {
        super(formatter, LocalDateTime::from);
    }

    @Override
    public LocalDateTime read(JsonReader in) throws IOException {
        Assert.notNull(getFormatter(), "DateTimeFormatter must not be null");
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
        }
        return LocalDateTime.parse(in.nextString(), getFormatter());
    }

}