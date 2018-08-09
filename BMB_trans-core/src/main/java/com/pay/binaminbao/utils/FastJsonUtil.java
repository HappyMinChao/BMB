package com.pay.binaminbao.utils;


import java.lang.reflect.Type;
import java.util.*;

import com.alibaba.fastjson.TypeReference;
import org.apache.commons.collections.KeyValue;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

/**
 * @describe : json 工具类
 * @author : minchao.du
 */
public class FastJsonUtil {

    private static final SerializeConfig config;

    static {
        // 可以在次配置带格式化的参数， 如日期
        config = new SerializeConfig();
        config.put(Date.class, new SimpleDateFormatSerializer("yyyyMMddhhmmss"));
        //config.put(java.util.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式  
        config.put(java.sql.Date.class, new SimpleDateFormatSerializer("yyyyMMddhhmmss")); // 使用和json-lib兼容的日期输出格式  
    }

    private static final SerializerFeature[] features = {SerializerFeature.WriteMapNullValue, // 输出空置字段  
            SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，输出为[]，而不是null  
            SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null，输出为0，而不是null  
            SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null  
            SerializerFeature.WriteNullStringAsEmpty // 字符类型字段如果为null，输出为""，而不是null  
    };


    public static String toJSONStringFeatures(Object object) {
        return JSON.toJSONString(object, config, features);
    }

    /*
     * 支持 bean， 数组， list
     */
    public static String toJson(Object object) {
        return JSON.toJSONString(object, config);
    }

    public static Object toBean(String jsonStr) {
        return JSON.parse(jsonStr);
    }

    public static <T> T toBean(String jsonStr, Class<T> clazz) {
        return JSON.parseObject(jsonStr, clazz);
    }

    public  static JSONObject toJsonObject(String json){
        return JSON.parseObject(json);
    }
    // 转换为数组  
    public static <T> Object[] toArray(String jsonStr) {
        return fromJSONToArray(jsonStr, null);
    }

    // 转换为数组  字符串必须以 [ 开始
    public static <T> Object[] fromJSONToArray(String text, Class<T> clazz) {
        return JSON.parseArray(text, clazz).toArray();
    }

    // 转换为List  
    public static <T> List<T> toList(String jsonStr, Class<T> clazz) {
        Type type = new TypeReference<List<T>>() {}.getType();
        return JSON.parseObject(jsonStr, type);
    }

    

    /**
     * json字符串转化为map  
     * @param json
     * @return
     */
    public static Map toMap(String json) {
        return JSONObject.parseObject(json);
    }

    /**
     * 将map转化为string  
     * @param map
     * @return
     */
    public static String toJSON(Map map) {
        return JSONObject.toJSONString(map);
    }
    
}  
