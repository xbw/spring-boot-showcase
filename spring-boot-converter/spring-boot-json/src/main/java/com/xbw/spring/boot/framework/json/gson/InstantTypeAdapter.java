package com.xbw.spring.boot.framework.json.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

/**
 * Type adapter for Instant.
 *
 * @author xbw
 */
public class InstantTypeAdapter extends AbstractTemporalTypeAdapter<Instant> {
    public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() {
        @SuppressWarnings("unchecked") // we use a runtime check to make sure the 'T's equal
        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            return typeToken.getRawType() == Instant.class ? (TypeAdapter<T>) new InstantTypeAdapter() : null;
        }
    };

    public InstantTypeAdapter() {
        this(DateTimeFormatter.ISO_INSTANT);
    }

    public InstantTypeAdapter(DateTimeFormatter formatter) {
        super(formatter, Instant::from);
    }
}