package com.vpiaotong.openapi.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import org.apache.commons.collections.KeyValue;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FastJsonUtils
 *
 * @author : minchao.du
 * @description :
 * @date : 2018/2/2
 */
public class FastJsonUtils {

    private static final SerializeConfig config;

    static {
        // 可以在次配置带格式化的参数， 如日期
        config = new SerializeConfig();
        config.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd hh:mm:ss"));
        //config.put(java.util.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式  
        config.put(java.sql.Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd hh:mm:ss")); // 使用和json-lib兼容的日期输出格式  
    }

    private static final SerializerFeature[] features = {SerializerFeature.WriteMapNullValue, // 输出空置字段  
            SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，输出为[]，而不是null  
            SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null，输出为0，而不是null  
            SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null  
            SerializerFeature.WriteNullStringAsEmpty // 字符类型字段如果为null，输出为""，而不是null  
    };


    public static String fromObjectToJSONFeatures(Object object) {
        return JSON.toJSONString(object, config, features);
    }

    public static String toJSON(Object object) {
        return JSON.toJSONString(object, config);
    }

    public static Object toBean(String text) {
        return JSON.parse(text);
    }

    public static <T> T toBean(String text, Class<T> clazz) {
        return JSON.parseObject(text, clazz);
    }

    // 转换为数组  
    public static <T> Object[] toArray(String text) {
        return toArray(text, null);
    }

    // 转换为数组  字符串必须以 [ 开始
    public static <T> Object[] toArray(String text, Class<T> clazz) {
        return JSON.parseArray(text, clazz).toArray();
    }

    // 转换为List  
    public static <T> List<T> toList(String text, Class<T> clazz) {
        return JSON.parseArray(text, clazz);
    }

    /**
     * 将javabean转化为序列化的json字符串  
     * @param keyvalue
     * @return
     */
    public static Object beanToJson(KeyValue keyvalue) {
        String textJson = JSON.toJSONString(keyvalue);
        Object objectJson  = JSON.parse(textJson);
        return objectJson;
    }

    /**
     * 将string转化为序列化的json字符串  
     * @param text
     * @return
     */
    public static Object textToJson(String text) {
        Object objectJson  = JSON.parse(text);
        return objectJson;
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


    public static HashMap<String,Map<String, String>> toNestMap(String jsonStr) {
        HashMap<String,Map<String, String>> nameMap = JSON.parseObject(jsonStr,new TypeReference<HashMap<String,Map<String, String>>>() {});
        return nameMap;
    }




}
