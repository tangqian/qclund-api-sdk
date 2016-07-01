package com.qcloud.module;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.TreeMap;

import com.qcloud.common.Request;

public abstract class Base {
	protected String serverHost = "";
	protected String serverUri = "/v2/index.php";
	protected String secretId = "";
	protected String secretKey = "";
	protected String defaultRegion = "";
	protected String requestMethod = "GET";

	public void setConfig(Map<String, String> config) {
		if (config == null)
			return;
		for (String key : config.keySet()) {
			if (key.equals("SecretId")) {
				setSecretId(config.get(key));
			} else if (key.equals("SecretKey")) {
				setSecretKey(config.get(key));
			} else if (key.equals("DefaultRegion")) {
				setDefaultRegion(config.get(key));
			} else if (key.equals("RequestMethod")) {
				setRequestMethod(config.get(key));
			}
		}
	}

	public void setSecretId(String secretId) {
		this.secretId = secretId;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public void setDefaultRegion(String region) {
		this.defaultRegion = region;
	}

	public void setRequestMethod(String method) {
		this.requestMethod = method;
	}

	public String getLastRequest() {
		return Request.getRequestUrl();
	}

	public String getLastResponse() {
		return Request.getRawResponse();
	}

	private String ucFirst(String word) {
		return word.replaceFirst(word.substring(0, 1), word.substring(0, 1).toUpperCase());
	}

	public String generateUrl(String actionName, TreeMap<String, Object> params) throws UnsupportedEncodingException {
		if (params == null)
			params = new TreeMap<String, Object>();
		params.put("Action", ucFirst(actionName));
		if (!params.containsKey("Region")) {
			params.put("Region", defaultRegion);
		}
		return Request.generateUrl(params, secretId, secretKey, requestMethod, serverHost, serverUri);
	}

	public String call(String actionName, TreeMap<String, Object> params) {
		return call(actionName, params, null);
	}

	public String call(String actionName, TreeMap<String, Object> params, File file) {
		if (params == null)
			params = new TreeMap<String, Object>();
		params.put("Action", ucFirst(actionName));
		if (!params.containsKey("Region")) {
			params.put("Region", defaultRegion);
		}

		String response = Request.send(params, secretId, secretKey, requestMethod, serverHost, serverUri, file);
		return response;
	}
}
