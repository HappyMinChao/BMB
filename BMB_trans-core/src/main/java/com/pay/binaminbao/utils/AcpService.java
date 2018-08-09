package com.pay.binaminbao.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.pay.binaminbao.beans.UnionPayBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @ClassName AcpService
 * @Description acpsdk接口服务类，接入商户集成请可以直接参考使用本类中的方法
 * @date 2016-7-22 下午2:44:37
 * 声明：以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己需要，按照技术文档编写。该代码仅供参考，不提供编码，性能，规范性等方面的保障<br>
 */
public class AcpService {

	private static Log log = LogFactory.getLog(AcpService.class);

	/**
	 * 请求报文签名(使用配置文件中配置的私钥证书或者对称密钥签名)<br>
	 * 功能：对请求报文进行签名,并计算赋值 certid,signature 字段并返回<br>
	 * @param reqData 请求报文map<br>
	 * @return　签名后的map对象<br>
	 */
	public static Map<String, String> sign(Map<String, String> reqData) {
		reqData = StringUtil.filterBlank(reqData);
		SDKUtil.sign(reqData, "UTF-8");
		return reqData;
	}
	
	/**
	 * 请求报文签名(使用配置文件中配置的私钥证书或者对称密钥签名)<br>
	 * 功能：对请求报文进行签名,并计算赋值 certid,signature 字段并返回<br>
	 * @param reqData 请求报文map<br>
	 * @param encoding 上送请求报文域 ENCODING 字段的值<br>
	 * @return　签名后的map对象<br>
	 */
	public static Map<String, String> sign(Map<String, String> reqData,String encoding) {
		reqData = StringUtil.filterBlank(reqData);
		SDKUtil.sign(reqData, encoding);
		return reqData;
	}

	/**
	 * 同signByCertInfo<br>
	 * 通过传入的签名密钥进行签名并返回签名值<br>
	 * @param reqData
	 *            待签名数据Map键值对形式
	 * @param encoding
	 *            编码
	 * @param certPath
	 *            证书绝对路径
	 * @param certPwd
	 *            证书密码
	 * @return 签名值
	 * @deprecated
	 */
	public static Map<String, String> sign(Map<String, String> reqData, String certPath,
			String certPwd,String encoding) {
		reqData = StringUtil.filterBlank(reqData);
		SDKUtil.signByCertInfo(reqData,certPath,certPwd,encoding);
		return reqData;
	}

	/**
	 * 多证书签名(通过传入私钥证书路径和密码签名）<br>
	 * 功能：如果有多个商户号接入银联,每个商户号对应不同的证书可以使用此方法:传入私钥证书和密码(并且在acp_sdk.properties中 配置 acpsdk.singleMode=false)<br>
	 * @param reqData 请求报文map<br>
	 * @param certPath 签名私钥文件（带路径）<br>
	 * @param certPwd 签名私钥密码<br>
	 * @param encoding 上送请求报文域encoding字段的值<br>
	 * @return　签名后的map对象<br>
	 */
	public static Map<String, String> signByCertInfo(Map<String, String> reqData, String certPath,
			String certPwd,String encoding) {
		reqData = StringUtil.filterBlank(reqData);
		SDKUtil.signByCertInfo(reqData,certPath,certPwd,encoding);
		return reqData;
	}

	/**
	 * 多密钥签名(通过传入密钥签名)<br>
	 * 功能：如果有多个商户号接入银联,每个商户号对应不同的证书可以使用此方法:传入私钥证书和密码(并且在acp_sdk.properties中 配置 acpsdk.singleMode=false)<br>
	 * @param reqData 请求报文map<br>
	 * @param secureKey 签名对称密钥<br>
	 * @param encoding 上送请求报文域encoding字段的值<br>
	 * @return　签名后的map对象<br>
	 */
	public static Map<String, String> signBySecureKey(Map<String, String> reqData, String secureKey, String encoding) {
		reqData = StringUtil.filterBlank(reqData);
		SDKUtil.signBySecureKey(reqData, secureKey, encoding);
		return reqData;
	}

	/**
	 * 验证签名(SHA-1摘要算法)<br>
	 * @param rspData 返回报文数据<br>
	 * @return true 通过 false 未通过<br>
	 */
	public static boolean validate(Map<String, String> rspData) {
		return SDKUtil.validate(rspData, "UTF-8");
	}
	
	/**
	 * 验证签名(SHA-1摘要算法)<br>
	 * @param rspData 返回报文数据<br>
	 * @param encoding 上送请求报文域encoding字段的值<br>
	 * @return true 通过 false 未通过<br>
	 */
	public static boolean validate(Map<String, String> rspData, String encoding) {
		return SDKUtil.validate(rspData, encoding);
	}

	/**
	 * 多密钥验签(通过传入密钥签名)<br>
	 * @param rspData 返回报文数据<br>
	 * @param encoding 上送请求报文域encoding字段的值<br>
	 * @return true 通过 false 未通过<br>
	 */
	public static boolean validateBySecureKey(Map<String, String> rspData, String secureKey, String encoding) {
		return SDKUtil.validateBySecureKey(rspData, secureKey, encoding);
	}


	/**
	 * @deprecated 5.1.0开发包已删除此方法，请直接参考5.1.0开发包中的VerifyAppData.java验签。
	 * 对控件支付成功返回的结果信息中data域进行验签（控件端获取的应答信息）<br>
	 * @param jsonData json格式数据，例如：{"sign" : "J6rPLClQ64szrdXCOtV1ccOMzUmpiOKllp9cseBuRqJ71pBKPPkZ1FallzW18gyP7CvKh1RxfNNJ66AyXNMFJi1OSOsteAAFjF5GZp0Xsfm3LeHaN3j/N7p86k3B1GrSPvSnSw1LqnYuIBmebBkC1OD0Qi7qaYUJosyA1E8Ld8oGRZT5RR2gLGBoiAVraDiz9sci5zwQcLtmfpT5KFk/eTy4+W9SsC0M/2sVj43R9ePENlEvF8UpmZBqakyg5FO8+JMBz3kZ4fwnutI5pWPdYIWdVrloBpOa+N4pzhVRKD4eWJ0CoiD+joMS7+C0aPIEymYFLBNYQCjM0KV7N726LA==",  "data" : "pay_result=success&tn=201602141008032671528&cert_id=68759585097"}
	 * @return 是否成功
	 */
	public static boolean validateAppResponse(String jsonData, String encoding) {
		log.info("控件应答信息验签处理开始：[" + jsonData + "]");
		if (StringUtils.isBlank(encoding)) {
			encoding = "UTF-8";
		}

        Pattern p = Pattern.compile("\\s*\"sign\"\\s*:\\s*\"([^\"]*)\"\\s*");
		Matcher m = p.matcher(jsonData);
		if(!m.find()) return false;
		String sign = m.group(1);

		p = Pattern.compile("\\s*\"data\"\\s*:\\s*\"([^\"]*)\"\\s*");
		m = p.matcher(jsonData);
		if(!m.find()) return false;
		String data = m.group(1);

		p = Pattern.compile("cert_id=(\\d*)");
		m = p.matcher(jsonData);
		if(!m.find()) return false;
		String certId = m.group(1);

		try {
			// 验证签名需要用银联发给商户的公钥证书.
			return SecureUtil.validateSignBySoft(CertUtil
					.getValidatePublicKey(certId), SecureUtil.base64Decode(sign
					.getBytes(encoding)), SecureUtil.sha1X16(data,
					encoding));
		} catch (UnsupportedEncodingException e) {
			log.info(e.getMessage(), e);
		} catch (Exception e) {
			log.info(e.getMessage(), e);
		}
		return false;
	}

	/**
	 * 功能：后台交易提交请求报文并接收同步应答报文<br>
	 * @param reqData 请求报文<br>
	 * @param reqUrl  请求地址<br>
	 * @param encoding<br>
	 * @return 应答http 200返回true ,其他false<br>
	 */
	public static Map<String,String> post(
			Map<String, String> reqData,String reqUrl,String encoding) {
		Map<String, String> rspData = new HashMap<String,String>();
		log.info("请求银联地址:" + reqUrl);
		//发送后台请求数据
		HttpClient hc = new HttpClient(reqUrl, 30000, 30000);//连接超时时间，读超时时间（可自行判断，修改）
		try {
			int status = hc.send(reqData, encoding);
			if (200 == status) {
				String resultString = hc.getResult();
				if (null != resultString && !"".equals(resultString)) {
					// 将返回结果转换为map
					Map<String,String> tmpRspData  = SDKUtil.convertResultStringToMap(resultString);
					rspData.putAll(tmpRspData);
				}
			}else{
				log.info("返回http状态码[" + status + "]，请检查请求报文或者请求地址是否正确");
			}
		} catch (Exception e) {
			log.info(e.getMessage(), e);
		}
		return rspData;
	}

	/**
	 * 功能：http Get方法 便民缴费产品中使用<br>
	 * @param reqUrl 请求地址<br>
	 * @param encoding<br>
	 * @return
	 */
	public static String get(String reqUrl,String encoding) {

		log.info("请求银联地址:" + reqUrl);
		//发送后台请求数据
		HttpClient hc = new HttpClient(reqUrl, 30000, 30000);
		try {
			int status = hc.sendGet(encoding);
			if (200 == status) {
				String resultString = hc.getResult();
				if (null != resultString && !"".equals(resultString)) {
					return resultString;
				}
			}else{
				log.info("返回http状态码[" + status + "]，请检查请求报文或者请求地址是否正确");
			}
		} catch (Exception e) {
			log.info(e.getMessage(), e);
		}
		return null;
	}


	/**
	 * 功能：前台交易构造HTTP POST自动提交表单<br>
	 * @param reqUrl 表单提交地址<br>
	 * @param hiddens 以MAP形式存储的表单键值<br>
	 * @param encoding 上送请求报文域encoding字段的值<br>
	 * @return 构造好的HTTP POST交易表单<br>
	 */
	public static String createAutoFormHtml(String reqUrl, Map<String, String> hiddens,String encoding) {
		StringBuffer sf = new StringBuffer();
		sf.append("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset="+encoding+"\"/></head><body>");
		sf.append("<form id = \"pay_form\" action=\"" + reqUrl
				+ "\" method=\"post\">");
		if (null != hiddens && 0 != hiddens.size()) {
			Set<Entry<String, String>> set = hiddens.entrySet();
			Iterator<Entry<String, String>> it = set.iterator();
			while (it.hasNext()) {
				Entry<String, String> ey = it.next();
				String key = ey.getKey();
				String value = ey.getValue();
				sf.append("<input type=\"hidden\" name=\"" + key + "\" id=\""
						+ key + "\" value=\"" + value + "\"/>");
			}
		}
		sf.append("</form>");
		sf.append("</body>");
		sf.append("<script type=\"text/javascript\">");
		sf.append("document.all.pay_form.submit();");
		sf.append("</script>");
		sf.append("</html>");
		return sf.toString();
	}


	/**
	 * 功能：将批量文件内容使用DEFLATE压缩算法压缩，Base64编码生成字符串并返回<br>
	 * 适用到的交易：批量代付，批量代收，批量退货<br>
	 * @param filePath 批量文件-全路径文件名<br>
	 * @return
	 */
	public static String enCodeFileContent(String filePath,String encoding){
		String baseFileContent = "";

		File file = new File(filePath);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				log.info(e.getMessage(), e);
			}
		}
		InputStream in = null;
		try {
			in = new FileInputStream(file);
			int fl = in.available();
			if (null != in) {
				byte[] s = new byte[fl];
				in.read(s, 0, fl);
				// 压缩编码.
				baseFileContent = new String(SecureUtil.base64Encode(FileUtil.deflater(s)),encoding);
			}
		} catch (Exception e) {
			log.info(e.getMessage(), e);
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					log.info(e.getMessage(), e);
				}
			}
		}
		return baseFileContent;
	}

	/**
	 * 功能：解析交易返回的fileContent字符串并落地 （ 解base64，解DEFLATE压缩并落地）<br>
	 * 适用到的交易：对账文件下载，批量交易状态查询<br>
	 * @param resData 返回报文map<br>
	 * @param fileDirectory 落地的文件目录（绝对路径）
	 * @param encoding 上送请求报文域encoding字段的值<br>
	 */
	public static String deCodeFileContent(Map<String, String> resData,String fileDirectory,String encoding) {
		// 解析返回文件
		String filePath = null;
		String fileContent = resData.get(SDKConstants.param_fileContent);
		if (null != fileContent && !"".equals(fileContent)) {
			FileOutputStream out = null;
			try {
				byte[] fileArray = FileUtil.inflater(SecureUtil
						.base64Decode(fileContent.getBytes(encoding)));
				if (StringUtils.isBlank(resData.get("fileName"))) {
					filePath = fileDirectory + File.separator + resData.get("merId")
							+ "_" + resData.get("batchNo") + "_"
							+ resData.get("txnTime") + ".txt";
				} else {
					filePath = fileDirectory + File.separator + resData.get("fileName");
				}
				File file = new File(filePath);
				if (file.exists()) {
					file.delete();
				}
				file.createNewFile();
			    out = new FileOutputStream(file);
				out.write(fileArray, 0, fileArray.length);
				out.flush();
			} catch (UnsupportedEncodingException e) {
				log.info(e.getMessage(), e);
			} catch (IOException e) {
				log.info(e.getMessage(), e);
			}finally{
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return filePath;
	}

	/**
	 * 功能：将结果文件内容 转换成明文字符串：解base64,解压缩<br>
	 * 适用到的交易：批量交易状态查询<br>
	 * @param fileContent 批量交易状态查询返回的文件内容<br>
	 * @return 内容明文<br>
	 */
	public static String getFileContent(String fileContent,String encoding){
		String fc = "";
		try {
			fc = new String(FileUtil.inflater(SecureUtil.base64Decode(fileContent.getBytes())),encoding);
		} catch (UnsupportedEncodingException e) {
			log.info(e.getMessage(), e);
		} catch (IOException e) {
			log.info(e.getMessage(), e);
		}
		return fc;
	}


	/**
	 * 功能：持卡人信息域customerInfo构造<br>
	 * 说明：不勾选对敏感信息加密权限使用旧的构造customerInfo域方式，不对敏感信息进行加密（对 phoneNo，cvn2， expired不加密），但如果送pin的话则加密<br>
	 * @param customerInfoMap 信息域请求参数 key送域名value送值,必送<br>
	 *            例如：customerInfoMap.put("certifTp", "01"); //证件类型<br>
	 *            customerInfoMap.put("certifId", "341126197709218366"); //证件号码<br>
	 *            customerInfoMap.put("customerNm", "互联网"); //姓名<br>
	 *            customerInfoMap.put("phoneNo", "13552535506"); //手机号<br>
	 *            customerInfoMap.put("smsCode", "123456"); //短信验证码<br>
	 *            customerInfoMap.put("pin", "111111"); //密码（加密）<br>
	 *            customerInfoMap.put("cvn2", "123"); //卡背面的cvn2三位数字（不加密）<br>
	 *            customerInfoMap.put("expired", "1711"); //有效期 年在前月在后（不加密)<br>
	 * @param accNo customerInfoMap送了密码那么卡号必送,如果customerInfoMap未送密码pin，此字段可以不送<br>
	 * @param encoding 上送请求报文域encoding字段的值<br>
	 * @return base64后的持卡人信息域字段<br>
	 */
	public static String getCustomerInfo(Map<String,String> customerInfoMap,String accNo,String encoding) {

		if(customerInfoMap.isEmpty())
			return "{}";
		StringBuffer sf = new StringBuffer("{");
		for(Iterator<String> it = customerInfoMap.keySet().iterator(); it.hasNext();){
			String key = it.next();
			String value = customerInfoMap.get(key);
			if(key.equals("pin")){
				if(null == accNo || "".equals(accNo.trim())){
					log.info("送了密码（PIN），必须在getCustomerInfo参数中上传卡号");
					throw new RuntimeException("加密PIN没送卡号无法后续处理");
				}else{
					value = encryptPin(accNo,value,encoding);
				}
			}
			sf.append(key).append(SDKConstants.EQUAL).append(value);
			if(it.hasNext())
				sf.append(SDKConstants.AMPERSAND);
		}
		String customerInfo = sf.append("}").toString();
		log.info("组装的customerInfo明文：" + customerInfo);
		try {
			return new String(SecureUtil.base64Encode(sf.toString().getBytes(
					encoding)),encoding);
		} catch (UnsupportedEncodingException e) {
			log.info(e.getMessage(), e);
		} catch (IOException e) {
			log.info(e.getMessage(), e);
		}
		return customerInfo;
	}

	/**
	 * 功能：持卡人信息域customerInfo构造，勾选对敏感信息加密权限 适用新加密规范，对pin和phoneNo，cvn2，expired加密 <br>
	 * 适用到的交易： <br>
	 * @param customerInfoMap 信息域请求参数 key送域名value送值,必送 <br>
	 *        例如：customerInfoMap.put("certifTp", "01");					//证件类型 <br>
				  customerInfoMap.put("certifId", "341126197709218366");	//证件号码 <br>
				  customerInfoMap.put("customerNm", "互联网");				//姓名 <br>
				  customerInfoMap.put("smsCode", "123456");					//短信验证码 <br>
				  customerInfoMap.put("pin", "111111");						//密码（加密） <br>
				  customerInfoMap.put("phoneNo", "13552535506");			//手机号（加密） <br>
				  customerInfoMap.put("cvn2", "123");           			//卡背面的cvn2三位数字（加密） <br>
				  customerInfoMap.put("expired", "1711");  				    //有效期 年在前月在后（加密） <br>
	 * @param accNo  customerInfoMap送了密码那么卡号必送,如果customerInfoMap未送密码PIN，此字段可以不送<br>
	 * @param encoding 上送请求报文域encoding字段的值
	 * @return base64后的持卡人信息域字段 <br>
	 */
	public static String getCustomerInfoWithEncrypt(Map<String,String> customerInfoMap,String accNo,String encoding) {
		if(customerInfoMap.isEmpty())
			return "{}";
		StringBuffer sf = new StringBuffer("{");
		//敏感信息加密域
		StringBuffer encryptedInfoSb = new StringBuffer("");

		for(Iterator<String> it = customerInfoMap.keySet().iterator(); it.hasNext();){
			String key = it.next();
			String value = customerInfoMap.get(key);
			if(key.equals("phoneNo") || key.equals("cvn2") || key.equals("expired")){
				encryptedInfoSb.append(key).append(SDKConstants.EQUAL).append(value).append(SDKConstants.AMPERSAND);
			}else{
				if(key.equals("pin")){
					if(null == accNo || "".equals(accNo.trim())){
						log.info("送了密码（PIN），必须在getCustomerInfoWithEncrypt参数中上传卡号");
						throw new RuntimeException("加密PIN没送卡号无法后续处理");
					}else{
						value = encryptPin(accNo,value,encoding);
					}
				}
				sf.append(key).append(SDKConstants.EQUAL).append(value).append(SDKConstants.AMPERSAND);
			}
		}

		if(!encryptedInfoSb.toString().equals("")){
			encryptedInfoSb.setLength(encryptedInfoSb.length()-1);//去掉最后一个&符号
			log.info("组装的customerInfo encryptedInfo明文：" + encryptedInfoSb.toString());
			sf.append("encryptedInfo").append(SDKConstants.EQUAL).append(encryptData(encryptedInfoSb.toString(), encoding));
		}else{
			sf.setLength(sf.length()-1);
		}

		String customerInfo = sf.append("}").toString();
		log.info("组装的customerInfo明文：" + customerInfo);
		try {
			return new String(SecureUtil.base64Encode(sf.toString().getBytes(encoding)),encoding);
		} catch (UnsupportedEncodingException e) {
			log.info(e.getMessage(), e);
		} catch (IOException e) {
			log.info(e.getMessage(), e);
		}
		return customerInfo;
	}

	/**
	 * 解析返回报文（后台通知）中的customerInfo域：<br>
	 * 解base64,如果带敏感信息加密 encryptedInfo 则将其解密并将 encryptedInfo中的域放到customerInfoMap返回<br>
	 * @param customerInfo<br>
	 * @param encoding<br>
	 * @return
	 */
	public static Map<String,String> parseCustomerInfo(String customerInfo,String encoding){
		Map<String,String> customerInfoMap = null;
		try {
				byte[] b = SecureUtil.base64Decode(customerInfo.getBytes(encoding));
				String customerInfoNoBase64 = new String(b,encoding);
			log.info("解base64后===>" + customerInfoNoBase64);
				//去掉前后的{}
				customerInfoNoBase64 = customerInfoNoBase64.substring(1, customerInfoNoBase64.length()-1);
				customerInfoMap = SDKUtil.parseQString(customerInfoNoBase64);
				if(customerInfoMap.containsKey("encryptedInfo")){
					String encInfoStr = customerInfoMap.get("encryptedInfo");
					customerInfoMap.remove("encryptedInfo");
					String encryptedInfoStr = decryptData(encInfoStr, encoding);
					Map<String,String> encryptedInfoMap = SDKUtil.parseQString(encryptedInfoStr);
					customerInfoMap.putAll(encryptedInfoMap);
				}
			} catch (UnsupportedEncodingException e) {
			log.info(e.getMessage(), e);
			} catch (IOException e) {
			log.info(e.getMessage(), e);
			}
		return customerInfoMap;
	}

	/**
	 * 解析返回报文（后台通知）中的customerInfo域：<br>
	 * 解base64,如果带敏感信息加密 encryptedInfo 则将其解密并将 encryptedInfo中的域放到customerInfoMap返回<br>
	 * @param customerInfo<br>
	 * @param encoding<br>
	 * @return
	 */
	public static Map<String, String> parseCustomerInfo(String customerInfo, String certPath,
			String certPwd, String encoding){
		Map<String,String> customerInfoMap = null;
		try {
				byte[] b = SecureUtil.base64Decode(customerInfo.getBytes(encoding));
				String customerInfoNoBase64 = new String(b,encoding);
			log.info("解base64后===>" + customerInfoNoBase64);
				//去掉前后的{}
				customerInfoNoBase64 = customerInfoNoBase64.substring(1, customerInfoNoBase64.length()-1);
				customerInfoMap = SDKUtil.parseQString(customerInfoNoBase64);
				if(customerInfoMap.containsKey("encryptedInfo")){
					String encInfoStr = customerInfoMap.get("encryptedInfo");
					customerInfoMap.remove("encryptedInfo");
					String encryptedInfoStr = decryptData(encInfoStr, certPath, certPwd, encoding);
					Map<String,String> encryptedInfoMap = SDKUtil.parseQString(encryptedInfoStr);
					customerInfoMap.putAll(encryptedInfoMap);
				}
			} catch (UnsupportedEncodingException e) {
			log.info(e.getMessage(), e);
			} catch (IOException e) {
			log.info(e.getMessage(), e);
			}
		return customerInfoMap;
	}

	/**
	 * 密码加密并做base64<br>
	 * @param accNo 卡号<br>
	 * @param pin 密码<br>
	 * @param encoding<br>
	 * @return 加密的内容<br>
	 */
	public static String encryptPin(String accNo, String pin, String encoding) {
		return SecureUtil.encryptPin(accNo, pin, encoding, CertUtil
				.getEncryptCertPublicKey());
	}

	/**
	 * 敏感信息加密并做base64(卡号，手机号，cvn2,有效期）<br>
	 * @param data 送 phoneNo,cvn2,有效期<br>
	 * @param encoding<br>
	 * @return 加密的密文<br>
	 */
	public static String encryptData(String data, String encoding) {
		return SecureUtil.encryptData(data, encoding, CertUtil.getEncryptCertPublicKey());
	}

	/**
	 * 敏感信息解密，使用配置文件acp_sdk.properties解密<br>
	 * @param base64EncryptedInfo 加密信息<br>
	 * @param encoding<br>
	 * @return 解密后的明文<br>
	 */
	public static String decryptData(String base64EncryptedInfo, String encoding) {
		return SecureUtil.decryptData(base64EncryptedInfo, encoding, CertUtil
				.getSignCertPrivateKey());
	}

	/**
	 * 敏感信息解密,通过传入的私钥解密<br>
	 * @param base64EncryptedInfo 加密信息<br>
	 * @param certPath 私钥文件（带全路径）<br>
	 * @param certPwd 私钥密码<br>
	 * @param encoding<br>
	 * @return
	 */
	public static String decryptData(String base64EncryptedInfo, String certPath,
			String certPwd, String encoding) {
		return SecureUtil.decryptData(base64EncryptedInfo, encoding, CertUtil
				.getSignCertPrivateKeyByStoreMap(certPath, certPwd));
	}

	/**
	 * 5.0.0加密磁道信息，5.1.0接口请勿使用<br>
	 * @param trackData 待加密磁道数据<br>
	 * @param encoding 编码格式<br>
	 * @return 加密的密文<br>
	 * @deprecated
	 */
	public static String encryptTrack(String trackData, String encoding) {
		return SecureUtil.encryptData(trackData, encoding,
				CertUtil.getEncryptTrackPublicKey());
	}

	/**
	 * 获取敏感信息加密证书的物理序列号<br>
	 * @return
	 */
	public static String getEncryptCertId(){
		return CertUtil.getEncryptCertId();
	}

	/**
	 * 对字符串做base64<br>
	 * @param rawStr<br>
	 * @param encoding<br>
	 * @return<br>
	 * @throws IOException
	 */
	public static String base64Encode(String rawStr,String encoding) throws IOException{
		byte [] rawByte = rawStr.getBytes(encoding);
		return new String(SecureUtil.base64Encode(rawByte),encoding);
	}
	/**
	 * 对base64的字符串解base64<br>
	 * @param base64Str base64字符串
	 * @param encoding 编码方式
	 * @return String
	 * @throws IOException io异常
	 */
	public static String base64Decode(String base64Str,String encoding) throws IOException{
		byte [] rawByte = base64Str.getBytes(encoding);
		return new String(SecureUtil.base64Decode(rawByte), encoding);
	}


	/**
	 * 有卡交易信息域(cardTransData)构造<br>
	 * 所有子域需用“{}”包含，子域间以“&”符号链接。格式如下：{子域名1=值&子域名2=值&子域名3=值}<br>
	 * 说明：本示例仅供参考，开发时请根据接口文档中的报文要素组装<br>
	 * @param cardTransDataMap cardTransData的数据<br>
	 * @param unionpayBean 必须包含merId、orderId、txnTime、txnAmt，磁道加密时需要使用<br>
	 * @param encoding 编码<br>
	 * @return String
	 */
	public static String getCardTransData(Map<String, String> cardTransDataMap,
										  UnionPayBean unionpayBean,String encoding) { {

		StringBuilder cardTransDataBuffer = new StringBuilder();

		if(cardTransDataMap.containsKey("track2Data")){
			StringBuilder track2Buffer = new StringBuilder();
			track2Buffer.append(unionpayBean.getMerId())
					.append(SDKConstants.COLON).append(unionpayBean.getOrderId())
					.append(SDKConstants.COLON).append(unionpayBean.getTxnTime())
					.append(SDKConstants.COLON).append(unionpayBean.getTxnAmt()==null?0:unionpayBean.getTxnAmt())
					.append(SDKConstants.COLON).append(cardTransDataMap.get("track2Data"));
				cardTransDataMap.put("track2Data",
					AcpService.encryptData(track2Buffer.toString(),	encoding));
		}

		if(cardTransDataMap.containsKey("track3Data")){
			StringBuilder track3Buffer = new StringBuilder();
			track3Buffer.append(unionpayBean.getMerId())
				.append(SDKConstants.COLON).append(unionpayBean.getOrderId())
				.append(SDKConstants.COLON).append(unionpayBean.getTxnTime())
				.append(SDKConstants.COLON).append(unionpayBean.getTxnAmt()==null?0:unionpayBean.getTxnAmt())
				.append(SDKConstants.COLON).append(cardTransDataMap.get("track3Data"));
				cardTransDataMap.put("track3Data",
					AcpService.encryptData(track3Buffer.toString(),	encoding));
		}

		return cardTransDataBuffer.append(SDKConstants.LEFT_BRACE)
				.append(SDKUtil.coverMap2String(cardTransDataMap))
				.append(SDKConstants.RIGHT_BRACE).toString();
		}

	}

	/**
	 * 获取应答报文中的加密公钥证书,并存储到本地,备份原始证书,并自动替换证书<br>
	 * 更新成功则返回1，无更新返回0，失败异常返回-1<br>
	 * @param resData 返回报文
	 * @param encoding 编码方式
	 * @return int
	 */
	public static int updateEncryptCert(Map<String, String> resData,
			String encoding) {
		return SDKUtil.getEncryptCert(resData, encoding);
	}

	/**
	 * 获取
	 * @param number
	 * @return
	 */
	public static int genLuhn(String number){
		return SecureUtil.genLuhn(number);
	}
}
