
package com.vpiaotong.openapi.util;

import javax.crypto.Cipher;
import java.nio.charset.Charset;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @classname: RSAUtil
 * @description: RSA私钥签名，公钥验签工具类
 * @author Jack Zhou
 * @date Jan 11, 2017 10:07:53 PM
 *
 */
public class RSAUtil {

    public static final String SIGN_ALGORITHMS = "SHA1WithRSA";

    /** 默认编码 */
    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    /**
     * RSA签名
     * 
     * @param content 待签名数据
     * @param privateKey 商户私钥
     * @return 签名值
     */
    public static String sign(String content, String privateKey) {
        try {
            System.out.println("签名字符串为： "+content);
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64Util.decode2Byte(privateKey));
            KeyFactory keyf = KeyFactory.getInstance("RSA");
            PrivateKey priKey = keyf.generatePrivate(priPKCS8);

            java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);

            signature.initSign(priKey);
            signature.update(content.getBytes(DEFAULT_CHARSET));

            byte[] signed = signature.sign();

            return Base64Util.encode2String(signed);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * RSA验签名检查
     * 
     * @param content 待签名数据
     * @param sign 签名值
     * @param publicKey 支付宝公钥
     * @return 布尔值
     */
    public static boolean verify(String content, String sign, String publicKey) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] encodedKey = Base64Util.decode2Byte(publicKey);
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));

            java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);

            signature.initVerify(pubKey);
            signature.update(content.getBytes(DEFAULT_CHARSET));

            boolean bverify = signature.verify(Base64Util.decode2Byte(sign));
            return bverify;

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 加密
     * 
     * @param content 密文
     * @param publicKeyStr 商户公钥
     * @return 加密后的字符串
     */
    public static String encrpyt(String content, String publicKeyStr) throws Exception {
        // 加解密类
        Cipher cipher = Cipher.getInstance("RSA");// Cipher.getInstance("RSA/ECB/PKCS1Padding");
        // 加密
        cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKeyStr));
        byte[] enBytes = cipher.doFinal(content.getBytes(DEFAULT_CHARSET));

        return Base64Util.encode2String(enBytes);
    }

    /**
     * 解密
     * 
     * @param content 密文
     * @param privateKeyStr 商户私钥
     * @return 解密后的字符串
     */
    public static String decrypt(String content, String privateKeyStr) throws Exception {
        // 加解密类
        Cipher cipher = Cipher.getInstance("RSA");// Cipher.getInstance("RSA/ECB/PKCS1Padding");

        cipher.init(Cipher.DECRYPT_MODE, getPrivateKey(privateKeyStr));
        byte[] deBytes = cipher.doFinal(Base64Util.decode2Byte(content));
        return new String(deBytes, DEFAULT_CHARSET);
    }

    /**
     * 得到公钥
     * 
     * @param key 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static PublicKey getPublicKey(String key) throws Exception {
        byte[] keyBytes = Base64Util.decode2Byte(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    /**
     * 得到私钥
     * 
     * @param key 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String key) throws Exception {
        byte[] keyBytes = Base64Util.decode2Byte(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    /**
     * 得到密钥字符串（经过base64编码）
     * 
     * @return
     */
    public static String getKeyString(Key key) throws Exception {
        byte[] keyBytes = key.getEncoded();
        return Base64Util.encode2String(keyBytes);
    }

    /**
     * 获得需要签名的数据，按照参数名字母升序的顺序将所有参数用&连接起来。
     * 
     * @param <T>
     * 
     * @param params 待签名参数集
     * @return 排好序的待签名字符串
     */
    public static <T> String getSignatureContent(Map<String, T> params) {
        if (params == null) {
            return null;
        }
        StringBuffer content = new StringBuffer();
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            if (params.get(key) == null) {
                continue;
            }
            String value = String.valueOf(params.get(key));
            content.append((i == 0 ? "" : "&") + key + "=" + value);
        }
        return content.toString();
    }

    /**
     * 获得需要签名的数据，按照参数名字母升序的顺序将所有参数用&连接起来。
     * 
     * @param mapList 待签名参数集
     * @return 排好序的待签名字符串
     */
    public static String getListSignatureContent(List<Map> mapList) {
        if (mapList == null) {
            return null;
        }
        List<String> listStr = new ArrayList<String>();
        for (Map<String, Object> map : mapList) {
            listStr.add(getSignatureContent(map));
        }
        Collections.sort(listStr);
        return listStr.toString();
    }
}
