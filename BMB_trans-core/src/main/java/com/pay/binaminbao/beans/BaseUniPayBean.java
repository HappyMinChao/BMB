package com.pay.binaminbao.beans;

import com.pay.binaminbao.utils.Constant;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseUniPayBean {

    private String version = Constant.VERSION;     // 1. 版本号 固定填写 5.0.0
    private String certId;      // 2. 证书 ID  N1..128   填写签名私钥证书的 Serial Number，该值可通过银联提 供的 SDK 获取
    private String signature;   // 3. 签名  ANS1..1024  填写对报文摘要的签名
    private String encoding = "UTF-8";    // 4. 编码方式  ANS1..2 0  填写报文使用的字符编码 若不填写，默认取值:UTF-8
    private int txnType;        // 5. 交易类型  N2
    private String txnSubType;  // 6. 交易子类     N2    依据实际交易类型填写 默认取值:00
    private String bizType;     // 7. 产品类型  N6
    private String orderId;     // 19. 商户订单 号  AN8..32
    private String txnTime;     // 20. 订单发送 时间YYYY MMDD hhmmss
    public static String frontUrl;  //后台服务对应的写法参照 FrontRcvResponse.java
    public static String backUrl;//受理方和发卡方自选填写的域[O]--后台通知地址 后台服务对应的写法参照 BackRcvResponse.java

    // 商户发送交易时间 格式:YYYYMMDDhhmmss
    public static String getCurrentTime() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }

    // AN8..40 商户订单号，不能含"-"或"_"
    public static String getOrderId() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }

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

    public int getTxnType() {
        return txnType;
    }

    public void setTxnType(int txnType) {
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

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTxnTime() {
        return txnTime;
    }

    public void setTxnTime(String txnTime) {
        this.txnTime = txnTime;
    }

    public static String getFrontUrl() {
        return frontUrl;
    }

    public static void setFrontUrl(String frontUrl) {
        BaseUniPayBean.frontUrl = frontUrl;
    }

    public static String getBackUrl() {
        return backUrl;
    }

    public static void setBackUrl(String backUrl) {
        BaseUniPayBean.backUrl = backUrl;
    }

    @Override
    public String toString() {
        return "BaseUniPayBean{" +
                "version='" + version + '\'' +
                ", certId='" + certId + '\'' +
                ", signature='" + signature + '\'' +
                ", ENCODING='" + encoding + '\'' +
                ", txnType=" + txnType +
                ", txnSubType='" + txnSubType + '\'' +
                ", bizType='" + bizType + '\'' +
                ", orderId='" + orderId + '\'' +
                ", txnTime='" + txnTime + '\'' +
                '}';
    }
}
