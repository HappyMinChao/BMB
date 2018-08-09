package com.pay.binaminbao.controller;

import com.pay.binaminbao.beans.JfbResponseBean;
import com.pay.binaminbao.utils.Constant;
import com.pay.binaminbao.utils.FastJsonUtil;
import javafx.scene.shape.VLineTo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/error", produces = "plain/text; charset=UTF-8")
public class ErrorController {
    
    @RequestMapping(value = "/notFound")
    public String notFound(){
        return FastJsonUtil.toJson(new JfbResponseBean(Constant.HTTP_STATUS_NOT_FOUND,"请求的接口不存在"));
    }
    
    @RequestMapping(value = "/forbidden")
    public String forbidden(){
        return FastJsonUtil.toJson(new JfbResponseBean(Constant.HTTP_STATUS_FORBIDDEN,"请求的接口不存在"));
    }
}
