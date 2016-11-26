package com.qcloud.api.modules.vod.dto;

import java.util.Date;

import com.qcloud.api.common.CloudBaseResponse;

/**
 * @author tangqian
 *
 */
public class DescribeVodInfoResponse extends CloudBaseResponse {

	private int totalCount;

	private VodInfo[] fileSet;

	public VodInfo[] getFileSet() {
		return fileSet;
	}

	public void setFileSet(VodInfo[] fileSet) {
		this.fileSet = fileSet;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public static class VodInfo {
		private String fileId;
		private String fileName;
		private long size;
		/**
		 * 视频时长，秒为单位
		 */
		private int duration;
		/**
		 * 视频状态， -1：未上传完成，不存在；0：初始化，暂未使用；1：审核不通过，暂未使用；2：正常；3：暂停；4：转码中；5：发布中；6：删除中；7：转码失败；10：等待转码;100：已删除
		 */
		private int status;
		private String vid;
		private Date createTime;
		private Date updateTime;
		private int classId;
		private String className;
		private String classPath;
		/*
		 * 视频封面图
		 */
		private String imageUrl;
		private String[] tags;
		private String description;
		/**
		 * 是否在API中进行过CDN发布操作；0 - 未发布过；1 - 发布中，2 - 成功，3 - 发布失败，4 - 中止(暂未使用)，5 - 已删除
		 */
		private int cdnStatus;

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

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public String getImageUrl() {
			return imageUrl;
		}

		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}

		public long getSize() {
			return size;
		}

		public void setSize(long size) {
			this.size = size;
		}

		public int getDuration() {
			return duration;
		}

		public void setDuration(int duration) {
			this.duration = duration;
		}

		public String getVid() {
			return vid;
		}

		public void setVid(String vid) {
			this.vid = vid;
		}

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}

		public Date getUpdateTime() {
			return updateTime;
		}

		public void setUpdateTime(Date updateTime) {
			this.updateTime = updateTime;
		}

		public int getClassId() {
			return classId;
		}

		public void setClassId(int classId) {
			this.classId = classId;
		}

		public String getClassName() {
			return className;
		}

		public void setClassName(String className) {
			this.className = className;
		}

		public String[] getTags() {
			return tags;
		}

		public void setTags(String[] tags) {
			this.tags = tags;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public int getCdnStatus() {
			return cdnStatus;
		}

		public void setCdnStatus(int cdnStatus) {
			this.cdnStatus = cdnStatus;
		}

		public String getClassPath() {
			return classPath;
		}

		public void setClassPath(String classPath) {
			this.classPath = classPath;
		}
	}
}
