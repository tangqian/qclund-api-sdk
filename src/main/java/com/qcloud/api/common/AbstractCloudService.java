package com.qcloud.api.common;

import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qcloud.api.callers.base.IModuleCaller;
import com.qcloud.utils.FastJsonUtils;


public abstract class AbstractCloudService<T extends CloudBaseResponse> implements ICloudService<T> {
	
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public final T callAction(IModuleCaller caller, TreeMap<String, Object> params, String requestMethod) {
		T result = null;
		try {
			String response = caller.request(getActionName(), params, requestMethod);
			System.out.println(response);
			result = FastJsonUtils.parseObject(response, getResponseClass());
			System.out.println(result);
			if(!result.isSuccess()){
				logger.error("Call Action Error#", result);
			}
		} catch (Exception e) {
			logger.error("Call Action exception", e);
		}
		return result;
	}
	
	protected abstract Class<T> getResponseClass();

}