package com.pay.binaminbao.beans;

import java.security.PrivateKey;

public class UnionPayBean {
    private String version;     // 1. 版本号 固定填写 5.0.0
    private String certId;      // 2. 证书 ID  N1..128   填写签名私钥证书的 Serial Number，该值可通过银联提 供的 SDK 获取
    private String signature;   // 3. 签名  ANS1..1024  填写对报文摘要的签名
    private String encoding;    // 4. 编码方式  ANS1..2 0  填写报文使用的字符编码 若不填写，默认取值:UTF-8
    private String txnType;        // 5. 交易类型  N2
    private String txnSubType;  // 6. 交易子类     N2    依据实际交易类型填写 默认取值:00
    private String bizType;     // 7. 产品类型  N6
    private String frontUrl;    // 8. 前台通知 地址  ANS1..2 56  前台返回商户结果时使用，前台类交易需上送
    private String backUrl;     // 9. 后台通知 地址  ANS1..2 56  后台返回商户结果时使用，如上送，则发送商户后台交 易结果通知
    private String accessType;  // 10. 接入类型  N1
    private String acqInsCode;  // 11. 收单机构 代码  AN8..11
    private String merCatCode;  // 12. 商户类别 N4
    private String merId;       // 13. 商户代码  AN15
    private String merName;     // 14. 商户名称  ANS1..4 0
    private String merAbbr;     // 15. 商户简称
    private String subMerId;    // 16. 二级商户 代码  AN5..15  商户类型为平台类商户接入时必须上送
    private String subMerName;  // 17. 二级商户 名称
    private String subMerAbbr;  // 18. 二级商户 简称
    private String orderId;     // 19. 商户订单 号  AN8..32
    private String txnTime;     // 20. 订单发送 时间YYYY MMDD hhmmss
    private String orderTimeout;// 21. 订单接收 超时时间  N1..10 单位为毫秒，交易发生时，该笔交易在银联互联网系统 中有效的最长时间。当距离交易发送时间超过该时间 时，银联互联网系统不再为该笔交易提供支付服务
    private String payTimeout;  // 22. 支付超时 时间 YYYY MMDD hhmms 订单支付超时时间，超过此时间用户支付成功的交易， 不通知商户，系统自动退款，大约 5 个工作日金额返还 到用户账户
    private String defaultPayType; //  23. 默认支付 方式   N4 取值如下: 0001:认证支付 0004:储值卡支付
    private String supPayType;   //24. 支持支付 方式
    private String payType;      //25. 支付方式
    private String currencyCode; //26. 交易币种  AN3  币种格式必须为 3 位代码，默认取值:156(人民币)
    private String accType;      //27.   账号类型 (卡介质)
    private String accNo;       // 28.  账号
    private String payCardType; // 29. 支付卡类 型
    private String issInsCode;  // 30. 发卡机构
    private String issInsProvince;  // 31. 开户行省
    private String issInsCity;  //  32. 开户行市
    private String issInsName;  // 33. 开户行名 称
    private String customerInfo;    //34.  身份信息
    private String txnAmt;      // 35. 交易金额
    private String balance;     //36. 余额
    private String billType;    // 37.  账单类型
    private String billNo;      //38.  账单号码
    private String bussCode;    // 39.  业务代码
    private String billPeriod;  // 40. 账单周期
    private String billQueryInfo;  // 41. 账单要素
    private String billDetailInfo;  // 42 账单明细
    private String bindId; // 43. 绑定标识 号
    private String bindInfoQty;    // 44. 绑定关系 信息条数
    private String bindInfoList;    //45. 绑定信息 集
    private String batchNo;     //46. 批次号
    private String totalQty;   //47. 总笔数
    private String totalAmt;   //48. 总金额
    private String fileType;    //49. 文件类型
    private String fileName;    // 50. 文件名
    private String fileContent; //51. 批量文件 内容
    private String reqReserved;    //52. 请求方自 定义域
    private String reserved;    // 53.保留域
    private String termId;  //54.  终端号
    private String issuerIdentifyMode;  // 55. 发卡机构 识别模式
    private String customerIp;  //56. 持卡人 IP
    private String queryId; //57.  查询流水 号
    private String origQryId;   // 58.  原交易查 询流水号
    private String traceNo; // 59. 系统跟踪 号
    private String traceTime;   // 60. 交易传输 时间
    private String settleDate;  //61.  清算日期
    private String settleCurrencyCode; //62. 清算币种
    private String settleAmt; //63. 清算金额
    private String exchangeRate; // 64. 清算汇率
    private String exchangeDate; //65.兑换日期
    private String respTime; //66. 响应时间
    private String origRespCode; //67. 原交易应 答码
    private String origRespMsg; //68. 原交易应 答信息
    private String respCode; //69. 应答码
    private String respMsg; //70.  应答信息
    private String checkFlag; // 71.验证标识
    private String activateStatus; // 72.  开通状态
    private String encryptCertId ; // 73.ID 加密证书
    private String userMac; //74. 终端信息 域
    private String riskRateInfo;//75. 风控信息 域
    private String temporaryPayInfo;    //76.. 小额临时 支付信息 域
    private String frontFailUrl;   //77.. 失败交易 前台跳转 地址
    private String cardTransData;  // 78.有卡交易 信息域
    private String tn;//79.银联受理 订单号
    private String payCardNo;   // 80.支付卡标 识
    private String payCardIssueName;    //81. 支付卡名 称
    private String channelType; //82. 渠道类型
    private String signMethod;//83. 签名方法
    private String instalTransInfo; //84. 分期付款 信息域
    private String orderDesc;   //85.订单描述
    private String relTxnType; //86. 关联业务 标识
    private String bookedAccNo; //87. 转入账号

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCertId() {
        return certId;
    }

    public void setCertId(String certId) {
        this.certId = certId;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getTxnType() {
        return txnType;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public String getTxnSubType() {
        return txnSubType;
    }

    public void setTxnSubType(String txnSubType) {
        this.txnSubType = txnSubType;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getFrontUrl() {
        return frontUrl;
    }

    public void setFrontUrl(String frontUrl) {
        this.frontUrl = frontUrl;
    }

    public String getBackUrl() {
        return backUrl;
    }

    public void setBackUrl(String backUrl) {
        this.backUrl = backUrl;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public String getAcqInsCode() {
        return acqInsCode;
    }

    public void setAcqInsCode(String acqInsCode) {
        this.acqInsCode = acqInsCode;
    }

    public String getMerCatCode() {
        return merCatCode;
    }

    public void setMerCatCode(String merCatCode) {
        this.merCatCode = merCatCode;
    }

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId;
    }

    public String getMerName() {
        return merName;
    }

    public void setMerName(String merName) {
        this.merName = merName;
    }

    public String getMerAbbr() {
        return merAbbr;
    }

    public void setMerAbbr(String merAbbr) {
        this.merAbbr = merAbbr;
    }

    public String getSubMerId() {
        return subMerId;
    }

    public void setSubMerId(String subMerId) {
        this.subMerId = subMerId;
    }

    public String getSubMerName() {
        return subMerName;
    }

    public void setSubMerName(String subMerName) {
        this.subMerName = subMerName;
    }

    public String getSubMerAbbr() {
        return subMerAbbr;
    }

    public void setSubMerAbbr(String subMerAbbr) {
        this.subMerAbbr = subMerAbbr;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTxnTime() {
        return txnTime;
    }

    public void setTxnTime(String txnTime) {
        this.txnTime = txnTime;
    }

    public String getOrderTimeout() {
        return orderTimeout;
    }

    public void setOrderTimeout(String orderTimeout) {
        this.orderTimeout = orderTimeout;
    }

    public String getPayTimeout() {
        return payTimeout;
    }

    public void setPayTimeout(String payTimeout) {
        this.payTimeout = payTimeout;
    }

    public String getDefaultPayType() {
        return defaultPayType;
    }

    public void setDefaultPayType(String defaultPayType) {
        this.defaultPayType = defaultPayType;
    }

    public String getSupPayType() {
        return supPayType;
    }

    public void setSupPayType(String supPayType) {
        this.supPayType = supPayType;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public String getPayCardType() {
        return payCardType;
    }

    public void setPayCardType(String payCardType) {
        this.payCardType = payCardType;
    }

    public String getIssInsCode() {
        return issInsCode;
    }

    public void setIssInsCode(String issInsCode) {
        this.issInsCode = issInsCode;
    }

    public String getIssInsProvince() {
        return issInsProvince;
    }

    public void setIssInsProvince(String issInsProvince) {
        this.issInsProvince = issInsProvince;
    }

    public String getIssInsCity() {
        return issInsCity;
    }

    public void setIssInsCity(String issInsCity) {
        this.issInsCity = issInsCity;
    }

    public String getIssInsName() {
        return issInsName;
    }

    public void setIssInsName(String issInsName) {
        this.issInsName = issInsName;
    }

    public String getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(String customerInfo) {
        this.customerInfo = customerInfo;
    }

    public String getTxnAmt() {
        return txnAmt;
    }

    public void setTxnAmt(String txnAmt) {
        this.txnAmt = txnAmt;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getBussCode() {
        return bussCode;
    }

    public void setBussCode(String bussCode) {
        this.bussCode = bussCode;
    }

    public String getBillPeriod() {
        return billPeriod;
    }

    public void setBillPeriod(String billPeriod) {
        this.billPeriod = billPeriod;
    }

    public String getBillQueryInfo() {
        return billQueryInfo;
    }

    public void setBillQueryInfo(String billQueryInfo) {
        this.billQueryInfo = billQueryInfo;
    }

    public String getBillDetailInfo() {
        return billDetailInfo;
    }

    public void setBillDetailInfo(String billDetailInfo) {
        this.billDetailInfo = billDetailInfo;
    }

    public String getBindId() {
        return bindId;
    }

    public void setBindId(String bindId) {
        this.bindId = bindId;
    }

    public String getBindInfoQty() {
        return bindInfoQty;
    }

    public void setBindInfoQty(String bindInfoQty) {
        this.bindInfoQty = bindInfoQty;
    }

    public String getBindInfoList() {
        return bindInfoList;
    }

    public void setBindInfoList(String bindInfoList) {
        this.bindInfoList = bindInfoList;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(String totalQty) {
        this.totalQty = totalQty;
    }

    public String getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(String totalAmt) {
        this.totalAmt = totalAmt;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }

    public String getReqReserved() {
        return reqReserved;
    }

    public void setReqReserved(String reqReserved) {
        this.reqReserved = reqReserved;
    }

    public String getReserved() {
        return reserved;
    }

    public void setReserved(String reserved) {
        this.reserved = reserved;
    }

    public String getTermId() {
        return termId;
    }

    public void setTermId(String termId) {
        this.termId = termId;
    }

    public String getIssuerIdentifyMode() {
        return issuerIdentifyMode;
    }

    public void setIssuerIdentifyMode(String issuerIdentifyMode) {
        this.issuerIdentifyMode = issuerIdentifyMode;
    }

    public String getCustomerIp() {
        return customerIp;
    }

    public void setCustomerIp(String customerIp) {
        this.customerIp = customerIp;
    }

    public String getQueryId() {
        return queryId;
    }

    public void setQueryId(String queryId) {
        this.queryId = queryId;
    }

    public String getOrigQryId() {
        return origQryId;
    }

    public void setOrigQryId(String origQryId) {
        this.origQryId = origQryId;
    }

    public String getTraceNo() {
        return traceNo;
    }

    public void setTraceNo(String traceNo) {
        this.traceNo = traceNo;
    }

    public String getTraceTime() {
        return traceTime;
    }

    public void setTraceTime(String traceTime) {
        this.traceTime = traceTime;
    }

    public String getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate;
    }

    public String getSettleCurrencyCode() {
        return settleCurrencyCode;
    }

    public void setSettleCurrencyCode(String settleCurrencyCode) {
        this.settleCurrencyCode = settleCurrencyCode;
    }

    public String getSettleAmt() {
        return settleAmt;
    }

    public void setSettleAmt(String settleAmt) {
        this.settleAmt = settleAmt;
    }

    public String getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getExchangeDate() {
        return exchangeDate;
    }

    public void setExchangeDate(String exchangeDate) {
        this.exchangeDate = exchangeDate;
    }

    public String getRespTime() {
        return respTime;
    }

    public void setRespTime(String respTime) {
        this.respTime = respTime;
    }

    public String getOrigRespCode() {
        return origRespCode;
    }

    public void setOrigRespCode(String origRespCode) {
        this.origRespCode = origRespCode;
    }

    public String getOrigRespMsg() {
        return origRespMsg;
    }

    public void setOrigRespMsg(String origRespMsg) {
        this.origRespMsg = origRespMsg;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }

    public String getCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(String checkFlag) {
        this.checkFlag = checkFlag;
    }

    public String getActivateStatus() {
        return activateStatus;
    }

    public void setActivateStatus(String activateStatus) {
        this.activateStatus = activateStatus;
    }

    public String getEncryptCertId() {
        return encryptCertId;
    }

    public void setEncryptCertId(String encryptCertId) {
        this.encryptCertId = encryptCertId;
    }

    public String getUserMac() {
        return userMac;
    }

    public void setUserMac(String userMac) {
        this.userMac = userMac;
    }

    public String getRiskRateInfo() {
        return riskRateInfo;
    }

    public void setRiskRateInfo(String riskRateInfo) {
        this.riskRateInfo = riskRateInfo;
    }

    public String getTemporaryPayInfo() {
        return temporaryPayInfo;
    }

    public void setTemporaryPayInfo(String temporaryPayInfo) {
        this.temporaryPayInfo = temporaryPayInfo;
    }

    public String getFrontFailUrl() {
        return frontFailUrl;
    }

    public void setFrontFailUrl(String frontFailUrl) {
        this.frontFailUrl = frontFailUrl;
    }

    public String getCardTransData() {
        return cardTransData;
    }

    public void setCardTransData(String cardTransData) {
        this.cardTransData = cardTransData;
    }

    public String getTn() {
        return tn;
    }

    public void setTn(String tn) {
        this.tn = tn;
    }

    public String getPayCardNo() {
        return payCardNo;
    }

    public void setPayCardNo(String payCardNo) {
        this.payCardNo = payCardNo;
    }

    public String getPayCardIssueName() {
        return payCardIssueName;
    }

    public void setPayCardIssueName(String payCardIssueName) {
        this.payCardIssueName = payCardIssueName;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getSignMethod() {
        return signMethod;
    }

    public void setSignMethod(String signMethod) {
        this.signMethod = signMethod;
    }

    public String getInstalTransInfo() {
        return instalTransInfo;
    }

    public void setInstalTransInfo(String instalTransInfo) {
        this.instalTransInfo = instalTransInfo;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }

    public String getRelTxnType() {
        return relTxnType;
    }

    public void setRelTxnType(String relTxnType) {
        this.relTxnType = relTxnType;
    }

    public String getBookedAccNo() {
        return bookedAccNo;
    }

    public void setBookedAccNo(String bookedAccNo) {
        this.bookedAccNo = bookedAccNo;
    }

    @Override
    public String toString() {
        return "UnionPayBean{" +
                "version='" + version + '\'' +
                ", certId='" + certId + '\'' +
                ", signature='" + signature + '\'' +
                ", ENCODING='" + encoding + '\'' +
                ", txnType=" + txnType +
                ", txnSubType='" + txnSubType + '\'' +
                ", bizType='" + bizType + '\'' +
                ", frontUrl='" + frontUrl + '\'' +
                ", backUrl='" + backUrl + '\'' +
                ", accessType='" + accessType + '\'' +
                ", acqInsCode='" + acqInsCode + '\'' +
                ", merCatCode='" + merCatCode + '\'' +
                ", merId='" + merId + '\'' +
                ", merName='" + merName + '\'' +
                ", merAbbr='" + merAbbr + '\'' +
                ", subMerId='" + subMerId + '\'' +
                ", subMerName='" + subMerName + '\'' +
                ", subMerAbbr='" + subMerAbbr + '\'' +
                ", orderId='" + orderId + '\'' +
                ", txnTime='" + txnTime + '\'' +
                ", orderTimeout='" + orderTimeout + '\'' +
                ", payTimeout='" + payTimeout + '\'' +
                ", defaultPayType='" + defaultPayType + '\'' +
                ", supPayType='" + supPayType + '\'' +
                ", payType='" + payType + '\'' +
                ", currencyCode='" + currencyCode + '\'' +
                ", accType='" + accType + '\'' +
                ", accNo='" + accNo + '\'' +
                ", payCardType='" + payCardType + '\'' +
                ", issInsCode='" + issInsCode + '\'' +
                ", issInsProvince='" + issInsProvince + '\'' +
                ", issInsCity='" + issInsCity + '\'' +
                ", issInsName='" + issInsName + '\'' +
                ", customerInfo='" + customerInfo + '\'' +
                ", txnAmt='" + txnAmt + '\'' +
                ", balance='" + balance + '\'' +
                ", billType='" + billType + '\'' +
                ", billNo='" + billNo + '\'' +
                ", bussCode='" + bussCode + '\'' +
                ", billPeriod='" + billPeriod + '\'' +
                ", billQueryInfo='" + billQueryInfo + '\'' +
                ", billDetailInfo='" + billDetailInfo + '\'' +
                ", bindId='" + bindId + '\'' +
                ", bindInfoQty='" + bindInfoQty + '\'' +
                ", bindInfoList='" + bindInfoList + '\'' +
                ", batchNo='" + batchNo + '\'' +
                ", totalQty='" + totalQty + '\'' +
                ", totalAmt='" + totalAmt + '\'' +
                ", fileType='" + fileType + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileContent='" + fileContent + '\'' +
                ", reqReserved='" + reqReserved + '\'' +
                ", reserved='" + reserved + '\'' +
                ", termId='" + termId + '\'' +
                ", issuerIdentifyMode='" + issuerIdentifyMode + '\'' +
                ", customerIp='" + customerIp + '\'' +
                ", queryId='" + queryId + '\'' +
                ", origQryId='" + origQryId + '\'' +
                ", traceNo='" + traceNo + '\'' +
                ", traceTime='" + traceTime + '\'' +
                ", settleDate='" + settleDate + '\'' +
                ", settleCurrencyCode='" + settleCurrencyCode + '\'' +
                ", settleAmt='" + settleAmt + '\'' +
                ", exchangeRate='" + exchangeRate + '\'' +
                ", exchangeDate='" + exchangeDate + '\'' +
                ", respTime='" + respTime + '\'' +
                ", origRespCode='" + origRespCode + '\'' +
                ", origRespMsg='" + origRespMsg + '\'' +
                ", respCode='" + respCode + '\'' +
                ", respMsg='" + respMsg + '\'' +
                ", checkFlag='" + checkFlag + '\'' +
                ", activateStatus='" + activateStatus + '\'' +
                ", encryptCertId='" + encryptCertId + '\'' +
                ", userMac='" + userMac + '\'' +
                ", riskRateInfo='" + riskRateInfo + '\'' +
                ", temporaryPayInfo='" + temporaryPayInfo + '\'' +
                ", frontFailUrl='" + frontFailUrl + '\'' +
                ", cardTransData='" + cardTransData + '\'' +
                ", tn='" + tn + '\'' +
                ", payCardNo='" + payCardNo + '\'' +
                ", payCardIssueName='" + payCardIssueName + '\'' +
                ", channelType='" + channelType + '\'' +
                ", signMethod='" + signMethod + '\'' +
                ", instalTransInfo='" + instalTransInfo + '\'' +
                ", orderDesc='" + orderDesc + '\'' +
                ", relTxnType='" + relTxnType + '\'' +
                ", bookedAccNo='" + bookedAccNo + '\'' +
                '}';
    }
}