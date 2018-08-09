package com.pay.binaminbao.service.impl;

import com.pay.binaminbao.beans.UnionPayBean;
import com.pay.binaminbao.config.SDKConfig;
import com.pay.binaminbao.service.QueryBillPayService;
import com.pay.binaminbao.utils.AcpService;
import com.pay.binaminbao.utils.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Map;

/**
 * desc: 根据业务要素查询账单
 * auth: minchao.du
 */
@Service
public class QueryBillPayServiceImpl extends BaseServiceImpl implements QueryBillPayService{

    Logger logger = LoggerFactory.getLogger(QueryBillPayServiceImpl.class);
    
    @Autowired
    private SDKConfig sdkConfig;
    
    private String reqeustUrl;
    
    @PostConstruct
    private void init(){
        reqeustUrl = sdkConfig.getJfQueryBillRequestUrl();   // 需要再次确认
    }
    
    @Override
    public Object preRequest(UnionPayBean unionPayBean, Map reqParams) throws IOException {
        unionPayBean.setVersion(Constant.VERSION);   //版本
        unionPayBean.setEncoding(Constant.ENCODING); //编码放肆
        unionPayBean.setSignMethod("01");            //签名类型
        unionPayBean.setTxnType("73");                 // 交易类型
        unionPayBean.setTxnSubType("01");            // 便民缴费
        unionPayBean.setBizType("000601");           //产品类型
        unionPayBean.setChannelType("08");           //渠道类型
        unionPayBean.setAccessType("0");             // 0:普通商户直连接入 2:平台类商户接入

        //对 billQueryInof base64加密
        unionPayBean.setBillQueryInfo(AcpService.base64Encode(unionPayBean.getBillQueryInfo(), Constant.ENCODING));
        
        return unionPayBean;
    }

    @Override
    public Object postRequest(UnionPayBean responseUnionPayBean) {
        String respCode = responseUnionPayBean.getRespCode();
        if (("00").equals(respCode)) {
            System.out.println("查询订单成功！");
        } else if ("03".equals(respCode) || "04".equals(respCode) || "05".equals(respCode)) {
            System.out.println("// TODO 处理超时，请稍后查询。// 处理中");
        } else {
            
            logger.info("");
            System.out.println("// 其他应答码为失败请排查原因或做失败处理");
        }
        return null;
    }

    @Override
    public String getRequestUrl() {
        return reqeustUrl;
    }
}
