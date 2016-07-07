package com.qcloud.api.modules.live.dto;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.qcloud.api.common.CloudBaseResponse;

/**
 * @author tangqian
 *
 */
public class DescribeLVBChannelListResponse extends CloudBaseResponse {

	private Integer count;

	private ChannelInfo[] channelSet;

	public Integer getCount() {
		return count;
	}

	@JSONField(name = "all_count")
	public void setCount(Integer count) {
		this.count = count;
	}

	public ChannelInfo[] getChannelSet() {
		return channelSet;
	}

	public void setChannelSet(ChannelInfo[] channelSet) {
		this.channelSet = channelSet;
	}

	public static class ChannelInfo {
		private String channelId;
		private String channelName;
		private Integer status;
		private Date createTime;

		public String getChannelId() {
			return channelId;
		}

		@JSONField(name = "channel_id")
		public void setChannelId(String channelId) {
			this.channelId = channelId;
		}

		public String getChannelName() {
			return channelName;
		}

		@JSONField(name = "channel_name")
		public void setChannelName(String channelName) {
			this.channelName = channelName;
		}

		public Integer getStatus() {
			return status;
		}

		@JSONField(name = "channel_status")
		public void setStatus(Integer status) {
			this.status = status;
		}

		public Date getCreateTime() {
			return createTime;
		}

		@JSONField(name = "create_time")
		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
	}

}
