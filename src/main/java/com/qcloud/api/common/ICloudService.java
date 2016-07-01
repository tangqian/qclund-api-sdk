package com.qcloud.api.common;

import java.util.TreeMap;

import com.qcloud.api.callers.base.IModuleCaller;

public interface ICloudService<T extends CloudBaseResponse> {

	T callAction(IModuleCaller caller, TreeMap<String, Object> params, String requestMethod);

	String getActionName();

}