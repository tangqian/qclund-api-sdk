package com.qcloud.api.callers;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.TreeMap;

import com.qcloud.api.callers.base.AbstractModuleCaller;
import com.qcloud.api.common.RequestClient;
import com.qcloud.utils.SHA1;

/**
 * @author tangqian
 *
 */
public class VodModuleCaller extends AbstractModuleCaller {
	
	private RequestClient client;
	
	public VodModuleCaller(RequestClient client) {
		this.client = client;
	}

	@Override
	public String getServerHost() {
		return "vod.api.qcloud.com";
	}

	@Override
	public RequestClient getRequestClient() {
		return client;
	}
	
	public String MultipartUploadVodFile(TreeMap<String, Object> params, String requestMethod) throws NoSuchAlgorithmException, IOException {
		String actionName = "MultipartUploadVodFile";

        String fileName = params.get("file").toString();
        params.remove("file");
        File f= new File(fileName);  
        
        if (!params.containsKey("fileSize")){
        	params.put("fileSize", f.length());
        }
        if (!params.containsKey("fileSha")){
        	params.put("fileSha", SHA1.fileNameToSHA(fileName));
        }
        
        return doRequest(actionName, params, requestMethod, f);
	}

}
