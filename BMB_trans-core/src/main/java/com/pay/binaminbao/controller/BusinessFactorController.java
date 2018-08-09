package com.pay.binaminbao.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.pay.binaminbao.beans.DealResult;
import com.pay.binaminbao.beans.JfbResponseBean;
import com.pay.binaminbao.service.BusinessFactorService;
import com.pay.binaminbao.utils.FastJsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/business")
public class BusinessFactorController {

    private Logger logger = LoggerFactory.getLogger(BusinessFactorController.class);
    
    @Autowired
    private BusinessFactorService businessFactorService;
    
    @RequestMapping(value = "/getFactor",produces = "plain/text; charset=UTF-8")
    public @ResponseBody String getBusinessFactor(String bussiCode){
        
        String businessFactor = businessFactorService.getBusinessFactor(bussiCode);
        JSON array = JSON.parseObject(businessFactor);
        String result = FastJsonUtil.toJson(new JfbResponseBean(DealResult.SUCCESS,array));
        logger.info("获取地区列表返回数据为： " + result);
        return result;
        
    }
    
}
