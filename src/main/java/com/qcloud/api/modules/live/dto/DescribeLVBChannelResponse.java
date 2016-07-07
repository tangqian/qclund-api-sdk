package com.qcloud.api.modules.live.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.qcloud.api.common.CloudBaseResponse;

/**
 * @author tangqian
 *
 */
public class DescribeLVBChannelResponse extends CloudBaseResponse {
	
	private ChannelAllInfo[] channels;

	public ChannelAllInfo[] getChannels() {
		return channels;
	}
	
	public ChannelAllInfo getChannel() {
		if(channels != null && channels.length > 0){
			return channels[0];
		}
		return null;
	}

	@JSONField(name = "channelInfo")
	public void setChannels(ChannelAllInfo[] channels) {
		this.channels = channels;
	}
	
	public static class UpstreamInfo{
		private String sourceName;
		private String sourceID;
		private Integer sourceType;
		private String sourceAddress;
		
		public String getSourceName() {
			return sourceName;
		}
		
		public void setSourceName(String sourceName) {
			this.sourceName = sourceName;
		}
		
		public String getSourceID() {
			return sourceID;
		}
		
		public void setSourceID(String sourceID) {
			this.sourceID = sourceID;
		}
		
		public Integer getSourceType() {
			return sourceType;
		}
		
		public void setSourceType(Integer sourceType) {
			this.sourceType = sourceType;
		}
		
		public String getSourceAddress() {
			return sourceAddress;
		}
		
		public void setSourceAddress(String sourceAddress) {
			this.sourceAddress = sourceAddress;
		}
	}

	public static class ChannelAllInfo {
		private String channelId;
		private String channelName;
		private String describe;
		private Integer status;
		private String downstreamHls;
		private String downstreamRtmp;
		private String downstreamflv;
		private String playerId;
		private String resolution;
		private String password;
		private UpstreamInfo[] upstreams;

		public String getDescribe() {
			return describe;
		}

		@JSONField(name = "channel_describe")
		public void setDescribe(String describe) {
			this.describe = describe;
		}
		
		public String getDownstreamflv() {
			return downstreamflv;
		}

		@JSONField(name = "flv_downstream_address")
		public void setDownstreamflv(String downstreamflv) {
			this.downstreamflv = downstreamflv;
		}

		public String getDownstreamHls() {
			return downstreamHls;
		}

		@JSONField(name = "hls_downstream_address")
		public void setDownstreamHls(String downstreamHls) {
			this.downstreamHls = downstreamHls;
		}

		public String getDownstreamRtmp() {
			return downstreamRtmp;
		}

		@JSONField(name = "rtmp_downstream_address")
		public void setDownstreamRtmp(String downstreamRtmp) {
			this.downstreamRtmp = downstreamRtmp;
		}

		public String getPlayerId() {
			return playerId;
		}

		@JSONField(name = "player_id")
		public void setPlayerId(String playerId) {
			this.playerId = playerId;
		}

		public String getResolution() {
			return resolution;
		}

		public void setResolution(String resolution) {
			this.resolution = resolution;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
		
		public UpstreamInfo getUpstream() {
			if(upstreams != null && upstreams.length > 0){
				return upstreams[0];
			}
			return null;
		}

		public UpstreamInfo[] getUpstreams() {
			return upstreams;
		}

		@JSONField(name = "upstream_list")
		public void setUpstreams(UpstreamInfo[] upstreams) {
			this.upstreams = upstreams;
		}

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
	}

}
