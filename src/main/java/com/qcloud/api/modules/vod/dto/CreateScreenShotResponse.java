package com.qcloud.api.modules.vod.dto;

import com.qcloud.api.common.CloudBaseResponse;

/**
 * @author tangqian
 *
 */
public class CreateScreenShotResponse extends CloudBaseResponse {

	private imageList data;

	public imageList getData() {
		return data;
	}

	public void setData(imageList data) {
		this.data = data;
	}

	public static class imageList {
		private ImgMessage[] list;

		public ImgMessage[] getList() {
			return list;
		}

		public void setList(ImgMessage[] list) {
			this.list = list;
		}
	}

	public static class ImgMessage {
		private String message;
		private int code;
		private ImgUrlsInfo[] imgUrls;

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public int getCode() {
			return code;
		}

		public void setCode(int code) {
			this.code = code;
		}

		public ImgUrlsInfo[] getImgUrls() {
			return imgUrls;
		}

		public void setImgUrls(ImgUrlsInfo[] imgUrls) {
			this.imgUrls = imgUrls;
		}
	}

	public static class ImgUrlsInfo {

		/**
		 * 播放地址
		 */
		private String url;
		/**
		 * 高度，单位：px
		 */
		private int vheight;
		/**
		 * 宽度，单位：px
		 */
		private int vwidth;

		private int id;

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public int getVheight() {
			return vheight;
		}

		public void setVheight(int vheight) {
			this.vheight = vheight;
		}

		public int getVwidth() {
			return vwidth;
		}

		public void setVwidth(int vwidth) {
			this.vwidth = vwidth;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

	}

}
