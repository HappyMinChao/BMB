package com.pay.binaminbao.service.impl;

import com.pay.binaminbao.beans.UnionPayBean;
import com.pay.binaminbao.service.TransService;
import com.pay.binaminbao.utils.AcpService;
import com.pay.binaminbao.config.SDKConfig;
import com.pay.binaminbao.utils.Constant;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class TransServiceImpl implements TransService {

    private static final Logger logger = LoggerFactory.getLogger(TransServiceImpl.class);
    @Autowired
    private SDKConfig sdkConfig;
    
    @PostConstruct
    public void init(){
        System.out.println(sdkConfig);
    }
    
    @Override
    public String queryPayBill(UnionPayBean unionPayBean)
    {
        Map<String, String> reqDataMap = null;
        try {
            reqDataMap = BeanUtils.describe(unionPayBean);
            reqDataMap.remove("class");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, String> reqData = AcpService.sign(reqDataMap); // 报文中certId,signature的值是在signData方法中获取并自动赋值的，只要证书配置正确即可。
        String requestBackUrl = sdkConfig.getJfBackRequestUrl(); // 交易请求url从配置文件读取对应属性文件acp_sdk.properties中的 acpsdk.backTransUrl
        Map<String, String> rspData = AcpService.post(reqData, requestBackUrl, Constant.ENCODING); // 发送请求报文并接受同步应答（默认连接超时时间30秒，读取返回结果超时时间30秒）;这里调用signData之后，调用submitUrl之前不能对submitFromData中的键值对做任何修改，如果修改会导致验签不通过

        /** 对应答码的处理，请根据您的业务逻辑来编写程序,以下应答码处理逻辑仅供参考-------------> **/
        // 应答码规范参考open.unionpay.com帮助中心 下载 产品接口规范 《平台接入接口规范-第5部分-附录》
        if (!rspData.isEmpty()) {
            if (AcpService.validate(rspData, Constant.ENCODING)) {
                logger.info("验证签名成功");
                String respCode = rspData.get("respCode");
                if (("00").equals(respCode)) {
                    // 成功
                    // TODO
                    logger.info("信用卡还款账单获取成功");
                    String billDetailInfo = null;
                    try {
                        billDetailInfo = AcpService.base64Decode(rspData.get("billDetailInfo"), Constant.ENCODING);
                        logger.info("信用卡账单明细：" + billDetailInfo);
                    } catch (IOException e) {
                        logger.error("解析账单明细出现异常：{}", e);
                    }
                } else {
                    // 其他应答码为失败请排查原因或做失败处理
                    logger.info("信用卡还款账单获取失败");
                }
            } else {
                logger.info("验证签名失败");
            }
        } else {
            // 未返回正确的http状态
            logger.info("未获取到返回报文或返回http状态码非200");
        }

        // String reqMessage = DemoBase.genHtmlResult(reqData);
        // String rspMessage = DemoBase.genHtmlResult(rspData);
        // resp.getWriter().write("</br>请求报文:<br/>" + reqMessage + "<br/>" + "应答报文:</br>" + rspMessage + "");

        return null;
    }

    @Override
    public String pay(UnionPayBean reqUnionpayBean,Map<String, String> customerInfoMap, Map<String, String> cardTransDataMap) {
        
        /** 对请求参数进行签名并发送http post请求，接收同步应答报文 **/
        Map<String, String> reqDataMap = null;
        try {
            reqUnionpayBean.setBillQueryInfo(AcpService.base64Encode(reqUnionpayBean.getBillQueryInfo(), Constant.ENCODING));
            // //////// 如果商户号开通了 商户对敏感信息加密的权限那么，需要对 卡号accNo，pin和phoneNo，cvn2，expired加密（如果这些上送的话），对敏感信息加密使用：
            reqUnionpayBean.setEncryptCertId(AcpService.getEncryptCertId());
            //reqUnionpayBean.setAccNo(AcpService.encryptData(reqUnionpayBean.getAccNo(), Constant.ENCODING));
            System.out.println("customerInfo" + customerInfoMap);
            String customerInfoStr = AcpService.getCustomerInfoWithEncrypt(customerInfoMap, reqUnionpayBean.getAccNo(), Constant.ENCODING);
            // ////////
            logger.info(customerInfoStr);
            if(customerInfoMap != null & customerInfoMap.size()!=0) //没子域别出现这个key
                reqUnionpayBean.setCustomerInfo(customerInfoStr);

            reqDataMap = BeanUtils.describe(reqUnionpayBean);
            System.out.println("cardTransDataMap: " + cardTransDataMap);
            String cardTransData1 = AcpService.getCardTransData(cardTransDataMap, reqUnionpayBean, Constant.ENCODING);
            //cardTransData1="{ICCardData=nyYIznLfaQ+9R/qfJwGAnxATBwABA6AoAAEKAQAAABAAZsgXYZ83BHcbevefNgIAF5UFCIAE4ACaAxUFJZwBAJ8CBgAAAAABAF8qAgFWggJ8AJ8aAgFWnwMGAAAAAAAAnzMD4PnInzQDQgMAnzUBIp8eCDgyMDM2Njk4hAigAAADMwEBAZ8JAgFAn0EEAAAABp9jEDAwMDEwMDAw/wAAAAAAAAA=&ICCardSeqNumber=002&POSentryModeCode=05&carrierAppTp=3&carrierTp=5&track2Data=pZ7Zc6AJkgm7IcHh6CsHBffPjMgltgIa9ZWwMlKhTD/MiuY+od2elNPw9ZauL2p3b05rilMK6N+BrnMmeqFRwQUspAaiQQr10CXHXfY2OBecA6UBRNGVpLwWBZIoo1RuvdgWdyw1xhiAtOzzFbR/AC0RiGGGhzt4hxUNdV5J0tzwG/Smf9h3QIspefXRrqakAP0lVaXscXkznGUgaXa7iOzOQyWgTdSQuMdufidDVdikjVghacHs4dvyEMswwJqB3co5bgxUBROj4RKWUqO+OVFUsRuY0QRRyRSoGsJk0fju2TnTyMlkt5tjCuNWgc04yCD0RiOmhFyUj7rgeDEx8Q==&track3Data=ezC3hbH96B+LF02TSh/Rcg3b7ibo9LoMNIT2sBA6cpOmIVXnMZE/PHreKdan4vxTT83o5Ct3YCT8OvSlPrfO/5vkbwDKdN83ulUrO2qDO+udw7+2+I8uxeTbnabhKFvDSJNvWaKbPnMZjhD0Tf83zrLPduCLieyD/jMUGsOSXgRXp0VlvMrusQ12QSGhyg9e8RnC+s3WHnPDKeqBsuMGxChkF58kG0Qps1TMYlMRYGEh7XS4r6qgE255z7Pp3ZSydvnYa6+SR0FQAdp3QfkKfR2473fmlHPu2eICfVHdtS132RZtDeF9t+GH6ugx2Aih0DISrywg4dOQETAtDinaFg==}";
            reqDataMap.put("cardTransData",cardTransData1);
            System.out.println(reqDataMap);
            reqDataMap.remove("class");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        Map<String, String> reqData = AcpService.sign(reqDataMap); // 报文中certId,signature的值是在signData方法中获取并自动赋值的，只要证书配置正确即可。
        String requestBackUrl = sdkConfig.getJfCardTransUrl(); // 交易请求url从配置文件读取对应属性文件acp_sdk.properties中的 acpsdk.backTransUrl
        Map<String, String> rspData = AcpService.post(reqData, requestBackUrl, Constant.ENCODING); // 发送请求报文并接受同步应答（默认连接超时时间30秒，读取返回结果超时时间30秒）;这里调用signData之后，调用submitUrl之前不能对submitFromData中的键值对做任何修改，如果修改会导致验签不通过

        /** 对应答码的处理，请根据您的业务逻辑来编写程序,以下应答码处理逻辑仅供参考-------------> **/
        // 应答码规范参考open.unionpay.com帮助中心 下载 产品接口规范 《平台接入接口规范-第5部分-附录》
        if (!rspData.isEmpty()) {
            if (AcpService.validate(rspData)) {
                logger.info("验证签名成功");
                String respCode = rspData.get("respCode");
                if (("00").equals(respCode)) {
                   System.out.println("充值成功！");
                } else if ("03".equals(respCode) || "04".equals(respCode) || "05".equals(respCode)) {
                    System.out.println("// TODO 处理超时，请稍后查询。// 处理中");
                } else {
                    System.out.println("// 其他应答码为失败请排查原因或做失败处理");
                }
            } else {
                logger.info("验证签名失败");
                // TODO 检查验证签名失败的原因
            }
        } else {
            // 未返回正确的http状态
            logger.info("未获取到返回报文或返回http状态码非200");
        }
        return null;
    }

    @Override
    public String queryStatus(UnionPayBean unionpayBeanForPayment) {

        String orderId = unionpayBeanForPayment.getOrderId();
        String txnTime = unionpayBeanForPayment.getTxnTime();

        /** 查询订单 **/

        Map<String, String> data = new HashMap<String, String>();

        /*** 银联全渠道系统，产品参数，除了encoding自行选择外其他不需修改 ***/
        data.put("version", Constant.VERSION); // 版本号
        data.put("encoding", Constant.ENCODING); // 字符集编码 可以使用UTF-8,GBK两种方式
        data.put("signMethod", sdkConfig.getSignMethod()); // 签名方法
        data.put("txnType", "00"); // 交易类型 00-默认
        data.put("txnSubType", "00"); // 交易子类型 默认00
        data.put("bizType", "000501"); // 业务类型 代收

        /*** 商户接入参数 ***/
        data.put("merId", sdkConfig.getMechId()); // 商户号码，请改成自己申请的商户号或者open上注册得来的777商户号测试
        data.put("accessType", "0"); // 接入类型，商户接入固定填0，不需修改

        /*** 要调通交易以下字段必须修改 ***/
        data.put("orderId", orderId); // ****商户订单号，每次发交易测试需修改为被查询的交易的订单号
        data.put("txnTime", txnTime); // ****订单发送时间，每次发交易测试需修改为被查询的交易的订单发送时间

        /** 请求参数设置完毕，以下对请求参数进行签名并发送http post请求，接收同步应答报文-------------> **/

        Map<String, String> reqData = AcpService.sign(data, Constant.ENCODING); // 报文中certId,signature的值是在signData方法中获取并自动赋值的，只要证书配置正确即可。
        String url = sdkConfig.getSingleQueryUrl(); // 交易请求url从配置文件读取对应属性文件acp_sdk.properties中的 acpsdk.singleQueryUrl
        Map<String, String> rspData = AcpService.post(reqData, url, Constant.ENCODING); // 发送请求报文并接受同步应答（默认连接超时时间30秒，读取返回结果超时时间30秒）;这里调用signData之后，调用submitUrl之前不能对submitFromData中的键值对做任何修改，如果修改会导致验签不通过

        /** 对应答码的处理，请根据您的业务逻辑来编写程序,以下应答码处理逻辑仅供参考-------------> **/
        // 应答码规范参考open.unionpay.com帮助中心 下载 产品接口规范 《平台接入接口规范-第5部分-附录》
        if (!rspData.isEmpty()) {
            if (AcpService.validate(rspData, Constant.ENCODING)) {
                logger.info("验证签名成功");
                if (("00").equals(rspData.get("respCode"))) {// 如果查询交易成功

                    String origRespCode = rspData.get("origRespCode");
                    String origRespMsg = rspData.get("origRespMsg");

                    if (("00").equals(origRespCode)) {
                        // 交易成功，更新商户订单状态
                        String txnType = rspData.get("txnType");
                        String txnSubType = rspData.get("txnSubType");
                        String accessType = rspData.get("accessType");
                        String queryId = rspData.get("queryId");
                        String traceNo = rspData.get("traceNo");
                        String traceTime = rspData.get("traceTime");
                        String settleDate = rspData.get("settleDate");
                        String settleAmt = rspData.get("settleAmt");
                        String txnAmt = rspData.get("txnAmt");
                        String accNo = rspData.get("accNo");
                        String payCardType = rspData.get("payCardType");
                        String payType = rspData.get("payType");
                        String payCardNo = rspData.get("payCardNo");
                        String payCardIssueName = rspData.get("payCardIssueName");
                        String cardTransData = rspData.get("cardTransData");
                        String issuerIdentifyMode = rspData.get("issuerIdentifyMode");
                        String bindId = rspData.get("bindId");
                    } else if (("03").equals(origRespCode) || ("04").equals(origRespCode) || ("05").equals(origRespCode)) {
                        // 订单处理中或交易状态未明，需稍后发起交易状态查询交易 【如果最终尚未确定交易是否成功请以对账文件为准】
                        // TODO
                    } else {
                        // 其他应答码为交易失败
                        // 更新状态
                        // TODO
                    }
                } else if (("34").equals(rspData.get("respCode"))) {
                    // 订单不存在，可认为交易状态未明，需要稍后发起交易状态查询，或依据对账结果为准
                } else {// 查询交易本身失败，如应答码10/11检查查询报文是否正确
                    // TODO
                }
            } else {
                logger.info("验证签名失败");
                // TODO 检查验证签名失败的原因
            }
        } else {
            // 未返回正确的http状态
            logger.info("未获取到返回报文或返回http状态码非200");
        }

        // String reqMessage = DemoBase.genHtmlResult(reqData);
        // String rspMessage = DemoBase.genHtmlResult(rspData);
        // resp.getWriter().write("</br>请求报文:<br/>" + reqMessage + "<br/>" + "应答报文:</br>" + rspMessage + "");

        return null;
        
    }

    @Override
    public String payback(String payback) {
        
        System.out.println(payback);
        return payback;
    }

    public static void main(String[] args){
        TransServiceImpl transServiceImpl = new TransServiceImpl();
        UnionPayBean unionPayBean = new UnionPayBean();
    }
}
