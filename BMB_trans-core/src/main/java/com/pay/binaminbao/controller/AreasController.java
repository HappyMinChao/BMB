package com.pay.binaminbao.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.pay.binaminbao.beans.DealResult;
import com.pay.binaminbao.beans.JfbResponseBean;
import com.pay.binaminbao.service.AreasService;
import com.pay.binaminbao.utils.FastJsonUtil;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 获取地区列表
 */
@RestController
@RequestMapping(value = "/areas")
public class AreasController {
    
    private Logger logger = LoggerFactory.getLogger(AreasController.class);
    
    @Autowired
    private AreasService areasService;
    
    @RequestMapping(value = "/getAreas"/*,produces = "plain/text; charset=UTF-8"*/)
    public String getAreas(){
        String areas = areasService.getAreas();
        JSONArray array = JSON.parseArray(areas);
        String result = FastJsonUtil.toJson(new JfbResponseBean(DealResult.SUCCESS,array));
        logger.info("获取地区列表返回数据为： " + result);
        return result;
    }
    
    
}
