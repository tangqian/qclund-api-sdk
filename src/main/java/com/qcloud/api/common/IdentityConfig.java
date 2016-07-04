package com.qcloud.api.common;

/**
 * @author tangqian
 *
 */
public class IdentityConfig {

	private final String secretId;
	
	private final String secretKey;
	
	public IdentityConfig(String secretId, String secretKey) {
		this.secretId = secretId;
		this.secretKey = secretKey;
	}

	public String getSecretId() {
		return secretId;
	}

	public String getSecretKey() {
		return secretKey;
	}
}
