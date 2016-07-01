package com.qcloud.api.modules.vod.dto;

import com.qcloud.api.common.CloudBaseResponse;



/**
 * @author tangqian
 *
 */
public class CreateClassResponse extends CloudBaseResponse {

	/**
	 * 新生成的分类id，最上层分类的id为-1
	 */
	private String newClassId;

	public String getNewClassId() {
		return newClassId;
	}

	public void setNewClassId(String newClassId) {
		this.newClassId = newClassId;
	}
}
