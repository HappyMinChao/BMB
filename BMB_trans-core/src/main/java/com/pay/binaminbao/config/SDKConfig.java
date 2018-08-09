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
 *   xshu       2014-05-28       MPI基本参数工具类
 * =============================================================================
 */
package com.pay.binaminbao.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


/**
 * @ClassName SDKConfig
 * @Description acpsdk配置文件acp_sdk.properties配置信息类
 * @date 2016-7-22 下午4:04:55
 *       声明：以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己需要，按照技术文档编写。
 *       该代码仅供参考，不提供编码，性能，规范性等方面的保障
 */

@Component
@PropertySource(value = "classpath:acp_sdk.properties")
@ConfigurationProperties(prefix = "acpsdk")
public class SDKConfig {
	
	private Log log = LogFactory.getLog(SDKConfig.class);
	
	public final String FILE_NAME = "acp_sdk.properties";
	
	
	//缴费查询账单地址
	private String jfQueryBillRequestUrl;
	
	/** 前台请求URL. */
	private String frontRequestUrl;
	/** 后台请求URL. */
	private String backRequestUrl;
	/** 单笔查询 */
	private String singleQueryUrl;
	/** 批量查询 */
	private String batchQueryUrl;
	/** 批量交易 */
	private String batchTransUrl;
	/** 文件传输 */
	private String fileTransUrl;
	/** 签名证书路径. */
	private String signCertPath;
	/** 签名证书密码. */
	private String signCertPwd;
	/** 签名证书类型. */
	private String signCertType;
	/** 加密公钥证书路径. */
	private String encryptCertPath;
	/** 验证签名公钥证书目录. */
	private String validateCertDir;
	/** 按照商户代码读取指定签名证书目录. */
	private String signCertDir;
	/** 磁道加密证书路径. */
	private String encryptTrackCertPath;
	/** 磁道加密公钥模数. */
	private String encryptTrackKeyModulus;
	/** 磁道加密公钥指数. */
	private String encryptTrackKeyExponent;
	/** 有卡交易. */
	private String cardRequestUrl;
	/** app交易 */
	private String appRequestUrl;
	/** 证书使用模式(单证书/多证书) */
	private String singleMode;
	/** 安全密钥(SHA256和SM3计算时使用) */
	private String secureKey;
	/** 中级证书路径 敏感信息加密文件路径 */
	private String middleCertPath;
	/** 根证书路径  */
	private String rootCertPath;
	/** 是否验证验签证书CN，除了false都验  */
	private boolean ifValidateCNName = true;
	/** 是否验证https证书，默认都不验  */
	private boolean ifValidateRemoteCert = false;
	/** signMethod，没配按01吧  */
	private String signMethod = "01";
	
	/** frontUrl  */
	private String frontUrl;
	/** backUrl  */
	private String backUrl;
	/** mechId */
	private String mechId;

	/*缴费相关地址*/
	private String jfFrontRequestUrl;
	private String jfBackRequestUrl;
	private String jfSingleQueryUrl;
	private String jfCardTransUrl;
	private String jfAppRequestUrl;

	private String qrcBackTransUrl;
	private String qrcB2cIssBackTransUrl;
	private String qrcB2cMerBackTransUrl;

	/** 配置文件中的前台URL常量. */
	public final String SDK_FRONT_URL = "acpsdk.frontTransUrl";
	/** 配置文件中的后台URL常量. */
	public final String SDK_BACK_URL = "acpsdk.backTransUrl";
	/** 配置文件中的单笔交易查询URL常量. */
	public final String SDK_SIGNQ_URL = "acpsdk.singleQueryUrl";
	/** 配置文件中的批量交易查询URL常量. */
	public final String SDK_BATQ_URL = "acpsdk.batchQueryUrl";
	/** 配置文件中的批量交易URL常量. */
	public final String SDK_BATTRANS_URL = "acpsdk.batchTransUrl";
	/** 配置文件中的文件类交易URL常量. */
	public final String SDK_FILETRANS_URL = "acpsdk.fileTransUrl";
	/** 配置文件中的有卡交易URL常量. */
	public final String SDK_CARD_URL = "acpsdk.cardTransUrl";
	/** 配置文件中的app交易URL常量. */
	public final String SDK_APP_URL = "acpsdk.appTransUrl";

	/** 以下缴费产品使用，其余产品用不到，无视即可 */
	// 前台请求地址
	public final String JF_SDK_FRONT_TRANS_URL= "acpsdk.jfFrontTransUrl";
	// 后台请求地址
	public final String JF_SDK_BACK_TRANS_URL="acpsdk.jfBackTransUrl";
	// 单笔查询请求地址
	public final String JF_SDK_SINGLE_QUERY_URL="acpsdk.jfSingleQueryUrl";
	// 有卡交易地址
	public final String JF_SDK_CARD_TRANS_URL="acpsdk.jfCardTransUrl";
	// App交易地址
	public final String JF_SDK_APP_TRANS_URL="acpsdk.jfAppTransUrl";
	// 人到人
	public final String QRC_BACK_TRANS_URL="acpsdk.qrcBackTransUrl";
	// 人到人
	public final String QRC_B2C_ISS_BACK_TRANS_URL="acpsdk.qrcB2cIssBackTransUrl";
	// 人到人
	public final String QRC_B2C_MER_BACK_TRANS_URL="acpsdk.qrcB2cMerBackTransUrl";


	/** RSA 签名证书路径常量. */
	public final String SDK_SIGNCERT_PATH = "acpsdk.signCert.path";
	/** RSA 签名证书密码常量. */
	public final String SDK_SIGNCERT_PWD = "acpsdk.signCert.pwd";
	/** RSA 签名证书类型常量. */
	public final String SDK_SIGNCERT_TYPE = "acpsdk.signCert.type";
	/** 配置文件中密码加密证书路径常量. */
	public final String SDK_ENCRYPTCERT_PATH = "acpsdk.encryptCert.path";
	/** 配置文件中磁道加密证书路径常量. */
	public final String SDK_ENCRYPTTRACKCERT_PATH = "acpsdk.encryptTrackCert.path";
	/** 配置文件中磁道加密公钥模数常量. */
	public final String SDK_ENCRYPTTRACKKEY_MODULUS = "acpsdk.encryptTrackKey.modulus";
	/** 配置文件中磁道加密公钥指数常量. */
	public final String SDK_ENCRYPTTRACKKEY_EXPONENT = "acpsdk.encryptTrackKey.exponent";
	/** 配置文件中验证签名证书目录常量. */
	public final String SDK_VALIDATECERT_DIR = "acpsdk.validateCert.dir";

	/** 配置文件中是否加密cvn2常量. */
	public final String SDK_CVN_ENC = "acpsdk.cvn2.enc";
	/** 配置文件中是否加密cvn2有效期常量. */
	public final String SDK_DATE_ENC = "acpsdk.date.enc";
	/** 配置文件中是否加密卡号常量. */
	public final String SDK_PAN_ENC = "acpsdk.pan.enc";
	/** 配置文件中证书使用模式 */
	public final String SDK_SINGLEMODE = "acpsdk.singleMode";
	/** 配置文件中安全密钥 */
	public final String SDK_SECURITYKEY = "acpsdk.secureKey";
	/** 配置文件中根证书路径常量  */
	public final String SDK_ROOTCERT_PATH = "acpsdk.rootCert.path";
	/** 配置文件中根证书路径常量  */
	public final String SDK_MIDDLECERT_PATH = "acpsdk.middleCert.path";
	/** 配置是否需要验证验签证书CN，除了false之外的值都当true处理 */
	public final String SDK_IF_VALIDATE_CN_NAME = "acpsdk.ifValidateCNName";
	/** 配置是否需要验证https证书，除了true之外的值都当false处理 */
	public final String SDK_IF_VALIDATE_REMOTE_CERT = "acpsdk.ifValidateRemoteCert";
	/** signmethod */
	public final String SDK_SIGN_METHOD ="acpsdk.signMethod";
	/** version */
	public final String SDK_VERSION = "acpsdk.version";
	/** ENCODING */
	public final String SDK_ENCODING = "acpsdk.ENCODING";
	/** 后台通知地址  */
	public final String SDK_BACKURL = "acpsdk.backUrl";
	/** 前台通知地址  */
	public final String SDK_FRONTURL = "acpsdk.frontUrl";
	/** 商户号 */
	public final String SDK_MECHID = "acpsdk.mechId";

	public String getFrontRequestUrl() {
		return frontRequestUrl;
	}

	public void setFrontRequestUrl(String frontRequestUrl) {
		this.frontRequestUrl = frontRequestUrl;
	}

	public String getBackRequestUrl() {
		return backRequestUrl;
	}

	public void setBackRequestUrl(String backRequestUrl) {
		this.backRequestUrl = backRequestUrl;
	}

	public String getSingleQueryUrl() {
		return singleQueryUrl;
	}

	public void setSingleQueryUrl(String singleQueryUrl) {
		this.singleQueryUrl = singleQueryUrl;
	}

	public String getBatchQueryUrl() {
		return batchQueryUrl;
	}

	public void setBatchQueryUrl(String batchQueryUrl) {
		this.batchQueryUrl = batchQueryUrl;
	}

	public String getBatchTransUrl() {
		return batchTransUrl;
	}

	public void setBatchTransUrl(String batchTransUrl) {
		this.batchTransUrl = batchTransUrl;
	}

	public String getFileTransUrl() {
		return fileTransUrl;
	}

	public void setFileTransUrl(String fileTransUrl) {
		this.fileTransUrl = fileTransUrl;
	}

	public String getSignCertPath() {
		return signCertPath;
	}

	public void setSignCertPath(String signCertPath) {
		this.signCertPath = signCertPath;
	}

	public String getSignCertPwd() {
		return signCertPwd;
	}

	public void setSignCertPwd(String signCertPwd) {
		this.signCertPwd = signCertPwd;
	}

	public String getSignCertType() {
		return signCertType;
	}

	public void setSignCertType(String signCertType) {
		this.signCertType = signCertType;
	}

	public String getEncryptCertPath() {
		return encryptCertPath;
	}

	public void setEncryptCertPath(String encryptCertPath) {
		this.encryptCertPath = encryptCertPath;
	}

	public String getValidateCertDir() {
		return validateCertDir;
	}

	public void setValidateCertDir(String validateCertDir) {
		this.validateCertDir = validateCertDir;
	}

	public String getSignCertDir() {
		return signCertDir;
	}

	public void setSignCertDir(String signCertDir) {
		this.signCertDir = signCertDir;
	}

	public String getEncryptTrackCertPath() {
		return encryptTrackCertPath;
	}

	public void setEncryptTrackCertPath(String encryptTrackCertPath) {
		this.encryptTrackCertPath = encryptTrackCertPath;
	}

	public String getEncryptTrackKeyModulus() {
		return encryptTrackKeyModulus;
	}

	public void setEncryptTrackKeyModulus(String encryptTrackKeyModulus) {
		this.encryptTrackKeyModulus = encryptTrackKeyModulus;
	}

	public String getEncryptTrackKeyExponent() {
		return encryptTrackKeyExponent;
	}

	public void setEncryptTrackKeyExponent(String encryptTrackKeyExponent) {
		this.encryptTrackKeyExponent = encryptTrackKeyExponent;
	}

	public String getCardRequestUrl() {
		return cardRequestUrl;
	}

	public void setCardRequestUrl(String cardRequestUrl) {
		this.cardRequestUrl = cardRequestUrl;
	}

	public String getAppRequestUrl() {
		return appRequestUrl;
	}

	public void setAppRequestUrl(String appRequestUrl) {
		this.appRequestUrl = appRequestUrl;
	}

	public String getSingleMode() {
		return singleMode;
	}

	public void setSingleMode(String singleMode) {
		this.singleMode = singleMode;
	}

	public String getSecureKey() {
		return secureKey;
	}

	public void setSecureKey(String secureKey) {
		this.secureKey = secureKey;
	}

	public String getMiddleCertPath() {
		return middleCertPath;
	}

	public void setMiddleCertPath(String middleCertPath) {
		this.middleCertPath = middleCertPath;
	}

	public String getRootCertPath() {
		return rootCertPath;
	}

	public void setRootCertPath(String rootCertPath) {
		this.rootCertPath = rootCertPath;
	}

	public boolean isIfValidateCNName() {
		return ifValidateCNName;
	}

	public void setIfValidateCNName(boolean ifValidateCNName) {
		this.ifValidateCNName = ifValidateCNName;
	}

	public boolean isIfValidateRemoteCert() {
		return ifValidateRemoteCert;
	}

	public void setIfValidateRemoteCert(boolean ifValidateRemoteCert) {
		this.ifValidateRemoteCert = ifValidateRemoteCert;
	}

	public String getSignMethod() {
		return signMethod;
	}

	public void setSignMethod(String signMethod) {
		this.signMethod = signMethod;
	}

	public String getFrontUrl() {
		return frontUrl;
	}

	public void setFrontUrl(String frontUrl) {
		this.frontUrl = frontUrl;
	}

	public String getBackUrl() {
		return backUrl;
	}

	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}

	public String getMechId() {
		return mechId;
	}

	public void setMechId(String mechId) {
		this.mechId = mechId;
	}

	public String getJfFrontRequestUrl() {
		return jfFrontRequestUrl;
	}

	public void setJfFrontRequestUrl(String jfFrontRequestUrl) {
		this.jfFrontRequestUrl = jfFrontRequestUrl;
	}

	public String getJfBackRequestUrl() {
		return jfBackRequestUrl;
	}

	public void setJfBackRequestUrl(String jfBackRequestUrl) {
		this.jfBackRequestUrl = jfBackRequestUrl;
	}

	public String getJfSingleQueryUrl() {
		return jfSingleQueryUrl;
	}

	public void setJfSingleQueryUrl(String jfSingleQueryUrl) {
		this.jfSingleQueryUrl = jfSingleQueryUrl;
	}

	public String getJfCardTransUrl() {
		return jfCardTransUrl;
	}

	public void setJfCardTransUrl(String jfCardTransUrl) {
		this.jfCardTransUrl = jfCardTransUrl;
	}

	public String getJfAppRequestUrl() {
		return jfAppRequestUrl;
	}

	public void setJfAppRequestUrl(String jfAppRequestUrl) {
		this.jfAppRequestUrl = jfAppRequestUrl;
	}

	public String getQrcBackTransUrl() {
		return qrcBackTransUrl;
	}

	public void setQrcBackTransUrl(String qrcBackTransUrl) {
		this.qrcBackTransUrl = qrcBackTransUrl;
	}

	public String getQrcB2cIssBackTransUrl() {
		return qrcB2cIssBackTransUrl;
	}

	public void setQrcB2cIssBackTransUrl(String qrcB2cIssBackTransUrl) {
		this.qrcB2cIssBackTransUrl = qrcB2cIssBackTransUrl;
	}

	public String getQrcB2cMerBackTransUrl() {
		return qrcB2cMerBackTransUrl;
	}

	public void setQrcB2cMerBackTransUrl(String qrcB2cMerBackTransUrl) {
		this.qrcB2cMerBackTransUrl = qrcB2cMerBackTransUrl;
	}

	public String getSDK_FRONT_URL() {
		return SDK_FRONT_URL;
	}

	public String getSDK_BACK_URL() {
		return SDK_BACK_URL;
	}

	public String getSDK_SIGNQ_URL() {
		return SDK_SIGNQ_URL;
	}

	public String getSDK_BATQ_URL() {
		return SDK_BATQ_URL;
	}

	public String getSDK_BATTRANS_URL() {
		return SDK_BATTRANS_URL;
	}

	public String getSDK_FILETRANS_URL() {
		return SDK_FILETRANS_URL;
	}

	public String getSDK_CARD_URL() {
		return SDK_CARD_URL;
	}

	public String getSDK_APP_URL() {
		return SDK_APP_URL;
	}

	public String getJF_SDK_FRONT_TRANS_URL() {
		return JF_SDK_FRONT_TRANS_URL;
	}

	public String getJF_SDK_BACK_TRANS_URL() {
		return JF_SDK_BACK_TRANS_URL;
	}

	public String getJF_SDK_SINGLE_QUERY_URL() {
		return JF_SDK_SINGLE_QUERY_URL;
	}

	public String getJF_SDK_CARD_TRANS_URL() {
		return JF_SDK_CARD_TRANS_URL;
	}

	public String getJF_SDK_APP_TRANS_URL() {
		return JF_SDK_APP_TRANS_URL;
	}

	public String getQRC_BACK_TRANS_URL() {
		return QRC_BACK_TRANS_URL;
	}

	public String getQRC_B2C_ISS_BACK_TRANS_URL() {
		return QRC_B2C_ISS_BACK_TRANS_URL;
	}

	public String getQRC_B2C_MER_BACK_TRANS_URL() {
		return QRC_B2C_MER_BACK_TRANS_URL;
	}

	public String getSDK_SIGNCERT_PATH() {
		return SDK_SIGNCERT_PATH;
	}

	public String getSDK_SIGNCERT_PWD() {
		return SDK_SIGNCERT_PWD;
	}

	public String getSDK_SIGNCERT_TYPE() {
		return SDK_SIGNCERT_TYPE;
	}

	public String getSDK_ENCRYPTCERT_PATH() {
		return SDK_ENCRYPTCERT_PATH;
	}

	public String getSDK_ENCRYPTTRACKCERT_PATH() {
		return SDK_ENCRYPTTRACKCERT_PATH;
	}

	public String getSDK_ENCRYPTTRACKKEY_MODULUS() {
		return SDK_ENCRYPTTRACKKEY_MODULUS;
	}

	public String getSDK_ENCRYPTTRACKKEY_EXPONENT() {
		return SDK_ENCRYPTTRACKKEY_EXPONENT;
	}

	public String getSDK_VALIDATECERT_DIR() {
		return SDK_VALIDATECERT_DIR;
	}

	public String getSDK_CVN_ENC() {
		return SDK_CVN_ENC;
	}

	public String getSDK_DATE_ENC() {
		return SDK_DATE_ENC;
	}

	public String getSDK_PAN_ENC() {
		return SDK_PAN_ENC;
	}

	public String getSDK_SINGLEMODE() {
		return SDK_SINGLEMODE;
	}

	public String getSDK_SECURITYKEY() {
		return SDK_SECURITYKEY;
	}

	public String getSDK_ROOTCERT_PATH() {
		return SDK_ROOTCERT_PATH;
	}

	public String getSDK_MIDDLECERT_PATH() {
		return SDK_MIDDLECERT_PATH;
	}

	public String getSDK_IF_VALIDATE_CN_NAME() {
		return SDK_IF_VALIDATE_CN_NAME;
	}

	public String getSDK_IF_VALIDATE_REMOTE_CERT() {
		return SDK_IF_VALIDATE_REMOTE_CERT;
	}

	public String getSDK_SIGN_METHOD() {
		return SDK_SIGN_METHOD;
	}

	public String getSDK_VERSION() {
		return SDK_VERSION;
	}

	public String getSDK_ENCODING() {
		return SDK_ENCODING;
	}

	public String getSDK_BACKURL() {
		return SDK_BACKURL;
	}

	public String getSDK_FRONTURL() {
		return SDK_FRONTURL;
	}

	public String getSDK_MECHID() {
		return SDK_MECHID;
	}


	public String getJfQueryBillRequestUrl() {
		return jfQueryBillRequestUrl;
	}

	public void setJfQueryBillRequestUrl(String jfQueryBillRequestUrl) {
		this.jfQueryBillRequestUrl = jfQueryBillRequestUrl;
	}
	
	@Override
	public String toString() {
		return "SDKConfig{" +
				"FILE_NAME='" + FILE_NAME + '\'' +
				", frontRequestUrl='" + frontRequestUrl + '\'' +
				", backRequestUrl='" + backRequestUrl + '\'' +
				", singleQueryUrl='" + singleQueryUrl + '\'' +
				", batchQueryUrl='" + batchQueryUrl + '\'' +
				", batchTransUrl='" + batchTransUrl + '\'' +
				", fileTransUrl='" + fileTransUrl + '\'' +
				", signCertPath='" + signCertPath + '\'' +
				", signCertPwd='" + signCertPwd + '\'' +
				", signCertType='" + signCertType + '\'' +
				", encryptCertPath='" + encryptCertPath + '\'' +
				", validateCertDir='" + validateCertDir + '\'' +
				", signCertDir='" + signCertDir + '\'' +
				", encryptTrackCertPath='" + encryptTrackCertPath + '\'' +
				", encryptTrackKeyModulus='" + encryptTrackKeyModulus + '\'' +
				", encryptTrackKeyExponent='" + encryptTrackKeyExponent + '\'' +
				", cardRequestUrl='" + cardRequestUrl + '\'' +
				", appRequestUrl='" + appRequestUrl + '\'' +
				", singleMode='" + singleMode + '\'' +
				", secureKey='" + secureKey + '\'' +
				", middleCertPath='" + middleCertPath + '\'' +
				", rootCertPath='" + rootCertPath + '\'' +
				", ifValidateCNName=" + ifValidateCNName +
				", ifValidateRemoteCert=" + ifValidateRemoteCert +
				", signMethod='" + signMethod + '\'' +
				", frontUrl='" + frontUrl + '\'' +
				", backUrl='" + backUrl + '\'' +
				", mechId='" + mechId + '\'' +
				", jfFrontRequestUrl='" + jfFrontRequestUrl + '\'' +
				", jfBackRequestUrl='" + jfBackRequestUrl + '\'' +
				", jfSingleQueryUrl='" + jfSingleQueryUrl + '\'' +
				", jfCardRequestUrl='" + jfCardTransUrl + '\'' +
				", jfAppRequestUrl='" + jfAppRequestUrl + '\'' +
				", qrcBackTransUrl='" + qrcBackTransUrl + '\'' +
				", qrcB2cIssBackTransUrl='" + qrcB2cIssBackTransUrl + '\'' +
				", qrcB2cMerBackTransUrl='" + qrcB2cMerBackTransUrl + '\'' +
				", SDK_FRONT_URL='" + SDK_FRONT_URL + '\'' +
				", SDK_BACK_URL='" + SDK_BACK_URL + '\'' +
				", SDK_SIGNQ_URL='" + SDK_SIGNQ_URL + '\'' +
				", SDK_BATQ_URL='" + SDK_BATQ_URL + '\'' +
				", SDK_BATTRANS_URL='" + SDK_BATTRANS_URL + '\'' +
				", SDK_FILETRANS_URL='" + SDK_FILETRANS_URL + '\'' +
				", SDK_CARD_URL='" + SDK_CARD_URL + '\'' +
				", SDK_APP_URL='" + SDK_APP_URL + '\'' +
				", JF_SDK_FRONT_TRANS_URL='" + JF_SDK_FRONT_TRANS_URL + '\'' +
				", JF_SDK_BACK_TRANS_URL='" + JF_SDK_BACK_TRANS_URL + '\'' +
				", JF_SDK_SINGLE_QUERY_URL='" + JF_SDK_SINGLE_QUERY_URL + '\'' +
				", JF_SDK_CARD_TRANS_URL='" + JF_SDK_CARD_TRANS_URL + '\'' +
				", JF_SDK_APP_TRANS_URL='" + JF_SDK_APP_TRANS_URL + '\'' +
				", QRC_BACK_TRANS_URL='" + QRC_BACK_TRANS_URL + '\'' +
				", QRC_B2C_ISS_BACK_TRANS_URL='" + QRC_B2C_ISS_BACK_TRANS_URL + '\'' +
				", QRC_B2C_MER_BACK_TRANS_URL='" + QRC_B2C_MER_BACK_TRANS_URL + '\'' +
				", SDK_SIGNCERT_PATH='" + SDK_SIGNCERT_PATH + '\'' +
				", SDK_SIGNCERT_PWD='" + SDK_SIGNCERT_PWD + '\'' +
				", SDK_SIGNCERT_TYPE='" + SDK_SIGNCERT_TYPE + '\'' +
				", SDK_ENCRYPTCERT_PATH='" + SDK_ENCRYPTCERT_PATH + '\'' +
				", SDK_ENCRYPTTRACKCERT_PATH='" + SDK_ENCRYPTTRACKCERT_PATH + '\'' +
				", SDK_ENCRYPTTRACKKEY_MODULUS='" + SDK_ENCRYPTTRACKKEY_MODULUS + '\'' +
				", SDK_ENCRYPTTRACKKEY_EXPONENT='" + SDK_ENCRYPTTRACKKEY_EXPONENT + '\'' +
				", SDK_VALIDATECERT_DIR='" + SDK_VALIDATECERT_DIR + '\'' +
				", SDK_CVN_ENC='" + SDK_CVN_ENC + '\'' +
				", SDK_DATE_ENC='" + SDK_DATE_ENC + '\'' +
				", SDK_PAN_ENC='" + SDK_PAN_ENC + '\'' +
				", SDK_SINGLEMODE='" + SDK_SINGLEMODE + '\'' +
				", SDK_SECURITYKEY='" + SDK_SECURITYKEY + '\'' +
				", SDK_ROOTCERT_PATH='" + SDK_ROOTCERT_PATH + '\'' +
				", SDK_MIDDLECERT_PATH='" + SDK_MIDDLECERT_PATH + '\'' +
				", SDK_IF_VALIDATE_CN_NAME='" + SDK_IF_VALIDATE_CN_NAME + '\'' +
				", SDK_IF_VALIDATE_REMOTE_CERT='" + SDK_IF_VALIDATE_REMOTE_CERT + '\'' +
				", SDK_SIGN_METHOD='" + SDK_SIGN_METHOD + '\'' +
				", SDK_VERSION='" + SDK_VERSION + '\'' +
				", SDK_ENCODING='" + SDK_ENCODING + '\'' +
				", SDK_BACKURL='" + SDK_BACKURL + '\'' +
				", SDK_FRONTURL='" + SDK_FRONTURL + '\'' +
				", SDK_MECHID='" + SDK_MECHID + '\'' +
				'}';
	}
}
