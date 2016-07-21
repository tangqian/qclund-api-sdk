package com.qcloud.api.modules.live.service;

import java.util.List;
import java.util.TreeMap;

import com.qcloud.api.callers.LiveModuleCaller;
import com.qcloud.api.common.CloudBaseResponse;
import com.qcloud.api.modules.live.LiveBaseService;

/**
 * @author tangqian
 *
 */
public class StopLVBChannelService extends LiveBaseService<CloudBaseResponse> {
	
	public StopLVBChannelService(LiveModuleCaller caller) {
		super(caller);
	}
	
	@Override
	public String getActionName() {
		return "StopLVBChannel";
	}
	
	@Override
	protected Class<CloudBaseResponse> getResponseClass() {
		return CloudBaseResponse.class;
	}

	/**
	 * 输入待停止直播频道的ID号（支持批量），停止这些直播频道。
	 * @param channelId
	 * @return
	 */
	public CloudBaseResponse call(List<String> channelIds) {
		TreeMap<String, Object> params = new TreeMap<String, Object>();
		int i = 1;
		for (String channelId : channelIds) {
			params.put("channelIds." + i, channelId);
			i++;
		}
		
		CloudBaseResponse result = callAction(params, "GET");
		return result;
	}
}
