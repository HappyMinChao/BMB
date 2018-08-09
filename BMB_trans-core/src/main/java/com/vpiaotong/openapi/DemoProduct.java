package com.vpiaotong.openapi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.vpiaotong.openapi.util.*;
import org.junit.Test;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Songdan/ZhaoFei
 * @date 2017/1/19 10:19
 */
public class DemoProduct {
    //demo中的
    //private String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIVLAoolDaE7m5oMB1ZrILHkMXMF6qmC8I/FCejz4hwBcj59H3rbtcycBEmExOJTGwexFkNgRakhqM+3uP3VybWu1GBYNmqVzggWKKzThul9VPE3+OTMlxeG4H63RsCO1//J0MoUavXMMkL3txkZBO5EtTqek182eePOV8fC3ZxpAgMBAAECgYBp4Gg3BTGrZaa2mWFmspd41lK1E/kPBrRA7vltMfPj3P47RrYvp7/js/Xv0+d0AyFQXcjaYelTbCokPMJT1nJumb2A/Cqy3yGKX3Z6QibvByBlCKK29lZkw8WVRGFIzCIXhGKdqukXf8RyqfhInqHpZ9AoY2W60bbSP6EXj/rhNQJBAL76SmpQOrnCI8Xu75di0eXBN/bE9tKsf7AgMkpFRhaU8VLbvd27U9vRWqtu67RY3sOeRMh38JZBwAIS8tp5hgcCQQCyrOS6vfXIUxKoWyvGyMyhqoLsiAdnxBKHh8tMINo0ioCbU+jc2dgPDipL0ym5nhvg5fCXZC2rvkKUltLEqq4PAkAqBf9b932EpKCkjFgyUq9nRCYhaeP6JbUPN3Z5e1bZ3zpfBjV4ViE0zJOMB6NcEvYpy2jNR/8rwRoUGsFPq8//AkAklw18RJyJuqFugsUzPznQvad0IuNJV7jnsmJqo6ur6NUvef6NA7ugUalNv9+imINjChO8HRLRQfRGk6B0D/P3AkBt54UBMtFefOLXgUdilwLdCUSw4KpbuBPw+cyWlMjcXCkj4rHoeksekyBH1GrBJkLqDMRqtVQUubuFwSzBAtlc";
    //private String ptPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCJkx3HelhEm/U7jOCor29oHsIjCMSTyKbX5rpoAY8KDIs9mmr5Y9r+jvNJH8pK3u5gNnvleT6rQgJQW1mk0zHuPO00vy62tSA53fkSjtM+n0oC1Fkm4DRFd5qJgoP7uFQHR5OEffMjy2qIuxChY4Au0kq+6RruEgIttb7wUxy8TwIDAQAB";

    //客户端的
    private String privateKey = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBANngWqoWDx06I+fCj9INhfvtfDs+iD/hQVRo6pl8/ZhgniRvAyO8VgzwqLdQHoX5QoDMVV3WWxsufXHs/g6oVr6wvGnV3/guSWf+RN1gXqeZQ+xUAkA0xMh6MwJgDkhqMMO/37J2NTA9XEGp20UA2MptncxSiMfMgUMOqCg3VcaRAgMBAAECgYEAvubMqgEgBxzdPwGD4KnGvwHL+2EEIuyswFgZTpls3imyBvxaBOLNhxMK0WvbAspvUck39zuNolhuw/bCeHdZYKboxLBzDMoGdYVko7aLE/vpYAOn5AAemP4hjVPcLHuDowlEC5oqyr7WxgLEFXpV92H8mIFR1FeIqf4NSVo5mAECQQD/7FDDerTgwbzXUBFL3KTPmhMoZ4AM1N7hDJBLUCjUpf4pqayLbC13psd7ZCcJRkXLxDOWUwA93u0N4oCGsd6BAkEA2fEcvpDXFwl15VYKzi1W53AUfF079WfoSxA4QKqhfvBSSUWi2OkmZP7+I+GZRwhFtc8nSOPISogPz5D5zoYAEQJAXwRSCAGq3jeP8TDc+xl0Z0+BBWNH+3udNFkBybR9NFZcwwMn+Ku1RdM6BOnuRFc+2qSBsrImSNJXmOwbY+buAQJBAIx3AR61fVuEpm60IOy3fh5WN23cLWvCl43hm0/aaZHF1FMTRZzlz/AiBKwdl8uXQk6mf9xTquCWhxIxjz+TT/ECQQDqI9dykHjU4GOsXyYSjUAHNsnnmKK5B6Mac0XOzL6H3Nb09QRwm0JkxlvMDVwNXO8XpQmPXtV5XQoorobUMRwj";
    //private String myPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDZ4FqqFg8dOiPnwo/SDYX77Xw7Pog/4UFUaOqZfP2YYJ4kbwMjvFYM8Ki3UB6F+UKAzFVd1lsbLn1x7P4OqFa+sLxp1d/4Lkln/kTdYF6nmUPsVAJANMTIejMCYA5IajDDv9+ydjUwPVxBqdtFANjKbZ3MUojHzIFDDqgoN1XGkQIDAQAB";
    
    //服务器端的    
    //private String kyPrivateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAIgnXAPVAqapnrdUfK+zSJsD1jsN6mNuFLuq703PXOnxn2s1qtbTMILGY7t8Bcnf8qx7CNk8sQdmImHxR97HyDiuDFL8KhtodvPsgrmyhSQo5s7cHSj16tbCwpp5UQ+8mWrXyNk8gNpOG9utS6uFYBqseZTqU1oj20XB1T37zF07AgMBAAECgYBp4NG5WShamoUGY/kiWPUZNv6dpdCjybLtzZuSkrLaaj9ii7VyoYCT8dsUrPmDZ1e7sWHMLVotOst6BruxDwMvjMsY3b4kXIcYW/NX9ZwtFuzDciHSNTDc2WJGBPlgEVknLORBpLyRAvBbvWlsn4ZJzHuttxw//LRLVZ2Ugs41oQJBAM3ICclIcBksBm1QcOSj9f7/iqU8KCQ5+1Zzouj+TaivknhvHxQZK7gRyhj/1MGhuBlDNeYrGkoj4qTOB9iAvicCQQCpYWlxdHSLQHR73M+Up7jTzBbe8oCNhXJjQEhHkipI1M48Sq9nPlzfNbJFaCOiWvCn9exQRzycWiMb27NF0ijNAkEAwAtR5sFtrHBDxzztPtQtvb7qu2464CQkhfgBL4Q1IZpJDZBW5sDe5jfj7hTDj7nHP/xTjuzm6g43dCq7c7givQJAHiaa8Hk8bbVRhuLtiJa6jekcgR9FxutkS0qcEzFvmXtYIt9gjc5KFemnDDfXcAUkJaHfKQMlROe+rCzVoq4gWQJBAMcYcMU90Wb8Pflgf7A6sRWwW8hdCDsfJkN7CNniWzGHESd2t/Pzza15aACedQtbTDuqPyI2/3rLe48jze5jSvM=";
    private String kyPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCIJ1wD1QKmqZ63VHyvs0ibA9Y7DepjbhS7qu9Nz1zp8Z9rNarW0zCCxmO7fAXJ3/KsewjZPLEHZiJh8Ufex8g4rgxS/CobaHbz7IK5soUkKObO3B0o9erWwsKaeVEPvJlq18jZPIDaThvbrUurhWAarHmU6lNaI9tFwdU9+8xdOwIDAQAB";
    
    private String password = "lsBnINDxtct8HZB7KCMyhWSJ";

    // TODO 请更换请求流水号前缀
    private String prefix = "KYPT0001";
    private String platformCode = "00000000";
    private OpenApi openApi = new OpenApi(password, platformCode, prefix, privateKey);
    
    /**
     * 
     * @title: testRSAGenerate
     * @description: RAS公钥私钥的生成
     * @throws Exception
     */
    @Test
    public void testRSAGenerate() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        String publicKeyStr = RSAUtil.getKeyString(publicKey);
        System.out.println("publicKeyString:" + publicKeyStr);
        String privateKeyStr = RSAUtil.getKeyString(privateKey);
        System.out.println("privateKeyString:" + privateKeyStr);
    }

    /**
     *  1. 
     * @title: testInvoiceBlueSimpleness
     * @description: 蓝票接口调用：单商品行蓝票
     */
    @Test
    public void testInvoiceBlueOneItem() {
        //String url = "http://fpkj.testnw.vpiaotong.cn/tp/openapi/invoiceBlue.pt";
        String url = "https://jh.cardinfo.com.cn/gateway/einvoice/blue";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("taxpayerNum", "91110108MA0043365M");
        // TODO 请更换请求流水号前缀
        map.put("invoiceReqSerialNo", prefix + "000000000018");
        map.put("buyerName", "卡友支付");
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> listMap = new HashMap<String, String>();
        listMap.put("taxClassificationCode", "3040201030000000000");
        listMap.put("quantity", "1.00");
        listMap.put("unitPrice", "0.1");
        listMap.put("invoiceAmount", "0.1");
        listMap.put("taxRateValue", "0.06");
        list.add(listMap);
        map.put("itemList", list);
        String content = JsonUtil.toJson(map);
        System.out.println("请求content内容： "+content);
        String buildRequest = openApi.buildRequest(content);
        
        String response = HttpUtils.sendPost(url, buildRequest, null);
        System.out.println("卡友响应： "+response);
        String responseDecode = openApi.disposeResponse(response, kyPublicKey);
        JSONObject responseJsonObject = JSON.parseObject(responseDecode);
        JSONObject contentObject = (JSONObject)responseJsonObject.get("content");
        String qrCodePath = (String)contentObject.get("qrCodePath");
        System.out.println(qrCodePath);
        String qrCodePathDecode = Base64Util.decode2String(qrCodePath);
        System.out.println("qrCodePathDecode = " + qrCodePathDecode);
        
    }

    @Test
    public void testQrcode(){
        String responseJson = "{\"sign\":\"cs/KiBrNMMDeyC/t6x+QU4SNLK13wHtaRdioy+vyoFRdxMEzkF31PvZwfxoodC0m4RhZHk5Tv+r3ODI5lqZJaFRCW06/un0ApEB5T1JZnSMQn0uol65RkOzGmX65XI0/s6t54hpQaucChKbJoQ3J3CoRieQMh6KB/xtLrVU+u18=\",\"content\":{\"invoiceReqSerialNo\":\"KYPT0001004809580123\",\"qrCode\":\"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\",\"qrCodePath\":\"aHR0cDovL2Zwa2oudGVzdG53LnZwaWFvdG9uZy5jbi90cC9zY2FuLWludm9pY2UvaW5kZXgvTWpkaVlYTlRUMVZCZFRWaWRscEdLMXBWU1VWMVMxRjNNMkYwVUZCVkwxUT0ucHQ=\"},\"serialNo\":\"KYPT\",\"code\":\"0000\",\"msg\":\"处理成功\"}";

        JSONObject responseJsonObject = JSON.parseObject(responseJson);
        JSONObject contentObject = (JSONObject)responseJsonObject.get("content");
        String contentJson = (String)contentObject.get("qrCodePath");
        JSONObject content = JSON.parseObject(contentJson);
        System.out.println(content);
        //JSONObject jsonObject = JSON.parseObject(content);
        String qrCodePath = (String)content.get("qrCodePath");
        System.out.println(qrCodePath);
        String qrCodePathDecode = Base64Util.decode2String(qrCodePath);
        System.out.println("qrCodePathDecode = " + qrCodePathDecode);
        
        
        qrCodePath = "aHR0cDovL2Zwa2oudnBpYW90b25nLmNvbS90cC9zY2FuLWludm9pY2UvaW5kZXgvZEdndlJtUjNMMk52ZVM5SGRURlpMMUYwUzJSNk1HbHBNM1ZoZFVRNVZFND0ucHQ=";
        qrCodePathDecode = Base64Util.decode2String(qrCodePath);
        System.out.println("qrCodePathDecode = " + qrCodePathDecode);
    }

    /**
     * 2. 
     * @title: testInvoiceRed
     * @description: 红票接口调用
     */
    @Test
    public void testInvoiceRed() {
        //String url = "http://fpkj.testnw.vpiaotong.cn/tp/openapi/invoiceRed.pt";
        String url = "https://jh.cardinfo.com.cn/gateway/einvoice/red";

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("taxpayerNum", "91110108MA0043365M");
        // TODO 请更换请求流水号前缀
        map.put("invoiceReqSerialNo", prefix + "000000000012");
        map.put("invoiceCode", "011001800111");
        map.put("invoiceNo", "21461287");
        map.put("redReason", "退货");
        map.put("amount", "-0.11");
        String content = JsonUtil.toJson(map);
        System.out.println(content);
        String buildRequest = openApi.buildRequest(content);
        System.out.println(buildRequest);
        String response = HttpUtils.postJson(url, buildRequest);
        System.out.println("卡友响应： "+response);
        String responseDecode = openApi.disposeResponse(response, kyPublicKey);
        System.out.println("验签解密内容： " + responseDecode);
        JSONObject responseJsonObject = JSON.parseObject(responseDecode);
        JSONObject contentObject = (JSONObject)responseJsonObject.get("content");
        String qrCodePath = (String)contentObject.get("qrCodePath");
        System.out.println(qrCodePath);
        String qrCodePathDecode = Base64Util.decode2String(qrCodePath);
        System.out.println("qrCodePathDecode = " + qrCodePathDecode);
    }

    /**
     * 3. 
     * @title: testGetInvoiceQrAndExtractCode
     * @description: 获取开票二维码和提取码接口
     */
    @Test
    public void testGetInvoiceQrAndExtractCode() {
        //String url = "http://fpkj.testnw.vpiaotong.cn/tp/openapi/getInvoiceQrAndExtractCode.pt";
        String url = "https://jh.cardinfo.com.cn/gateway";
        String content = "{\"taxClassificationName\":\"食品\",\"itemName\":\"食品\",\"taxpayerNum\":\"110101201702071\",\"taxClassificationCode\":\"100100100\",\"tradeTime\":\"2016-12-22 08:15:54\",\"tradeNo\":\"XXXXXXXX\",\"invoiceAmount\":\"12.34\",\"enterpriseName\":\"电子票测试新1\",\"itemVersionIdentifiers\":\"1238834323423423423\",\"quantity\":\"1\",\"unitPrice\":\"12.34\",\"taxRateValue\":\"0.17\",\"allowInvoiceCount\":\"1\"}";
        String buildRequest = openApi.buildRequest(content);
        String response = HttpUtils.postJson(url, buildRequest);
        System.out.println(response);
        System.out.println(openApi.disposeResponse(response, kyPublicKey));

    }

    /**
     * 4.  
     * @title: testInvoicePaperDestroy
     * @description: 作废接口调用
     */
    @Test
    public void testInvoicePaperDestroy() {
        //String url = "http://fpkj.testnw.vpiaotong.cn/tp/openapi/invoicePaperDestroy.pt";
        String url = "https://jh.cardinfo.com.cn/gateway/einvoice/qrcode/batch";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("taxpayerNum", "91110108MA0043365M");
        // TODO 请更换请求流水号前缀
        map.put("invoiceReqSerialNo", prefix + "000093000220");
        map.put("invoiceCode", "011001800111");
        map.put("invoiceNo", "21461294");
        map.put("destroyReason", "作废的原因作废的原因原因作废的原因作废的原因");
        map.put("amount", "-0.11");
        String content = JsonUtil.toJson(map);
        System.out.println(content);
        String buildRequest = openApi.buildRequest(content);
        System.out.println(buildRequest);
        String response = HttpUtils.postJson(url, buildRequest);
        System.out.println(response);
        System.out.println(openApi.disposeResponse(response, kyPublicKey));

    }

    /**
     * 5.  
     * @title: testGetPTBoxStatus
     * @description: 获取票通宝状态接口
     */
    @Test
    public void testGetPTBoxStatus() {
        // String url = "http://fpkj.testnw.vpiaotong.cn/tp/openapi/getPTBoxStatus.pt";
        String url = "http://10.10.128.22:8080/gateway/einvoice/box";
        String content = "{\"taxpayerNum\":\"91110108MA0043365M\",\"enterpriseName\":\"电子票测试新1\"}";
        String buildRequest = openApi.buildRequest(content);
        String response = HttpUtils.postJson(url, buildRequest);
        System.out.println(response);
        System.out.println(openApi.disposeResponse(response, kyPublicKey));

    }

    /**
     * 6. 
     * @title: testGetInvoiceRepertoryInfo
     * @description: 获取库存接口
     */
    @Test
    public void testGetInvoiceRepertoryInfo() {
        //String url = "http://fpkj.testnw.vpiaotong.cn/tp/openapi/getInvoiceRepertoryInfo.pt";
        String url = "http://10.10.128.22:8080/gateway/einvoice/repertory";
        String content = "{\"taxpayerNum\":\"110101201702073\",\"enterpriseName\":\"电子票测试新1\"}";
        String buildRequest = openApi.buildRequest(content);
        String response = HttpUtils.postJson(url, buildRequest);
        System.out.println(response);
        System.out.println(openApi.disposeResponse(response, kyPublicKey));

    }

    /**
     *
     * @title: testInvoiceBlueSimpleness
     * @description: 蓝票接口调用：单商品行蓝票(含税)
     */
    @Test
    public void testInvoiceBlueOneItemIncludeTax() {
        //String url = "http://fpkj.testnw.vpiaotong.cn/tp/openapi/invoiceBlue.pt";
        String url = "http://10.10.128.22:8080/gateway/einvoice/blue";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("taxpayerNum", "110101201702071");
        // TODO 请更换请求流水号前缀
        map.put("invoiceReqSerialNo", prefix + "000000000027");
        map.put("buyerName", "购买方名称1");
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> listMap = new HashMap<String, String>();
        listMap.put("taxClassificationCode", "1010101020000000000");
        listMap.put("quantity", "1.00");
        listMap.put("unitPrice", "56.64");
        listMap.put("invoiceAmount", "56.64");
        listMap.put("taxRateValue", "0.13");
        listMap.put("includeTaxFlag", "1");
        list.add(listMap);
        map.put("itemList", list);
        String content = JsonUtil.toJson(map);
        System.out.println(content);
        String buildRequest = openApi.buildRequest(content);
        System.out.println(buildRequest);
        String response = HttpUtils.postJson(url, buildRequest);
        System.out.println(response);
    }





    /**
     *
     * @title: testInvoiceBlueMultiItem
     * @description: 蓝票接口调用：多商品行蓝票
     */
    @Test
    public void testInvoiceBlueMultiItem() {
        //String url = "http://fpkj.testnw.vpiaotong.cn/tp/openapi/invoiceBlue.pt";
        String url = "http://10.10.128.22:8080/gateway/einvoice/blue";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("taxpayerNum", "110101201702071");
        // TODO 请更换请求流水号前缀
        map.put("invoiceReqSerialNo", prefix + "902234567952");
        map.put("buyerName", "购买方名称");
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> listMapOne = new HashMap<String, String>();
        listMapOne.put("taxClassificationCode", "1010101020000000000");
        listMapOne.put("quantity", "1.00");
        listMapOne.put("unitPrice", "56.64");
        listMapOne.put("invoiceAmount", "56.64");
        listMapOne.put("taxRateValue", "0.13");
        Map<String, String> listMapTwo = new HashMap<String, String>();
        listMapTwo.put("taxClassificationCode", "5030000000000000000");
        listMapTwo.put("quantity", "1.00");
        listMapTwo.put("unitPrice", "15.21");
        listMapTwo.put("invoiceAmount", "15.21");
        listMapTwo.put("taxRateValue", "0.17");
        list.add(listMapOne);
        list.add(listMapTwo);
        map.put("itemList", list);
        String content = JsonUtil.toJson(map);
        System.out.println(content);
        String buildRequest = new OpenApi(password, platformCode, prefix, privateKey).buildRequest(content);
        System.out.println(buildRequest);
        String response = HttpUtils.postJson(url, buildRequest);
        System.out.println(openApi.disposeResponse(response, kyPublicKey));
    }

    /**
     *
     * @title: testInvoiceBlueMultiItem
     * @description: 蓝票接口调用：多商品行蓝票(含税)
     */
    @Test
    public void testInvoiceBlueMultiItemIncludeTax() {
        //String url = "http://fpkj.testnw.vpiaotong.cn/tp/openapi/invoiceBlue.pt";

        String url = "http://10.10.128.22:8080/gateway/einvoice/blue";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("taxpayerNum", "110101201702071");
        // TODO 请更换请求流水号前缀
        map.put("invoiceReqSerialNo", prefix + "902234567952");
        map.put("buyerName", "购买方名称");
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> listMapOne = new HashMap<String, String>();
        listMapOne.put("taxClassificationCode", "1010101020000000000");
        listMapOne.put("quantity", "1.00");
        listMapOne.put("unitPrice", "56.64");
        listMapOne.put("invoiceAmount", "56.64");
        listMapOne.put("taxRateValue", "0.13");
        Map<String, String> listMapTwo = new HashMap<String, String>();
        listMapTwo.put("taxClassificationCode", "5030000000000000000");
        listMapTwo.put("quantity", "1.00");
        listMapTwo.put("unitPrice", "15.21");
        listMapTwo.put("invoiceAmount", "15.21");
        listMapTwo.put("taxRateValue", "0.17");
        listMapTwo.put("includeTaxFlag", "1");
        list.add(listMapOne);
        list.add(listMapTwo);
        map.put("itemList", list);
        String content = JsonUtil.toJson(map);
        System.out.println(content);
        String buildRequest = openApi.buildRequest(content);
        System.out.println(buildRequest);
        String response = HttpUtils.postJson(url, buildRequest);
        System.out.println(openApi.disposeResponse(response, kyPublicKey));

    }

    /**
     *
     * @title: testInvoiceBlueMultiItem
     * @description: 蓝票接口调用：单商品行加带折扣行蓝票,在商品行里加入折扣率即可
     */
    @Test
    public void testInvoiceBlueOneItemAndDiscount() {
        //String url = "http://fpkj.testnw.vpiaotong.cn/tp/openapi/invoiceBlue.pt";
        String url = "http://10.10.128.22:8080/gateway/einvoice/blue";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("taxpayerNum", "110101201702071");
        // TODO 请更换请求流水号前缀
        map.put("invoiceReqSerialNo", prefix + "902234567955");
        map.put("buyerName", "购买方名称");
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> listMapOne = new HashMap<String, String>();
        listMapOne.put("taxClassificationCode", "1010101020000000000");
        listMapOne.put("quantity", "1.00");
        listMapOne.put("unitPrice", "56.64");
        listMapOne.put("invoiceAmount", "56.64");
        listMapOne.put("taxRateValue", "0.13");
        // 折扣率
        listMapOne.put("discountRateValue", "0.1");
        list.add(listMapOne);
        map.put("itemList", list);
        String content = JsonUtil.toJson(map);
        System.out.println(content);
        String buildRequest = openApi.buildRequest(content);
        System.out.println(buildRequest);
        String response = HttpUtils.postJson(url, buildRequest);
        System.out.println(openApi.disposeResponse(response, kyPublicKey));
    }

    /**
     *
     * @title: testInvoiceBlueMultiItem
     * @description: 蓝票接口调用：单商品行加带折扣行蓝票,在商品行里加入折扣率即可(含税)
     */
    @Test
    public void testInvoiceBlueOneItemAndDiscountIncludeTax() {
        //String url = "http://fpkj.testnw.vpiaotong.cn/tp/openapi/invoiceBlue.pt";
        String url = "http://10.10.128.22:8080/gateway/einvoice/blue";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("taxpayerNum", "110101201702071");
        // TODO 请更换请求流水号前缀
        map.put("invoiceReqSerialNo", prefix + "000000000032");
        map.put("buyerName", "购买方名称");
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> listMapOne = new HashMap<String, String>();
        listMapOne.put("taxClassificationCode", "1010101020000000000");
        listMapOne.put("quantity", "1.00");
        listMapOne.put("unitPrice", "56.64");
        listMapOne.put("invoiceAmount", "56.64");
        listMapOne.put("taxRateValue", "0.13");
        // 折扣率
        listMapOne.put("discountRateValue", "0.1");
        listMapOne.put("discountRateValue", "0.1");
        listMapOne.put("includeTaxFlag", "1");
        list.add(listMapOne);
        map.put("itemList", list);
        String content = JsonUtil.toJson(map);
        System.out.println(content);
        String buildRequest = new OpenApi(password, "11111111", "DEMO", privateKey).buildRequest(content);
        System.out.println(buildRequest);
        String response = HttpUtils.postJson(url, buildRequest);
        System.out.println(openApi.disposeResponse(response, kyPublicKey));
    }

    /**
     *
     * @title: testInvoiceBlueMultiItem
     * @description: 蓝票接口调用：单商品行使用优惠政策
     */
    @Test
    public void testInvoiceBlueOneItemAndPreferentialPolicy() {
        //String url = "http://fpkj.testnw.vpiaotong.cn/tp/openapi/invoiceBlue.pt";
        String url = "http://10.10.128.22:8080/gateway/einvoice/blue";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("taxpayerNum", "110101201702071");
        // TODO 请更换请求流水号前缀
        map.put("invoiceReqSerialNo", prefix + "902234567963");
        map.put("buyerName", "购买方名称");
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> listMapOne = new HashMap<String, String>();
        listMapOne.put("taxClassificationCode", "1010101020000000000");
        listMapOne.put("quantity", "1.00");
        listMapOne.put("unitPrice", "56.64");
        listMapOne.put("invoiceAmount", "56.64");
        listMapOne.put("taxRateValue", "0.13");
        // 优惠政策标示
        listMapOne.put("preferentialPolicyFlag", "1");
        listMapOne.put("vatSpecialManage", "22222222222222222");
        list.add(listMapOne);
        map.put("itemList", list);
        String content = JsonUtil.toJson(map);
        System.out.println(content);
        String buildRequest = new OpenApi(password, "11111111", "DEMO", privateKey).buildRequest(content);
        System.out.println(buildRequest);
        String response = HttpUtils.postJson(url, buildRequest);
        System.out.println(openApi.disposeResponse(response, kyPublicKey));
    }

    /**
     *
     * @title: testInvoiceBlueMultiItem
     * @description: 蓝票接口调用：零税率商品(使用零税率标示)
     */
    @Test
    public void testInvoiceBlueZeroTax() {
        //String url = "http://fpkj.testnw.vpiaotong.cn/tp/openapi/invoiceBlue.pt";
        String url = "http://10.10.128.22:8080/gateway/einvoice/blue";

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("taxpayerNum", "110101201702071");
        // TODO 请更换请求流水号前缀
        map.put("invoiceReqSerialNo", prefix + "902234567941");
        map.put("buyerName", "购买方名称");
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> listMapOne = new HashMap<String, String>();
        listMapOne.put("taxClassificationCode", "1010101020000000000");
        listMapOne.put("quantity", "1.00");
        listMapOne.put("unitPrice", "56.64");
        listMapOne.put("invoiceAmount", "56.64");
        listMapOne.put("taxRateValue", "0");
        // 零税率标示
        listMapOne.put("zeroTaxFlag", "1");
        list.add(listMapOne);
        map.put("itemList", list);
        String content = JsonUtil.toJson(map);
        System.out.println(content);
        String buildRequest = openApi.buildRequest(content);
        System.out.println(buildRequest);
        String response = HttpUtils.postJson(url, buildRequest);
        System.out.println(openApi.disposeResponse(response, kyPublicKey));
    }

    /**
     *
     * @title: testGetNotDefaultItemInvoiceQrcode
     * @description: 获取开票二维码接口
     */
    @Test
    public void testGetNotDefaultItemInvoiceQrcode() {
        //String url = "http://fpkj.testnw.vpiaotong.cn/tp/openapi/getNotDefaultItemInvoiceQrcode.pt";
        String url = "http://10.10.128.22:8080/gateway/einvoice/qrcode";

        String content = "{\"taxClassificationName\":\"食品\",\"itemName\":\"食品\",\"taxpayerNum\":\"110101201702071\",\"taxClassificationCode\":\"100100100\",\"tradeTime\":\"2016-12-22 08:15:54\",\"tradeNo\":\"XXXXXXXX\",\"invoiceAmount\":\"12.34\",\"enterpriseName\":\"电子票测试新1\",\"itemVersionIdentifiers\":\"1238834323423423423\",\"quantity\":\"1\",\"unitPrice\":\"12.34\",\"taxRateValue\":\"0.17\"}";
        String buildRequest = openApi.buildRequest(content);
        String response = HttpUtils.postJson(url, buildRequest);
        System.out.println(openApi.disposeResponse(response, kyPublicKey));
    }
    
    //===========================
    
    










    
    
    
    
   

   

    /**
     * 
     * @title: testAuthWeChatCards
     * @description:微信卡包授
     */
    @Test
    public void testAuthWeChatCards() {
        String url = "http://fpkj.testnw.vpiaotong.cn/tp/openapi/authWeChatCards.pt";
        String content = "{\"taxpayerNum\":\"110101201702071\",\"invoiceReqSerialNo\":\"GAGA0000000000000009\"}";
        String buildRequest = new OpenApi(password, "11111111", "DEMO", privateKey).buildRequest(content);
        String response = HttpUtils.postJson(url, buildRequest);
        System.out.println(response);
        System.out.println(new OpenApi(password, "11111111", "DEMO", privateKey).disposeResponse(response, kyPublicKey));

    }
	
    

    /**
     * 
     * @title: test3DES
     * @description: 3DES加密
     */
    @Test
    public void test3DES() {
        String content = "{\"taxpayerNum\": \"9120931023801231\",\"enterpriseName\": \"西单大悦城有限公司\",\"paymentTransID\": \"12109238102831023102983\",\"paymentType\": \"2\",\"paymentTransTime\": \"2017-01-19 18:20:09\",\"paymentTransMoney\": \"20\",\"orderID\": \"12109238102831023102981\",\"orderMoney\": \"30\"}";
        System.out.println(SecurityUtil.encrypt3DES("8f1$OeJ@eSR0z5Jh%!LmiBzi", content));
    }

    /**
     * 
     * @title: test3DESDecry
     * @description: 3DES解密
     */
    @Test
    public void test3DESDecry() {
        String str = "/WMVSKtTr2fzX4ZIeJQiqWfjst0dxVJoMrupVju7ZHFOBUD6YnTUGMAbwnaSp/wLEKFxxbGLsj9jQKSPxhKPvVZXmnEGTYsfby0KS/VrbSVAUo2EWz/53K9fajQ4Q1aoELsU/hTHO2KnTBEAEMRowGHX8lMFbvJ/ayu+PYUJ0/OHDr/dyI56fh6R6HfTvH2vsQb7ORjAk2fHHy3Bm/DFMhyqCk42ed6ygstwCXNYkjFjjCYTthYR/z801LDcOKq9TAI8TrMh/DtUXFmCRH70+e616we0Rv1zfxcmt9nqe9AI8uJUF0Bd+tdImWMF98dcclATKZ1Nk7We/xv4QUvoxsQJX2BFnCnw0rx6mPvisFcQDgo270EYLR5eFsBHy1oH1UD8VkgdjapOQzR0QhExSJ2z2bMBe56RW+kxITUE4qbP4pQd5MtSkBcc8NCSPrP2si8kQ/9R2XB+e3vCL97dvsyW4s9n2XDOsi8kQ/9R2XCwRlI/fcxKDsT8CTecGsv9Ps6UCdRNfzdBq/+JH66xM5yWBq5l5ZDSmZfvpMFpwc3dn/Zh4wbNEPhJi8S/TmAHIr8gLw2EgzT2f979G/8kboBAaWbrt1bgQ7sUHNGmvrE=";
        System.out.println(SecurityUtil.decrypt3DES("IskuI3GAMm2DAfaweCcndyCp", str));
    }

    /**
     * 
     * @title: testBase64
     * @description: Base64编码
     */
    @Test
    public void testBase64() {
        String str = "JJON0d93C9nQN013N+cCwwIYbRVYlWChGQkSgAWG8g4mD1xFU6oGPauqih5gW7ZTcpejSPS8TqRbdBFdBATSXdwZqPM0q8sVYf3xwlp8OEw6INcUCvRW7myiFkzSJLV4Ost42d5Xp+sicgMj0bn99BsRSqe06BMvYTA46L/vGGPqN4tuuy2B/enpkGLcOQdPdtC+wG8ub6+zykisJT5I7EMls73cjaSlj1iRw/PT9huULu97iPHIiqnKhK05AXkvgWMcfg42+bLeG/kPgbaAtwAkXN/yDkKACcDML2WE8TZ+BFsaQPbH+BfY/XQ4VXSYF5NGeulhDJr1DLIHgH+KNQ==";
        System.out.println(Base64Util.encode2String(str));
    }

    /**
     * 
     * @title: testSign
     * @description: RSA签名
     */
    @Test
    public void testSign() {
        String content = "{\"taxpayerNum\":\"GAGA000000000001\",\"enterpriseName\":\"测试注册A\",\"platformCode\":\"13242753\",\"registrationCode\":\"87774618\",\"authorizationCode\":\"4209110715\"}";
        String buildRequest = new OpenApi("D5ImkGeVdsCaZyna4G73jv6j", "vZpDTUSV", "GAGA",
                "")
                        .buildRequest(content);
        System.out.println(buildRequest);
    }

    /**
     * 
     * @title: testVerify
     * @description:RSA验签
     */
    @Test
    public void testVerify() {
        //String requestJson = "{\"format\":\"JSON\",\"sign\":\"i8VGz3Qs4tnw89aeJON21nn2h8oQAynM4BALX8iKb+BlfXvIc2PS1hHen0cnJm53bvgRfyVCEgXQkpFK1Rdiw6ht3KD1IxtYMSkISUuwRphuhwPQiVVsODZiGSRQpHCwcv+I7szWhOErY0sYBqPHfHSsvZ4bD/rOp7K+0Lp/sw0\\u003d\",\"signType\":\"RSA\",\"version\":\"1.0\",\"platformCode\":\"TohDKeNU\",\"content\":\"EYY+FTkOvZs70g6eplhvD8P3oOZBVwtO1mN3dwDgNo0wCi9URbwtGJBqiaKUpjvn43HuE04aelW7J+ZgewZG5AmgdbY0aFhDejb5czd6UPt8DWLK9NyrgKMtKBc4LULfPKFhQwchtfgWWJtEHMrIyyHQPe5N5vgmAGGBE4nYUyazp6p42JnMLJCUHHNKsI2zApQWp20I1BCMhT/XZc+jXZ3q50UmHJP9l4vKCfESLxwahdADgB2HLqRWLr1OgxYenUUNkokwtV5TxQBYr/iKdQ03HFEVjNV+tuTTrcdakA/tF0UOc71roxxmHFgJU8bl64U+KRMEM1cQoEDwRzjqcbfGRrj/JFEqQel2k7mDsNZi24EvIExNDFautR9XLsuryZ8vrDwK33Ln/oYz+K5wooyf13kv8KDQgVhWCkW3XmmWwGMCttzOP9pVbiD3WP6MJ5qmhtK7R4JBibMoLAjFUcA2IZgk9tnphTUlQZcFEIPCT0cv0fyvuw\\u003d\\u003d\",\"timestamp\":\"2017-05-12 13:55:06\",\"serialNo\":\"CHSE20170512135506OW6jE08r\"}";
        String requestJson = "{\"format\":\"JSON\",\"sign\":\"pgpcWs2atBXymlz7GBH14ZPOILYVA3tD/pKixL4Nps3HU0pEeozYL0zLk8sbw69VRoVY46f++MW9FDQAnepPsDik8aZ3ySjEjHmHa6SXywPOXmF/7dpEohuqhG1ksNwzcBDHxn68GMXrb41ZqEXCRrLNF9BRt/5kfEvol2h33DQ\\u003d\",\"signType\":\"RSA\",\"version\":\"1.0\",\"platformCode\":\"11111111\",\"content\":\"9Kval5TJJQUKWAvqqwMVPinrBAbQJUKU0BbykxZK1WQghouTV/GBnQURtC19Num2fTXcM2S1AFAkG5Kn3qXqeVMkQt/rzOa/VBga6DJgXgZ5K6imMRDGlQHYFDCcwB6Cysf9TG2I+vv7WOK1J/H5zD1jetMXZnLVfvKMjqouFooY8slo3tbeZXyDUeZgH5JTqGKqWbCzuCwghouTV/GBnaOHtgK5iI1SA4eJsWsMycOatphBCZ0g1l48n4zvjTdzxrNMKyYgwFVdruuXsvq0Z5RdxFKNSN6PbpxEAvIcDg58Nvz2/RjM8PRiFvMxDKkz8rQw+x4bzAbN66/sKV+tyd8/pmWwqtryYY8PR2an39yI+mW3YT01JQ\\u003d\\u003d\",\"timestamp\":\"2018-02-01 09:11:00\",\"serialNo\":\"DEMO20180201091100sRmuM4ig\"}";

        Map<String, String> paramMap = JsonUtil.json2Map(requestJson);
        String sign = paramMap.remove("sign");
        String a = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCdL0fHPUtT9bTc8tTx5cSNnLO09P/4pTnYyPpp9Jt+NU+nTYiVjDrX97a7M0NuuoxpX4KSFz3WKTgGir+uYcQUm50+oEaRI1gQZrjnIfXEyafdS1Gcr34OAvObxvShQst/p3swVatqK4b7/SfYrQN6S4tQ4L+06QBKgvvaxpGPTwIDAQAB";
        boolean verify = RSAUtil.verify(RSAUtil.getSignatureContent(paramMap), sign, kyPublicKey);
        System.out.println(verify);
        System.out.println(kyPublicKey.length());
        System.out.println(privateKey.length());
    }

    /**
     * 
     * @title: testPush
     * @description:testPush
     */
    @Test
    public void testPush() {
        String a = "{\"sign\":\"hmoBFbyTi1QmnMtPcE8/XUSR6HcNVQ4hR2ZBHbgzs7m6dKeNjBuNoE5rqcEmAaR/PPTwlO0NMlvY+z21mr3LNOe/NI6mXzaithXruCqCJeBdbzj1YZmqmcr1evxfNAaP+ThzII6rMVhqHnOqt38sDyh1PFb6Lcg8S93smnYwp+8\\u003d\",\"timestamp\":\"2017-05-19 14:12:55\",\"content\":\"c3PX8j3oA9qlajRp8VsSxVAeABJFVFrndbivQXMoaFv5nnMQ3P2vziTi4d6XY8t0u4abbhMUElprxSMhy5880hTeUkVPVKu2oinrVJW227hdToKathg+KhE86RccUS4v/t7+MI7KL7c3lonzpC2hmFAFGoZL8ncK1P1dh6Smf53IDTtJ6Wxi3dVKJt6XHw4IK+kEYcrO/dG2E7GEfoUT/w\\u003d\\u003d\",\"serialNo\":\"GAGA201705191412550mACGnRI\",\"platformCode\":\"vZpDTUSV\",\"signType\":\"RSA\",\"format\":\"JSON\",\"version\":\"1.0\"}";
        String requestJson = "{\"format\":\"JSON\",\"sign\":\"eGhssKmnCMh1UckqqIqhv8XBiTPOU3JqGBHlflc8lZWHJtMBnN4r5Q2ovgNqkfZv78wE8jpiaucpQGKmHKXOc9Tda0oWcW5o0jF6mPtN5+xgRjeZIVQN1ZGGLay5/OQb/9SlwRdFN1a1zy0uAwjn+57i4UivS2NEeAn2Xd6yjvI\\u003d\",\"signType\":\"RSA\",\"version\":\"1.0\",\"platformCode\":\"1Ie3pU3A\",\"content\":\"QfjtruvJgIDYjKMCC92dt2QDqulOgslkDpVfwE3aOgULxrY52WlS4jLoADo/MIdqa/tqETq46Po0u7KGk9k5t1NYA0J/LYGuLmqfGYLjOXT0M8HLFK3UTwN4sgCK03bBzaRfD8xjR0X21E+TzgMuPCtQ8/Wgt5TK2i9l0CzeclbBlVRAJT7mGyzQI2rlvd9ffM7Ok+kk/IvdxMfZ+ZfDAg\\u003d\\u003d\",\"timestamp\":\"2017-05-16 15:53:56\",\"serialNo\":\"WYTC20170516155356pS5CzZMs\"}";
        String postJson = HttpUtils.postJson("http://imis.dev.goago.cn/ptInvoiceResult/register.do", a);

        System.out.println(postJson);
    }

    @Test
    public void testpush() {
        String content = "{\"taxpayerNum\":\"GAGA000000000001\",\"enterpriseName\":\"测试注册A\",\"platformCode\":\"13242753\",\"registrationCode\":\"87774618\",\"authorizationCode\":\"4209110715\"}";
        String encrypt3des = SecurityUtil.encrypt3DES("vJxgkIaQiwPu2nWSLTSaVSwl", content);
        System.out.println(encrypt3des);
        
    }

    @Test
    public void testContent(){
        String contentJson = "{\"taxpayerNum\":\"110101201702071\",\"invoiceReqSerialNo\":\"DEMO0000000000000024\",\"itemList\":[{\"taxClassificationCode\":\"1010101020000000000\",\"invoiceAmount\":\"56.64\",\"quantity\":\"1.00\",\"taxRateValue\":\"0.13\",\"unitPrice\":\"56.64\"}]}";
        System.out.println(contentJson);
        JSONObject jsonObject = JSON.parseObject(contentJson);
        Object invoiceReqSerialNo = jsonObject.get("invoiceReqSerialNo");
        System.out.println(invoiceReqSerialNo);
        getInvoicereqSerialnoByContent(contentJson);
    }

    private String getInvoicereqSerialnoByContent(String content){
        Map<String, Object> contentMap = (Map<String, Object>) FastJsonUtils.toBean(content);
        String invoiceReqSerialNo = (String) contentMap.get("invoiceReqSerialNo");
        return invoiceReqSerialNo;
    }

    private static final String  COMPLEX_JSON_STR = "{\"teacherName\":\"crystall\",\"teacherAge\":27,\"course\":{\"courseName\":\"english\",\"code\":1270},\"students\":[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]}";

    @Test
    public void testComplexJSONStrToJSONObject(){

        JSONObject jsonObject = JSON.parseObject(COMPLEX_JSON_STR);
        //JSONObject jsonObject1 = JSONObject.parseObject(COMPLEX_JSON_STR);//因为JSONObject继承了JSON，所以这样也是可以的
        System.out.println(COMPLEX_JSON_STR);
        String teacherName = jsonObject.getString("teacherName");
        Integer teacherAge = jsonObject.getInteger("teacherAge");
        JSONObject course = jsonObject.getJSONObject("course");
        JSONArray students = jsonObject.getJSONArray("students");
        
        System.out.print(teacherAge);

    }
}
