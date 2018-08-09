package com.pay.binaminbao.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Md5Util
 *
 * @author : minchao.du
 * @description :
 * @date : 2018/1/2
 */
public class Md5Util {

    private static Logger logger = LoggerFactory.getLogger(Md5Util.class);

    //这样是可以的
    /* 
    static {
        logger = LoggerFactory.getLogger(Md5Util.class);
    }*/

    /**
     * @description:获取验签字符串
     * @param sn
     * @return String
     */
    public static String getSign(String sn){
        String md5Str = getMd5Str(sn);
        String retStr = "";
        if(null != md5Str) {
            retStr = md5Str.substring(5,25);
        }
        return retStr;
    }

    /**
     * @description:获取验签字符串
     * @param sn
     * @return String
     */
    public static String get32Sign(String sn){
        return getMd5Str(sn);
    }

    public static String getMd5Str(String source) {
        byte b[] = getMd5Bytes(source);
        int i;
        StringBuffer buf = new StringBuffer("");
        for (int offset = 0; offset < b.length; offset++) {
            i = b[offset];
            if (i < 0)
                i += 256;
            if (i < 16)
                buf.append("0");
            buf.append(Integer.toHexString(i));
        }
        logger.info("getMd5Str source: {}" , source);
        return buf.toString();
    }

    public static byte[] getMd5Bytes(String source) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(source.getBytes("UTF-8"));
            byte b[] = md.digest();
            return b;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public static String getTimeKey(String milisec){
        return milisec.substring(6, 9)+milisec.substring(10,13);
    }

    /**
     * 按照聚合支付规则的加密方法
     * 1、para按照key进行排序，然后组成字符串
     * 2、字符串儿的末尾加上key
     * 3、md5加密
     * 4、小写转大写
     * @author zhou.zhao
     */
    public static String encryption(Map<String,String>para,String key){
        StringBuffer retMsgB = new StringBuffer();
        TreeMap<String,String> tm = new TreeMap<String,String>();
        tm.putAll(para);
        for (Entry<String, String> entry : tm.entrySet()) {
            String mkey = entry.getKey();
            String mvalue = entry.getValue();

            if(!StringUtils.isBlank(mkey)&&!StringUtils.isBlank(mvalue)){
                String temValue = new String(mvalue);
                //如果 mvalue 包含转移 \ 去除
                if(temValue.contains("\\")){
                    temValue = temValue.replace("\\", "");
                }
                retMsgB.append(mkey+"="+temValue+"&");
            }

        }
        String retMsg = retMsgB.toString();
        if(retMsg.charAt(retMsg.length()-1)=='&'){
            retMsg = retMsg.substring(0,retMsg.length()-1);
        }
        retMsg = retMsg+key;
        logger.info("验签字符串为： " + retMsg);
        /** 加密 、转大写 */
        retMsg = getMd5Str(retMsg).toUpperCase();
        logger.info("加密验签字符串为： " + retMsg);
        return retMsg.toString();
    }

    /*public static boolean checkEncryption(JSONObject json,String key,String checksign){
        if(null == checksign) {
            return false;
        }
        Map<String, String> map = new HashMap<String, String>();
        JsonHelper.toMap(jso);
        String sign = encryption(map,key);
        logger.info("计算得到验签sign为："+ sign + " , 上传的sign为 : " + checksign);
        if(checksign.equals(sign)){
            return true;
        }else {
            return false;
        }
    }*/


    public static String getMd5code(String instr)
    {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        if (StringUtils.isEmpty(instr))
        {
            return "";

        }
        byte[] strTemp;
        try
        {
            strTemp = instr.getBytes("UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
            strTemp = instr.getBytes();
        }
        // MD5计算方法
        MessageDigest mdTemp;
        try
        {
            mdTemp = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e)
        {
            return "";
        }
        mdTemp.update(strTemp);
        byte[] md = mdTemp.digest();
        int j = md.length;
        char str[] = new char[j * 2];
        int k = 0;
        for (byte byte0 : md)
        {
            str[k++] = hexDigits[byte0 >>> 4 & 0xf];
            str[k++] = hexDigits[byte0 & 0xf];
        }
        return new String(str);
    }
    
    public static void main(String[]args){
        
        String str = "token=ZPcDQZeXb7b2UfU_E7v4Qg6x8PgEk44T_ASue9U8RyTlHBeZPiu5nQMtEgp6Y2s0gO1AjuVUj9cxOUfiiFLJZA23fe9bd0f5d3703df1a31d20541fd3bd";
               str = "token=ZPcDQZeXb7b2UfU_E7v4Qg6x8PgEk44T_ASue9U8RyTlHBeZPiu5nQMtEgp6Y2s0gO1AjuVUj9cxOUfiiFLJZA&&23fe9bd0f5d3703df1a31d20541fd3bd";
        String sign = getMd5code(str);
        
        System.out.println(sign);
    }

}
