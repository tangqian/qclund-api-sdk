package com.qcloud;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

import com.qcloud.module.Base;

/**
 * @brief 模块调用类
 * @author robinslsun
 *
 */
public class QcloudApiModuleCenter {

	private Base module;
	
	/**
	 * 构造模块调用类
	 * @param module 实际模块实例
	 * @param config 模块配置参数
	 */
	public QcloudApiModuleCenter(Base module, Map<String, String> config){
		this.module = module;
		this.module.setConfig(config);
	}
	
	/**
	 * 生成Api调用地址
	 * @param actionName 模块动作名称
	 * @param params 模块请求参数
	 * @return Api调用地址
	 * @throws UnsupportedEncodingException 
	 */
	public String generateUrl(String actionName, TreeMap<String, Object> params) throws UnsupportedEncodingException{
		return module.generateUrl(actionName, params);
	}
	
	/**
	 * Api调用
	 * @param actionName 模块动作名称
	 * @param params 模块请求参数
	 * @return json字符串
	 * @throws Exception
	 */
	public String call(String actionName, TreeMap<String, Object> params) throws Exception
	{
		for(Method method : module.getClass().getMethods()){
			if(method.getName().equals(actionName)){
				try {
					return (String) method.invoke(module, params);
				} catch (Exception e) {
					throw e;
				} 
			}
		}
		return module.call(actionName, params);
	}
}
