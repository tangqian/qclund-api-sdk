package com.qcloud.common;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.TreeMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qcloud.utils.Base64;

public class Sign {

	private static Logger logger = LoggerFactory.getLogger(Sign.class);

	// 编码方式
	private static final String CONTENT_CHARSET = "UTF-8";

	// HMAC算法
	private static final String HMAC_ALGORITHM = "HmacSHA1";

	private static Mac mac;

	/**
	 * @brief 签名
	 * @author gavinyao@tencent.com
	 *
	 * @param signStr
	 *            被加密串
	 * @param secret
	 *            加密密钥
	 *
	 * @return
	 */
	public static String sign(String signStr, String secret) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
		initMac(secret);
		byte[] hash = mac.doFinal(signStr.getBytes(CONTENT_CHARSET));
		String sig = Base64.encode(hash);
		return sig;
	}

	public static String makeSignPlainText(String requestMethod, String requestHost, String requestPath, TreeMap<String, Object> requestParams) {
		StringBuilder sb = new StringBuilder();
		sb.append(requestMethod).append(requestHost).append(requestPath).append(buildParamStr(requestParams, requestMethod));
		return sb.toString();
	}

	private static String buildParamStr(TreeMap<String, Object> requestParams, String requestMethod) {
		StringBuilder sb = new StringBuilder();
		for (String key : requestParams.keySet()) {
			// 排除上传文件的参数
			if (requestMethod == "POST" && requestParams.get(key).toString().substring(0, 1).equals("@")) {
				continue;
			}
			if (sb.length() == 0) {
				sb.append('?');
			} else {
				sb.append('&');
			}
			sb.append(key.replace("_", ".")).append('=').append(requestParams.get(key).toString());
		}
		return sb.toString();
	}

	private static void initMac(String secret) {
		if (mac == null) {
			try {
				mac = Mac.getInstance(HMAC_ALGORITHM);
				SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(CONTENT_CHARSET), mac.getAlgorithm());
				mac.init(secretKey);
			} catch (Exception e) {
				logger.error("Get HmacSHA1 instance error", e);
			}
		}
	}

	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
		System.out.println(sign("test", "XYER@123"));
		System.out.println(sign("test1", "XYER@123"));
	}
}
