package com.qcloud.api.callers.base;

import java.util.TreeMap;

/**
 * @author tangqian
 *
 */
public interface IModuleCaller {

	/**
	 * 模块请求入口
	 * 
	 * @param actionName
	 *            接口名
	 * @param params
	 * @param requestMethod
	 *            GET/POST,默认GET
	 * @return
	 * @throws Exception
	 */
	String request(String actionName, TreeMap<String, Object> params, String requestMethod) throws Exception;

	/**
	 * 获取模块请求域名
	 * @return 类似点播服务为vod.api.qcloud.com
	 */
	String getServerHost();

	/**
	 * 获取默认区域参数
	 * bj:北京
	 * gz:广州
	 * sh:上海
	 * hk:香港
	 * ca:北美
	 * @return
	 */
	String getDefaultRegion();

}
