package com.qcloud.api.modules.vod.service;

import java.util.TreeMap;

import com.qcloud.api.callers.VodModuleCaller;
import com.qcloud.api.modules.vod.VodBaseService;
import com.qcloud.api.modules.vod.dto.DescribeVodPlayInfoResponse;

/**
 * @author tangqian
 *
 */
public class DescribeVodPlayInfoService extends VodBaseService<DescribeVodPlayInfoResponse> {
	
	public DescribeVodPlayInfoService(VodModuleCaller caller) {
		super(caller);
	}
	
	@Override
	public String getActionName() {
		return "DescribeVodPlayInfo";
	}
	
	@Override
	protected Class<DescribeVodPlayInfoResponse> getResponseClass() {
		return DescribeVodPlayInfoResponse.class;
	}


	/**
	 * 获取视频信息，可以根据视频文件名获得视频信息列表。
	 * @param fileName 视频名称（前缀匹配）
	 * @param pageNo 页号,可空
	 * @param pageSize 分页大小，可空
	 * @return
	 */
	public DescribeVodPlayInfoResponse call(String fileName, Integer pageNo, Integer pageSize) {
		TreeMap<String, Object> params = new TreeMap<String, Object>();
		params.put("fileName", fileName);
		if (pageNo != null)
			params.put("pageNo", pageNo);
		if (pageSize != null)
			params.put("pageSize", pageSize);
		
		DescribeVodPlayInfoResponse result = callVodAction(params, "GET");
		return result;
	}
}
