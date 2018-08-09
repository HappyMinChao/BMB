package com.vpiaotong.openapi;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vpiaotong.openapi.util.*;
import org.junit.Assert;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;

/**
 * 
 * @author yanfei.zhao
 *
 */
public class Demo_test {

	/**
	 * 合作伙伴私钥
	 */
	private String privateKey = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBANngWqoWDx06I+fCj9INhfvtfDs+iD/hQVRo6pl8/ZhgniRvAyO8VgzwqLdQHoX5QoDMVV3WWxsufXHs/g6oVr6wvGnV3/guSWf+RN1gXqeZQ+xUAkA0xMh6MwJgDkhqMMO/37J2NTA9XEGp20UA2MptncxSiMfMgUMOqCg3VcaRAgMBAAECgYEAvubMqgEgBxzdPwGD4KnGvwHL+2EEIuyswFgZTpls3imyBvxaBOLNhxMK0WvbAspvUck39zuNolhuw/bCeHdZYKboxLBzDMoGdYVko7aLE/vpYAOn5AAemP4hjVPcLHuDowlEC5oqyr7WxgLEFXpV92H8mIFR1FeIqf4NSVo5mAECQQD/7FDDerTgwbzXUBFL3KTPmhMoZ4AM1N7hDJBLUCjUpf4pqayLbC13psd7ZCcJRkXLxDOWUwA93u0N4oCGsd6BAkEA2fEcvpDXFwl15VYKzi1W53AUfF079WfoSxA4QKqhfvBSSUWi2OkmZP7+I+GZRwhFtc8nSOPISogPz5D5zoYAEQJAXwRSCAGq3jeP8TDc+xl0Z0+BBWNH+3udNFkBybR9NFZcwwMn+Ku1RdM6BOnuRFc+2qSBsrImSNJXmOwbY+buAQJBAIx3AR61fVuEpm60IOy3fh5WN23cLWvCl43hm0/aaZHF1FMTRZzlz/AiBKwdl8uXQk6mf9xTquCWhxIxjz+TT/ECQQDqI9dykHjU4GOsXyYSjUAHNsnnmKK5B6Mac0XOzL6H3Nb09QRwm0JkxlvMDVwNXO8XpQmPXtV5XQoorobUMRwj";

	/**
	 * 平台公钥
	 */
	private String kyPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCIJ1wD1QKmqZ63VHyvs0ibA9Y7DepjbhS7qu9Nz1zp8Z9rNarW0zCCxmO7fAXJ3/KsewjZPLEHZiJh8Ufex8g4rgxS/CobaHbz7IK5soUkKObO3B0o9erWwsKaeVEPvJlq18jZPIDaThvbrUurhWAarHmU6lNaI9tFwdU9+8xdOwIDAQAB";

	/**
	 * 3desKey
	 */
	private String desKey = "lsBnINDxtct8HZB7KCMyhWSJ";

	/**
	 * 合作伙伴简称
	 */
	// TODO 请更换请求流水号前缀
	private String prefix = "KYPT0001";
	
	/**
	 * 合作伙伴编码
	 */
	String platformCode = "00000000";
	
	/**
	 * 生产环境URL
	 */
	String prodUrl = "https://jh.cardinfo.com.cn/gateway";
	
	/**
	 * 测试环境URL
	 */
	String testUrl = "http://10.10.128.22:8080/gateway";
	
	/**
	 * 注册企业
	 */
	@org.junit.Test
	public void testRegister() {

		String url = testUrl + "/einvoice/register";
		Map<String, String> map = new HashMap<>();
		map.put("taxpayerNum", "110101201702071");
		map.put("enterpriseName", "安徽天康（集团）股份有限公司");
		map.put("legalPersonName", "赵宽");
		map.put("contactsName", "赵宽");
		map.put("contactsEmail", "170982438@qq.com");
		map.put("contactsPhone", "18510449865");
		map.put("regionCode", "34");
		map.put("cityName", "滁州市");
		map.put("enterpriseAddress", "安徽省天长市仁和南路20号");
		
		Gson gson = new Gson();
		String content = gson.toJson(map);
		System.out.println(content);
		String buildRequest = new OpenApi(desKey, platformCode, prefix,
				privateKey).buildRequest(content);
		System.out.println(buildRequest);
		String response = HttpUtils.postJson(url, buildRequest);
		System.out.println("响应数据" + response);
		try {
			String disposeResponse = new OpenApi(desKey, "", "", "")
					.disposeResponse(response, kyPublicKey);
			System.out.println("响应数据明文=" + disposeResponse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	@org.junit.Test
	public void testEinvoiceQuery() {

		String url = testUrl + "/einvoice";
		Map<String, String> map = new HashMap<>();
		map.put("taxpayerNum", "91110108MA0043365M");
		map.put("invoiceReqSerialNo", "KYPT0001000000000011");
		Gson gson = new Gson();
		String content = gson.toJson(map);
		System.out.println(content);
		String buildRequest = new OpenApi(desKey, platformCode, prefix,
				privateKey).buildRequest(content);
		System.out.println(buildRequest);
		String response = HttpUtils.postJson(url, buildRequest);
		System.out.println("响应数据+++++++" + response);
		
		try {
			String disposeResponse = new OpenApi(desKey, "", "", "")
					.disposeResponse(response, kyPublicKey);
			System.out.println("响应数据明文++++++++++=" + disposeResponse);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@org.junit.Test
	public void testCardPackageAuth() {

		String url = testUrl+ "/cardpackage/auth";
		Map<String, String> map = new HashMap<>();
		map.put("taxpayerNum", "91110108MA0043365M");
		map.put("invoiceReqSerialNo", "KYPT0001000000000011");
		Gson gson = new Gson();
		String content = gson.toJson(map);
		System.out.println(content);
		String buildRequest = new OpenApi(desKey, platformCode, prefix,
				privateKey).buildRequest(content);
		System.out.println(buildRequest);
		String response = HttpUtils.postJson(url, buildRequest);
		System.out.println("响应数据" + response);
		
		try {
			String disposeResponse = new OpenApi(desKey, "", "", "")
					.disposeResponse(response, kyPublicKey);
			System.out.println("响应数据明文=" + disposeResponse);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@org.junit.Test
	public void testGetQRcode() {

		String url = testUrl + "/einvoice/qrcode";
		
		Map<String, Object> map = new HashMap<>();
		map.put("taxpayerNum", "91110108MA0043365M");
		map.put("enterpriseName", "北京票通信息技术有限公司");
		map.put("tradeNo", "123456");
		map.put("tradeTime", "2018-02-07 17:00:32");
		map.put("invoiceAmount", "56.64");
		
		// TODO 请更换请求流水号前缀
		map.put("invoiceReqSerialNo", "KYPT0001000000000024");
		map.put("buyerName", "购买方名称");
		List<Map<String, String>> list = new ArrayList<>();
		Map<String, String> listMapOne = new HashMap<>();
		listMapOne.put("itemName", "测试发票");
		listMapOne.put("taxRateValue", "0");
		listMapOne.put("taxClassificationCode", "1010101020000000000");
		listMapOne.put("quantity", "1.00");
		listMapOne.put("unitPrice", "56.64");
		listMapOne.put("invoiceItemAmount", "56.64");
		
		list.add(listMapOne);
		map.put("itemList", list);
		
		Gson gson = new Gson();
		String content = gson.toJson(map);
		System.out.println(content);
		String buildRequest = new OpenApi(desKey, platformCode, prefix,
				privateKey).buildRequest(content);
		System.out.println(buildRequest);
		String response = HttpUtils.postJson(url, buildRequest);
		System.out.println("响应数据" + response);
		
		try {
			String disposeResponse = new OpenApi(desKey, "", "", "")
					.disposeResponse(response, kyPublicKey);
			System.out.println("响应数据明文=" + disposeResponse);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@org.junit.Test
	public void testGetQRcodeInfo() {

		String url = testUrl + "/einvoice/qrcodeinfo";
		
		Map<String, Object> map = new HashMap<>();
		map.put("taxpayerNum", "110101201702071");
		map.put("tradeNo", "123456");
		map.put("invoiceAmount", "56.64");
		
		Gson gson = new Gson();
		String content = gson.toJson(map);
		System.out.println(content);
		String buildRequest = new OpenApi(desKey, platformCode, prefix,
				privateKey).buildRequest(content);
		System.out.println(buildRequest);
		String response = HttpUtils.postJson(url, buildRequest);
		System.out.println("响应数据" + response);
		
		try {
			String disposeResponse = new OpenApi(desKey, "", "", "")
					.disposeResponse(response, kyPublicKey);
			System.out.println("响应数据明文=" + disposeResponse);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	

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
	 * @title: testRequest
	 * @description: 构建返回消息
	 */
	@org.junit.Test
	public void testRequest() {
		// 组装内容json字符串
		String request = "{\"code\":\"0000\",\"msg\":\"处理成功\",\"sign\":\"IZwZbuzdUlgqLNBjdpAVApRg4DTBW/ayu3ypEyxMlQzPl1v/rix4S33OMPX8ERdbjnKbcTfY/hii1BTjk8Sf2JBeJ4mAn7vVGd+b1+Q24dmOEGz0mNHWT3bai6Bbs1DPJCyg+WSbnj84Zo3oAbSdVGEFLOLnbjH3Lsew0ZlpKt8\\u003d\",\"serialNo\":\"DYCX20170119135647nkxQRZwl\",\"content\":\"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\\u003d\\u003d\"}";
		// 报文3DES加密
		System.out
				.println(new OpenApi(
						"8f1$OeJ@eSR0z5Jh%!LmiBzi",
						"12345678",
						"DYCX",
						"MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAId9YUdOmcm96vR/QY4Dlz35yyyM9qwzmDGvVjEkjNg3XdJKtD+PFBvQpF3hDq0j7tqncdefVfUl+U/8FpDl6ibwu4DbpHzQc+A3/BJSzqdyZ9qCy4I+I/3OLMSRIMJr36go69HvN40OlwT4cjAJTVhzeZ7jyvb6IZa9VH8hQvwbAgMBAAECgYAjjgVvHvNAe7QigRqE1/BidSCaCkvZGJkBwM9PihXjz3OxcaUnYLaYLgJUGHEvglR10KcRPGDkOhjYooCReaQePeNubi/rNjACR8vYpS7yzN+2J3G3elkknDi25xvGL8iDO09USBLLqsiYw4N/NY8SI1GfwmaTg0qczaWqf0y7IQJBAMf+r5O74Rba0gydHADWJL9uV92iPsFkIkeLU9C15nadi45TaCRD+2EA8K885MBQkhoH0S33uwk3bZv9yLnD5PMCQQCtbm0MRbX7OXvZK+2kO9pCC8Y82Ip9bMhNqL9R1pSxbgI6wSVbvbekabKTgzGbW/yWD8gc6rHlNn3p9TXtr3Y5AkBpweRW2yNMUqqAwRPt3U/RayhLq2BEJA98hdDsrUQRtX325WRMySZFRZoBENJmYDTiTv/urvHZDdaIhst+cHjBAkEAgPszFoFxyhkCRo0o+4+Xzq67C91vgcA5Z6gsQUz44MwT1GZ4xjXeMeR57sMPTZ+nxmKOTRMuom1YT3sSVGa94QJBALkvrwOu+SB/2EvWikpPlM70aG9b2W/iLNI+8/o8g8u7VRPJzAH0RHWV2xUhO/1Nrr8+VKIs97gk2dsHbfAgR+8=")
						.buildRequest(request));
	}

	/**
	 * 
	 * @title: testRequest
	 * @description: 对接收消息进行验签
	 */
	@org.junit.Test
	public void testResponse() {
		String responseStr = "{\"sign\":\"GX2Acam95T/qULsd5TwIKQ1fUdwQc2IH1FWEfq5VALqcLc2otFmltxz0ll2oI8LOUoOg5awma44yzv1yT1Y1uJ4i4AY/TTyiQ82CPSkdVFc0sAxdzK/7Ftn+ElXBXF/tbS5EdJO2B3ML2BsKItXZby/sRfReiGmhu3EcnDgzIqk\\u003d\",\"timestamp\":\"2017-04-19 11:13:54\",\"content\":\"H3xxyxwskyef5/5kD6PM+PTaDilrnqeBWc3vI6c8xGSsvBbyLd0BA18P4p5CZK7ZMM4C3j0MuYa58P1rgwriZ/xk3bjYBZ7iCCarGv+ewKC+RL0RXsKFaW5oUZ64sYmIj0LKhOvceaqvVAPNUj0j1EpS8aoEkVTcfNcWKiwN/BSzicFKsdrrzrPeIPbCtgd0bAYCDCfiAmkE/Nt8wBPFqQ\\u003d\\u003d\",\"serialNo\":\"SDZS20170419111354HqMiR5zD\",\"platformCode\":\"KUXO88Q9\",\"signType\":\"RSA\",\"format\":\"JSON\",\"version\":\"1.0\"}";
		;
		System.out
				.println(new OpenApi(
						desKey,
						"12345678",
						"DYCX",
						"MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAId9YUdOmcm96vR/QY4Dlz35yyyM9qwzmDGvVjEkjNg3XdJKtD+PFBvQpF3hDq0j7tqncdefVfUl+U/8FpDl6ibwu4DbpHzQc+A3/BJSzqdyZ9qCy4I+I/3OLMSRIMJr36go69HvN40OlwT4cjAJTVhzeZ7jyvb6IZa9VH8hQvwbAgMBAAECgYAjjgVvHvNAe7QigRqE1/BidSCaCkvZGJkBwM9PihXjz3OxcaUnYLaYLgJUGHEvglR10KcRPGDkOhjYooCReaQePeNubi/rNjACR8vYpS7yzN+2J3G3elkknDi25xvGL8iDO09USBLLqsiYw4N/NY8SI1GfwmaTg0qczaWqf0y7IQJBAMf+r5O74Rba0gydHADWJL9uV92iPsFkIkeLU9C15nadi45TaCRD+2EA8K885MBQkhoH0S33uwk3bZv9yLnD5PMCQQCtbm0MRbX7OXvZK+2kO9pCC8Y82Ip9bMhNqL9R1pSxbgI6wSVbvbekabKTgzGbW/yWD8gc6rHlNn3p9TXtr3Y5AkBpweRW2yNMUqqAwRPt3U/RayhLq2BEJA98hdDsrUQRtX325WRMySZFRZoBENJmYDTiTv/urvHZDdaIhst+cHjBAkEAgPszFoFxyhkCRo0o+4+Xzq67C91vgcA5Z6gsQUz44MwT1GZ4xjXeMeR57sMPTZ+nxmKOTRMuom1YT3sSVGa94QJBALkvrwOu+SB/2EvWikpPlM70aG9b2W/iLNI+8/o8g8u7VRPJzAH0RHWV2xUhO/1Nrr8+VKIs97gk2dsHbfAgR+8=")
						.disposeResponse(
								responseStr,
								"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCHfWFHTpnJver0f0GOA5c9+cssjPasM5gxr1YxJIzYN13SSrQ/jxQb0KRd4Q6tI+7ap3HXn1X1JflP/BaQ5eom8LuA26R80HPgN/wSUs6ncmfagsuCPiP9zizEkSDCa9+oKOvR7zeNDpcE+HIwCU1Yc3me48r2+iGWvVR/IUL8GwIDAQAB"));
	}


	
	

	





	/**
	 * 
	 * @title: test3DES
	 * @description: 3DES加密
	 */
	@org.junit.Test
	public void test3DES() {
		String content = "{\"taxpayerNum\": \"9120931023801231\",\"enterpriseName\": \"西单大悦城有限公司\",\"paymentTransID\": \"12109238102831023102983\",\"paymentType\": \"2\",\"paymentTransTime\": \"2017-01-19 18:20:09\",\"paymentTransMoney\": \"20\",\"orderID\": \"12109238102831023102981\",\"orderMoney\": \"30\"}";
		System.out.println(SecurityUtil.encrypt3DES("8f1$OeJ@eSR0z5Jh%!LmiBzi",
				content));
	}

	/**
	 * 
	 * @title: test3DESDecry
	 * @description: 3DES解密
	 */
	@org.junit.Test
	public void test3DESDecry() {
		String str = "PdCTi+1Ejs9Poi/PWg7f7BhxQ+dX/7A5y7r7ZhglYacRCFWkOaCP2LeC7aQpk0fHonJB2nA5XqPxjtAeBjcoz8LBh3UQWjcpcLfQ1Moqy5gL4G1PRu7DE7mEM7gGGQOjBSjMN+d37jsAJFzf8g5CgAnAzC9lhPE2d/TMQqgK2ybcGajzNKvLFSqGSpcBf9cnmth54ZK1RsU1O4X/W4EoHrsu1dE22JLku3svuYdfyxjfXXL6kdjPofkttxbSYqZT1z5lccXrPpW4FnrjqQ5/9b5dvVySBZw5gudVDS1QVDs6INcUCvRW7myiFkzSJLV4Ost42d5Xp+sx+Z34oV4d0yOgWlpRLu5AHZPQLjxZTgs\\u003d\\";
		System.out.println(SecurityUtil.decrypt3DES("8f1$OeJ@eSR0z5Jh%!LmiBzi",
				str));
	}

	/**
	 * 
	 * @title: testBase64
	 * @description: Base64编码
	 */
	@org.junit.Test
	public void testBase64() {
		String str = "JJON0d93C9nQN013N+cCwwIYbRVYlWChGQkSgAWG8g4mD1xFU6oGPauqih5gW7ZTcpejSPS8TqRbdBFdBATSXdwZqPM0q8sVYf3xwlp8OEw6INcUCvRW7myiFkzSJLV4Ost42d5Xp+sicgMj0bn99BsRSqe06BMvYTA46L/vGGPqN4tuuy2B/enpkGLcOQdPdtC+wG8ub6+zykisJT5I7EMls73cjaSlj1iRw/PT9huULu97iPHIiqnKhK05AXkvgWMcfg42+bLeG/kPgbaAtwAkXN/yDkKACcDML2WE8TZ+BFsaQPbH+BfY/XQ4VXSYF5NGeulhDJr1DLIHgH+KNQ==";
		Assert.assertEquals(
				"Cw/TJeTTrq1p2kycOqFH4VIqBto0czXNui3T9fiZ4cVg+aaCIfGbsRZN4GXNv88qhlT5C1gCZHkv9Ea6qpQ8YVw5pswLO+3ZdW0Hk8wIKH+hjWtZvdvquTU7hf9bgSgeHlXmjmqRN9UFcJC+j9OB8+tcfUu/x6ffJ97dcveaLjLqN4tuuy2B/Vu45oybFKUf+50w/pKQl5sMWySiudjbKOJObC++6jaL1LSwyYhAWQsVpq+9hNe/4SanIFz/VroduYQzuAYZA6MeEXqeb98ePnlfDFIcMTWHawJW0sT1jYQSey3SM5osuP4h87TDuiqaeAZUSXCKBYjHfKzklbTve+4WtMsGXisrcc6O0WFhrds=",
				Base64Util.encode2String(str));
	}
	
	/**
	 * 
	 * @title: testSign
	 * @description: RSA签名
	 */
	@org.junit.Test
	public void testSign() {
		String unsign = "content=PdCTi+1Ejs9Poi/PWg7f7BhxQ+dX/7A5y7r7ZhglYacRCFWkOaCP2LeC7aQpk0fHonJB2nA5XqPxjtAeBjcoz8LBh3UQWjcpcLfQ1Moqy5gL4G1PRu7DE7mEM7gGGQOjBSjMN+d37jsAJFzf8g5CgAnAzC9lhPE2d/TMQqgK2ybcGajzNKvLFSqGSpcBf9cnmth54ZK1RsU1O4X/W4EoHrsu1dE22JLku3svuYdfyxgz7kGUoiN0H/8Qq0yx69p11z5lccXrPpW4FnrjqQ5/9b5dvVySBZw5gudVDS1QVDs6INcUCvRW7myiFkzSJLV4Ost42d5Xp+sx+Z34oV4d0yOgWlpRLu5AHZPQLjxZTgs=&format=JSON&platformCode=12345678&serialNo=DYCX2017012015132010450433&signType=RSA&timestamp=2017-01-20 15:13:20&version=1.0";
		String str = "content=PdCTi+1Ejs9Poi/PWg7f7BhxQ+dX/7A5y7r7ZhglYacRCFWkOaCP2LeC7aQpk0fHonJB2nA5XqPxjtAeBjcoz8LBh3UQWjcpcLfQ1Moqy5gL4G1PRu7DE7mEM7gGGQOjBSjMN+d37jsAJFzf8g5CgAnAzC9lhPE2d/TMQqgK2ybcGajzNKvLFSqGSpcBf9cnmth54ZK1RsU1O4X/W4EoHrsu1dE22JLku3svuYdfyxgz7kGUoiN0H/8Qq0yx69p11z5lccXrPpW4FnrjqQ5/9b5dvVySBZw5gudVDS1QVDs6INcUCvRW7myiFkzSJLV4Ost42d5Xp+sx+Z34oV4d0yOgWlpRLu5AHZPQLjxZTgs=&format=JSON&platformCode=12345678&serialNo=DYCX2017012015132091976377&signType=RSA&timestamp=2017-01-20 15:13:20&version=1.0";
		String sign = "KvMUk4OnZhtm8cWhP5il+S++cEMqUf2WEY8ObbXHLCkjTvlg6tVJ1uq5CkxajGt+tGuRNYEZcq5jpSXRrh6Nsdx2sZDr8QzB/YAFMalZu2ZW7WelTBTO7o2IA2EJb+fqeZBZnFiEHbGYO8EDcufQ9or1BJSuJc+jLwXVuy6dESQ=";
		Assert.assertEquals(unsign, str);
		Assert.assertEquals(
				sign,
				"KvMUk4OnZhtm8cWhP5il+S++cEMqUf2WEY8ObbXHLCkjTvlg6tVJ1uq5CkxajGt+tGuRNYEZcq5jpSXRrh6Nsdx2sZDr8QzB/YAFMalZu2ZW7WelTBTO7o2IA2EJb+fqeZBZnFiEHbGYO8EDcufQ9or1BJSuJc+jLwXVuy6dESQ=");

		Assert.assertEquals(
				"KvMUk4OnZhtm8cWhP5il+S++cEMqUf2WEY8ObbXHLCkjTvlg6tVJ1uq5CkxajGt+tGuRNYEZcq5jpSXRrh6Nsdx2sZDr8QzB/YAFMalZu2ZW7WelTBTO7o2IA2EJb+fqeZBZnFiEHbGYO8EDcufQ9or1BJSuJc+jLwXVuy6dESQ=",
				RSAUtil.sign(
						str,
						"MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAId9YUdOmcm96vR/QY4Dlz35yyyM9qwzmDGvVjEkjNg3XdJKtD+PFBvQpF3hDq0j7tqncdefVfUl+U/8FpDl6ibwu4DbpHzQc+A3/BJSzqdyZ9qCy4I+I/3OLMSRIMJr36go69HvN40OlwT4cjAJTVhzeZ7jyvb6IZa9VH8hQvwbAgMBAAECgYAjjgVvHvNAe7QigRqE1/BidSCaCkvZGJkBwM9PihXjz3OxcaUnYLaYLgJUGHEvglR10KcRPGDkOhjYooCReaQePeNubi/rNjACR8vYpS7yzN+2J3G3elkknDi25xvGL8iDO09USBLLqsiYw4N/NY8SI1GfwmaTg0qczaWqf0y7IQJBAMf+r5O74Rba0gydHADWJL9uV92iPsFkIkeLU9C15nadi45TaCRD+2EA8K885MBQkhoH0S33uwk3bZv9yLnD5PMCQQCtbm0MRbX7OXvZK+2kO9pCC8Y82Ip9bMhNqL9R1pSxbgI6wSVbvbekabKTgzGbW/yWD8gc6rHlNn3p9TXtr3Y5AkBpweRW2yNMUqqAwRPt3U/RayhLq2BEJA98hdDsrUQRtX325WRMySZFRZoBENJmYDTiTv/urvHZDdaIhst+cHjBAkEAgPszFoFxyhkCRo0o+4+Xzq67C91vgcA5Z6gsQUz44MwT1GZ4xjXeMeR57sMPTZ+nxmKOTRMuom1YT3sSVGa94QJBALkvrwOu+SB/2EvWikpPlM70aG9b2W/iLNI+8/o8g8u7VRPJzAH0RHWV2xUhO/1Nrr8+VKIs97gk2dsHbfAgR+8="));
	}

	/**
	 * 
	 * @title: testVerify
	 * @description:RSA验签
	 */
	@org.junit.Test
	public void testVerify() {
		String requestJson = "{\"sign\":\"GX2Acam95T/qULsd5TwIKQ1fUdwQc2IH1FWEfq5VALqcLc2otFmltxz0ll2oI8LOUoOg5awma44yzv1yT1Y1uJ4i4AY/TTyiQ82CPSkdVFc0sAxdzK/7Ftn+ElXBXF/tbS5EdJO2B3ML2BsKItXZby/sRfReiGmhu3EcnDgzIqk\\u003d\",\"timestamp\":\"2017-04-19 11:13:54\",\"content\":\"H3xxyxwskyef5/5kD6PM+PTaDilrnqeBWc3vI6c8xGSsvBbyLd0BA18P4p5CZK7ZMM4C3j0MuYa58P1rgwriZ/xk3bjYBZ7iCCarGv+ewKC+RL0RXsKFaW5oUZ64sYmIj0LKhOvceaqvVAPNUj0j1EpS8aoEkVTcfNcWKiwN/BSzicFKsdrrzrPeIPbCtgd0bAYCDCfiAmkE/Nt8wBPFqQ\\u003d\\u003d\",\"serialNo\":\"SDZS20170419111354HqMiR5zD\",\"platformCode\":\"KUXO88Q9\",\"signType\":\"RSA\",\"format\":\"JSON\",\"version\":\"1.0\"}";
		Map<String, String> paramMap = new Gson().fromJson(requestJson,
				new TypeToken<Map<String, String>>() {
				}.getType());
		String sign = paramMap.remove("sign");
		System.out.println("sign=" + sign);
		boolean verify = RSAUtil.verify(RSAUtil.getSignatureContent(paramMap),
				sign, kyPublicKey);
		System.out.println(verify);
	}

	@org.junit.Test
	public void testVerify1() {
		String requestJson = "{\"format\":\"JSON\",\"sign\":\"Ha/9deVvg2IoUScAmXumEdVOb1n+xUgxKURn4/5h5NsE6x8vDOTAB9ojmriVisdqV5NPZ8b8IpV34yKHiDbUUOg/aeLA7nnq9duJn7y32nLObXHqHzycrCNfwSL1WlgaLfxTRk5RbslyXtARbe3K09t5UUSZ6UBSkQS6xYOJHxA\\u003d\",\"signType\":\"RSA\",\"version\":\"1.0\",\"platformCode\":\"11111111\",\"content\":\"9Kval5TJJQUKWAvqqwMVPinrBAbQJUKU0BbykxZK1WQghouTV/GBnbLL3KZ0/iXCfTXcM2S1AFAkG5Kn3qXqeVMkQt/rzOa/VBga6DJgXgZ5K6imMRDGlQHYFDCcwB6Cysf9TG2I+vv7WOK1J/H5zD1jetMXZnLVfvKMjqouFooY8slo3tbeZXyDUeZgH5JTqGKqWbCzuCwghouTV/GBnaOHtgK5iI1SA4eJsWsMycOatphBCZ0g1iL0MwiSRYkFtfb6RYmYqehvLNQX+GQmuDnUY3JOO4gy03ppb87kOuu1mgGRRMeXrIZcXePwrLvBHyzsmr2C7E6cfwNLn52gKw\\u003d\\u003d\",\"timestamp\":\"2018-01-31 17:58:25\",\"serialNo\":\"DEMO20180131175825Sd1I5cha\"}";

		try {
			String disposeResponse = new OpenApi(desKey, "", "", "")
					.disposeResponse(requestJson, kyPublicKey);
			System.out.println(disposeResponse);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Map<String, String> paramMap = new Gson().fromJson(requestJson,
		// new TypeToken<Map<String, String>>() {}.getType());
		// String sign1 = paramMap.get("sign");
		// System.out.println("sign1="+ sign1);
		// String sign = paramMap.remove("sign");
		// String sign2 =
		// "Ha/9deVvg2IoUScAmXumEdVOb1n+xUgxKURn4/5h5NsE6x8vDOTAB9ojmriVisdqV5NPZ8b8IpV34yKHiDbUUOg/aeLA7nnq9duJn7y32nLObXHqHzycrCNfwSL1WlgaLfxTRk5RbslyXtARbe3K09t5UUSZ6UBSkQS6xYOJHxA\\u003d";
		// boolean verify =
		// RSAUtil.verify(RSAUtil.getSignatureContent(paramMap),sign2 ,
		// kyPublicKey);
		// System.out.println(verify);
	}

	// @org.junit.Test
	// public void testVerify1() {
	// String requestJson =
	// "{\"sign\":\"GX2Acam95T/qULsd5TwIKQ1fUdwQc2IH1FWEfq5VALqcLc2otFmltxz0ll2oI8LOUoOg5awma44yzv1yT1Y1uJ4i4AY/TTyiQ82CPSkdVFc0sAxdzK/7Ftn+ElXBXF/tbS5EdJO2B3ML2BsKItXZby/sRfReiGmhu3EcnDgzIqk\\u003d\",\"timestamp\":\"2017-04-19 11:13:54\",\"content\":\"H3xxyxwskyef5/5kD6PM+PTaDilrnqeBWc3vI6c8xGSsvBbyLd0BA18P4p5CZK7ZMM4C3j0MuYa58P1rgwriZ/xk3bjYBZ7iCCarGv+ewKC+RL0RXsKFaW5oUZ64sYmIj0LKhOvceaqvVAPNUj0j1EpS8aoEkVTcfNcWKiwN/BSzicFKsdrrzrPeIPbCtgd0bAYCDCfiAmkE/Nt8wBPFqQ\\u003d\\u003d\",\"serialNo\":\"SDZS20170419111354HqMiR5zD\",\"platformCode\":\"KUXO88Q9\",\"signType\":\"RSA\",\"format\":\"JSON\",\"version\":\"1.0\"}";
	// try {
	// String disposeResponse = new OpenApi(password, "", "",
	// "").disposeResponse(requestJson, kyPublicKey);
	// System.out.println(disposeResponse);
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// // Map<String, String> paramMap = new Gson().fromJson(requestJson,
	// // new TypeToken<Map<String, String>>() {}.getType());
	// // String sign = paramMap.remove("sign");
	// // System.out.println("sign=" + sign);
	// // boolean verify = RSAUtil.verify(RSAUtil.getSignatureContent(paramMap),
	// sign, kyPublicKey);
	// // System.out.println(verify);
	// }
	
	
	public static void main(String[] args) {
//		String imgStr  = "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";
//		try {
//			generateImage(imgStr,"D:\\img\\a.jpg");
//			System.out.println("图片转换成功");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		System.out.println(getImageStr("D:\\img\\1.jpg"));
		
	}
	
	/** 
	* 将字符串转为图片 
	* @param imgStr 
	* @return 
	*/  
	public static boolean generateImage(String imgStr,String imgFile)throws Exception {// 对字节数组字符串进行Base64解码并生成图片  
	if (imgStr == null) // 图像数据为空  
		return false;
	byte[]	b = Base64Util.decode2Byte(imgStr); 
	// 生成jpeg图片  
	String imgFilePath = imgFile;// 新生成的图片  
	OutputStream out = new FileOutputStream(imgFilePath);  
	out.write(b);  
	out.flush();  
	out.close();  
	return true;  
	}  
	
	/**
	 * @Description: 根据图片地址转换为base64编码字符串
	 * @Author: 
	 * @CreateTime: 
	 * @return
	 */
	public static String getImageStr(String imgFile) {
	    InputStream inputStream = null;
	    byte[] data = null;
	    try {
	        inputStream = new FileInputStream(imgFile);
	        data = new byte[inputStream.available()];
	        inputStream.read(data);
	        inputStream.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	  return   Base64Util.encode2String(data);
	}

	private OpenApi openApi = new OpenApi(desKey, platformCode, prefix, privateKey);


	/**
	 *  1. 
	 * @title: testInvoiceBlueSimpleness
	 * @description: 蓝票接口调用：单商品行蓝票
	 */
	@Test
	public void testInvoiceBlueOneItem() {
		String url = testUrl + "/einvoice/blue";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("taxpayerNum", "110101201702071");
		// TODO 请更换请求流水号前缀
		map.put("invoiceReqSerialNo", prefix + "000000009376");
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
		System.out.println("请求content内容： "+content);
		String buildRequest = openApi.buildRequest(content);

		String response = HttpUtils.sendPost(url, buildRequest, null);
		System.out.println("卡友响应： "+response);
		String s = openApi.disposeResponse(response, kyPublicKey);
		System.out.println("验签解密内容： " + s);

	}

	/**
	 * 2. 
	 * @title: testInvoiceRed
	 * @description: 红票接口调用
	 */
	@Test
	public void testInvoiceRed() {
		String url = testUrl + "/einvoice/red";

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("taxpayerNum", "110101201702071");
		// TODO 请更换请求流水号前缀
		map.put("invoiceReqSerialNo", prefix + "000000000024");
		map.put("invoiceCode", "050003522444");
		map.put("invoiceNo", "11302054");
		map.put("redReason", "冲红");
		map.put("amount", "-56.64");
		String content = JsonUtil.toJson(map);
		System.out.println(content);
		String buildRequest = openApi.buildRequest(content);
		System.out.println(buildRequest);
		String response = HttpUtils.postJson(url, buildRequest);
		System.out.println("卡友响应： "+response);
		String s = openApi.disposeResponse(response, kyPublicKey);
		System.out.println("验签解密内容： " + s);
	}

	

	/**
	 * 4.  
	 * @title: testInvoicePaperDestroy
	 * @description: 作废接口调用
	 */
	@Test
	public void testInvoicePaperDestroy() {
		String url = testUrl + "/einvoice/qrcode/batch";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("taxpayerNum", "110101201702071");
		// TODO 请更换请求流水号前缀
		map.put("invoiceReqSerialNo", prefix + "000000000024");
		map.put("invoiceCode", "5000153650");
		map.put("invoiceNo", "06594215");
		map.put("destroyReason", "作废的原因作废的原因原因作废的原因作废的原因");
		map.put("amount", "-113.9");
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
		String url = testUrl + "/einvoice/box";
		String content = "{\"taxpayerNum\":\"110101201702071\",\"enterpriseName\":\"电子票测试新1\"}";
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
		String url = testUrl + "/einvoice/repertory";
		String content = "{\"taxpayerNum\":\"110101201702071\",\"enterpriseName\":\"电子票测试新1\"}";
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
		String url = testUrl + "/einvoice/blue";
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
		String url = testUrl + "/einvoice/blue";
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
		String buildRequest = openApi.buildRequest(content);
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

		String url = testUrl + "/einvoice/blue";
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
		String url = testUrl + "/einvoice/blue";
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
		String url = testUrl + "/einvoice/blue";
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
		String buildRequest = openApi.buildRequest(content);
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
		String url = testUrl + "/einvoice/blue";
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
		String buildRequest = openApi.buildRequest(content);
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
		String url = testUrl + "/einvoice/blue";

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
	
	
	

}
