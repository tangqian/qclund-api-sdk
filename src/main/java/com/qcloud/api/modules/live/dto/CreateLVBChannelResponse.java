package com.qcloud.api.modules.live.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.qcloud.api.common.CloudBaseResponse;

/**
 * @author tangqian
 *
 */
public class CreateLVBChannelResponse extends CloudBaseResponse {

	/**
	 * 新生成的分类id，最上层分类的id为-1
	 */
	private String channelId;

	private CreateChannelInfo channelInfo;

	public String getChannelId() {
		return channelId;
	}

	@JSONField(name = "channel_id")
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public CreateChannelInfo getChannelInfo() {
		return channelInfo;
	}

	public void setChannelInfo(CreateChannelInfo channelInfo) {
		this.channelInfo = channelInfo;
	}

	public static class DownstreamAddress {
		private int rateType;
		private String rtmpAddress;
		private String flvAddress;
		private String hlsAddress;

		public int getRateType() {
			return rateType;
		}

		@JSONField(name = "rate_type")
		public void setRateType(int rateType) {
			this.rateType = rateType;
		}

		public String getRtmpAddress() {
			return rtmpAddress;
		}

		@JSONField(name = "rtmp_downstream_address")
		public void setRtmpAddress(String rtmpAddress) {
			this.rtmpAddress = rtmpAddress;
		}

		public String getFlvAddress() {
			return flvAddress;
		}

		@JSONField(name = "flv_downstream_address")
		public void setFlvAddress(String flvAddress) {
			this.flvAddress = flvAddress;
		}

		public String getHlsAddress() {
			return hlsAddress;
		}

		@JSONField(name = "hls_downstream_address")
		public void setHlsAddress(String hlsAddress) {
			this.hlsAddress = hlsAddress;
		}
	}

	public static class CreateChannelInfo {
		private Integer protocol;
		private String upstreamAddress;
		private DownstreamAddress[] downstreamAddress;

		public Integer getProtocol() {
			return protocol;
		}

		public void setProtocol(Integer protocol) {
			this.protocol = protocol;
		}

		public String getUpstreamAddress() {
			return upstreamAddress;
		}

		@JSONField(name = "upstream_address")
		public void setUpstreamAddress(String upstreamAddress) {
			this.upstreamAddress = upstreamAddress;
		}

		public DownstreamAddress[] getDownstreamAddress() {
			return downstreamAddress;
		}

		@JSONField(name = "downstream_address")
		public void setDownstreamAddress(DownstreamAddress[] downstreamAddress) {
			this.downstreamAddress = downstreamAddress;
		}
	}

}
