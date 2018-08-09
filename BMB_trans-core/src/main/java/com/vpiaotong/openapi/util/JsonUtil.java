package com.vpiaotong.openapi.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.Map;

/**
 * Json相关工具类
 * @author Songdan
 * @date 2017/1/19 10:49
 */
public class JsonUtil {

    private static final Gson GSON = new Gson();

    /**
     * json格式的字符串转换为Map
     *
     * @param jsonStr json字符串
     * @return
     */
    public static Map<String, String> json2Map(String jsonStr) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        return gson.fromJson(jsonStr, new TypeToken<Map<String, String>>() {

        }.getType());
    }

    /**
     * 把一个对象转换为Json字符串
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        return GSON.toJson(obj);
    }

}
