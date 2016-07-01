package com.qcloud.api.modules.vod.service;

import java.util.TreeMap;

import com.qcloud.api.callers.VodModuleCaller;
import com.qcloud.api.modules.vod.VodBaseService;
import com.qcloud.api.modules.vod.dto.CreateScreenShotResponse;

/**
 * @author tangqian
 *
 */
public class CreateScreenShotService extends VodBaseService<CreateScreenShotResponse> {
	
	public CreateScreenShotService(VodModuleCaller caller) {
		super(caller);
	}
	
	@Override
	public String getActionName() {
		return "CreateScreenShot";
	}
	
	@Override
	protected Class<CreateScreenShotResponse> getResponseClass() {
		return CreateScreenShotResponse.class;
	}

	/**
	 * 针对具体文件，获取其不同尺寸下多张截图URL地址。文件按照id顺序排列，分别对应时间轴0%位置、10%位置，20%位置，至90%位置的截图。
	 * @param fileId
	 * @return
	 */
	public CreateScreenShotResponse call(String fileId) {
		TreeMap<String, Object> params = new TreeMap<String, Object>();
		params.put("pullset.0.fileId", fileId);
		
		CreateScreenShotResponse result = callVodAction(params, "GET");
		return result;
	}
}
