package com.pay.binaminbao;


import com.pay.binaminbao.beans.UnionPayBean;
import com.pay.binaminbao.service.PayBillService;
import com.pay.binaminbao.service.QueryBillPayService;
import com.pay.binaminbao.utils.AcpService;
import com.pay.binaminbao.utils.Constant;
import com.pay.binaminbao.utils.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BmbApplication.class})
public class QueryBillTest {
    @Autowired
    private QueryBillPayService queryBillPayService;
    
    /*
        Form_6_2_3_BillQuery
        key : txnType--> value: 73
        key : channelType--> value: 08
        key : merId--> value: 777290058110097
        key : txnSubType--> value: 01
        key : version--> value: 5.1.0
        key : signMethod--> value: 01
        key : encoding--> value: UTF-8
        key : billQueryInfo--> value: eyJ1c3JfbnVtIjoiMDgwMjAxODAyMDUwMjAwNzIifQ==
        key : bizType--> value: 000601
        key : orderId--> value: 20171113182137322
        key : bussCode--> value: O1_8800_0001
        key : accessType--> value: 0
        key : txnTime--> value: 20171113182137
        * */
    
    @Test
    public void queryBill(){
        UnionPayBean unionPayBean = new UnionPayBean();
        unionPayBean.setMerId("777290058110097");    //设置商户代码 777290058110048(demo)  777290058153269(申请)
        unionPayBean.setOrderId("20171113184527454");   // 商户订单号
        unionPayBean.setTxnTime(DateUtil.dateToStr("yyyyMMddHHmmss",new Date()));   // 订单发送时间
        unionPayBean.setBussCode("O1_8800_0001"); //账单类型_地区码_附加地区码 示例:D1_3300_0000 页面传递的参数
        unionPayBean.setBillQueryInfo("{\"usr_num\":\"08020180205020072\"}");
        //unionPayBean.setBillQueryInfo("eyJ1c3JfbnVtIjoiMDgwMjAxODAyMDUwMjAwNzIifQ=="); // {"usr_num":"08020180205020072"}
        UnionPayBean responseUnionPayBean = (UnionPayBean) queryBillPayService.dealRequest(unionPayBean, null);
        System.out.println(responseUnionPayBean.getRespCode());
    }
}
