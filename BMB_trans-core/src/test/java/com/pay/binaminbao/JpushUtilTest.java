package com.pay.binaminbao;

import com.publicpay.account.jpush.JpushUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * JpushUtilTest
 *
 * @author : minchao.du
 * @description :
 * @date : 2018/2/28
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BmbApplication.class})
public class JpushUtilTest {

    @Test
    public void test() {
        for (int i = 0; i <= 2; i++) {
            System.out.println("时间为：" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
            Map<String, String> extras = new HashMap<String, String>();
            extras.put("MSGID", "111111117");
            extras.put("CATEGORY", "NOTICE");
            // extras.put(EXTRAS.URL.toString(), "https://jpapp.pay8.com/jp-communication/push/broadDetail?id=109");
            // extras.put(EXTRAS.SUMMARY.toString(), "理财师提现分润时，金额不得低于100元。每月15号审核上个月分润，分润审核日若为节假日，则顺延到下一工作日处理，分润到账日为T+1个工作日。");
            JpushUtil.sendPush(Arrays.asList("13165ffa4e084d3594f"),
                    "您有一笔白领宝交易已出款，本金6689.00元到兴业银行储蓄卡(2319)，收益45.98元到账户余额 本金预计到账时间：2016-05-23(具体到账时间请以银行为准) 收益预计到账时间：2016-05-23", extras, "", "LCS");
        }
    }

    
}
