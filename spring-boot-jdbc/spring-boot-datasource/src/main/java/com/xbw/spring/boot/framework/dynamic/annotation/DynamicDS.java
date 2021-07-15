package com.xbw.spring.boot.framework.dynamic.annotation;


import com.xbw.spring.boot.framework.dynamic.enums.DynamicDataSourceEnum;

import java.lang.annotation.*;


@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DynamicDS {
    public DynamicDataSourceEnum value() default DynamicDataSourceEnum.DEFAULT;
}
