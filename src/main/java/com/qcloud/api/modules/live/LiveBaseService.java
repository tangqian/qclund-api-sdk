package com.qcloud.api.modules.live;

import java.util.TreeMap;

import com.qcloud.api.callers.LiveModuleCaller;
import com.qcloud.api.common.AbstractCloudService;
import com.qcloud.api.common.CloudBaseResponse;


public abstract class LiveBaseService<T extends CloudBaseResponse> extends AbstractCloudService<T> {

	private LiveModuleCaller caller;
	
	public LiveBaseService(LiveModuleCaller caller) {
		this.caller = caller;
	}
	
	public T callAction(TreeMap<String, Object> params, String requestMethod) {
		return callAction(caller, params, requestMethod);
	}
}