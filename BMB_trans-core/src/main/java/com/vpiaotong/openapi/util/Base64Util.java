package com.vpiaotong.openapi.util;

import org.apache.commons.codec.binary.Base64;

import java.nio.charset.Charset;

/**
 * @classname: Base64Util
 * @description: Base64工具类
 * @author Jack Zhou
 * @date Jan 11, 2017 10:07:26 PM
 *
 */
public class Base64Util {

    /** 默认编码 */
    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    /**
     * base64转码
     * 
     * @param bytes byte[]
     * @return base64的字节流
     */
    public static byte[] encode(byte[] bytes) {
        return Base64.encodeBase64(bytes);
    }

    /**
     * base64转码
     * 
     * @param bytes byte[]
     * @return 字符串
     */
    public static String encode2String(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }

    /**
     * 字符串默认按UTF-8格式进行base64转码
     * 
     * @param targetString 字符串
     * @return base64转码后的字符串
     */
    public static String encode2String(String targetString) {
        byte[] bytes = targetString.getBytes(DEFAULT_CHARSET);
        return Base64.encodeBase64String(bytes);
    }

    /**
     * 字符串默认按UTF-8格式进行base64转码
     * 
     * @param targetString 字符串
     * @return base64的字节流
     */
    public static byte[] encode2Byte(String targetString) {
        byte[] bytes = targetString.getBytes(DEFAULT_CHARSET);
        return Base64.encodeBase64(bytes);
    }

    /**
     * base64解码
     * 
     * @param bytes byte[]
     * @return base64的字节流
     */
    public static byte[] decode(byte[] bytes) {
        return Base64.decodeBase64(bytes);
    }

    /**
     * 字符串默认按UTF-8格式进行base64解码
     * 
     * @param targetString 字符串
     * @return base64解码后的字符串
     */
    public static String decode(String targetString) {
        return new String(Base64.decodeBase64(targetString));
    }

    /**
     * base64解码
     * 
     * @param targetString byte[]
     * @return base64的字节流
     */
    public static byte[] decode2Byte(String targetString) {
        byte[] bytes = targetString.getBytes(DEFAULT_CHARSET);
        return Base64.decodeBase64(bytes);
    }

    /**
     * 字符串按指定编码格式进行base64解码
     * 
     * @param targetString 字符串
     * @return base64解码后的字符串
     */
    public static String decode2String(String targetString) {
        byte[] bytes = targetString.getBytes(DEFAULT_CHARSET);
        return new String(decode(bytes));
    }

}
