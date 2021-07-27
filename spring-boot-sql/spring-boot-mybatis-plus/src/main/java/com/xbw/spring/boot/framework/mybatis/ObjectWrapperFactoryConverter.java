package com.xbw.spring.boot.framework.mybatis;

import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @see com.baomidou.mybatisplus.extension.MybatisMapWrapperFactory
 */
@ConditionalOnProperty(name = "mybatis-plus.configuration.object-wrapper-factory")
@Component
@ConfigurationPropertiesBinding
public class ObjectWrapperFactoryConverter implements Converter<String, ObjectWrapperFactory> {
    @Override
    public ObjectWrapperFactory convert(String source) {
        try {
            return (ObjectWrapperFactory) Class.forName(source).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}