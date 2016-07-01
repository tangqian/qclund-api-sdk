package com.qcloud.api.modules.vod.service;

import java.util.TreeMap;

import com.qcloud.api.callers.VodModuleCaller;
import com.qcloud.api.modules.vod.VodBaseService;
import com.qcloud.api.modules.vod.dto.DescribeVodPlayUrlsResponse;

/**
 * @author tangqian
 *
 */
public class DescribeVodPlayUrlsService extends VodBaseService<DescribeVodPlayUrlsResponse> {
	
	public DescribeVodPlayUrlsService(VodModuleCaller caller) {
		super(caller);
	}
	
	@Override
	public String getActionName() {
		return "DescribeVodPlayUrls";
	}
	
	@Override
	protected Class<DescribeVodPlayUrlsResponse> getResponseClass() {
		return DescribeVodPlayUrlsResponse.class;
	}

	/**
	 * 获取当前视频所有播放地址、格式、码率、高度、宽度信息
	 * @param fileId
	 * @return
	 */
	public DescribeVodPlayUrlsResponse call(String fileId) {
		TreeMap<String, Object> params = new TreeMap<String, Object>();
		params.put("fileId", fileId);
		
		DescribeVodPlayUrlsResponse result = callVodAction(params, "GET");
		return result;
	}
}
