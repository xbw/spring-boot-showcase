package com.xbw.spring.boot.project.memcached;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xbw
 */
@SuppressWarnings("unchecked")
@Deprecated
public class MemcachedUtils {
    private static final Logger logger = LoggerFactory.getLogger(MemcachedUtils.class);
    private static LocalCacheManager localCacheManager = LocalCacheManager.getInstance();
    private static StackTraceElement[] stackTraceElements = new Exception().getStackTrace();


    // ==========================基础转换================================

    /**
     * 根据指定下标及字段，获取缓存List数组集合目标字段的值.
     * <p>
     * 要求缓存对象中必须含有相应字段名
     *
     * @param list      源数组
     * @param index     源数组下标
     * @param srcName   指定字段名
     * @param decName   目标字段名
     * @param cacheName 缓存名
     * @return List<T [ ]>
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static <T> List<T[]> convertFromCache(List<T[]> list, Integer index, String srcName, String decName, String cacheName)
            throws IllegalArgumentException, IllegalAccessException {
        List<T[]> decList = new ArrayList<>();
        for (T[] src : list) {
            T[] dec = convertFromCache(src, index, srcName, decName, cacheName);
            decList.add(dec);
        }
        return decList;
    }

    /**
     * 根据指定下标及字段，获取缓存数据目标字段的值.
     * <p>
     * 要求缓存对象中必须含有相应字段名
     *
     * @param src       源数组
     * @param index     源数组下标
     * @param srcName   指定字段名
     * @param decName   目标字段名
     * @param cacheName 缓存名
     * @return T[]
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static <T> T[] convertFromCache(T[] src, Integer index, String srcName, String decName, String cacheName)
            throws IllegalArgumentException, IllegalAccessException {
        List<T> list = localCacheManager.getLocalCache(cacheName);
        int flag = 0;// 异常情况判断

        if (list == null) {// 无缓存数据
            logger.debug("无cacheKey为：{} 的缓存数据!", cacheName);
            return src;
        }
        if (src == null || src.length <= index) {
            logger.debug("源数组不允许为null,且下标不能越界！");
            return src;
        }

        Object srcNameValue = src[index];// 指定字段的值
        Object decNameValue = null;// 目标字段的值
        int srcNameIndex = 0;// 指定字段位置
        int decNameIndex = 0;// 目标字段位置

        Field[] fields = list.get(0).getClass().getDeclaredFields();
        Field.setAccessible(fields, true);
        for (int i = 0; i < fields.length && flag != 2; i++) {
            if (srcName.equals(fields[i].getName())) {
                srcNameIndex = i;
                flag++;
            } else if (decName.equals(fields[i].getName())) {
                decNameIndex = i;
                flag++;
            }
        }

        if (flag != 2) {// 目标字段名参数有错误！
            logger.debug("{} 字段名参数有错误！", src.getClass().getName());
            return src;
        }

        flag = 0;

        for (int i = 0; i < list.size(); i++) {

            fields = list.get(i).getClass().getDeclaredFields();
            Field.setAccessible(fields, true);
            if (fields[srcNameIndex].get(list.get(i)).toString()
                    .equals(srcNameValue)) {
                decNameValue = fields[decNameIndex].get(list.get(i));
                flag++;
                break;
            }
        }

        if (flag == 0) {// 缓存中无匹配对象数据！
            decNameValue = "";
            logger.debug("{} 无 {}.{}={} 的匹配数据！", stackTraceElements[2], src.getClass().getSimpleName(), srcName, srcNameValue);
//			return src;
        }

        List<T> tempList = new ArrayList<T>();
        for (int i = 0; i < src.length; i++) {
            tempList.add(src[i]);
        }
        tempList.add((T) decNameValue);
        T[] dec = (T[]) tempList.toArray();
        return dec;
    }

    /**
     * 对List对象集合，根据指定字段获取目标字段的值.
     * <p>
     * 要求源对象中必须含有相应字段名
     *
     * @param list      源对象
     * @param srcName   指定字段名
     * @param decName   目标字段名
     * @param cacheName 缓存名
     * @return List<T>
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */

    public static <T> List<T> convertFromCache(List<T> list, String srcName, String decName, String cacheName)
            throws IllegalArgumentException, IllegalAccessException {
        for (T src : list) {
            src = convertFromCache(src, srcName, decName, cacheName);
        }
        return list;
    }

    /**
     * 对单一对象，根据指定字段获取目标字段的值.
     * <p>
     * 要求源对象中必须含有相应字段名
     *
     * @param src       源对象
     * @param srcName   指定字段名
     * @param decName   目标字段名
     * @param cacheName 缓存名
     * @return T
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static <T> T convertFromCache(T src, String srcName, String decName, String cacheName)
            throws IllegalArgumentException, IllegalAccessException {
        List<T> list = localCacheManager.getLocalCache(cacheName);
        int flag = 0;// 异常情况判断

        if (list == null) {// 无缓存数据
            logger.debug("无cacheKey为：{} 的缓存数据!", cacheName);
            return src;
        }

        Object srcNameValue = null;// 源字段的值
        int srcNameIndex = 0;// 源字段位置
        int decNameIndex = 0;// 目标字段位置

        Field[] srcFields = src.getClass().getDeclaredFields();
        Field.setAccessible(srcFields, true);
        for (int i = 0; i < srcFields.length && flag != 2; i++) {
            if (srcName.equals(srcFields[i].getName())) {// 匹配指定字段名
                srcNameValue = srcFields[i].get(src);
                flag++;
            } else if (decName.equals(srcFields[i].getName())) {
                srcNameIndex = i;
                flag++;
            }
        }

        if (flag != 2) {// 对象字段名参数有错误！
            logger.debug(src.getClass().getName() + "对象字段名参数有错误！");
            return src;
        }

        flag = 0;

        for (int ii = 0; ii < list.size(); ii++) {

            Field[] decFields = list.get(ii).getClass().getDeclaredFields();
            Field.setAccessible(decFields, true);

            for (int i = 0; i < decFields.length; i++) {
                if (decName.equals(decFields[i].getName())) {
                    decNameIndex = i;
                    break;
                }
            }
            for (int i = 0; i < decFields.length; i++) {
                if (srcName.equals(decFields[i].getName())
                        && decFields[i].get(list.get(ii)).equals(srcNameValue)) {
                    srcFields[srcNameIndex].set(src,
                            decFields[decNameIndex].get(list.get(ii)));
                    flag++;
                    break;
                }
            }
            if (flag > 0) {
                break;
            }
        }

        if (flag == 0) {// 缓存中无匹配对象数据！
            logger.debug("{} 无 {}.{}={} 的匹配数据！", stackTraceElements[2], src.getClass().getSimpleName(), srcName, srcNameValue);
            return src;
        }

        return src;
    }

}