package com.xbw.spring.boot.framework.xml.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * https://www.baeldung.com/jaxb-unmarshalling-dates
 */
public class DateAdapter extends XmlAdapter<String, Date> {
    private static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final ThreadLocal<DateFormat> THREAD_LOCAL = ThreadLocal.withInitial(() -> new SimpleDateFormat(DEFAULT_PATTERN));

    @Override
    public String marshal(Date v) {
        return THREAD_LOCAL.get().format(v);
    }

    @Override
    public Date unmarshal(String v) throws ParseException {
        return THREAD_LOCAL.get().parse(v);
    }

}