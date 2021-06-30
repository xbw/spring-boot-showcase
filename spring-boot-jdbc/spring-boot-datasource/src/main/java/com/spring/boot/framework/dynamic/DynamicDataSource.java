package com.spring.boot.framework.dynamic;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

public class DynamicDataSource extends AbstractRoutingDataSource {

    public final static String DEFAULT_DATASOURCE = "default";
    public final static String PRIMARY_DATASOURCE = "primary";
    public final static String SECONDARY_DATASOURCE = "secondary";
    public final static String TERTIARY_DATASOURCE = "tertiary";
    public final static String QUATERNARY_DATASOURCE = "quaternary";
    public final static String QUINARY_DATASOURCE = "quinary";
    public final static String SENARY_DATASOURCE = "senary";
    public final static String SEPTENARY_DATASOURCE = "septenary";
    public final static String OCTONARY_DATASOURCE = "octonary";
    public final static String NONARY_DATASOURCE = "nonary";
    public final static String DENARY_DATASOURCE = "denary";

    public DynamicDataSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceHolder.getDataSource();
    }
}
