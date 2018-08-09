/*
package com.publicpay.account.jpush;

*/
/**
 * TShowScheduleTimer
 *
 * @author : minchao.du
 * @description :
 * @date : 2018/1/23
 *//*


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;
import java.util.TimerTask;

*/
/**
 * 定时检测验票时间 
 *
 * @author zhuhuoyun
 * @date 2016年6月1日
 *//*


public class TShowScheduleTimer extends TimerTask {
    @Resource
    private TShowScheduleServices tshowScheduleServices;
    @Resource
    private OCheckTimeService oCheckTimeService;
    @Resource
    private JpushMessageService jpushMessageService;
    private static final Logger logger = LoggerFactory.getLogger(TShowScheduleTimer.class);

    @Override
    public void run() {
        delayTshow();
    }

    public void delayTshow() {
        logger.info("每隔一分钟监听队列消息，判断是否到验票时间！！！");
        List<String> showList = tshowScheduleServices.ObtainTShowsFromRedisQueue();
        if (showList != null && showList.size() > 0) {
            // 获取即将要检票的场次  
            for (String str : showList) {
                String a[] = str.split("-");
                String obdId = a[0];
                String cinemaId = a[1];
                String showId = a[2];
                String showTime = a[3];
                String message = a[4];
                OCheckTimeModel oCheckTimeModel = new OCheckTimeModel();
                oCheckTimeModel.setCinemaid(cinemaId);
                List<OCheckTimeModel> checkTimes = oCheckTimeService.selectBySelective(oCheckTimeModel);
                if (checkTimes != null && checkTimes.size() > 0) {
                    // 获取开场前几分钟  
                    int preTime = checkTimes.get(0).getStartEntrance();
                    // 判断是否到检票时间  
                    if (DateUtil.getTimeRangeAfterHM(showTime) <= preTime) {
                        List<String> obdIds = new ArrayList<String>();
                        obdIds.add(obdId);
                        jpushMessageService.senPushByRegesterId(obdIds, message);
                        tshowScheduleServices.deleteTShowFromRedisQueue(showId);
                    }
                }
            }
        } else {
            logger.info("showList is null !");
        }
    }
}  
*/
