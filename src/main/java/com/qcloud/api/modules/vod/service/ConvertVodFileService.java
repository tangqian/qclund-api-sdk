package com.qcloud.api.modules.vod.service;

import java.util.TreeMap;

import com.qcloud.api.callers.VodModuleCaller;
import com.qcloud.api.common.CloudBaseResponse;
import com.qcloud.api.modules.vod.VodBaseService;

/**
 * @author tangqian
 *
 */
public class ConvertVodFileService extends VodBaseService<CloudBaseResponse> {
	
	public ConvertVodFileService(VodModuleCaller caller) {
		super(caller);
	}
	
	@Override
	public String getActionName() {
		return "ConvertVodFile";
	}
	
	@Override
	protected Class<CloudBaseResponse> getResponseClass() {
		return CloudBaseResponse.class;
	}

	/**
	 * 用于对已经上传的视频进行转码和添加水印
	 * @param fileId
	 * @param isScreenshot
	 * @param isWatermark
	 * @param notifyUrl
	 * @return
	 */
	public CloudBaseResponse call(String fileId, Integer isScreenshot, Integer isWatermark, String notifyUrl) {
		TreeMap<String, Object> params = new TreeMap<String, Object>();
		params.put("fileId", fileId);
		if(isScreenshot != null)
			params.put("isScreenshot", isScreenshot);
		if(isWatermark != null)
			params.put("isWatermark", isWatermark);
		if(notifyUrl != null)
			params.put("notifyUrl", notifyUrl);		
		
		CloudBaseResponse result = callVodAction(params, "GET");
		return result;
	}
}
