package com.vpiaotong.openapi;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pay.binaminbao.utils.FastJsonUtil;
import com.pay.binaminbao.utils.JsonHelper;
import com.vpiaotong.openapi.util.Base64Util;
import com.vpiaotong.openapi.util.JsonUtil;

import java.util.Map;

/**
 * TestJson
 *
 * @author : minchao.du
 * @description :
 * @date : 2018/1/31
 */
public class TestJson {

    public static void main(String[] args) throws Exception {

        String jsonStr = "{\"format\":\"JSON\",\"sign\":\"Zm13SRz+jHzGkQsTB0t+nodz3mJJjqchfbwpUWRw1f8wYS7aJ29Btb/XGsrsQdai85gSTgkqrkezrNpsvjbkDUpgNyzOZpHgu6V1jF2zMfzKdIgfHr5bdJB/mfZoopvg9tEQ+BSw6WUnyfjyC5bpGvHM3hu7sE5nYH9VOJRkLxc\\u003d\",\"signType\":\"RSA\",\"version\":\"1.0\",\"platformCode\":\"11111111\",\"content\":\"9Kval5TJJQUKWAvqqwMVPinrBAbQJUKU0BbykxZK1WQghouTV/GBnbLL3KZ0/iXCfTXcM2S1AFAkG5Kn3qXqeVMkQt/rzOa/VBga6DJgXgZ5K6imMRDGlQHYFDCcwB6Cysf9TG2I+vv7WOK1J/H5zD1jetMXZnLVfvKMjqouFooY8slo3tbeZXyDUeZgH5JTqGKqWbCzuCwghouTV/GBnaOHtgK5iI1SA4eJsWsMycOatphBCZ0g1iL0MwiSRYkFtfb6RYmYqehvLNQX+GQmuDnUY3JOO4gy03ppb87kOuu1mgGRRMeXrIZcXePwrLvBHyzsmr2C7E6cfwNLn52gKw\\u003d\\u003d\",\"timestamp\":\"2018-01-31 14:43:17\",\"serialNo\":\"DEMO20180131144317MfY8jFP1\"}";

        
        
        String s = stringToUnicode(jsonStr);
        jsonStr = unicodeToString(s);

        System.out.println(jsonStr);
        /*
        byte[] bytes = jsonStr.getBytes("utf-8");

        Base64Util.encode2String(bytes);*/
        
        JsonObject jsonObject = new JsonParser().parse(jsonStr).getAsJsonObject();
        JsonElement sign = jsonObject.get("sign");
        System.out.println(sign.toString());
        //String sign = (String)map.get("sign");
        String sign1 = stringToUnicode(sign.toString());
        System.out.println(sign1);
        String sign2 = unicodeToString(sign1.toString());
        System.out.println(sign2);


    }

    //字符串转换unicode
    public static String stringToUnicode(String string) {
        StringBuffer unicode = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);  // 取出每一个字符
            unicode.append("\\u" +Integer.toHexString(c));// 转换为unicode
        }
        return unicode.toString();
    }

    //unicode 转字符串
    public static String unicodeToString(String unicode) {
        StringBuffer string = new StringBuffer();
        String[] hex = unicode.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {
            int data = Integer.parseInt(hex[i], 16);// 转换出每一个代码点
            string.append((char) data);// 追加成string
        }
        return string.toString();
    }
}
