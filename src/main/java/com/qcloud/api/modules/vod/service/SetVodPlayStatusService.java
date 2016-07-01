package com.qcloud.api.modules.vod.service;

import java.util.TreeMap;

import com.qcloud.api.callers.VodModuleCaller;
import com.qcloud.api.common.CloudBaseResponse;
import com.qcloud.api.modules.vod.VodBaseService;

/**
 * @author tangqian
 *
 */
public class SetVodPlayStatusService extends VodBaseService<CloudBaseResponse> {
	
	public SetVodPlayStatusService(VodModuleCaller caller) {
		super(caller);
	}
	
	@Override
	public String getActionName() {
		return "SetVodPlayStatus";
	}
	
	@Override
	protected Class<CloudBaseResponse> getResponseClass() {
		return CloudBaseResponse.class;
	}

	/**
	 * 视频暂停或者恢复播放
	 * @param fileId
	 * @param playStatus 视频播放状态，0为暂停，1为恢复
	 * @return
	 */
	public CloudBaseResponse call(String fileId, Integer playStatus) {
		TreeMap<String, Object> params = new TreeMap<String, Object>();
		params.put("fileId", fileId);
		params.put("playStatus", playStatus);
		CloudBaseResponse result = callVodAction(params, "GET");
		return result;
	}
}
