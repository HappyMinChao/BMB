package com.vpiaotong.openapi;

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
public class Demo1 {

    private String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIVLAoolDaE7m5oMB1ZrILHkMXMF6qmC8I/FCejz4hwBcj59H3rbtcycBEmExOJTGwexFkNgRakhqM+3uP3VybWu1GBYNmqVzggWKKzThul9VPE3+OTMlxeG4H63RsCO1//J0MoUavXMMkL3txkZBO5EtTqek182eePOV8fC3ZxpAgMBAAECgYBp4Gg3BTGrZaa2mWFmspd41lK1E/kPBrRA7vltMfPj3P47RrYvp7/js/Xv0+d0AyFQXcjaYelTbCokPMJT1nJumb2A/Cqy3yGKX3Z6QibvByBlCKK29lZkw8WVRGFIzCIXhGKdqukXf8RyqfhInqHpZ9AoY2W60bbSP6EXj/rhNQJBAL76SmpQOrnCI8Xu75di0eXBN/bE9tKsf7AgMkpFRhaU8VLbvd27U9vRWqtu67RY3sOeRMh38JZBwAIS8tp5hgcCQQCyrOS6vfXIUxKoWyvGyMyhqoLsiAdnxBKHh8tMINo0ioCbU+jc2dgPDipL0ym5nhvg5fCXZC2rvkKUltLEqq4PAkAqBf9b932EpKCkjFgyUq9nRCYhaeP6JbUPN3Z5e1bZ3zpfBjV4ViE0zJOMB6NcEvYpy2jNR/8rwRoUGsFPq8//AkAklw18RJyJuqFugsUzPznQvad0IuNJV7jnsmJqo6ur6NUvef6NA7ugUalNv9+imINjChO8HRLRQfRGk6B0D/P3AkBt54UBMtFefOLXgUdilwLdCUSw4KpbuBPw+cyWlMjcXCkj4rHoeksekyBH1GrBJkLqDMRqtVQUubuFwSzBAtlc";

    private String ptPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCJkx3HelhEm/U7jOCor29oHsIjCMSTyKbX5rpoAY8KDIs9mmr5Y9r+jvNJH8pK3u5gNnvleT6rQgJQW1mk0zHuPO00vy62tSA53fkSjtM+n0oC1Fkm4DRFd5qJgoP7uFQHR5OEffMjy2qIuxChY4Au0kq+6RruEgIttb7wUxy8TwIDAQAB";

    private String password = "lsBnINDxtct8HZB7KCMyhWSJ";

    // TODO 请更换请求流水号前缀
    private String prefix = "DEMO";

    /**
     *
     * @title: testRSAGenerate
     * @description: RAS公钥私钥的生成
     * @throws Exception
     */
    @org.junit.Test
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
     *
     * @title: testRegister
     * @description: 注册接口调用
     */
    @org.junit.Test
    public void testRegister() {

        String url = "http://fpkj.testnw.vpiaotong.cn/tp/openapi/register.pt";
        Map<String, String> map = new HashMap<String, String>();
        map.put("taxpayerNum", prefix + "000000000000");
        map.put("enterpriseName", "票通信息");
        map.put("legalPersonName", "AA");
        map.put("contactsName", "AA");
        map.put("contactsEmail", "11@qq.com");
        map.put("contactsPhone", "15111111111");
        map.put("regionCode", "11");
        map.put("cityName", "海淀区");
        map.put("enterpriseAddress", "地址");
        // TODO 请修改为正确的图片Base64传
        map.put("taxRegistrationCertificate", "sdddddddddddddddddddd");
        map.put("isPermitPaperInvoice", "false");
        String content = JsonUtil.toJson(map);
        System.out.println(content);
        String buildRequest = new OpenApi(password, "11111111", "DEMO", privateKey).buildRequest(content);
        System.out.println(buildRequest);
        String response = HttpUtils.postJson(url, buildRequest);
        System.out.println(response);
    }

    /**
     *
     * @title: testGetInvoiceQrAndExtractCode
     * @description: 获取多项目开票二维码和提取码接口
     */
    @org.junit.Test
    public void testGetQrCodeByItems() {
        String url = "http://fpkj.testnw.vpiaotong.cn/tp/openapi/getQrCodeByItems.pt";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("taxpayerNum", "110101201702071");
        map.put("enterpriseName", "电子票测试新1");
        map.put("tradeNo", prefix + "10000001");
        map.put("tradeTime", "2017-06-26 09:15:54");
        map.put("invoiceAmount", "200");
        map.put("casherName", "收款人A");
        map.put("reviewerName", "审核人A");
        map.put("drawerName", "开票人A");
        map.put("allowInvoiceCount", "1");
        // map.put("smsFlag", "购买方名称");
        // map.put("expireTime", "购买方名称");
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> listMapOne = new HashMap<String, String>();
        listMapOne.put("itemName", "小麦");
        listMapOne.put("taxRateValue", "0.17");
        listMapOne.put("taxClassificationCode", "110101201702071");
        listMapOne.put("quantity", "1");
        listMapOne.put("unitPrice", "50");
        listMapOne.put("invoiceItemAmount", "50");
        Map<String, String> listMapTwo = new HashMap<String, String>();
        listMapTwo.put("itemName", "大米");
        listMapTwo.put("taxRateValue", "0.17");
        listMapTwo.put("taxClassificationCode", "110101201702071");
        listMapTwo.put("quantity", "1");
        listMapTwo.put("unitPrice", "50");
        listMapTwo.put("invoiceItemAmount", "50");
        Map<String, String> listMap3 = new HashMap<String, String>();
        listMap3.put("itemName", "小米");
        listMap3.put("taxRateValue", "0.17");
        listMap3.put("taxClassificationCode", "110101201702071");
        listMap3.put("quantity", "1");
        listMap3.put("unitPrice", "50");
        listMap3.put("invoiceItemAmount", "50");
        Map<String, String> listMap4 = new HashMap<String, String>();
        listMap4.put("itemName", "糯米");
        listMap4.put("taxRateValue", "0.17");
        listMap4.put("taxClassificationCode", "110101201702071");
        listMap4.put("quantity", "1");
        listMap4.put("unitPrice", "50");
        listMap4.put("invoiceItemAmount", "50");
        list.add(listMapOne);
        list.add(listMapTwo);
        list.add(listMap3);
        list.add(listMap4);
        map.put("itemList", list);
        String content = JsonUtil.toJson(map);
        String buildRequest = new OpenApi(password, "11111111", "DEMO", privateKey).buildRequest(content);
        String response = HttpUtils.postJson(url, buildRequest);
        System.out.println(response);
        System.out.println(new OpenApi(password, "11111111", "DEMO", privateKey).disposeResponse(response, ptPublicKey));
    }

    /**
     *
     * @title: testInvoiceBlueSimpleness
     * @description: 蓝票接口调用：单商品行蓝票
     */
    @org.junit.Test
    public void testInvoiceBlueOneItem() {
        String url = "http://fpkj.testnw.vpiaotong.cn/tp/openapi/invoiceBlue.pt";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("taxpayerNum", "110101201702071");
        // TODO 请更换请求流水号前缀
        map.put("invoiceReqSerialNo", prefix + "0000000000000024");
        map.put("buyerName", "购买方名称1");
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> listMap = new HashMap<String, String>();
        listMap.put("taxClassificationCode", "1010101020000000000");
        listMap.put("quantity", "1.00");
        listMap.put("unitPrice", "56.64");
        listMap.put("invoiceAmount", "56.64");
        listMap.put("taxRateValue", "0.13");
        list.add(listMap);
        map.put("itemList", list);
        String content = JsonUtil.toJson(map);
        System.out.println(content);
        String buildRequest = new OpenApi(password, "11111111", "DEMO", privateKey).buildRequest(content);
        System.out.println(buildRequest);
        String response = HttpUtils.postJson(url, buildRequest);
        System.out.println(response);
    }

    /**
     *
     * @title: testInvoiceBlueSimpleness
     * @description: 蓝票接口调用：单商品行蓝票(含税)
     */
    @org.junit.Test
    public void testInvoiceBlueOneItemIncludeTax() {
        String url = "http://fpkj.testnw.vpiaotong.cn/tp/openapi/invoiceBlue.pt";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("taxpayerNum", "110101201702071");
        // TODO 请更换请求流水号前缀
        map.put("invoiceReqSerialNo", prefix + "0000000000000027");
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
        String buildRequest = new OpenApi(password, "11111111", "DEMO", privateKey).buildRequest(content);
        System.out.println(buildRequest);
        String response = HttpUtils.postJson(url, buildRequest);
        System.out.println(response);
    }

    /**
     *
     * @title: testInvoiceBlueMultiItem
     * @description: 蓝票接口调用：多商品行蓝票
     */
    @org.junit.Test
    public void testInvoiceBlueMultiItem() {
        String url = "http://fpkj.testnw.vpiaotong.cn/tp/openapi/invoiceBlue.pt";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("taxpayerNum", "110101201702071");
        // TODO 请更换请求流水号前缀
        map.put("invoiceReqSerialNo", prefix + "6578902234567952");
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
        String buildRequest = new OpenApi(password, "11111111", "DEMO", privateKey).buildRequest(content);
        System.out.println(buildRequest);
        String response = HttpUtils.postJson(url, buildRequest);
        System.out.println(response);
    }

    /**
     *
     * @title: testInvoiceBlueMultiItem
     * @description: 蓝票接口调用：多商品行蓝票(含税)
     */
    @org.junit.Test
    public void testInvoiceBlueMultiItemIncludeTax() {
        String url = "http://fpkj.testnw.vpiaotong.cn/tp/openapi/invoiceBlue.pt";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("taxpayerNum", "110101201702071");
        // TODO 请更换请求流水号前缀
        map.put("invoiceReqSerialNo", prefix + "6578902234567952");
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
        String buildRequest = new OpenApi(password, "11111111", "DEMO", privateKey).buildRequest(content);
        System.out.println(buildRequest);
        String response = HttpUtils.postJson(url, buildRequest);
        System.out.println(response);
    }

    /**
     *
     * @title: testInvoiceBlueMultiItem
     * @description: 蓝票接口调用：单商品行加带折扣行蓝票,在商品行里加入折扣率即可
     */
    @org.junit.Test
    public void testInvoiceBlueOneItemAndDiscount() {
        String url = "http://fpkj.testnw.vpiaotong.cn/tp/openapi/invoiceBlue.pt";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("taxpayerNum", "110101201702071");
        // TODO 请更换请求流水号前缀
        map.put("invoiceReqSerialNo", prefix + "345678902234567955");
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
        String buildRequest = new OpenApi(password, "11111111", "DEMO", privateKey).buildRequest(content);
        System.out.println(buildRequest);
        String response = HttpUtils.postJson(url, buildRequest);
        System.out.println(response);
    }

    /**
     *
     * @title: testInvoiceBlueMultiItem
     * @description: 蓝票接口调用：单商品行加带折扣行蓝票,在商品行里加入折扣率即可(含税)
     */
    @org.junit.Test
    public void testInvoiceBlueOneItemAndDiscountIncludeTax() {
        String url = "http://fpkj.testnw.vpiaotong.cn/tp/openapi/invoiceBlue.pt";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("taxpayerNum", "110101201702071");
        // TODO 请更换请求流水号前缀
        map.put("invoiceReqSerialNo", prefix + "0000000000000032");
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
        System.out.println(response);
    }

    /**
     *
     * @title: testInvoiceBlueMultiItem
     * @description: 蓝票接口调用：单商品行使用优惠政策
     */
    @org.junit.Test
    public void testInvoiceBlueOneItemAndPreferentialPolicy() {
        String url = "http://fpkj.testnw.vpiaotong.cn/tp/openapi/invoiceBlue.pt";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("taxpayerNum", "110101201702071");
        // TODO 请更换请求流水号前缀
        map.put("invoiceReqSerialNo", prefix + "6678902234567963");
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
        System.out.println(response);
    }

    /**
     *
     * @title: testInvoiceBlueMultiItem
     * @description: 蓝票接口调用：零税率商品(使用零税率标示)
     */
    @org.junit.Test
    public void testInvoiceBlueZeroTax() {
        String url = "http://fpkj.testnw.vpiaotong.cn/tp/openapi/invoiceBlue.pt";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("taxpayerNum", "110101201702071");
        // TODO 请更换请求流水号前缀
        map.put("invoiceReqSerialNo", prefix + "5678902234567941");
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
        String buildRequest = new OpenApi(password, "11111111", "DEMO", privateKey).buildRequest(content);
        System.out.println(buildRequest);
        String response = HttpUtils.postJson(url, buildRequest);
        System.out.println(response);
    }

    /**
     *
     * @title: testInvoiceRed
     * @description: 红票接口调用
     */
    @org.junit.Test
    public void testInvoiceRed() {
        String url = "http://fpkj.testnw.vpiaotong.cn/tp/openapi/invoiceRed.pt";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("taxpayerNum", "110101201702071");
        // TODO 请更换请求流水号前缀
        map.put("invoiceReqSerialNo", prefix + "5678902234568903");
        map.put("invoiceCode", "050003522444");
        map.put("invoiceNo", "11302054");
        map.put("redReason", "冲红");
        map.put("amount", "-56.64");
        String content = JsonUtil.toJson(map);
        System.out.println(content);
        String buildRequest = new OpenApi(password, "11111111", "DEMO", privateKey).buildRequest(content);
        System.out.println(buildRequest);
        String response = HttpUtils.postJson(url, buildRequest);
        System.out.println(response);
    }

    /**
     *
     * @title: testGetNotDefaultItemInvoiceQrcode
     * @description: 获取开票二维码接口
     */
    @org.junit.Test
    public void testGetNotDefaultItemInvoiceQrcode() {
        String url = "http://fpkj.testnw.vpiaotong.cn/tp/openapi/getNotDefaultItemInvoiceQrcode.pt";
        String content = "{\"taxClassificationName\":\"食品\",\"itemName\":\"食品\",\"taxpayerNum\":\"110101201702071\",\"taxClassificationCode\":\"100100100\",\"tradeTime\":\"2016-12-22 08:15:54\",\"tradeNo\":\"XXXXXXXX\",\"invoiceAmount\":\"12.34\",\"enterpriseName\":\"电子票测试新1\",\"itemVersionIdentifiers\":\"1238834323423423423\",\"quantity\":\"1\",\"unitPrice\":\"12.34\",\"taxRateValue\":\"0.17\"}";
        String buildRequest = new OpenApi(password, "11111111", "DEMO", privateKey).buildRequest(content);
        String response = HttpUtils.postJson(url, buildRequest);
        System.out.println(response);
        System.out
                .println(new OpenApi(password, "11111111", "DEMO", privateKey).disposeResponse(response, ptPublicKey));

    }

    /**
     *
     * @title: testGetInvoiceQrAndExtractCode
     * @description: 获取开票二维码和提取码接口
     */
    @org.junit.Test
    public void testGetInvoiceQrAndExtractCode() {
        String url = "http://fpkj.testnw.vpiaotong.cn/tp/openapi/getInvoiceQrAndExtractCode.pt";
        String content = "{\"taxClassificationName\":\"食品\",\"itemName\":\"食品\",\"taxpayerNum\":\"110101201702071\",\"taxClassificationCode\":\"100100100\",\"tradeTime\":\"2016-12-22 08:15:54\",\"tradeNo\":\"XXXXXXXX\",\"invoiceAmount\":\"12.34\",\"enterpriseName\":\"电子票测试新1\",\"itemVersionIdentifiers\":\"1238834323423423423\",\"quantity\":\"1\",\"unitPrice\":\"12.34\",\"taxRateValue\":\"0.17\",\"allowInvoiceCount\":\"1\"}";
        String buildRequest = new OpenApi(password, "11111111", "DEMO", privateKey).buildRequest(content);
        String response = HttpUtils.postJson(url, buildRequest);
        System.out.println(response);
        System.out
                .println(new OpenApi(password, "11111111", "DEMO", privateKey).disposeResponse(response, ptPublicKey));

    }

    /**
     *
     * @title: testGetPTBoxStatus
     * @description: 获取票通宝状态接口
     */
    @org.junit.Test
    public void testGetPTBoxStatus() {
        String url = "http://fpkj.testnw.vpiaotong.cn/tp/openapi/getPTBoxStatus.pt";
        String content = "{\"taxpayerNum\":\"110101201702071\",\"enterpriseName\":\"电子票测试新1\"}";
        String buildRequest = new OpenApi(password, "11111111", "DEMO", privateKey).buildRequest(content);
        String response = HttpUtils.postJson(url, buildRequest);
        System.out.println(response);
        System.out
                .println(new OpenApi(password, "11111111", "DEMO", privateKey).disposeResponse(response, ptPublicKey));

    }

    /**
     *
     * @title: testGetInvoiceRepertoryInfo
     * @description: 获取库存接口
     */
    @org.junit.Test
    public void testGetInvoiceRepertoryInfo() {
        String url = "http://fpkj.testnw.vpiaotong.cn/tp/openapi/getInvoiceRepertoryInfo.pt";
        String content = "{\"taxpayerNum\":\"110101201702073\",\"enterpriseName\":\"电子票测试新1\"}";
        String buildRequest = new OpenApi(password, "11111111", "DEMO", privateKey).buildRequest(content);
        String response = HttpUtils.postJson(url, buildRequest);
        System.out.println(response);
        System.out
                .println(new OpenApi(password, "11111111", "DEMO", privateKey).disposeResponse(response, ptPublicKey));

    }

    /**
     *
     * @title: testAuthWeChatCards
     * @description:微信卡包授
     */
    @org.junit.Test
    public void testAuthWeChatCards() {
        String url = "http://fpkj.testnw.vpiaotong.cn/tp/openapi/authWeChatCards.pt";
        String content = "{\"taxpayerNum\":\"110101201702071\",\"invoiceReqSerialNo\":\"GAGA0000000000000009\"}";
        String buildRequest = new OpenApi(password, "11111111", "DEMO", privateKey).buildRequest(content);
        String response = HttpUtils.postJson(url, buildRequest);
        System.out.println(response);
        System.out
                .println(new OpenApi(password, "11111111", "DEMO", privateKey).disposeResponse(response, ptPublicKey));

    }

    /**
     *
     * @title: testInvoicePaperDestroy
     * @description: 作废接口调用
     */
    @org.junit.Test
    public void testInvoicePaperDestroy() {
        String url = "http://fpkj.testnw.vpiaotong.cn/tp/openapi/invoicePaperDestroy.pt";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("taxpayerNum", "110101201702071");
        // TODO 请更换请求流水号前缀
        map.put("invoiceReqSerialNo", prefix + "0000000000000688");
        map.put("invoiceCode", "5000153650");
        map.put("invoiceNo", "06594215");
        map.put("destroyReason", "作废的原因作废的原因原因作废的原因作废的原因");
        map.put("amount", "-113.9");
        String content = JsonUtil.toJson(map);
        System.out.println(content);
        String buildRequest = new OpenApi(password, "11111111", "DEMO", privateKey).buildRequest(content);
        System.out.println(buildRequest);
        String response = HttpUtils.postJson(url, buildRequest);
        System.out.println(response);
        System.out
                .println(new OpenApi(password, "11111111", "DEMO", privateKey).disposeResponse(response, ptPublicKey));

    }

    /**
     *
     * @title: test3DES
     * @description: 3DES加密
     */
    @org.junit.Test
    public void test3DES() {
        String content = "{\"taxpayerNum\": \"9120931023801231\",\"enterpriseName\": \"西单大悦城有限公司\",\"paymentTransID\": \"12109238102831023102983\",\"paymentType\": \"2\",\"paymentTransTime\": \"2017-01-19 18:20:09\",\"paymentTransMoney\": \"20\",\"orderID\": \"12109238102831023102981\",\"orderMoney\": \"30\"}";
        System.out.println(SecurityUtil.encrypt3DES("8f1$OeJ@eSR0z5Jh%!LmiBzi", content));
    }

    /**
     *
     * @title: test3DESDecry
     * @description: 3DES解密
     */
    @org.junit.Test
    public void test3DESDecry() {
        String str = "/WMVSKtTr2fzX4ZIeJQiqWfjst0dxVJoMrupVju7ZHFOBUD6YnTUGMAbwnaSp/wLEKFxxbGLsj9jQKSPxhKPvVZXmnEGTYsfby0KS/VrbSVAUo2EWz/53K9fajQ4Q1aoELsU/hTHO2KnTBEAEMRowGHX8lMFbvJ/ayu+PYUJ0/OHDr/dyI56fh6R6HfTvH2vsQb7ORjAk2fHHy3Bm/DFMhyqCk42ed6ygstwCXNYkjFjjCYTthYR/z801LDcOKq9TAI8TrMh/DtUXFmCRH70+e616we0Rv1zfxcmt9nqe9AI8uJUF0Bd+tdImWMF98dcclATKZ1Nk7We/xv4QUvoxsQJX2BFnCnw0rx6mPvisFcQDgo270EYLR5eFsBHy1oH1UD8VkgdjapOQzR0QhExSJ2z2bMBe56RW+kxITUE4qbP4pQd5MtSkBcc8NCSPrP2si8kQ/9R2XB+e3vCL97dvsyW4s9n2XDOsi8kQ/9R2XCwRlI/fcxKDsT8CTecGsv9Ps6UCdRNfzdBq/+JH66xM5yWBq5l5ZDSmZfvpMFpwc3dn/Zh4wbNEPhJi8S/TmAHIr8gLw2EgzT2f979G/8kboBAaWbrt1bgQ7sUHNGmvrE=";
        System.out.println(SecurityUtil.decrypt3DES("IskuI3GAMm2DAfaweCcndyCp", str));
    }

    /**
     *
     * @title: testBase64
     * @description: Base64编码
     */
    @org.junit.Test
    public void testBase64() {
        String str = "JJON0d93C9nQN013N+cCwwIYbRVYlWChGQkSgAWG8g4mD1xFU6oGPauqih5gW7ZTcpejSPS8TqRbdBFdBATSXdwZqPM0q8sVYf3xwlp8OEw6INcUCvRW7myiFkzSJLV4Ost42d5Xp+sicgMj0bn99BsRSqe06BMvYTA46L/vGGPqN4tuuy2B/enpkGLcOQdPdtC+wG8ub6+zykisJT5I7EMls73cjaSlj1iRw/PT9huULu97iPHIiqnKhK05AXkvgWMcfg42+bLeG/kPgbaAtwAkXN/yDkKACcDML2WE8TZ+BFsaQPbH+BfY/XQ4VXSYF5NGeulhDJr1DLIHgH+KNQ==";
        System.out.println(Base64Util.encode2String(str));
    }

    /**
     *
     * @title: testSign
     * @description: RSA签名
     */
    @org.junit.Test
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
    @org.junit.Test
    public void testVerify() {
        String requestJson = "{\"format\":\"JSON\",\"sign\":\"i8VGz3Qs4tnw89aeJON21nn2h8oQAynM4BALX8iKb+BlfXvIc2PS1hHen0cnJm53bvgRfyVCEgXQkpFK1Rdiw6ht3KD1IxtYMSkISUuwRphuhwPQiVVsODZiGSRQpHCwcv+I7szWhOErY0sYBqPHfHSsvZ4bD/rOp7K+0Lp/sw0\\u003d\",\"signType\":\"RSA\",\"version\":\"1.0\",\"platformCode\":\"TohDKeNU\",\"content\":\"EYY+FTkOvZs70g6eplhvD8P3oOZBVwtO1mN3dwDgNo0wCi9URbwtGJBqiaKUpjvn43HuE04aelW7J+ZgewZG5AmgdbY0aFhDejb5czd6UPt8DWLK9NyrgKMtKBc4LULfPKFhQwchtfgWWJtEHMrIyyHQPe5N5vgmAGGBE4nYUyazp6p42JnMLJCUHHNKsI2zApQWp20I1BCMhT/XZc+jXZ3q50UmHJP9l4vKCfESLxwahdADgB2HLqRWLr1OgxYenUUNkokwtV5TxQBYr/iKdQ03HFEVjNV+tuTTrcdakA/tF0UOc71roxxmHFgJU8bl64U+KRMEM1cQoEDwRzjqcbfGRrj/JFEqQel2k7mDsNZi24EvIExNDFautR9XLsuryZ8vrDwK33Ln/oYz+K5wooyf13kv8KDQgVhWCkW3XmmWwGMCttzOP9pVbiD3WP6MJ5qmhtK7R4JBibMoLAjFUcA2IZgk9tnphTUlQZcFEIPCT0cv0fyvuw\\u003d\\u003d\",\"timestamp\":\"2017-05-12 13:55:06\",\"serialNo\":\"CHSE20170512135506OW6jE08r\"}";

        Map<String, String> paramMap = JsonUtil.json2Map(requestJson);
        String sign = paramMap.remove("sign");
        String a = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCdL0fHPUtT9bTc8tTx5cSNnLO09P/4pTnYyPpp9Jt+NU+nTYiVjDrX97a7M0NuuoxpX4KSFz3WKTgGir+uYcQUm50+oEaRI1gQZrjnIfXEyafdS1Gcr34OAvObxvShQst/p3swVatqK4b7/SfYrQN6S4tQ4L+06QBKgvvaxpGPTwIDAQAB";
        boolean verify = RSAUtil.verify(RSAUtil.getSignatureContent(paramMap), sign, a);
        System.out.println(verify);
        System.out.println(ptPublicKey.length());
        System.out.println(privateKey.length());
    }

    /**
     *
     * @title: testPush
     * @description:testPush
     */
    @org.junit.Test
    public void testPush() {
        String a = "{\"sign\":\"hmoBFbyTi1QmnMtPcE8/XUSR6HcNVQ4hR2ZBHbgzs7m6dKeNjBuNoE5rqcEmAaR/PPTwlO0NMlvY+z21mr3LNOe/NI6mXzaithXruCqCJeBdbzj1YZmqmcr1evxfNAaP+ThzII6rMVhqHnOqt38sDyh1PFb6Lcg8S93smnYwp+8\\u003d\",\"timestamp\":\"2017-05-19 14:12:55\",\"content\":\"c3PX8j3oA9qlajRp8VsSxVAeABJFVFrndbivQXMoaFv5nnMQ3P2vziTi4d6XY8t0u4abbhMUElprxSMhy5880hTeUkVPVKu2oinrVJW227hdToKathg+KhE86RccUS4v/t7+MI7KL7c3lonzpC2hmFAFGoZL8ncK1P1dh6Smf53IDTtJ6Wxi3dVKJt6XHw4IK+kEYcrO/dG2E7GEfoUT/w\\u003d\\u003d\",\"serialNo\":\"GAGA201705191412550mACGnRI\",\"platformCode\":\"vZpDTUSV\",\"signType\":\"RSA\",\"format\":\"JSON\",\"version\":\"1.0\"}";
        String requestJson = "{\"format\":\"JSON\",\"sign\":\"eGhssKmnCMh1UckqqIqhv8XBiTPOU3JqGBHlflc8lZWHJtMBnN4r5Q2ovgNqkfZv78wE8jpiaucpQGKmHKXOc9Tda0oWcW5o0jF6mPtN5+xgRjeZIVQN1ZGGLay5/OQb/9SlwRdFN1a1zy0uAwjn+57i4UivS2NEeAn2Xd6yjvI\\u003d\",\"signType\":\"RSA\",\"version\":\"1.0\",\"platformCode\":\"1Ie3pU3A\",\"content\":\"QfjtruvJgIDYjKMCC92dt2QDqulOgslkDpVfwE3aOgULxrY52WlS4jLoADo/MIdqa/tqETq46Po0u7KGk9k5t1NYA0J/LYGuLmqfGYLjOXT0M8HLFK3UTwN4sgCK03bBzaRfD8xjR0X21E+TzgMuPCtQ8/Wgt5TK2i9l0CzeclbBlVRAJT7mGyzQI2rlvd9ffM7Ok+kk/IvdxMfZ+ZfDAg\\u003d\\u003d\",\"timestamp\":\"2017-05-16 15:53:56\",\"serialNo\":\"WYTC20170516155356pS5CzZMs\"}";
        String postJson = HttpUtils.postJson("http://imis.dev.goago.cn/ptInvoiceResult/register.do", a);

        System.out.println(postJson);
    }

    @org.junit.Test
    public void testpush() {
        String content = "{\"taxpayerNum\":\"GAGA000000000001\",\"enterpriseName\":\"测试注册A\",\"platformCode\":\"13242753\",\"registrationCode\":\"87774618\",\"authorizationCode\":\"4209110715\"}";
        String encrypt3des = SecurityUtil.encrypt3DES("vJxgkIaQiwPu2nWSLTSaVSwl", content);
        System.out.println(encrypt3des);
    }
    
}
