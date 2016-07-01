package com.qcloud.api.common;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;


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
	
	public boolean isSuccess(){
		return code == CodeEnum.SUCCESS.getCode();
	}

	public String getCodeDetail() {
		String detail = "Code#" + code;
		CodeEnum codeEnum = CodeEnum.getEnum(code);
		if (codeEnum != null) {
			detail += ",Info=" + codeEnum.getMeaning();
		}
		return detail;
	}
	
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this) + "\r\n" + getCodeDetail();
    }

}
