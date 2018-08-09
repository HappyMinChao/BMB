package com.publicpay.account.jpush;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

import com.publicpay.account.config.JpushConfig;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 向极光推送消息的帮助类
 * @see: JpushUtil 此处填写需要参考的类
 * @version 2016年2月24日 上午11:35:16
 * @author sheng.ren
 */
public class JpushUtil {
    private static final Logger logger = LoggerFactory.getLogger(JpushUtil.class);

    private JpushConfig jpushConfig;
    private static JpushConfig jpushConfigStat;

    @PostConstruct
    private void init(){
        jpushConfigStat = jpushConfig;
    }

    /**
     * @Description 构建推送对象
     * @param registrationIds 要推送的设备注册ID的列表
     * @param alert 推送的内容
     * @param extras 推送的额外的参数
     * @param title 自定义标题，android手机专用
     * @return
     */
    public static PushPayload buildPushObject_android_and_ios(Collection<String> registrationIds, String alert, Map<String, String> extras, String title) {
        extras = extras == null ? new HashMap<>() : extras;
        alert = alert == null ? "" : alert;
        title = title == null ? "" : title;

        String sound = extras.get("sound");
        logger.info("use sound :" + sound);
        if(StringUtils.isBlank(sound)){
            sound = "default";
        }
        return PushPayload.newBuilder().setPlatform(Platform.android_ios())
                .setAudience(Audience.newBuilder().addAudienceTarget(AudienceTarget.registrationId(registrationIds)).build())
                .setNotification(Notification.newBuilder()
                        .setAlert(alert)
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .addExtras(extras).setTitle(title).build())
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setBadge(1).setSound(sound)
                                .addExtras(extras).build())
                        .build())
                .build();

    }

    /**
     * @Description 向极光推送消息的通讯方法
     * @param registrationIds 要推送的设备注册ID的列表
     * @param alert 推送的内容
     * @param extras 推送的额外的参数
     */
    public static PushResult sendPush(Collection<String> registrationIds, String alert, Map<String, String> extras, String title, String app) {
        // HttpProxy proxy = new HttpProxy("localhost", 3128);
        // Can use this https proxy: https://github.com/Exa-Networks/exaproxy

        logger.info("发送极光推送 registrationIds ： {} , title : {} , app : {} " , registrationIds,title, app);
        JPushClient jpushClient = new JPushClient("fa9d5cd4eb6b23aa4e88d68b", "3edcac12d78570e7ae9368a9", 3);

        // 根据极光id生成推送的内容
        PushPayload payload = buildPushObject_android_and_ios(registrationIds, alert, extras, title);
        // 推送给IOS系统的是生产环境
        payload.resetOptionsApnsProduction(true);
        payload.resetOptionsTimeToLive(864000); // 保存离线推送10天

        try {
            PushResult result = jpushClient.sendPush(payload);
            logger.info("Got result - " + result);
            return result;
        } catch (APIConnectionException e) {
            logger.error("极光客户端连接失败！", e);
        } catch (APIRequestException e) {
            logger.error("极光请求抛出异常， HTTP Status: {} , Error Code: {} , Error Message: {}, Msg ID: {}, 异常信息为： {}",e.getStatus(), e.getErrorCode(),e.getErrorMessage(),e.getMsgId(),e);
        }
        return null;
    }


    /**
     * @Description 消息中心向极光推送消息方法
     * @param registrationIds 要推送的设备注册ID的列表
     * @param alert 推送的内容
     * @param extras 推送的额外的参数
     */
    public static Map<String, Object> sendPushByMessageCenter(Collection<String> registrationIds, String alert, Map<String, String> extras, String title, String app) {
        logger.debug("传送的发送目标app为：" + app);
        Map<String, Object> pushResult = new HashMap<String, Object>();
        JPushClient jpushClient = new JPushClient(jpushConfigStat.getMasterSecret(), jpushConfigStat.getAppKey(), jpushConfigStat.getMaxNum());
        // For push, all you need do is to build PushPayload object.
        PushPayload payload = null;
        try {
            payload = buildPushObject_android_and_ios(registrationIds, alert, extras, title);
        }catch(Exception ex){
            ex.printStackTrace();
            logger.info("buildPushObject_android_and_ios,alert: " + alert+",extras:"+extras+title+","+ex.getMessage());
            pushResult.put("code", "FAIL");
            pushResult.put("msg", "buildPushObject_android_and_ios:Exception");
            return pushResult;
        }
		/*if (RESULT.TRUE.toString().equals(apnsProductionMC)) { // 推送给IOS系统的是生产环境还是测试环境
			payload.resetOptionsApnsProduction(true);
		} else {
			payload.resetOptionsApnsProduction(false);
		}*/
        payload.resetOptionsTimeToLive(864000); // 保存离线推送10天
        try {
            PushResult result = jpushClient.sendPush(payload);
            logger.info("Got result - " + result);
            pushResult.put("code", "SUCCESS");
            pushResult.put("resultInfo", result);
        } catch (APIConnectionException e) {
            logger.error("Connection error. Should retry later. ", e);
            pushResult.put("code", "FAIL");
            pushResult.put("msg", "Connection error. Should retry later.");
        } catch (APIRequestException e) {
            logger.error("sendPushByMessageCenter极光请求抛出异常， HTTP Status: {} , Error Code: {} , Error Message: {}, Msg ID: {}, 异常信息为： {}",e.getStatus(), e.getErrorCode(),e.getErrorMessage(),e.getMsgId(),e);
            pushResult.put("code", "FAIL");
            pushResult.put("msg", "status:"+e.getStatus()+",code:"+e.getErrorCode()+",errorMsg:"+e.getErrorMessage()+",msgId:"+e.getMsgId());
        }
        return pushResult;
    }

    /**
     * @Description 向极光推送消息的测试推送
     * @param registrationIds 要推送的设备注册ID的列表
     * @param alert 推送的内容
     * @param extras 推送的额外的参数
     */
    public static PushResult sendValidatePush(Collection<String> registrationIds, String alert, Map<String, String> extras, String title, String app) {
        logger.debug("传送的发送目标app为：" + app);

        try {
            JPushClient jpushClient = new JPushClient(jpushConfigStat.getMasterSecret(), jpushConfigStat.getAppKey(), jpushConfigStat.getMaxNum());
            // For push, all you need do is to build PushPayload object.
            logger.info("push params -registrationIds :" + registrationIds+",alert:"+alert+",extras:"+extras+",title:"+title);

            PushPayload payload = buildPushObject_android_and_ios(registrationIds, alert, extras, title);

            payload.resetOptionsTimeToLive(864000); // 保存离线推送10天
            PushResult result = jpushClient.sendPushValidate(payload);
            logger.info("Got result - " + result);
            return result;
        } catch (APIConnectionException e) {
            logger.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            logger.error("Error response from JPush server. Should review and fix it. ", e);
            logger.info("HTTP Status: " + e.getStatus());
            logger.info("Error Code: " + e.getErrorCode());
            logger.info("Error Message: " + e.getErrorMessage());
            logger.info("Msg ID: " + e.getMsgId());
        }
        return null;
    }




}
