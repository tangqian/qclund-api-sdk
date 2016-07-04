package com.qcloud.api.modules.vod.service;

import java.io.File;
import java.util.TreeMap;

import com.qcloud.api.callers.VodModuleCaller;
import com.qcloud.api.modules.vod.VodBaseService;
import com.qcloud.api.modules.vod.dto.MultipartUploadVodFileResponse;
import com.qcloud.utils.SHA1;

/**
 * @author tangqian
 *
 */
public class MultipartUploadVodFileService extends VodBaseService<MultipartUploadVodFileResponse> {

	private static final int FIX_DATA_SIZE = 5 * 1024 * 1024; // 每次上传字节数，可自定义，512KB至5MB之间
	private static final int FIRST_DATA_SIZE = 10 * 1024; // 切片上传：最小片字节数（10K）

	public MultipartUploadVodFileService(VodModuleCaller caller) {
		super(caller);
	}

	@Override
	public String getActionName() {
		return "MultipartUploadVodFile";
	}

	@Override
	protected Class<MultipartUploadVodFileResponse> getResponseClass() {
		return MultipartUploadVodFileResponse.class;
	}

	/**
	 * 视频上传到腾讯云
	 * 
	 * @param srcFilePath
	 *            本地服务器上文件绝对路径
	 * @param fileName
	 *            服务器上显示的文件名,为null时，自动从srcFilePath参数中获取文件名部分
	 * @param classId
	 *            分类ID，可以null
	 * @param option
	 *            可以null,默认转码
	 * @return
	 */
	public MultipartUploadVodFileResponse call(String srcFilePath, String fileName, Integer classId, MultipartUploadVodFileOption option) {
		MultipartUploadVodFileResponse result = null;
		File file = new File(srcFilePath);
		if (!file.isFile()) {
			result = MultipartUploadVodFileResponse.newFailInstance();
			result.setMessage("源文件不存在");
			return result;
		}

		String fileType = getFileType(srcFilePath);
		if (fileType == null) {
			result = MultipartUploadVodFileResponse.newFailInstance();
			result.setMessage("源文件类型未知，请检查后文件后缀名");
			return result;
		}
		
		if(fileName == null){
			fileName = file.getName();
		}
		System.out.println(fileName);
		try {
			long fileSize = file.length();
			String fileSHA1 = SHA1.fileNameToSHA(srcFilePath);

			int tmpDataSize = (int) Math.min(FIRST_DATA_SIZE, fileSize);
			long remainderSize = fileSize;
			int tmpOffset = 0;

			TreeMap<String, Object> params = new TreeMap<String, Object>();
			while (remainderSize > 0) {
				params.clear();
				params.put("fileSize", fileSize);
				params.put("fileSha", fileSHA1);
				params.put("fileType", fileType);
				params.put("fileName", fileName);
				if (classId != null)
					params.put("classId", classId);
				setOption(params, option);
				
				params.put("file", srcFilePath);
				params.put("dataSize", tmpDataSize);
				params.put("offset", tmpOffset);

				System.out.println("--do while upload--");
				result = callVodAction(params, "POST");
				
				if (result.getCode() == -3002) { // 服务器异常返回，需要重试上传(offset=0, dataSize=10K,满足大多数视频的上传)
					tmpDataSize = (int) Math.min(FIRST_DATA_SIZE, fileSize);
					tmpOffset = 0;
					continue;
				} else if (result.getCode() != 0) {
					break;
				}

				if (result.getFlag() == 1) {
					break;
				} else {
					tmpOffset = Integer.parseInt(result.getOffset());
					remainderSize = fileSize - tmpOffset;
					if (FIX_DATA_SIZE < remainderSize) {
						tmpDataSize = FIX_DATA_SIZE;
					} else {
						tmpDataSize = (int) remainderSize;
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			logger.error("Upload file Fail，srcFilePath={}", srcFilePath);
			logger.error("Exception happens,", e);
		}
		return result;
	}

	private void setOption(TreeMap<String, Object> params, MultipartUploadVodFileOption option) {
		params.put("isTranscode", 1);// 默认转码
		params.put("isScreenshot", 1);// 默认转码
		if (option != null) {
			if (option.getIsTranscode() != null)
				params.put("isTranscode", option.getIsTranscode());

			if (option.getIsScreenshot() != null)
				params.put("isScreenshot", option.getIsScreenshot());

			if (option.getIsWatermark() != null)
				params.put("isWatermark", option.getIsWatermark());
		}
	}

	private String getFileType(String filePath) {
		int pos = filePath.lastIndexOf('.');
		return pos == -1 ? null : filePath.substring(pos + 1);
	}

	public static class MultipartUploadVodFileOption {

		/**
		 * 是否转码，0：否，1：是，默认为0；
		 */
		private Integer isTranscode;

		/**
		 * 是否截图，0：否，1：是，默认为0
		 */
		private Integer isScreenshot;

		/**
		 * 是否打水印，0：否，1：是，默认为0；
		 */
		private Integer isWatermark;

		public Integer getIsTranscode() {
			return isTranscode;
		}

		public void setIsTranscode(Integer isTranscode) {
			this.isTranscode = isTranscode;
		}

		public Integer getIsScreenshot() {
			return isScreenshot;
		}

		public void setIsScreenshot(Integer isScreenshot) {
			this.isScreenshot = isScreenshot;
		}

		public Integer getIsWatermark() {
			return isWatermark;
		}

		public void setIsWatermark(Integer isWatermark) {
			this.isWatermark = isWatermark;
		}

	}
}
