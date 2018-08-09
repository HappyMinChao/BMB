package com.pay.binaminbao.service;


import com.pay.binaminbao.beans.UnionPayBean;

import java.util.Map;

/**
 * @Description: 银联缴费接口
 * @version 2017年11月02日
 * @author minchao.du
 */
public interface TransService {
    /**
     * @Description 查询缴费账单
     */
    public String queryPayBill(UnionPayBean unionpayBeanForPayment);

    /**
     * @Description 进行支付
     */
    public String pay(UnionPayBean unionpayBeanForPayment ,Map<String, String> customerInfoMap, Map<String, String> cardTransDataMap);

    /**
     * @Description 银联缴费账单状态查询
     */
    public String queryStatus(UnionPayBean unionpayBeanForPayment);
    
    public String payback(String payback);
}


