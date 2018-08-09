package com.pay.binaminbao.service.impl;

import com.pay.binaminbao.beans.UnionPayBean;
import com.pay.binaminbao.config.SDKConfig;
import com.pay.binaminbao.service.PayBillService;
import com.pay.binaminbao.utils.AcpService;
import com.pay.binaminbao.utils.Constant;
import com.pay.binaminbao.utils.DateUtil;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * desc: 根据账单进行付款
 * auth: minchao.du
 */
@Service
public class PayBillServiceImpl extends BaseServiceImpl implements PayBillService{

    Logger logger = LoggerFactory.getLogger(PayBillServiceImpl.class);
    
    @Autowired
    private SDKConfig sdkConfig;
    
    private static  String requestUrl ;
    
    @PostConstruct
    private void init(){
        requestUrl = sdkConfig.getJfCardTransUrl();
    }
    
    @Override
    public Object preRequest(UnionPayBean reqUnionpayBean, Map reqParams) throws IOException {


        Map customerInfoMap = (Map)reqParams.get("customerInfoMap");
        Map cardTransDataMap = (Map) reqParams.get("cardTransDataMap");

        reqUnionpayBean.setVersion(Constant.VERSION);   //版本
        reqUnionpayBean.setEncoding(Constant.ENCODING); //编码放肆
        reqUnionpayBean.setSignMethod("01");            //签名类型
        reqUnionpayBean.setTxnType("13");                 // 交易类型
        reqUnionpayBean.setTxnSubType("01");            // 便民缴费
        reqUnionpayBean.setBizType("000601");           //产品类型
        reqUnionpayBean.setChannelType("08");           //渠道类型
        reqUnionpayBean.setAccessType("0");             // 0:普通商户直连接入 2:平台类商户接入

        reqUnionpayBean.setOrderId(DateUtil.dateToStr("yyyyMMddHHmmss",new Date()));   // 商户订单号
        reqUnionpayBean.setTxnTime(DateUtil.dateToStr("yyyyMMddHHmmss",new Date()));
        reqUnionpayBean.setCurrencyCode(Constant.CURRENCYCODE);

        //设置回调地址
        reqUnionpayBean.setBackUrl("http://172.28.4.14:8081/trans/backUrl?1=1");
        reqUnionpayBean.setFrontUrl("http://172.28.4.14:8081/trans/frontUrl?1=1");
        try {
            // 1. 获取私钥id
            reqUnionpayBean.setEncryptCertId(AcpService.getEncryptCertId());

            // 2. 账单信息
            reqUnionpayBean.setBillQueryInfo(AcpService.base64Encode(reqUnionpayBean.getBillQueryInfo(), Constant.ENCODING));

            // 用户信息  包含了电话号码，卡有效期， 密码、 cv2
            if (customerInfoMap != null & customerInfoMap.size() != 0) { //没子域别出现这个key
                String customerInfoStr = AcpService.getCustomerInfoWithEncrypt(customerInfoMap, reqUnionpayBean.getAccNo(), Constant.ENCODING);
                reqUnionpayBean.setCustomerInfo(customerInfoStr);
            }


            //55域IC卡数据，请改为从pos后读取填写，注意如果pos读取的数据是二进制格式的话，iCCardData这个参数请直接设置为二进制的数据。此处为了方便演示用的16进制数据，所以有个转2进制的过程。。
            //IC卡必送，磁条卡不送
            byte[] iCCardData;
            String iccStr = cardTransDataMap.get("iCCardData").toString();
            iCCardData = Hex.decodeHex(iccStr.toCharArray());
            cardTransDataMap.put("ICCardData", Base64.encodeBase64String(iCCardData));
            cardTransDataMap.remove("iCCardData");      // 注意必须删除
            
            // 卡信息
            String cardTransData = AcpService.getCardTransData(cardTransDataMap, reqUnionpayBean, Constant.ENCODING);
            reqUnionpayBean.setCardTransData(cardTransData);
        } catch (DecoderException e) {
            logger.info("转换16进制字符串抛出异常： {}", e);
        } catch (Exception e) {
            logger.info("抛出异常： {}", e);
        }
        
        return reqUnionpayBean;
    }

    @Override
    public Object postRequest(UnionPayBean responseUnionPayBean) {
        String respCode = responseUnionPayBean.getRespCode();
        if (("00").equals(respCode)) {
            System.out.println("充值成功！");
        } else if ("03".equals(respCode) || "04".equals(respCode) || "05".equals(respCode)) {
            System.out.println("// TODO 处理超时，请稍后查询。// 处理中");
        } else {
            System.out.println("// 其他应答码为失败请排查原因或做失败处理");
        }
        return null;
    }

    @Override
    public String getRequestUrl() {
        return requestUrl;
    }
}
