package com.qcloud.module;

import com.qcloud.api.callers.VodModuleCaller;
import com.qcloud.api.common.IdentityConfig;
import com.qcloud.api.common.RequestClient;

/**
 * @author tangqian
 *
 */
public class Config {

	private static final String secretId = "AKIDc7nRqE2zj4Ofv1wXHbhaYTba4sCIjazr";
	private static final String secretKey = "cfwMPsQIYhFM7H0VjzeAOgNE4AzNvutg";

	public static IdentityConfig identityConfig = new IdentityConfig(secretId, secretKey);

	public static RequestClient client = new RequestClient(identityConfig);
	
	public static VodModuleCaller vodCaller = new VodModuleCaller(Config.client);
}
