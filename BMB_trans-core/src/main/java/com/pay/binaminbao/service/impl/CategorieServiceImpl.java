package com.pay.binaminbao.service.impl;

import com.pay.binaminbao.service.CategorieService;
import com.pay.binaminbao.utils.AcpService;
import com.pay.binaminbao.utils.Constant;
import org.springframework.stereotype.Service;

@Service
public class CategorieServiceImpl implements CategorieService {
    
    @Override
    public String getCategories(String provinceCode) {
        String reqUrl ="https://gateway.95516.com/jiaofei/config/s/categories/"+ provinceCode ;
        String rspStr = AcpService.get(reqUrl, Constant.ENCODING);
        return rspStr;
    }
    
}
