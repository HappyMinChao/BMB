package com.pay.binaminbao;

import com.pay.binaminbao.utils.HttpClientUtil;
import com.pay.binaminbao.utils.JsonHelper;
import com.pay.binaminbao.utils.SignUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;
import java.util.Map;

/**
 * IWater
 *
 * @author : minchao.du
 * @description :
 * @date : 2018/1/2
 */

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BmbApplication.class})
public class IWater {
    
    private static final String companyRequestUrl = "http://test.iwaterdata.com/v1/watercompanys/nt.json"; 
    private static final String queryMeterByUserNoUrl = "http://test.iwaterdata.com/v1/meterinfo/nt.json";
    private static final String transCoreSyncUrl = "http://test.iwaterdata.com/v1/sync/nt.json";
    private static final String apiToken = "23fe9bd0f5d3703df1a31d20541fd3bd";
    private static final String token = "ZPcDQZeXb7b2UfU_E7v4Qg6x8PgEk44T_ASue9U8RyTlHBeZPiu5nQMtEgp6Y2s0gO1AjuVUj9cxOUfiiFLJZA";
    @Test
    public void testSign(){
        Map<String, String> requestPara = new HashMap<String, String>();

        requestPara.put("token", token);
        
        String iWaterSign = SignUtil.getIWaterSign(requestPara, apiToken);

        //requestPara.put("token", token);
        requestPara.put("sign", iWaterSign);
        
        String requestJson = JsonHelper.toJsonStr(requestPara);
        
        String reqUrl =  "requestPara=" + requestJson;
        System.out.println("发送的url参数为： " + reqUrl);
        
        String responseStr = HttpClientUtil.sendGet(companyRequestUrl, reqUrl, false);
        System.out.println(responseStr);
    }
    
    @Test
    public void testQueryMeterByUserNo(){

        Map<String, String> requestPara = new HashMap<String, String>();
        
        requestPara.put("waterCorpId", "34359738368");
        requestPara.put("meterNo", "80379");
        
        requestPara.put("token", token);
        String iWaterSign = SignUtil.getIWaterSign(requestPara, apiToken);
        //requestPara.put("token", token);
        requestPara.put("sign", iWaterSign);

        String requestJson = JsonHelper.toJsonStr(requestPara);

        String reqUrl =  "requestPara=" + requestJson;
        System.out.println("发送的url参数为： " + reqUrl);

        String responseStr = HttpClientUtil.sendGet(queryMeterByUserNoUrl, reqUrl, false);
        System.out.println(responseStr);
    }
    
    @Test
    public void testSync(){

        Map<String, String> requestPara = new HashMap<String, String>();

        requestPara.put("waterCorpId", "34359738368");
        requestPara.put("meterNo", "80379");
        requestPara.put("amount", "678.06");
        requestPara.put("orderNum",System.currentTimeMillis()+"");

        requestPara.put("token", token);
        String iWaterSign = SignUtil.getIWaterSign(requestPara, apiToken);
        //requestPara.put("token", token);
        requestPara.put("sign", iWaterSign);

        String requestJson = JsonHelper.toJsonStr(requestPara);

        String reqUrl =  "requestPara=" + requestJson;
        System.out.println("发送的url参数为： " + reqUrl);

        String responseStr = HttpClientUtil.sendGet(transCoreSyncUrl, reqUrl, false);
        System.out.println(responseStr);
    }
    
    
}
