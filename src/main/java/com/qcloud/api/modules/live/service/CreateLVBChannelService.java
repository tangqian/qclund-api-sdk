package com.qcloud.api.modules.live.service;

import java.util.TreeMap;

import com.qcloud.api.callers.LiveModuleCaller;
import com.qcloud.api.modules.live.LiveBaseService;
import com.qcloud.api.modules.live.dto.CreateLVBChannelResponse;

/**
 * @author tangqian
 *
 */
public class CreateLVBChannelService extends LiveBaseService<CreateLVBChannelResponse> {
	
	/**
	 * 选择输出源类型(1只有RTMP/flv输出2：只有HLS输出3：有RTMP/FLV HLS输出)
	 */
	private static final int OUTPUT_SOURCE_TYPE = 3;
	
	public CreateLVBChannelService(LiveModuleCaller caller) {
		super(caller);
	}
	
	@Override
	public String getActionName() {
		return "CreateLVBChannel";
	}
	
	@Override
	protected Class<CreateLVBChannelResponse> getResponseClass() {
		return CreateLVBChannelResponse.class;
	}

	/**
	 * 输入频道相关信息，创建直播频道。
	 * @param channelName 直播频道的名称
	 * @param srcName 直播源名称
	 * @param srcType 直播流协议 1 RTMP推流  2 RTMP拉流 3 HLS拉流
	 * @return
	 */
	public CreateLVBChannelResponse call(String channelName, String srcName, int srcType) {
		TreeMap<String, Object> params = new TreeMap<String, Object>();
		params.put("channelName", channelName);
		params.put("outputSourceType", OUTPUT_SOURCE_TYPE);
		//原画：0 标清：10 高清：20
		params.put("outputRate.1", "0");
		params.put("outputRate.2", "10");
		params.put("sourceList.1.name", srcName);
		params.put("sourceList.1.type", srcType);
		
		CreateLVBChannelResponse result = callAction(params, "GET");
		return result;
	}
}
