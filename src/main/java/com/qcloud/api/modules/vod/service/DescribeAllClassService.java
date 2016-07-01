package com.qcloud.api.modules.vod.service;

import com.qcloud.api.callers.VodModuleCaller;
import com.qcloud.api.modules.vod.VodBaseService;
import com.qcloud.api.modules.vod.dto.DescribeAllClassResponse;

/**
 * @author tangqian
 *
 */
public class DescribeAllClassService extends VodBaseService<DescribeAllClassResponse> {

	public DescribeAllClassService(VodModuleCaller caller) {
		super(caller);
	}

	@Override
	public String getActionName() {
		return "DescribeAllClass";
	}
	
	@Override
	protected Class<DescribeAllClassResponse> getResponseClass() {
		return DescribeAllClassResponse.class;
	}

	/**
	 * 获得当前用户所有的分类层级关系
	 * @return
	 */
	public DescribeAllClassResponse call() {
		return callVodAction(null, "GET");
	}
}
