package com.xbw.spring.boot.framework.json.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import org.springframework.util.Assert;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQuery;

/**
 * Type adapter for Temporal implementation.
 *
 * @author xbw
 */
public abstract class AbstractTemporalTypeAdapter<T extends TemporalAccessor> extends TypeAdapter<T> {
    private final DateTimeFormatter formatter;
    private final TemporalQuery<T> temporalQuery;

    public AbstractTemporalTypeAdapter(final DateTimeFormatter formatter, final TemporalQuery<T> temporalQuery) {
        Assert.notNull(formatter, "DateTimeFormatter must not be null");
        Assert.notNull(temporalQuery, "TemporalQuery must not be null");
        this.formatter = formatter;
        this.temporalQuery = temporalQuery;
    }

    @Override
    public void write(JsonWriter out, T value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }
        out.value(formatter.format(value));
    }

    @Override
    public T read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
        }
        return formatter.parse(in.nextString(), temporalQuery);
    }

    public DateTimeFormatter getFormatter() {
        return formatter;
    }
}