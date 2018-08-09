package com.publicpay.account.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author: yanfei.zhao
 * @Description: 短信配置
 * @Date: Create in 20:26 2017/12/7
 */

@Configuration
@PropertySource(value= "classpath:jpush.properties",ignoreResourceNotFound=true)
@ConfigurationProperties(prefix = "jpush.send")
public class JpushConfig {

    private static final Logger logger = LoggerFactory.getLogger(JpushConfig.class);

    private String masterSecret;
    private String appKey;
    private int maxNum;

    public int getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }

    public String getMasterSecret() {
        return masterSecret;
    }

    public String getAppKey() {
        return appKey;
    }

    @Override
    public String toString() {
        return "JpushConfig{" +
                "masterSecret='" + masterSecret + '\'' +
                ", appKey='" + appKey + '\'' +
                '}';
    }
}
