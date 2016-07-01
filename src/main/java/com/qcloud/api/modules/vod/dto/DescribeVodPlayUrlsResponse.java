package com.qcloud.api.modules.vod.dto;

import com.qcloud.api.common.CloudBaseResponse;

/**
 * @author tangqian
 *
 */
public class DescribeVodPlayUrlsResponse extends CloudBaseResponse {

	private VodPlayUrlInfo[] playSet;

	public VodPlayUrlInfo[] getPlaySet() {
		return playSet;
	}

	public void setPlaySet(VodPlayUrlInfo[] playSet) {
		this.playSet = playSet;
	}

}
