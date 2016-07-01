package com.qcloud.api.modules.vod.service;

import java.util.TreeMap;

import com.qcloud.api.callers.VodModuleCaller;
import com.qcloud.api.modules.vod.VodBaseService;
import com.qcloud.api.modules.vod.dto.CreateClassResponse;

/**
 * @author tangqian
 *
 */
public class CreateClassService extends VodBaseService<CreateClassResponse> {
	
	public CreateClassService(VodModuleCaller caller) {
		super(caller);
	}
	
	@Override
	public String getActionName() {
		return "CreateClass";
	}
	
	@Override
	protected Class<CreateClassResponse> getResponseClass() {
		return CreateClassResponse.class;
	}

	/**
	 * 创建视频分类
	 * @param className
	 * @param parentId 父分类的id号，若不填，默认生成一级分类
	 * @return
	 */
	public CreateClassResponse call(String className, Integer parentId) {
		TreeMap<String, Object> params = new TreeMap<String, Object>();
		params.put("className", className);
		if (parentId != null)
			params.put("parentId", parentId);
		
		CreateClassResponse result = callVodAction(params, "GET");
		return result;
	}
}
