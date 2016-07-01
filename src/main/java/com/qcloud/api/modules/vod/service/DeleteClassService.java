package com.qcloud.api.modules.vod.service;

import java.util.TreeMap;

import com.qcloud.api.callers.VodModuleCaller;
import com.qcloud.api.common.CloudBaseResponse;
import com.qcloud.api.modules.vod.VodBaseService;

/**
 * @author tangqian
 *
 */
public class DeleteClassService extends VodBaseService<CloudBaseResponse> {
	
	public DeleteClassService(VodModuleCaller caller) {
		super(caller);
	}
	
	@Override
	public String getActionName() {
		return "DeleteClass";
	}
	
	@Override
	protected Class<CloudBaseResponse> getResponseClass() {
		return CloudBaseResponse.class;
	}

	/**
	 * 用于管理视频文件，删除分类
	 * @param classId 分类id
	 * @return
	 */
	public CloudBaseResponse call(Integer classId) {
		TreeMap<String, Object> params = new TreeMap<String, Object>();
		params.put("classId", classId);
		CloudBaseResponse result = callVodAction(params, "GET");
		return result;
	}
}
