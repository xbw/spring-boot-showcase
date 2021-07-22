package com.xbw.spring.boot.framework.json.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Type adapter for LocalDate.
 *
 * @author xbw
 * @see com.google.gson.internal.bind.DateTypeAdapter
 */
public class LocalDateTypeAdapter extends AbstractTemporalTypeAdapter<LocalDate> {
    public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() {
        @SuppressWarnings("unchecked") // we use a runtime check to make sure the 'T's equal
        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            return typeToken.getRawType() == LocalDate.class ? (TypeAdapter<T>) new LocalDateTypeAdapter() : null;
        }
    };

    public LocalDateTypeAdapter() {
        this(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public LocalDateTypeAdapter(DateTimeFormatter formatter) {
        super(formatter, LocalDate::from);
    }
}