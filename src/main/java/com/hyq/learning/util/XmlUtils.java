package com.hyq.learning.util;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;
import java.util.*;

/**
 * @author：huyuanqiang
 * @time: 2019-11-14 16:42
 * @description:
 **/
public class XmlUtils {
    private final static XmlMapper xmlMapper = new XmlMapper();

    /**
     * 字符串转对象
     *
     * @param json      字符串
     * @param valueType 对象类型
     * @param <T>       泛型
     * @return 对象
     */
    public static <T> T entity(String json, Class<T> valueType) {
        try {
            return xmlMapper.readValue(json, valueType);
        } catch (IOException e) {
            //log.warn("json to entity error: {}", e.getMessage());
            throw new RuntimeException("数据有误：" + e.getMessage());
        }
    }

    /**
     * 字符串转List
     *
     * @param json      字符串
     * @param valueType 对象类型
     * @param <T>       泛型
     * @return 对象
     */
    public static <T> List<T> list(String json, Class<T> valueType) {
        if (json == null)
            return new ArrayList<>();
        JavaType javaType = xmlMapper.getTypeFactory().constructParametricType(List.class, valueType);
        try {
            return xmlMapper.readValue(json, javaType);
        } catch (IOException e) {
            //log.warn("json to list error: {}", e.getMessage());
            throw new RuntimeException("数据有误：" + e.getMessage());
        }
    }

    /**
     * 字符串转Set
     *
     * @param json      字符串
     * @param valueType 对象类型
     * @param <T>       泛型
     * @return 对象
     */
    public static <T> Set<T> set(String json, Class<T> valueType) {
        if (json == null)
            return new HashSet<>();
        JavaType javaType = xmlMapper.getTypeFactory().constructParametricType(Set.class, valueType);
        try {
            return xmlMapper.readValue(json, javaType);
        } catch (IOException e) {
            //log.warn("json to set error: {}", e.getMessage());
            throw new RuntimeException("数据有误：" + e.getMessage());
        }
    }

    /**
     * 字符串转Map, key和value为字符串
     *
     * @param json 字符串
     * @return 对象
     */
    public static Map<String, String> map(String json) {
        return map(json, String.class);
    }

    /**
     * 字符串转Map, key为字符串
     *
     * @param json      字符串
     * @param valueType value类型
     * @param <V>       value的泛型
     * @return 对象
     */
    public static <V> Map<String, V> map(String json, Class<V> valueType) {
        return map(json, String.class, valueType);
    }

    /**
     * 字符串转Map
     *
     * @param json      字符串
     * @param keyType   key类型
     * @param valueType value类型
     * @param <K>       key的泛型
     * @param <V>       value的泛型
     * @return 对象
     */
    public static <K, V> Map<K, V> map(String json, Class<K> keyType, Class<V> valueType) {
        if (json == null)
            return new HashMap<>();
        JavaType javaType = xmlMapper.getTypeFactory().constructParametricType(HashMap.class, keyType, valueType);
        try {
            return xmlMapper.readValue(json, javaType);
        } catch (IOException e) {
            //log.warn("json to map error: {}", e.getMessage());
            throw new RuntimeException("数据有误：" + e.getMessage());
        }
    }

    /**
     * 任意对象转Json字符串
     *
     * @param object 对象
     * @return Json字符串
     */
    public static String string(Object object) {
        try {
            return xmlMapper.writeValueAsString(object);
        } catch (IOException e) {
            //log.warn("object to string error: {}", e.getMessage());
            throw new RuntimeException("数据有误：" + e.getMessage());
        }
    }
}

