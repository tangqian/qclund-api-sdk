package com.qcloud.api.modules.live.service;

import java.util.TreeMap;

import com.qcloud.api.callers.LiveModuleCaller;
import com.qcloud.api.modules.live.LiveBaseService;
import com.qcloud.api.modules.live.dto.DescribeLVBChannelResponse;

/**
 * @author tangqian
 *
 */
public class DescribeLVBChannelService extends LiveBaseService<DescribeLVBChannelResponse> {
	
	public DescribeLVBChannelService(LiveModuleCaller caller) {
		super(caller);
	}
	
	@Override
	public String getActionName() {
		return "DescribeLVBChannel";
	}
	
	@Override
	protected Class<DescribeLVBChannelResponse> getResponseClass() {
		return DescribeLVBChannelResponse.class;
	}

	/**
	 * 输入待查询频道的ID号，获得该频道的当前状态、名称、描述、直播源信息和输出源信息。
	 * @param channelId
	 * @return
	 */
	public DescribeLVBChannelResponse call(String channelId) {
		TreeMap<String, Object> params = new TreeMap<String, Object>();
		params.put("channelId", channelId);
		
		DescribeLVBChannelResponse result = callAction(params, "GET");
		return result;
	}
}
