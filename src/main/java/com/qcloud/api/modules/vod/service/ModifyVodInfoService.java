package com.qcloud.api.modules.vod.service;

import java.util.TreeMap;

import com.qcloud.api.callers.VodModuleCaller;
import com.qcloud.api.common.CloudBaseResponse;
import com.qcloud.api.modules.vod.VodBaseService;

/**
 * @author tangqian
 *
 */
public class ModifyVodInfoService extends VodBaseService<CloudBaseResponse> {
	
	public ModifyVodInfoService(VodModuleCaller caller) {
		super(caller);
	}
	
	@Override
	public String getActionName() {
		return "ModifyVodInfo";
	}
	
	@Override
	protected Class<CloudBaseResponse> getResponseClass() {
		return CloudBaseResponse.class;
	}

	/**
	 * 修改对应视频文件的描述信息，包括分类、名称、描述等
	 * @param fileId 文件id
	 * @param className 分类信息
	 * @param fileName 文件名称
	 * @param fileIntro 文件描述
	 * @param classId 分类id
	 * @return
	 */
	public CloudBaseResponse call(String fileId, String className, String fileName, String fileIntro, Integer classId) {
		TreeMap<String, Object> params = new TreeMap<String, Object>();
		params.put("fileId", fileId);
		/*if(className != null)
			params.put("className", className);*/
		if(fileName != null)
			params.put("fileName", fileName);
		if(fileIntro != null)
			params.put("fileIntro", fileIntro);
		if(classId != null)
			params.put("classId", classId);
		CloudBaseResponse result = callVodAction(params, "GET");
		return result;
	}
}
