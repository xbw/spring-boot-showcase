package com.xbw.spring.boot.framework.json.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Type adapter for LocalTime.
 *
 * @author xbw
 */
public class LocalTimeTypeAdapter extends AbstractTemporalTypeAdapter<LocalTime> {

    public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() {
        @SuppressWarnings("unchecked") // we use a runtime check to make sure the 'T's equal
        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            return typeToken.getRawType() == LocalTime.class ? (TypeAdapter<T>) new LocalTimeTypeAdapter() : null;
        }
    };

    public LocalTimeTypeAdapter() {
        this(DateTimeFormatter.ISO_LOCAL_TIME);
    }

    public LocalTimeTypeAdapter(DateTimeFormatter formatter) {
        super(formatter, LocalTime::from);
    }
}