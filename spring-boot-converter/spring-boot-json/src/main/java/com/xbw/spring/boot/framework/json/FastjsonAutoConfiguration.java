package com.xbw.spring.boot.framework.json;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.JsonbHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Choose one of the following four methods
 */
@ConditionalOnProperty(name = "spring.mvc.converters.preferred-json-mapper", havingValue = "fastjson")
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(FastJsonConfig.class)
public class FastjsonAutoConfiguration implements WebMvcConfigurer {

    /**
     * https://github.com/alibaba/fastjson/wiki/%E5%9C%A8-Spring-%E4%B8%AD%E9%9B%86%E6%88%90-Fastjson
     *
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        customize(converters);
    }

    /**
     * after configureMessageConverters(List<HttpMessageConverter<?>> converters)
     *
     * @param converters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        customize(converters);
    }

    private void customize(List<HttpMessageConverter<?>> converters) {
        Iterator<HttpMessageConverter<?>> iterator = converters.iterator();
        while (iterator.hasNext()) {
            HttpMessageConverter converter = iterator.next();
            if (converter instanceof MappingJackson2HttpMessageConverter
                    | converter instanceof GsonHttpMessageConverter
                    | converter instanceof JsonbHttpMessageConverter
                    | converter instanceof com.xbw.spring.boot.framework.json.converter.JsonbHttpMessageConverter) {
                iterator.remove();
            }
        }
//        converters.add(0, getConverter());
    }

    @Bean
    public FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {
        return getConverter();
    }

    /**
     * Override {@link org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration#messageConverters }
     *
     * @return
     */
    @ConditionalOnMissingBean(FastJsonHttpMessageConverter.class)
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        return new HttpMessageConverters(getConverter());
    }

    private FastJsonHttpMessageConverter getConverter() {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.PrettyFormat
        );
        converter.setFastJsonConfig(fastJsonConfig);

        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON);
        converter.setSupportedMediaTypes(fastMediaTypes);

        return converter;
    }
}
