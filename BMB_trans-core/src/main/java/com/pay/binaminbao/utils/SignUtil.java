package com.pay.binaminbao.utils;


import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
/**
 * SignUtil
 *
 * @author : minchao.du
 * @description :
 * @date : 2018/1/2
 */
public class SignUtil {


    private static final Logger log = LoggerFactory.getLogger(SignUtil.class);

    /**
     * @param paramMap  计算签名的参数map
     * @param signKey 计算签名密钥的key
     * @param sign  上传的签名
     * @return
     */

    public static boolean cheakSign(Map<String, String> paramMap, String signKey, String sign){
        log.info("传入的map参数为：{}, sign ：{} " , JsonHelper.toJsonStr(paramMap), sign);

        String signKeyValue = paramMap.get(signKey);
        if (StringUtils.isBlank(signKeyValue) || StringUtils.isBlank(sign)){
            return false;
        }

        String signDes = getSign(paramMap, signKey);
        log.info("验证签名中计算出来的签名字符为： {} ,上传的签名为： {} " , signDes, sign);
        Boolean retCheck = signDes.equals(sign) ? true : false;

        return retCheck;
    }

    public static String getSign(Map<String, String> paramMap,String signKey ){

        //获取签名字符串
        String signString = getSignString(paramMap, null, "&");
        log.info("签名前的字符串为： {}" , signString);
        String signKeyValue = paramMap.get(signKey);

        // 计算签名密钥
        String secretKey = Md5Util.getSign("Ipos_facilitatorService"+ signKeyValue);
        log.debug("用签名字符串 {} , 获取到的密钥为 {} ." , signKeyValue, secretKey);
        // 计算签名字符窜
        String signDes = Md5Util.getSign(signString + secretKey).toUpperCase();

        return signDes;
    }

    public static String getIWaterSign(Map<String, String> paramMap,String subfixStr ){

        //获取签名字符串
        String signString = getSignString(paramMap, null, "&&");
        signString = signString + "&&" + subfixStr;
        log.info("签名前的字符串为： {}" , signString);

        // 计算签名密钥
        String sign = Md5Util.getMd5code(signString).toLowerCase();
        log.debug("获取到的签名为: {}" , sign);
        
        return sign;
    }
    /**
     * 拼接代签名的支付串
     * @param paramsMap
     * @param excludeParamsMap
     * @return
     */
    private static String getSignString(Map<String, String> paramsMap,Map<String, String> excludeParamsMap, String splitStr){

        if (paramsMap == null){
            return "";
        }

        // 按参数名排序
        ArrayList<String> paramNames = new ArrayList<>(paramsMap.keySet());
        Collections.sort(paramNames);

        StringBuilder signSource = new StringBuilder();
        Iterator<String> iterator = paramNames.iterator();
        while (iterator.hasNext()) {
            String paramName = iterator.next();

            if (excludeParamsMap != null && excludeParamsMap.containsKey(paramName)) {continue;}
            if (StringUtils.isNotBlank(paramsMap.get(paramName))) {
                signSource.append(paramName).append("=").append(paramsMap.get(paramName)+"");
                if (iterator.hasNext()) {
                    signSource.append(splitStr);
                }
            }
        }

        return signSource.toString();
    }
}
