package com.qcloud.api.modules.vod.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.qcloud.api.common.CloudBaseResponse;

/**
 * @author tangqian
 *
 */
public class DescribeAllClassResponse extends CloudBaseResponse {

	private ClassDirInfo[] data;

	public ClassDirInfo[] getData() {
		return data;
	}

	public void setData(ClassDirInfo[] data) {
		this.data = data;
	}

	public static class ClassDirInfo {
		private ClassNodeInfo info;
		private ClassDirInfo[] subclass;

		public ClassDirInfo[] getSubclass() {
			return subclass;
		}

		public void setSubclass(ClassDirInfo[] subclass) {
			this.subclass = subclass;
		}

		public ClassNodeInfo getInfo() {
			return info;
		}

		public void setInfo(ClassNodeInfo info) {
			this.info = info;
		}
	}

	public static class ClassNodeInfo {
		private int id;
		private int parentId;
		private String name;
		private int level;
		private int fileNum;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getParentId() {
			return parentId;
		}

		@JSONField(name = "parent_id")
		public void setParentId(int parent_id) {
			this.parentId = parent_id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getLevel() {
			return level;
		}

		public void setLevel(int level) {
			this.level = level;
		}

		public int getFileNum() {
			return fileNum;
		}

		@JSONField(name = "file_num")
		public void setFileNum(int file_num) {
			this.fileNum = file_num;
		}
	}
}
