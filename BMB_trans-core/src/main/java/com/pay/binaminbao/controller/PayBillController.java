package com.pay.binaminbao.controller;

import com.pay.binaminbao.beans.CardCustIno;
import com.pay.binaminbao.beans.CardInfo;
import com.pay.binaminbao.beans.UnionPayBean;
import com.pay.binaminbao.service.PayBillService;
import com.pay.binaminbao.service.TransService;
import com.pay.binaminbao.utils.Constant;
import com.pay.binaminbao.utils.DateUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/trans")
public class PayBillController {
    
    @Autowired
    TransService transService;
    
    @Autowired
    private PayBillService billPayService;
    
    @RequestMapping(value = "/pay",method = {RequestMethod.GET,RequestMethod.POST},produces = "application/json")
    public @ResponseBody String pay(UnionPayBean unionPayBean, CardInfo cardInfo , CardCustIno cardCustIno){
       
        Map<String, Object> requestMap = new HashMap<String, Object>();
        Map<String, String> customerInfoMap = null;
        Map<String, String> cardTransDataMap = null;
        try {
            customerInfoMap = BeanUtils.describe(cardCustIno);
            customerInfoMap.remove("class");
            cardTransDataMap = BeanUtils.describe(cardInfo);
            cardTransDataMap.remove("class");
        } catch (Exception e) {
            e.printStackTrace();
        }
        requestMap.put("customerInfoMap",customerInfoMap);
        requestMap.put("cardTransDataMap",cardTransDataMap);
        UnionPayBean respPayBean = (UnionPayBean) billPayService.dealRequest(unionPayBean, requestMap);
        System.out.println(respPayBean);
        return "SUCCESS";
    }

    @RequestMapping(value = "/backUrl",method = {RequestMethod.GET,RequestMethod.POST})
    public @ResponseBody String callBack(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        System.out.println(parameterMap);
        return "SUCCESS";
    }

    @RequestMapping(value = "/frontUrl",method = {RequestMethod.GET,RequestMethod.POST})
    public @ResponseBody String callFrontUrl(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        System.out.println(parameterMap);
        return "SUCCESS";
    }
    
}
