package com.qcloud.api.modules.vod;

import java.util.TreeMap;

import com.qcloud.api.callers.VodModuleCaller;
import com.qcloud.api.common.AbstractCloudService;
import com.qcloud.api.common.CloudBaseResponse;


public abstract class VodBaseService<T extends CloudBaseResponse> extends AbstractCloudService<T> {

	private VodModuleCaller caller;
	
	public VodBaseService(VodModuleCaller caller) {
		this.caller = caller;
	}
	
	public T callVodAction(TreeMap<String, Object> params, String requestMethod) {
		return callAction(caller, params, requestMethod);
	}
}