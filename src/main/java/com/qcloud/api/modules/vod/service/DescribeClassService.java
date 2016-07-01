package com.qcloud.api.modules.vod.service;

import com.qcloud.api.callers.VodModuleCaller;
import com.qcloud.api.modules.vod.VodBaseService;
import com.qcloud.api.modules.vod.dto.DescribeClassResponse;

/**
 * @author tangqian
 *
 */
public class DescribeClassService extends VodBaseService<DescribeClassResponse> {

	public DescribeClassService(VodModuleCaller caller) {
		super(caller);
	}

	@Override
	public String getActionName() {
		return "DescribeClass";
	}
	
	@Override
	protected Class<DescribeClassResponse> getResponseClass() {
		return DescribeClassResponse.class;
	}

	/**
	 * 获取全局分类列表，包括ID和分类描述的具体关系，和具体文件无关。
	 * @return
	 */
	public DescribeClassResponse call() {
		return callVodAction(null, "GET");
	}
}
