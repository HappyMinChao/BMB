/**
 *
 * Licensed Property to China UnionPay Co., Ltd.
 *
 * (C) Copyright of China UnionPay Co., Ltd. 2010
 *     All Rights Reserved.
 *
 *
 * Modification History:
 * =============================================================================
 *   Author         Date          Description
 *   ------------ ---------- ---------------------------------------------------
 *   xshu       2014-05-28      MPI工具类
 * =============================================================================
 */
package com.pay.binaminbao.utils;

import java.io.UnsupportedEncodingException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import com.pay.binaminbao.config.SDKConfig;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import static com.pay.binaminbao.utils.SDKConstants.*;


/**
 * @ClassName SDKUtil
 * @Description acpsdk工具类
 * @date 2016-7-22 下午4:06:18
 *       声明：以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己需要，按照技术文档编写。该代码仅供参考，不提供编码，性能，规范性等方面的保障<br>
 */
@Component
public class SDKUtil {

	private static Logger log;
	
	private static SDKConfig sdkConfig;
	
	@Autowired 
	private SDKConfig sdkConfigWired;
	
	@PostConstruct
	private void init(){
		sdkConfig = sdkConfigWired;
		log = LoggerFactory.getLogger(SDKUtil.class);
	}
	
	/**
	 * 根据signMethod的值，提供三种计算签名的方法
	 * @param data
	 *            待签名数据Map键值对形式
	 * @param encoding
	 *            编码
	 * @return 签名是否成功
	 */
	public static boolean sign(Map<String, String> data, String encoding) {

		if (StringUtils.isBlank(encoding)) {
			encoding = "UTF-8";
		}
		String signMethod = data.get(param_signMethod);
		String version = data.get(SDKConstants.param_version);
		if (!VERSION_1_0_0.equals(version) && !VERSION_5_0_1.equals(version) && StringUtils.isBlank(signMethod)) {
			log.info("signMethod must Not null");
			return false;
		}

		if (StringUtils.isBlank(version)) {
			log.info("version must Not null");
			return false;
		}
		if (SIGNMETHOD_RSA.equals(signMethod)|| VERSION_1_0_0.equals(version) || VERSION_5_0_1.equals(version)) {
			if (VERSION_5_0_0.equals(version)|| VERSION_1_0_0.equals(version) || VERSION_5_0_1.equals(version)) {
				// 设置签名证书序列号
				data.put(SDKConstants.param_certId, CertUtil.getSignCertId());
				// 将Map信息转换成key1=value1&key2=value2的形式
				String stringData = coverMap2String(data);
				log.info("待签名请求报文串:[" + stringData + "]");
				byte[] byteSign = null;
				String stringSign = null;
				try {
					// 通过SHA1进行摘要并转16进制
					byte[] signDigest = SecureUtil
							.sha1X16(stringData, encoding);
					byteSign = SecureUtil.base64Encode(SecureUtil.signBySoft(
							CertUtil.getSignCertPrivateKey(), signDigest));
					stringSign = new String(byteSign);
					// 设置签名域值
					data.put(SDKConstants.param_signature, stringSign);
					return true;
				} catch (Exception e) {
					log.info("Sign Error", e);
					return false;
				}
			} else if (VERSION_5_1_0.equals(version)) {
				// 设置签名证书序列号
				data.put(SDKConstants.param_certId, CertUtil.getSignCertId());
				// 将Map信息转换成key1=value1&key2=value2的形式
				String stringData = coverMap2String(data);
				log.info("待签名请求报文串:[" + stringData + "]");
				byte[] byteSign = null;
				String stringSign = null;
				try {
					// 通过SHA256进行摘要并转16进制
					byte[] signDigest = SecureUtil
							.sha256X16(stringData, encoding);
					byteSign = SecureUtil.base64Encode(SecureUtil.signBySoft256(
							CertUtil.getSignCertPrivateKey(), signDigest));
					stringSign = new String(byteSign);
					// 设置签名域值
					data.put(SDKConstants.param_signature, stringSign);
					return true;
				} catch (Exception e) {
					log.info("Sign Error", e);
					return false;
				}
			}
		} else if (SIGNMETHOD_SHA256.equals(signMethod)) {
			return signBySecureKey(data, sdkConfig.getSecureKey(), encoding);
		} else if (SIGNMETHOD_SM3.equals(signMethod)) {
			return signBySecureKey(data, sdkConfig.getSecureKey(), encoding);
		}
		return false;
	}

	/**
	 * 通过传入的私钥进行签名并返回签名值<br>
	 * @param data
	 *            待签名数据Map键值对形式
	 * @param encoding
	 *            编码
	 * @param secureKey
	 *            私钥
	 * @return 签名值
	 */
	public static boolean signBySecureKey(Map<String, String> data, String secureKey,
			String encoding) {

		if (StringUtils.isBlank(encoding)) {
			encoding = "UTF-8";
		}
		if (StringUtils.isBlank(secureKey)) {
			log.info("secureKey is empty");
			return false;
		}
		String signMethod = data.get(param_signMethod);
		if (StringUtils.isBlank(signMethod)) {
			log.info("signMethod must Not null");
			return false;
		}

		if (SIGNMETHOD_SHA256.equals(signMethod)) {
			// 将Map信息转换成key1=value1&key2=value2的形式
			String stringData = coverMap2String(data);
			log.info("待签名请求报文串:[" + stringData + "]");
			String strBeforeSha256 = stringData
					+ SDKConstants.AMPERSAND
					+ SecureUtil.sha256X16Str(secureKey, encoding);
			String strAfterSha256 = SecureUtil.sha256X16Str(strBeforeSha256,
					encoding);
			// 设置签名域值
			data.put(SDKConstants.param_signature, strAfterSha256);
			return true;
		} else if (SIGNMETHOD_SM3.equals(signMethod)) {
			String stringData = coverMap2String(data);
			log.info("待签名请求报文串:[" + stringData + "]");
			String strBeforeSM3 = stringData
					+ SDKConstants.AMPERSAND
					+ SecureUtil.sm3X16Str(secureKey, encoding);
			String strAfterSM3 = SecureUtil.sm3X16Str(strBeforeSM3, encoding);
			// 设置签名域值
			data.put(SDKConstants.param_signature, strAfterSM3);
			return true;
		}
		return false;
	}

	/**
	 * 通过传入的签名密钥进行签名并返回签名值<br>
	 * @param data
	 *            待签名数据Map键值对形式
	 * @param encoding
	 *            编码
	 * @param certPath
	 *            证书绝对路径
	 * @param certPwd
	 *            证书密码
	 * @return 签名值
	 */
	public static boolean signByCertInfo(Map<String, String> data,
			String certPath, String certPwd, String encoding) {

		if (StringUtils.isBlank(encoding)) {
			encoding = "UTF-8";
		}
		if (StringUtils.isBlank(certPath) || StringUtils.isBlank(certPwd)) {
			log.info("CertPath or CertPwd is empty");
			return false;
		}
		String signMethod = data.get(param_signMethod);
		String version = data.get(SDKConstants.param_version);
		if (!VERSION_1_0_0.equals(version) && !VERSION_5_0_1.equals(version) && StringUtils.isBlank(signMethod)) {
			log.info("signMethod must Not null");
			return false;
		}
		if (StringUtils.isBlank(version)) {
			log.info("version must Not null");
			return false;
		}

		if (SIGNMETHOD_RSA.equals(signMethod) || VERSION_1_0_0.equals(version) || VERSION_5_0_1.equals(version)) {
			if (VERSION_5_0_0.equals(version) || VERSION_1_0_0.equals(version) || VERSION_5_0_1.equals(version)) {
				// 设置签名证书序列号
				data.put(SDKConstants.param_certId, CertUtil.getCertIdByKeyStoreMap(certPath, certPwd));
				// 将Map信息转换成key1=value1&key2=value2的形式
				String stringData = coverMap2String(data);
				log.info("待签名请求报文串:[" + stringData + "]");
				byte[] byteSign = null;
				String stringSign = null;
				try {
					// 通过SHA1进行摘要并转16进制
					byte[] signDigest = SecureUtil
							.sha1X16(stringData, encoding);
					byteSign = SecureUtil.base64Encode(SecureUtil.signBySoft(
							CertUtil.getSignCertPrivateKeyByStoreMap(certPath, certPwd), signDigest));
					stringSign = new String(byteSign);
					// 设置签名域值
					data.put(SDKConstants.param_signature, stringSign);
					return true;
				} catch (Exception e) {
					log.info("Sign Error", e);
					return false;
				}
			} else if (VERSION_5_1_0.equals(version)) {
				// 设置签名证书序列号
				data.put(SDKConstants.param_certId, CertUtil.getCertIdByKeyStoreMap(certPath, certPwd));
				// 将Map信息转换成key1=value1&key2=value2的形式
				String stringData = coverMap2String(data);
				log.info("待签名请求报文串:[" + stringData + "]");
				byte[] byteSign = null;
				String stringSign = null;
				try {
					// 通过SHA256进行摘要并转16进制
					byte[] signDigest = SecureUtil
							.sha256X16(stringData, encoding);
					byteSign = SecureUtil.base64Encode(SecureUtil.signBySoft256(
							CertUtil.getSignCertPrivateKeyByStoreMap(certPath, certPwd), signDigest));
					stringSign = new String(byteSign);
					// 设置签名域值
					data.put(SDKConstants.param_signature, stringSign);
					return true;
				} catch (Exception e) {
					log.info("Sign Error", e);
					return false;
				}
			}

		}
		return false;
	}

	/**
	 * 验证签名
	 * @param resData
	 *            返回报文数据
	 * @param encoding
	 *            编码格式
	 * @return
	 */
	public static boolean validateBySecureKey(Map<String, String> resData, String secureKey, String encoding) {
		log.info("验签处理开始");
		if (StringUtils.isBlank(encoding)) {
			encoding = "UTF-8";
		}
		String signMethod = resData.get(param_signMethod);
		if (SIGNMETHOD_SHA256.equals(signMethod)) {
			// 1.进行SHA256验证
			String stringSign = resData.get(SDKConstants.param_signature);
			log.info("签名原文：[" + stringSign + "]");
			// 将Map信息转换成key1=value1&key2=value2的形式
			String stringData = coverMap2String(resData);
			log.info("待验签返回报文串：[" + stringData + "]");
			String strBeforeSha256 = stringData
					+ SDKConstants.AMPERSAND
					+ SecureUtil.sha256X16Str(secureKey, encoding);
			String strAfterSha256 = SecureUtil.sha256X16Str(strBeforeSha256,
					encoding);
			return stringSign.equals(strAfterSha256);
		} else if (SIGNMETHOD_SM3.equals(signMethod)) {
			// 1.进行SM3验证
			String stringSign = resData.get(SDKConstants.param_signature);
			log.info("签名原文：[" + stringSign + "]");
			// 将Map信息转换成key1=value1&key2=value2的形式
			String stringData = coverMap2String(resData);
			log.info("待验签返回报文串：[" + stringData + "]");
			String strBeforeSM3 = stringData
					+ SDKConstants.AMPERSAND
					+ SecureUtil.sm3X16Str(secureKey, encoding);
			String strAfterSM3 = SecureUtil
					.sm3X16Str(strBeforeSM3, encoding);
			return stringSign.equals(strAfterSM3);
		}
		return false;
	}

	/**
	 * 验证签名
	 * @param resData
	 *            返回报文数据
	 * @param encoding
	 *            编码格式
	 * @return
	 */
	public static boolean validate(Map<String, String> resData, String encoding) {
		log.info("验签处理开始");
		if (StringUtils.isBlank(encoding)) {
			encoding = "UTF-8";
		}
		String signMethod = resData.get(param_signMethod);
		String version = resData.get(SDKConstants.param_version);
		if (SIGNMETHOD_RSA.equals(signMethod) || VERSION_1_0_0.equals(version) || VERSION_5_0_1.equals(version)) {
			// 获取返回报文的版本号
			if (VERSION_5_0_0.equals(version) || VERSION_1_0_0.equals(version) || VERSION_5_0_1.equals(version)) {
				String stringSign = resData.get(SDKConstants.param_signature);
				log.info("签名原文：[" + stringSign + "]");
				// 从返回报文中获取certId ，然后去证书静态Map中查询对应验签证书对象
				String certId = resData.get(SDKConstants.param_certId);
				log.info("对返回报文串验签使用的验签公钥序列号：[" + certId + "]");
				// 将Map信息转换成key1=value1&key2=value2的形式
				String stringData = coverMap2String(resData);
				log.info("待验签返回报文串：[" + stringData + "]");
				try {
					// 验证签名需要用银联发给商户的公钥证书.
					return SecureUtil.validateSignBySoft(CertUtil.getValidatePublicKey(certId), SecureUtil
							.base64Decode(stringSign.getBytes(encoding)),
							SecureUtil.sha1X16(stringData, encoding));
				} catch (UnsupportedEncodingException e) {
					log.info(e.getMessage(), e);
				} catch (Exception e) {
					log.info(e.getMessage(), e);
				}
			} else if (VERSION_5_1_0.equals(version)) {
				// 1.从返回报文中获取公钥信息转换成公钥对象
				String strCert = resData.get(SDKConstants.param_signPubKeyCert);
				// log.info("验签公钥证书：["+strCert+"]");
				X509Certificate x509Cert = CertUtil.genCertificateByStr(strCert);
				if(x509Cert == null) {
					log.info("convert signPubKeyCert failed");
					return false;
				}
				// 2.验证证书链
				if (!CertUtil.verifyCertificate(x509Cert)) {
					log.info("验证公钥证书失败，证书信息：[" + strCert + "]");
					return false;
				}

				// 3.验签
				String stringSign = resData.get(SDKConstants.param_signature);
				log.info("签名原文：[" + stringSign + "]");
				// 将Map信息转换成key1=value1&key2=value2的形式
				String stringData = coverMap2String(resData);
				log.info("待验签返回报文串：[" + stringData + "]");
				try {
					// 验证签名需要用银联发给商户的公钥证书.
					boolean result = SecureUtil.validateSignBySoft256(x509Cert
							.getPublicKey(), SecureUtil.base64Decode(stringSign
							.getBytes(encoding)), SecureUtil.sha256X16(
							stringData, encoding));
					log.info("验证签名" + (result ? "成功" : "失败"));
					return result;
				} catch (UnsupportedEncodingException e) {
					log.info(e.getMessage(), e);
				} catch (Exception e) {
					log.info(e.getMessage(), e);
				}
			}

		} else if (SIGNMETHOD_SHA256.equals(signMethod)) {
			// 1.进行SHA256验证
			String stringSign = resData.get(SDKConstants.param_signature);
			log.info("签名原文：[" + stringSign + "]");
			// 将Map信息转换成key1=value1&key2=value2的形式
			String stringData = coverMap2String(resData);
			log.info("待验签返回报文串：[" + stringData + "]");
			String strBeforeSha256 = stringData
					+ SDKConstants.AMPERSAND
					+ SecureUtil.sha256X16Str(sdkConfig
							.getSecureKey(), encoding);
			String strAfterSha256 = SecureUtil.sha256X16Str(strBeforeSha256,
					encoding);
			boolean result =  stringSign.equals(strAfterSha256);
			log.info("验证签名" + (result ? "成功" : "失败"));
			return result;
		} else if (SIGNMETHOD_SM3.equals(signMethod)) {
			// 1.进行SM3验证
			String stringSign = resData.get(SDKConstants.param_signature);
			log.info("签名原文：[" + stringSign + "]");
			// 将Map信息转换成key1=value1&key2=value2的形式
			String stringData = coverMap2String(resData);
			log.info("待验签返回报文串：[" + stringData + "]");
			String strBeforeSM3 = stringData
					+ SDKConstants.AMPERSAND
					+ SecureUtil.sm3X16Str(sdkConfig
							.getSecureKey(), encoding);
			String strAfterSM3 = SecureUtil
					.sm3X16Str(strBeforeSM3, encoding);
			boolean result =  stringSign.equals(strAfterSM3);
			log.info("验证签名" + (result ? "成功" : "失败"));
			return result;
		}
		return false;
	}

	/**
	 * 将Map中的数据转换成key1=value1&key2=value2的形式 不包含签名域signature
	 * @param data
	 *            待拼接的Map数据
	 * @return 拼接好后的字符串
	 */
	public static String coverMap2String(Map<String, String> data) {
		TreeMap<String, String> tree = new TreeMap<String, String>();
		Iterator<Entry<String, String>> it = data.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String> en = it.next();
			if (SDKConstants.param_signature.equals(en.getKey().trim())) {
				continue;
			}
			tree.put(en.getKey(), en.getValue());
		}
		it = tree.entrySet().iterator();
		StringBuffer sf = new StringBuffer();
		while (it.hasNext()) {
			Entry<String, String> en = it.next();
			sf.append(en.getKey() + SDKConstants.EQUAL + en.getValue()
					+ SDKConstants.AMPERSAND);
		}
		return sf.substring(0, sf.length() - 1);
	}


	/**
	 * 兼容老方法 将形如key=value&key=value的字符串转换为相应的Map对象
	 * @param result
	 * @return
	 */
	public static Map<String, String> coverResultString2Map(String result) {
		return convertResultStringToMap(result);
	}

	/**
	 * 将形如key=value&key=value的字符串转换为相应的Map对象
	 * @param result
	 * @return
	 */
	public static Map<String, String> convertResultStringToMap(String result) {
		Map<String, String> map = null;

		if (StringUtils.isNotBlank(result)) {
			if (result.startsWith("{") && result.endsWith("}")) {
				result = result.substring(1, result.length() - 1);
			}
			map = parseQString(result);
		}

		return map;
	}


	/**
	 * 解析应答字符串，生成应答要素
	 * @param str
	 *            需要解析的字符串
	 * @return 解析的结果map
	 * @throws UnsupportedEncodingException
	 */
	public static Map<String, String> parseQString(String str) {

		Map<String, String> map = new HashMap<String, String>();
		int len = str.length();
		StringBuilder temp = new StringBuilder();
		char curChar;
		String key = null;
		boolean isKey = true;
		boolean isOpen = false;//值里有嵌套
		char openName = 0;
		if(len>0){
			for (int i = 0; i < len; i++) {// 遍历整个带解析的字符串
				curChar = str.charAt(i);// 取当前字符
				if (isKey) {// 如果当前生成的是key

					if (curChar == '=') {// 如果读取到=分隔符
						key = temp.toString();
						temp.setLength(0);
						isKey = false;
					} else {
						temp.append(curChar);
					}
				} else  {// 如果当前生成的是value
					if(isOpen){
						if(curChar == openName){
							isOpen = false;
						}

					}else{//如果没开启嵌套
						if(curChar == '{'){//如果碰到，就开启嵌套
							isOpen = true;
							openName ='}';
						}
						if(curChar == '['){
							isOpen = true;
							openName =']';
						}
					}

					if (curChar == '&' && !isOpen) {// 如果读取到&分割符,同时这个分割符不是值域，这时将map里添加
						putKeyValueToMap(temp, isKey, key, map);
						temp.setLength(0);
						isKey = true;
					} else {
						temp.append(curChar);
					}
				}

			}
			putKeyValueToMap(temp, isKey, key, map);
		}
		return map;
	}

	private static void putKeyValueToMap(StringBuilder temp, boolean isKey,
			String key, Map<String, String> map) {
		if (isKey) {
			key = temp.toString();
			if (key.length() == 0) {
				throw new RuntimeException("QString format illegal");
			}
			map.put(key, "");
		} else {
			if (key.length() == 0) {
				throw new RuntimeException("QString format illegal");
			}
			map.put(key, temp.toString());
		}
	}

	/**
	 * 获取应答报文中的加密公钥证书,并存储到本地,并备份原始证书<br>
	 * 更新成功则返回1，无更新返回0，失败异常返回-1。
	 * @param resData
	 * @param encoding
	 * @return
	 */
	public static int getEncryptCert(Map<String, String> resData,
			String encoding) {
		String strCert = resData.get(SDKConstants.param_encryptPubKeyCert);
		String certType = resData.get(SDKConstants.param_certType);
		if (StringUtils.isBlank(strCert) || StringUtils.isBlank(certType))
			return -1;
		X509Certificate x509Cert = CertUtil.genCertificateByStr(strCert);
		if (CERTTYPE_01.equals(certType)) {
			// 更新敏感信息加密公钥
			if (!CertUtil.getEncryptCertId().equals(
					x509Cert.getSerialNumber().toString())) {
				// ID不同时进行本地证书更新操作
				String localCertPath = sdkConfig.getEncryptCertPath();
				String newLocalCertPath = FileUtil.genBackupName(localCertPath);
				// 1.将本地证书进行备份存储
				if (!FileUtil.copyFile(localCertPath, newLocalCertPath))
					return -1;
				// 2.备份成功,进行新证书的存储
				if (!FileUtil.writeFile(localCertPath, strCert, encoding))
					return -1;
				log.info("save new encryptPubKeyCert success");
				CertUtil.resetEncryptCertPublicKey();
				return 1;
			}else {
				return 0;
			}

		} else if (CERTTYPE_02.equals(certType)) {
//			// 更新磁道加密公钥
//			if (!CertUtil.getEncryptTrackCertId().equals(
//					x509Cert.getSerialNumber().toString())) {
//				// ID不同时进行本地证书更新操作
//				String localCertPath = sdkConfig.getEncryptTrackCertPath();
//				String newLocalCertPath = genBackupName(localCertPath);
//				// 1.将本地证书进行备份存储
//				if (!copyFile(localCertPath, newLocalCertPath))
//					return -1;
//				// 2.备份成功,进行新证书的存储
//				if (!writeFile(localCertPath, strCert, ENCODING))
//					return -1;
			// log.info("save new encryptPubKeyCert success");
//				CertUtil.resetEncryptTrackCertPublicKey();
//				return 1;
//			}else {
				return 0;
//			}
		}else {
			log.info("unknown cerType:" + certType);
			return -1;
		}
	}

}
