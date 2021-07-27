package com.xbw.spring.boot.framework.config;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.wrapper.MapWrapper;
import org.apache.ibatis.reflection.wrapper.ObjectWrapper;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.util.Map;

@Configuration
public class MybatisConfig {
    @Autowired
    MybatisProperties mybatisProperties;

    @ConditionalOnProperty(name = "mybatis.configuration.object-wrapper-factory")
    @Bean
    @ConfigurationPropertiesBinding
    public Converter<String, ObjectWrapperFactory> objectWrapperFactoryConverter() {
        // lambda error: does the class parameterize those types?
        Converter<String, ObjectWrapperFactory> objectWrapperFactoryConverter = new Converter<String, ObjectWrapperFactory>() {
            @Override
            public ObjectWrapperFactory convert(String source) {
                try {
                    return (ObjectWrapperFactory) Class.forName(source).newInstance();
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        return objectWrapperFactoryConverter;
    }

    @ConditionalOnMissingBean(name = "objectWrapperFactoryConverter")
    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> configuration.setObjectWrapperFactory(new MybatisMapWrapperFactory());
    }

    @ConditionalOnMissingBean(ConfigurationCustomizer.class)
    @Bean
    @ConfigurationProperties(prefix = "mybatis.configuration")
    public org.apache.ibatis.session.Configuration mybatisConfiguration() {
        org.apache.ibatis.session.Configuration configuration = mybatisProperties.getConfiguration();
        if (configuration == null) {
            configuration = new org.apache.ibatis.session.Configuration();
        }
        configuration.setObjectWrapperFactory(new com.xbw.spring.boot.framework.mybatis.MybatisMapWrapperFactory());
        return configuration;
    }

    static class MybatisMapWrapperFactory implements ObjectWrapperFactory {
        @Override
        public boolean hasWrapperFor(Object object) {
            return object != null && object instanceof Map;
        }

        @SuppressWarnings("unchecked")
        @Override
        public ObjectWrapper getWrapperFor(MetaObject metaObject, Object object) {
            return new MybatisMapWrapper(metaObject, (Map) object);
        }
    }

    static class MybatisMapWrapper extends MapWrapper {
        MybatisMapWrapper(MetaObject metaObject, Map<String, Object> map) {
            super(metaObject, map);
        }

        @Override
        public String findProperty(String name, boolean useCamelCaseMapping) {
            if (useCamelCaseMapping
                    && ((name.charAt(0) >= 'A' && name.charAt(0) <= 'Z')
                    || name.contains("_"))) {
                return underlineToCamelhump(name);
            }
            return name;
        }

        /**
         * 将下划线风格替换为驼峰风格
         *
         * @param inputString
         * @return
         */
        private String underlineToCamelhump(String inputString) {
            StringBuilder sb = new StringBuilder();

            boolean nextUpperCase = false;
            for (int i = 0; i < inputString.length(); i++) {
                char c = inputString.charAt(i);
                if (c == '_') {
                    if (sb.length() > 0) {
                        nextUpperCase = true;
                    }
                } else {
                    if (nextUpperCase) {
                        sb.append(Character.toUpperCase(c));
                        nextUpperCase = false;
                    } else {
                        sb.append(Character.toLowerCase(c));
                    }
                }
            }
            return sb.toString();
        }
    }
}