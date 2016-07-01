package com.qcloud.api.modules.vod.dto;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * @author tangqian
 *
 */
public class VodPlayUrlInfo {
	/**
	 * 播放地址
	 */
	private String url;
	/**
	 * 格式， 0: ["", "原始"], 1: ["带水印", "原始"], 10: ["手机", "mp4"], 20: ["标清", "mp4"], 30: ["高清", "mp4"], 
	 * 110: ["手机", "flv"], 120: ["标清", "flv"], 130: ["高清", "flv"],
	 * 210: ["手机", "hls"], 220: ["标清", "hls"], 230: ["高清", "hls"],240: ["超高清", "hls"]
	 */
	private int definition;
	/**
	 * 码率，单位：kbps
	 */
	private int vbitrate;
	/**
	 * 高度，单位：px
	 */
	private int vheight;
	/**
	 * 宽度，单位：px
	 */
	private int vwidth;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getDefinition() {
		return definition;
	}

	public void setDefinition(int definition) {
		this.definition = definition;
	}

	public int getVbitrate() {
		return vbitrate;
	}

	public void setVbitrate(int vbitrate) {
		this.vbitrate = vbitrate;
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

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
