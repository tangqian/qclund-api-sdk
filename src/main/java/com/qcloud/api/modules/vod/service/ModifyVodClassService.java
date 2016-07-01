package com.qcloud.api.modules.vod.service;

import java.util.TreeMap;

import com.qcloud.api.callers.VodModuleCaller;
import com.qcloud.api.common.CloudBaseResponse;
import com.qcloud.api.modules.vod.VodBaseService;

/**
 * @author tangqian
 *
 */
public class ModifyVodClassService extends VodBaseService<CloudBaseResponse> {
	
	public ModifyVodClassService(VodModuleCaller caller) {
		super(caller);
	}
	
	@Override
	public String getActionName() {
		return "ModifyVodClass";
	}
	
	@Override
	protected Class<CloudBaseResponse> getResponseClass() {
		return CloudBaseResponse.class;
	}

	/**
	 * 修改视频文件对应分类
	 * @param fileId 视频ID
	 * @param classId 分类ID
	 * @return
	 */
	public CloudBaseResponse call(String fileId, Integer classId) {
		TreeMap<String, Object> params = new TreeMap<String, Object>();
		params.put("fileId", fileId);
		params.put("classId", classId);
		CloudBaseResponse result = callVodAction(params, "GET");
		return result;
	}
}
