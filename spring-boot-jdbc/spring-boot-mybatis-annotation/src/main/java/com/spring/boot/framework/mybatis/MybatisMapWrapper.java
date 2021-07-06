/*
 * Copyright (c) 2011-2021, baomidou (jobob@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.spring.boot.framework.mybatis;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.wrapper.MapWrapper;

import java.util.Map;

/**
 * 返回Map结果集，下划线转驼峰
 *
 * @author yuxiaobin
 * @see com.baomidou.mybatisplus.extension.handlers.MybatisMapWrapper
 * @since 2017/12/19
 */
public class MybatisMapWrapper extends MapWrapper {

    public MybatisMapWrapper(MetaObject metaObject, Map<String, Object> map) {
        super(metaObject, map);
    }

    @Override
    public String findProperty(String name, boolean useCamelCaseMapping) {
        if (useCamelCaseMapping && !StringUtils.isCamel(name)) {
            return StringUtils.underlineToCamel(name);
        }
        return name;
    }


}

/**
 * String 工具类
 *
 * @author D.Yang, hcl
 * @author hcl
 * @since 2016-08-18
 */
class StringUtils {

    /**
     * 判断字符串是不是驼峰命名
     *
     * <li> 包含 '_' 不算 </li>
     * <li> 首字母大写的不算 </li>
     *
     * @param str 字符串
     * @return 结果
     */
    public static boolean isCamel(String str) {
        return Character.isLowerCase(str.charAt(0)) && !str.contains("_");
    }

    /**
     * 判断字符串中是否全是空白字符
     *
     * @param cs 需要判断的字符串
     * @return 如果字符串序列是 null 或者全是空白，返回 true
     */
    public static boolean isBlank(CharSequence cs) {
        if (cs != null) {
            int length = cs.length();
            for (int i = 0; i < length; i++) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 字符串下划线转驼峰格式
     *
     * @param param 需要转换的字符串
     * @return 转换好的字符串
     */
    public static String underlineToCamel(String param) {
        if (isBlank(param)) {
            return "";
        }
        String temp = param.toLowerCase();
        int len = temp.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = temp.charAt(i);
            if (c == '_') {
                if (++i < len) {
                    sb.append(Character.toUpperCase(temp.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

}