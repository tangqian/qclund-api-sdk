package com.qcloud.api.callers;

import com.qcloud.api.callers.base.AbstractModuleCaller;
import com.qcloud.api.common.RequestClient;

/**
 * @author tangqian
 *
 */
public class LiveModuleCaller extends AbstractModuleCaller {
	
	public LiveModuleCaller(RequestClient client) {
		super(client, null);
	}
	
	/**
	 * @param client
	 * @param defaultRegion
	 */
	public LiveModuleCaller(RequestClient client, String defaultRegion) {
		super(client, defaultRegion);
	}

	@Override
	public String getServerHost(String actionName) {
		return "live.api.qcloud.com";
	}
	
}
