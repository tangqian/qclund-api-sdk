package com.qcloud.api.modules.vod.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.qcloud.api.common.CloudBaseResponse;

/**
 * @author tangqian
 *
 */
public class DescribeVodPlayInfoResponse extends CloudBaseResponse {

	private int totalCount;

	private VodPlayInfo[] fileSet;

	public VodPlayInfo[] getFileSet() {
		return fileSet;
	}

	public void setFileSet(VodPlayInfo[] fileSet) {
		this.fileSet = fileSet;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public static class VodPlayInfo {
		private String fileId;
		private String fileName;
		/**
		 * 视频时长，秒为单位
		 */
		private String duration;
		/**
		 * 视频状态， -1：未上传完成，不存在；0：初始化，暂未使用；1：审核不通过，暂未使用；2：正常；3：暂停；4：转码中；5：发布中；6：删除中；7：转码失败；100：已删除
		 */
		private String status;
		/**
		 * 视频封面图
		 */
		private String imageUrl;
		private VodPlayUrlInfo[] playSet;

		public String getFileId() {
			return fileId;
		}

		public void setFileId(String fileId) {
			this.fileId = fileId;
		}

		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		public String getDuration() {
			return duration;
		}

		public void setDuration(String duration) {
			this.duration = duration;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getImageUrl() {
			return imageUrl;
		}

		@JSONField(name = "image_url")
		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}

		public VodPlayUrlInfo[] getPlaySet() {
			return playSet;
		}

		public void setPlaySet(VodPlayUrlInfo[] playSet) {
			this.playSet = playSet;
		}

	}
}
