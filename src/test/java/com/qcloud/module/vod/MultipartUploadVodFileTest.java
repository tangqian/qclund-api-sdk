package com.qcloud.module.vod;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.alibaba.fastjson.JSONObject;
import com.qcloud.QcloudApiModuleCenter;
import com.qcloud.module.Vod;
import com.qcloud.utils.FastJsonUtils;
import com.qcloud.utils.SHA1;

/**
 * @author tangqian
 *
 */
public class MultipartUploadVodFileTest {
	public static void main(String[] args) {
		Map<String, String> config = new HashMap<String, String>();

		config.put("SecretId", "你的SecretId");
		config.put("SecretKey", "你的SecretKey");
		config.put("RequestMethod", "POST");
		config.put("DefaultRegion", "gz");
		QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Vod(), config);
		try {
			System.out.println("starting...");
			String fileName = "F:\\video\\2016-06\\61149.flv";
			long fileSize = new File(fileName).length();
			String fileSHA1 = SHA1.fileNameToSHA(fileName);

			int fixDataSize = 1024 * 1024 * 5; // 每次上传字节数，可自定义
			int firstDataSize = 1024 * 10; // 切片上传：最小片字节数（默认不变）,如果：dataSize +
											// offset > fileSize,把这个值变小即可
			int tmpDataSize = firstDataSize;
			long remainderSize = fileSize;
			int tmpOffset = 0;
			int code, flag;
			String fileId = null;
			String result = null;

			if (remainderSize <= 0) {
				System.out.println("wrong file path...");
			}
			
			while (remainderSize > 0) {
				TreeMap<String, Object> params = new TreeMap<String, Object>();
				/*
				 * 亲，输入参数的类型，记得参考wiki详细说明
				 */
				params.put("fileSha", fileSHA1);
				params.put("fileType", "flv");
				params.put("fileName", "61149.flv");
				params.put("fileSize", fileSize);
				params.put("dataSize", tmpDataSize);
				params.put("offset", tmpOffset);
				params.put("file", fileName);
				params.put("isTranscode", 0);
				params.put("isScreenshot", 0);
				params.put("isWatermark", 0);

				//module.generateUrl("MultipartUploadVodFile", params);
				result = module.call("MultipartUploadVodFile", params);
				System.out.println(result);
				JSONObject json_result = FastJsonUtils.parseObject(result);
				code = json_result.getIntValue("code");
				if (code == -3002) { // 服务器异常返回，需要重试上传(offset=0,
										// dataSize=10K,满足大多数视频的上传)
					tmpDataSize = firstDataSize;
					tmpOffset = 0;
					continue;
				} else if (code != 0) {
					return;
				}
				
				flag = json_result.getIntValue("flag");
				if (flag == 1) {
					fileId = json_result.getString("fileId");
					break;
				} else {
					tmpOffset = Integer.parseInt(json_result.getString("offset"));
				}
				remainderSize = fileSize - tmpOffset;
				if (fixDataSize < remainderSize) {
					tmpDataSize = fixDataSize;
				} else {
					tmpDataSize = (int) remainderSize;
				}
			}
			System.out.println(fileId);
			System.out.println("end...");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error..." + e.toString());
		}
	}
}
