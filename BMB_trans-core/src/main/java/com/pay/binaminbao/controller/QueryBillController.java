package com.pay.binaminbao.controller;

import com.pay.binaminbao.beans.UnionPayBean;
import com.pay.binaminbao.service.QueryBillPayService;
import com.pay.binaminbao.service.impl.QueryBillPayServiceImpl;
import com.pay.binaminbao.utils.Constant;
import com.pay.binaminbao.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/bill")
public class QueryBillController {

    @Autowired
    private QueryBillPayService queryBillPayService;
    
    @RequestMapping(value = "/query")
    public @ResponseBody String queryBill(UnionPayBean unionPayBean){

        unionPayBean.setVersion(Constant.VERSION);   //版本
        unionPayBean.setEncoding(Constant.ENCODING); //编码放肆
        unionPayBean.setSignMethod("01");            //签名类型
        unionPayBean.setTxnType("73");                 // 交易类型
        unionPayBean.setTxnSubType("01");            // 便民缴费
        unionPayBean.setBizType("000601");           //产品类型
        unionPayBean.setChannelType("08");           //渠道类型
        unionPayBean.setAccessType("0");             // 0:普通商户直连接入 2:平台类商户接入
        
        unionPayBean.setMerId("777290058110048");    //设置商户代码 777290058110048(demo)  777290058153269(申请)
        unionPayBean.setOrderId(DateUtil.dateToStr("yyyyMMddHHmmss",new Date()));   // 商户订单号
        unionPayBean.setTxnTime(DateUtil.dateToStr("yyyyMMddHHmmss",new Date()));

        unionPayBean.setBussCode("O1_8800_0001"); //账单类型_地区码_附加地区码 示例:D1_3300_0000 页面传递的参数
        unionPayBean.setBillQueryInfo("{\"mbill_no\":[\"1\"],\"owe_tag\":\"D\"}");
        
        //unionPayBean.setOrigQryId("201711101611437958418");
        queryBillPayService.dealRequest(unionPayBean, null);
        return "SUCCESS";
    }
    
}
