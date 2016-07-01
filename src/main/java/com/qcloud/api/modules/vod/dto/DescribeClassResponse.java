package com.qcloud.api.modules.vod.dto;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.qcloud.api.common.CloudBaseResponse;

/**
 * @author tangqian
 *
 */
public class DescribeClassResponse extends CloudBaseResponse {

	private ClassInfo[] data;

	public ClassInfo[] getData() {
		return data;
	}

	public void setData(ClassInfo[] data) {
		this.data = data;
	}

	public static class ClassInfo {
		private int id;
		private String name;
		private Date createTime;
		private Date updateTime;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Date getCreateTime() {
			return createTime;
		}

		@JSONField(name = "create_time")
		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}

		public Date getUpdateTime() {
			return updateTime;
		}

		@JSONField(name = "update_time")
		public void setUpdateTime(Date updateTime) {
			this.updateTime = updateTime;
		}

	}
}
