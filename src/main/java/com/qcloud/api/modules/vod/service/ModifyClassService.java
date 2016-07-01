package com.qcloud.api.modules.vod.service;

import java.util.TreeMap;

import com.qcloud.api.callers.VodModuleCaller;
import com.qcloud.api.common.CloudBaseResponse;
import com.qcloud.api.modules.vod.VodBaseService;

/**
 * @author tangqian
 *
 */
public class ModifyClassService extends VodBaseService<CloudBaseResponse> {
	
	public ModifyClassService(VodModuleCaller caller) {
		super(caller);
	}
	
	@Override
	public String getActionName() {
		return "ModifyClass";
	}
	
	@Override
	protected Class<CloudBaseResponse> getResponseClass() {
		return CloudBaseResponse.class;
	}

	/**
	 * 修改分类名
	 * @param classId 待修改的分类id
	 * @param className 新的分类名
	 * @return
	 */
	public CloudBaseResponse call(Integer classId, String className) {
		TreeMap<String, Object> params = new TreeMap<String, Object>();
		params.put("classId", classId);
		params.put("className", className);
		CloudBaseResponse result = callVodAction(params, "GET");
		return result;
	}
}
