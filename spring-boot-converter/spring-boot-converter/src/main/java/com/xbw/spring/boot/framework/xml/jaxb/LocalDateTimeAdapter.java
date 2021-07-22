package com.xbw.spring.boot.framework.xml.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {
    private static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final ThreadLocal<DateTimeFormatter> THREAD_LOCAL = ThreadLocal.withInitial(() -> DateTimeFormatter.ofPattern(DEFAULT_PATTERN));

    @Override
    public String marshal(LocalDateTime v) {
        return THREAD_LOCAL.get().format(v);
    }

    @Override
    public LocalDateTime unmarshal(String v) {
        return LocalDateTime.parse(v, THREAD_LOCAL.get());
    }

}