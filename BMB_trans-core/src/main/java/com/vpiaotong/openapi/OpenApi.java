package com.vpiaotong.openapi;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.vpiaotong.openapi.util.JsonUtil;
import com.vpiaotong.openapi.util.RSAUtil;
import com.vpiaotong.openapi.util.SecurityUtil;
import com.vpiaotong.openapi.util.UUIDUtil;
import org.joda.time.DateTime;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Songdan
 * @date 2017/1/19 10:44
 */
public class OpenApi {

    private final String platformCode;

    private final String prefix;

    private final String password;

    private final String privateKey;

    /**
     *
     * @param password 3DES密钥
     * @param platformCode 平台编码
     * @param prefix 平台前缀
     * @param privateKey RSA私钥
     */
    public OpenApi(String password, String platformCode, String prefix, String privateKey) {
        this.password = password;
        this.platformCode = platformCode;
        this.prefix = prefix;
        this.privateKey = privateKey;
    }

    /**
     * 创建请求字符串
     * 
     * @param content json格式的报文主体
     * @return
     */
    public String buildRequest(String content) {
        return OpenAPIUtil.buildRequestData(platformCode, prefix, content, password, privateKey);
    }

    /**
     * 对响应信息验签和解密
     * 
     * @param responseStr
     * @param ptKey
     * @return
     */
    public String disposeResponse(String responseStr, String ptKey) {
        return OpenAPIUtil.disposeResponse(responseStr, ptKey, password);
    }

    private static class OpenAPIUtil {

        private static final String VERSION = "1.0";

        private static final String FORMAT = "JSON";

        private static final String SIGNTYPE = "RSA";

        private static final String PLATFORMCODE_FIELD = "platformCode";

        private static final String SIGNTYPE_FIELD = "signType";

        private static final String SIGN_FIELD = "sign";

        private static final String FORMAT_FIELD = "format";

        private static final String TIMESTAMP_FIELD = "timestamp";

        private static final String VERSION_FIELD = "version";

        private static final String SERIALNO_FIELD = "serialNo";

        private static final String CONTENT_FIELD = "content";

        /**
         * 构建请求字符串
         * 
         * @param platformCode 平台编码
         * @param prefix 平台前缀
         * @param content 请求主体内容
         * @param password 3DES秘钥
         * @param privateKey RSA 私钥
         * @return
         */
        public static String buildRequestData(String platformCode, String prefix, String content, String password,
                                              String privateKey) {
            Map<String, String> map = new HashMap<String, String>();
            // 报文3DES加密
            String reqContent = SecurityUtil.encrypt3DES(password, content);
            map.put(PLATFORMCODE_FIELD, platformCode);
            map.put(SIGNTYPE_FIELD, SIGNTYPE);
            map.put(FORMAT_FIELD, FORMAT);
            map.put(VERSION_FIELD, VERSION);
            map.put(CONTENT_FIELD, reqContent);
            map.put(TIMESTAMP_FIELD, DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
            map.put(SERIALNO_FIELD, UUIDUtil.getSerialNo(prefix));
            map.put(SIGN_FIELD, RSAUtil.sign(RSAUtil.getSignatureContent(map), privateKey));
            return JsonUtil.toJson(map);
        }

        public static String disposeResponse(String jsonStr, String publicKey, String password) {
            JsonObject jsonObject = new JsonParser().parse(jsonStr).getAsJsonObject();
            Map<String, String> map = JsonUtil.json2Map(jsonStr);
            String sign = map.remove(SIGN_FIELD);
            if (RSAUtil.verify(RSAUtil.getSignatureContent(map), sign, publicKey)) {
                String plainContent = SecurityUtil.decrypt3DES(password, map.get(CONTENT_FIELD));
                jsonObject.add(CONTENT_FIELD, new JsonParser().parse(plainContent));
            } else {
                throw new IllegalStateException("验签失败");
            }
            return jsonObject.toString();
        }

    }

}
