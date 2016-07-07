package com.qcloud.api.modules.live.service;

import java.util.TreeMap;

import com.qcloud.api.callers.LiveModuleCaller;
import com.qcloud.api.modules.live.LiveBaseService;
import com.qcloud.api.modules.live.dto.DescribeLVBChannelListResponse;

/**
 * @author tangqian
 *
 */
public class DescribeLVBChannelListService extends LiveBaseService<DescribeLVBChannelListResponse> {
	
	public DescribeLVBChannelListService(LiveModuleCaller caller) {
		super(caller);
	}
	
	@Override
	public String getActionName() {
		return "DescribeLVBChannelList";
	}
	
	@Override
	protected Class<DescribeLVBChannelListResponse> getResponseClass() {
		return DescribeLVBChannelListResponse.class;
	}

	
	public DescribeLVBChannelListResponse call(Integer channelStatus, Integer pageNo, Integer pageSize) {
		TreeMap<String, Object> params = new TreeMap<String, Object>();
		if(channelStatus != null)
			params.put("channelStatus", channelStatus);
		if(pageNo != null)
			params.put("pageNo", pageNo);
		if(pageSize != null)
			params.put("pageSize", pageSize);
		
		DescribeLVBChannelListResponse result = callAction(params, "GET");
		return result;
	}
}
