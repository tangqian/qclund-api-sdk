package com.qcloud.module.vod;

import com.qcloud.api.callers.LiveModuleCaller;
import com.qcloud.api.callers.VodModuleCaller;
import com.qcloud.api.common.IdentityConfig;
import com.qcloud.api.common.RequestClient;

/**
 * @author tangqian
 *
 */
public class Config {

	private static final String secretId = "你的SecretId";
	private static final String secretKey = "你的SecretKey";

	public static IdentityConfig identityConfig = new IdentityConfig(secretId, secretKey);

	public static RequestClient client = new RequestClient(identityConfig);
	
	public static VodModuleCaller vodCaller = new VodModuleCaller(client);
	
	public static LiveModuleCaller liveCaller = new LiveModuleCaller(client);
}
