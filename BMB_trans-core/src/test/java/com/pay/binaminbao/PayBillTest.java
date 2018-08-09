package com.pay.binaminbao;


import com.pay.binaminbao.beans.UnionPayBean;
import com.pay.binaminbao.controller.PayBillController;
import com.pay.binaminbao.service.PayBillService;
import com.pay.binaminbao.utils.Constant;
import com.pay.binaminbao.utils.DateUtil;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;
import java.util.Map;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BmbApplication.class})
public class PayBillTest {
    @Autowired
    private PayBillService payBillService;
    
    @Test
    public void payBill(){
        UnionPayBean unionPayBean = new UnionPayBean();
        unionPayBean.setMerId("777290058110048");    //设置商户代码 777290058110048(demo)  777290058153269(申请)
        unionPayBean.setBussCode("O1_8800_0001"); //账单类型_地区码_附加地区码 示例:D1_3300_0000 页面传递的参数
        unionPayBean.setOrigQryId("201711130959393566728");  /// 每次对会变
        unionPayBean.setAccType("01");  // 从前台获取， 选取付款方式为ic卡还是银行卡  01:银行卡  02：存折  03: IC卡
        unionPayBean.setAccNo("6225000000000253");   //账号  使用的测试账号
        unionPayBean.setTxnAmt("53060");   //交易金额
        unionPayBean.setBillQueryInfo("{\"mbill_no\":[\"1\"],\"owe_tag\":\"D\"}"); //billQueryInfo-->{"mbill_no":["1"],"owe_tag":"D"}
        
        /*//银行卡验证信息及身份信息
        Map<String, String> customerInfoMap = new HashMap<String, String>();
        customerInfoMap.put("certifTp", "01"); // 证件类型
        customerInfoMap.put("certifId", "341126197709218366"); // 证件号码
        customerInfoMap.put("customerNm", "全渠道"); // 姓名
        customerInfoMap.put("phoneNo", "13552535506"); // 手机号 */
        
        Map<String,String> customerInfoMap = new HashMap<String,String>();
        customerInfoMap.put("pin", "123456");   //有密信用卡必送，无密信用卡不送，借记卡必送。
        customerInfoMap.put("expired", "3010"); //选送，生产多数发卡行送或者不送都会成功，部分发卡行送了会失败，极少数发卡行IC卡不送会失败，建议先不送，确定哪家发卡行必送再改。

        //按规范加密和组装然后设置到map里。
        //注意此域为非常规用法，请勿调用AcpService.getCustomerInfo方法组装，
        //调之前请确定卡号已经设置完成。
        //如果商户配置敏感信息不加密请按第一行填写，如果配了加密请按第二行填写。
//	    String customerInfoStr = AcpService.getCustomerInfo(customerInfoMap, accNo, DemoBase.encoding);
        
        /**   ================================================================
         * 测试卡信息设置
         *
         * 测试卡说明：
         * 请真实刷卡测试，
         * 磁条卡请报给测试支持人员配置，
         * IC卡请使用检测中心的测试卡，如之前接过线下pos，可用之前的测试IC卡，如果没有可以问银联分公司借，借不到请联系测试支持人员，信总也有少量的检查中心测试卡可外借。如用真实卡仿真会校验arqc失败导致反34.
         */
        Map<String, String> cardTransDataMap = new HashMap<String, String>();
        //55域IC卡数据，请改为从pos后读取填写，注意如果pos读取的数据是二进制格式的话，iCCardData这个参数请直接设置为二进制的数据。此处为了方便演示用的16进制数据，所以有个转2进制的过程。。
        //IC卡必送，磁条卡不送
        String iCCardData = "9F2608CE72DF690FBD47FA9F2701809F101307000103A02800010A01000000100066C817619F3704771B7AF79F360200179505088004E0009A031505259C01009F02060000000001005F2A02015682027C009F1A0201569F03060000000000009F3303E0F9C89F34034203009F3501229F1E0838323033363639388408A0000003330101019F090201409F4104000000069F63103030303130303030FF00000000000000";
        cardTransDataMap.put("iCCardData", iCCardData);
       
//		//23域卡序列号，请改为从pos后读取填写。
//		//IC卡必送，磁条卡不送。
        cardTransDataMap.put("ICCardSeqNumber", "002");
//		//35域第二磁道数据，请改为从pos后读取填写，注意别带长度。
        //必送。
        cardTransDataMap.put("track2Data", "6225000000000253=301022000000");
        //36域第三磁道数据，请改为从pos后读取填写，注意别带长度。
        //选送。
        cardTransDataMap.put("track3Data", "6225000000000253=301022000000");

        //磁条卡填02，ic卡接触填05，非接填07
        cardTransDataMap.put("POSentryModeCode", "05");
        cardTransDataMap.put("carrierAppTp", "3"); //勿改，mpos接入固定用法
        cardTransDataMap.put("carrierTp", "5");    //勿改，mpos接入固定用法

        Map<String, Object> requestMap = new HashMap<String, Object>();
        requestMap.put("customerInfoMap",customerInfoMap);
        requestMap.put("cardTransDataMap",cardTransDataMap);

        //以下3个字段如果送了会透传至发卡行，如果有需求要送，请在生产环境对各个发卡行的卡进行充分测再送。
//		cardTransDataMap.put("transSendMode", "1");  //透传到CUPS的 F60.3.5：交易发起方式，具体看全渠道有卡产品规范或者cups规范。
//		cardTransDataMap.put("termEntryCap", "6"); //透传到CUPS的 F60.2.2：终端读取能力，具体看全渠道有卡产品规范或者cups规范。
//		cardTransDataMap.put("chipCondCode", "0"); //透传到CUPS的 F 60.2.3：IC卡条件代码，具体看全渠道有卡产品规范或者cups规范。

        // String pay = transService.pay(unionPayBean,customerInfoMap,cardTransDataMap);
        UnionPayBean responseUnionPayBean = (UnionPayBean) payBillService.dealRequest(unionPayBean, requestMap);
        System.out.println(responseUnionPayBean.getRespCode());
    }
}
