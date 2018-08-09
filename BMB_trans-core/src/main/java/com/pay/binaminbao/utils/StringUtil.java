package com.pay.binaminbao.utils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class StringUtil{

    private static Logger log;

    @PostConstruct
    private void init(){
        log = LoggerFactory.getLogger(StringUtil.class);
    }

    /**
     * 过滤请求报文中的空字符串或者空字符串
     * @param contentData
     * @return
     */
    public static Map<String, String> filterBlank(Map<String, String> contentData){
        log.info("打印请求报文域 :");
        Map<String, String> submitFromData = new HashMap<String, String>();
        Set<String> keyset = contentData.keySet();

        for(String key:keyset){
            String value = contentData.get(key);
            if (StringUtils.isNotBlank(value)) {
                // 对value值进行去除前后空处理
                submitFromData.put(key, value.trim());
                log.info(key + "-->" + String.valueOf(value));
            }
        }
        return submitFromData;
    }
}
