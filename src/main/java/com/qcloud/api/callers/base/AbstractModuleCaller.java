package com.qcloud.api.callers.base;

import java.io.File;
import java.lang.reflect.Method;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;

import com.qcloud.api.common.RequestClient;

/**
 * @author tangqian
 *
 */
public abstract class AbstractModuleCaller implements IModuleCaller {

	private static final String SERVER_PATH = "/v2/index.php";
	
	private String defaultRegion = "gz";

	@Override
	public String getDefaultRegion() {
		return defaultRegion;
	}
	
	public void setDefaultRegion(String region){
		defaultRegion = region;
	}

	@Override
	public final String request(String actionName, TreeMap<String, Object> params, String requestMethod) throws Exception {
		if(requestMethod == null || !requestMethod.equals("POST")){
			requestMethod = "GET";
		}
		for (Method method : getClass().getMethods()) {
			if (method.getName().equals(actionName)) {
				try {
					return (String) method.invoke(this, params, requestMethod);
				} catch (Exception e) {
					throw e;
				}
			}
		}
		return doRequest(actionName, params, requestMethod, null);
	}

	protected final String doRequest(String actionName, TreeMap<String, Object> params, String requestMethod, File file) {
		if (params == null)
			params = new TreeMap<String, Object>();
		params.put("Action", StringUtils.capitalize(actionName));
		if (!params.containsKey("Region")) {
			params.put("Region", getDefaultRegion());
		}
		String response = getRequestClient().send(requestMethod, getServerHost(actionName) + SERVER_PATH, params, file);
		return response;
	}

	public abstract RequestClient getRequestClient();

}
