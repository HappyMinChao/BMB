package com.vpiaotong.openapi.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.*;
import org.apache.commons.lang.*;
import org.apache.commons.beanutils.*;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JpushHttpUtils
 *
 * @author : minchao.du
 * @description :
 * @date : 2018/4/12
 */

@SuppressWarnings("deprecation")
public class JpushHttpUtils {


    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub

        String[] regIds = {"120c83f76009642715e", "120c83f760094d31afe"};

        JpushHttpUtils jePush = new JpushHttpUtils();

        String result = jePush.push(Arrays.asList(regIds), Arrays.asList("ANDROID"),"EAR Approval", "EAR D20170001 is pending for your approval", "");
        JSONObject resData = JSONObject.fromObject(result);

    }
    

    public static String PLATFORM_ANDROID = "ANDROID";
    public static String PLATFORM_IOS = "IOS";
    public static String PLATFORM_ALL = "ALL";

    private static String PUSH_URL = "https://api.jpush.cn/v3/push";

    private String masterSecret = "be359ec8ff78dc34fafd3e99";	//the secret key from JPush in your register project
    private String appKey = "4e30c3637ede17e2140e7321";			//the app key from JPush in your register project
    private String platform = "all";
    private boolean apnsProdcution = true;
    private int liveTime = 86400;

    public String push(List<String> regIds, List<String> platForms, String title, String content, String extParmJson) {
        JSONObject json = this.generateJson(regIds, platForms, title, content, extParmJson);
        return this.sendPostRequest(json.toString());
    }

    private JSONObject generateJson(List<String> regIds, List<String> platForms, String title, String content, String extParmJson) {
        JSONObject json = new JSONObject();

        JSONArray platform = new JSONArray();
        for (String platForm : platForms)
            platform.add(platForm.toLowerCase());

        JSONObject audience = new JSONObject();
        audience.put("registration_id", regIds);
        JSONObject notification = new JSONObject();

        if(platForms.contains(PLATFORM_ANDROID) || platForms.contains(PLATFORM_ALL)){
            JSONObject android = new JSONObject();
            android.put("title", title);
            android.put("alert", content);
            JSONObject android_extras = new JSONObject();
            android.put("extras", android_extras);
            notification.put("android", android);
        }

        if(platForms.contains(PLATFORM_IOS) || platForms.contains(PLATFORM_ALL)){
            JSONObject ios = new JSONObject();
            ios.put("title", title);
            ios.put("alert", content);
            ios.put("sound", "default");
            JSONObject ios_extras = new JSONObject();
            ios.put("extras", ios_extras);

            notification.put("ios", ios);
        }

        json.put("platform", platform);
        json.put("audience", audience);
        json.put("notification", notification);
        return json;
    }

    private String sendPostRequest(String data) {
        String result = "";
        try {
            Map<String, String> headerMap = new HashMap<>();
            headerMap.put("Authorization",getAuthString());
            result = HttpUtils.sendPost(PUSH_URL, data, headerMap);
                System.out.println("返回结果： "+result);
        }catch(Exception e) {
        }

        return result;
    }

    private String getAuthString() {
        String str =  appKey + ":" + masterSecret;
        byte[] key = str.getBytes();
        Base64 base64Encoder = new Base64();
        String strs = "Basic " + base64Encoder.encodeBase64String(key);
        return strs;
    }

}

