package com.qcloud.common.dto;

import com.qcloud.common.CodeEnum;

/**
 * 腾讯云接口基本响应对象
 * 
 * @author tangqian
 *
 */
public class CloudBaseResponse {

	private int code;

	private String message;

	private String location;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCodeDetail() {
		String detail = "Error#" + code;
		CodeEnum codeEnum = CodeEnum.getEnum(code);
		if (codeEnum != null) {
			detail += ",Info=" + codeEnum.getMeaning();
		}
		return detail;
	}

}
