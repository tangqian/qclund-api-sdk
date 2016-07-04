package com.qcloud.api.modules.vod.dto;

import com.qcloud.api.common.CloudBaseResponse;

/**
 * @author tangqian
 *
 */
public class MultipartUploadVodFileResponse extends CloudBaseResponse {

	/**
	 * 是否最后一片，0：上传未完成，1：整个文件上传完成
	 */
	private int flag;

	/**
	 * 下一片数据应该从什么偏移继续上传，flag为0时才有值
	 */
	private String offset;

	/**
	 * 视频文件的ID，flag为1时才有值
	 */
	private String fileId;
	
	public MultipartUploadVodFileResponse(){
	}
	
	public MultipartUploadVodFileResponse(int code){
		setCode(code);
	}
	
	public static MultipartUploadVodFileResponse newFailInsatance(){
		return new MultipartUploadVodFileResponse(-1);
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getOffset() {
		return offset;
	}

	public void setOffset(String offset) {
		this.offset = offset;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

}
