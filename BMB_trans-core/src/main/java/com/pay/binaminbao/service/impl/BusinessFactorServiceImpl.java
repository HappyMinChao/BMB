package com.pay.binaminbao.service.impl;

import com.pay.binaminbao.service.BusinessFactorService;
import com.pay.binaminbao.utils.AcpService;
import com.pay.binaminbao.utils.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 根据业务要素目录，获取的缴费类型，查询具体的业务要素
 */

@Service
public class BusinessFactorServiceImpl implements BusinessFactorService{
    
    Logger logger = LoggerFactory.getLogger(BusinessFactorServiceImpl.class);
    
    @Override
    public String getBusinessFactor(String categorie) {
        //7.2.3　获取业务要素（biz）
        String reqUrl ="https://gateway.95516.com/jiaofei/config/s/biz/"+categorie;
        String rspStr = AcpService.get(reqUrl, Constant.ENCODING);
        
        logger.info("获取业务要素返回结果： "+rspStr);
        
        return rspStr;
    }
}
