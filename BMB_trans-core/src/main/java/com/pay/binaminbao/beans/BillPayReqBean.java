package com.pay.binaminbao.beans;

public class BillPayReqBean {
    
    private String merId;       // 商户编号
    private String bussCode;    // 业务编码
    private String origQryId;   // 账单号
    private String accType;     // 选取付款方式为ic卡还是银行卡  01:银行卡  02：存折  03: IC卡
    private String accNo;       // 银行卡号
    private String txnAmt;      // 缴费金额
    private String buillQueryInfo;  // 账单详细信息
       
}
