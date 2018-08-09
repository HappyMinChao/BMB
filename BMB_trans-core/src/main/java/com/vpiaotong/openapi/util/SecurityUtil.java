package com.vpiaotong.openapi.util;

import com.vpiaotong.openapi.exception.ExceptionHandler;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;

/**
 * @classname: SecurityUtil
 * @description: 安全加密解密工具类（3DES）
 * @author Jack Zhou
 * @date Jan 11, 2017 10:05:57 PM
 *
 */
public class SecurityUtil {

    /**
     * 可用 DES,DESede,Blowfish
     */
    private static final String ALGORITHM_3DES = "DESede";

    /** 默认编码 */
    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    /**
     * 3DES加密
     * 
     * @param encryptPassword 密钥长度为24位 密钥
     * @param encryptByte byte
     * @return
     */
    public static byte[] encrypt3DES(String encryptPassword, byte[] encryptByte) {
        try {
            Cipher cipher = init3DES(encryptPassword, Cipher.ENCRYPT_MODE);
            byte[] doFinal = cipher.doFinal(encryptByte);
            return doFinal;
        }
        catch (Exception e) {
            ExceptionHandler.castException(e);
        }
        return null;
    }

    /**
     * 3DES加密
     * 
     * @param encryptPassword 密钥长度为24位 密钥
     * @param encryptStr String
     * @return
     */
    public static String encrypt3DES(String encryptPassword, String encryptStr) {
        try {
            Cipher cipher = init3DES(encryptPassword, Cipher.ENCRYPT_MODE);
            byte[] enBytes = cipher.doFinal(encryptStr.getBytes(DEFAULT_CHARSET));
            return Base64Util.encode2String(enBytes);
        }
        catch (Exception e) {
            ExceptionHandler.castException(e);
        }
        return null;
    }

    /**
     * 3DES解密
     * 
     * @param decryptPassword 密钥长度为24位 密钥
     * @param decryptByte byte
     * @return
     */
    public static byte[] decrypt3DES(String decryptPassword, byte[] decryptByte) {
        try {
            Cipher cipher = init3DES(decryptPassword, Cipher.DECRYPT_MODE);
            byte[] doFinal = cipher.doFinal(decryptByte);
            return doFinal;
        }
        catch (Exception e) {
            ExceptionHandler.castException(e);
        }
        return null;

    }

    /**
     * 3DES解密
     * 
     * @param decryptPassword 密钥长度为24位 密钥
     * @param decryptString byte
     * @return
     */
    public static String decrypt3DES(String decryptPassword, String decryptString) {
        try {
            Cipher cipher = init3DES(decryptPassword, Cipher.DECRYPT_MODE);
            byte[] deBytes = cipher.doFinal(Base64Util.decode2Byte(decryptString));
            return new String(deBytes, DEFAULT_CHARSET);
        }
        catch (Exception e) {
            ExceptionHandler.castException(e);
        }
        return null;

    }

    /**
     * 3DES初始化
     * 
     * @param decryptPassword 秘钥
     * @param cipherMode 加密/解密
     * @return Cipher
     * @throws Exception 异常
     */
    private static Cipher init3DES(String decryptPassword, int cipherMode) throws Exception {
        SecretKey deskey = new SecretKeySpec(decryptPassword.getBytes(), ALGORITHM_3DES);
        Cipher cipher = Cipher.getInstance(ALGORITHM_3DES);
        cipher.init(cipherMode, deskey);
        return cipher;
    }
}
