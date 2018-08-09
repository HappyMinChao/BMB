package com.pay.binaminbao.service.impl;

import com.pay.binaminbao.beans.UnionPayBean;
import com.pay.binaminbao.service.BaseService;
import com.pay.binaminbao.utils.AcpService;
import com.pay.binaminbao.utils.Constant;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public abstract class BaseServiceImpl implements BaseService {

    public Object /* UnionPayBean */ dealRequest(UnionPayBean reqUnionpayBean , Map params){
        
        Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);
        
        /** 对请求参数进行签名并发送http post请求，接收同步应答报文 **/
        Map<String, String> reqDataMap = null;
        String requestUrl = this.getRequestUrl();
        Map<String, String> reqData = null;
        try {
            // 请求银行前处理数据
            preRequest(reqUnionpayBean ,params );
            
            //把reqUnionpayBean 转换为 map
            reqDataMap = BeanUtils.describe(reqUnionpayBean);
            reqDataMap.remove("class");
            //对发送的数据签名
            reqData = AcpService.sign(reqDataMap); // 报文中certId,signature的值是在signData方法中获取并自动赋值的，只要证书配置正确即可。
        } catch (Exception e) {
            logger.info(this.getClass().getName() +" : "+ e);
        }
        
        Map<String, String> responseData = AcpService.post(reqData, requestUrl, Constant.ENCODING); // 发送请求报文并接受同步应答（默认连接超时时间30秒，读取返回结果超时时间30秒）;这里调用signData之后，调用submitUrl之前不能对submitFromData中的键值对做任何修改，如果修改会导致验签不通过

        /** 对应答码的处理，请根据您的业务逻辑来编写程序,以下应答码处理逻辑仅供参考-------------> **/
        // 应答码规范参考open.unionpay.com帮助中心 下载 产品接口规范 《平台接入接口规范-第5部分-附录》
        UnionPayBean responseUnionPayBean = new UnionPayBean();
        if (!responseData.isEmpty()) {
            try {
                if (AcpService.validate(responseData)) {
                    BeanUtils.populate(responseUnionPayBean, responseData);
                    postRequest(responseUnionPayBean);
                } else {
                    logger.info(this.getClass().getName() + "验证签名失败");
                }
            } catch (Exception e) {
                logger.info("处理银行返回结构跑出异常： " + e );
            }
        } else {
            logger.info(this.getClass().getName() + "未获取到返回报文或返回http状态码非200");
        }
        return responseUnionPayBean;
        
    }
    
    public abstract Object preRequest(UnionPayBean reqUnionpayBean , Map params) throws IOException;
    
    public abstract Object postRequest(UnionPayBean responseUnionPayBean);
    
    public abstract String getRequestUrl();

}
