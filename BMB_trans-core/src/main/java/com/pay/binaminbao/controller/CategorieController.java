package com.pay.binaminbao.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.pay.binaminbao.beans.DealResult;
import com.pay.binaminbao.beans.JfbResponseBean;
import com.pay.binaminbao.service.CategorieService;
import com.pay.binaminbao.utils.FastJsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * desc: 获取业务要素目录
 * auth: minchao.du
 */

@Controller
@RequestMapping(value = "/categories")
public class CategorieController {

    Logger logger = LoggerFactory.getLogger(CategorieController.class);
    
    @Autowired
    private CategorieService categorieService;
    
    @RequestMapping(value = "/getCategories",produces = "plain/text; charset=UTF-8")
    public @ResponseBody String getCategories(String cityCode){
        
        String arraycategories = categorieService.getCategories(cityCode);
        JSONArray array = JSON.parseArray(arraycategories);
        String result = FastJsonUtil.toJson(new JfbResponseBean(DealResult.SUCCESS, array));
        logger.info("获取业务要素返回的结果为： " + result);
        return result;
        
    }
    
}
