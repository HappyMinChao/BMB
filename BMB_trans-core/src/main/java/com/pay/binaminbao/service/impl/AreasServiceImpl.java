package com.pay.binaminbao.service.impl;

import com.pay.binaminbao.service.AreasService;
import com.pay.binaminbao.utils.AcpService;
import com.pay.binaminbao.utils.Constant;
import org.springframework.stereotype.Service;

@Service
public class AreasServiceImpl implements AreasService {
    
    @Override
    public String getAreas() {
        //7.2.1　获取地区列表（areas）,获取已开通业务的地区列表
        String reqUrl ="https://gateway.95516.com/jiaofei/config/s/areas";
        String rspStr = AcpService.get(reqUrl, Constant.ENCODING);
        return rspStr;
    }
    
}
