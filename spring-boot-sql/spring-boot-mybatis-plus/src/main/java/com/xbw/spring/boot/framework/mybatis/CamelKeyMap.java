package com.xbw.spring.boot.framework.mybatis;

import java.util.HashMap;


/**
 * 对于返回map类型，将key值转为小驼峰格式
 *
 * @author jczhou
 */
public class CamelKeyMap extends HashMap<Object, Object> {

    /**
     * 将字符串转为小驼峰
     *
     * @param inputString
     * @return
     */
    public static String underlineToCamelhump(String inputString) {
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

    @Override
    public Object put(Object key, Object value) {
        key = underlineToCamelhump(String.valueOf(key));
        return super.put(key, value);
    }
}