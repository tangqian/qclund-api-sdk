package com.qcloud.api.common;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tangqian
 *
 */
public enum CodeEnum {

	SUCCESS(0, "成功"), 
	C_2300(-2300, "api sdk throw exception"),
	C_2700(-2700, "api sdk throw exception"),
	C_3000(-3000, "api sdk throw exception"),
	C_3001(-3001, "api sdk throw exception"),
	C_3003(-3003, "api sdk throw exception"),
	C4000(4000, "请求参数非法"), 
	C4100(4100, "鉴权失败"), 
	C4200(4200, "请求过期"), 
	C4300(4300, "拒绝访问"), 
	C4400(4400, "超过配额"), 
	C4500(4500, "重放攻击"), 
	C4600(4600, "协议不支持"),
	C5000(5000, "资源不存在"),
	C5100(5100, "资源操作失败"),
	C5200(5200, "资源购买失败"),
	C5300(5300, "资源购买失败"),
	C5400(5400, "部分执行成功"),
	C5500(5500, "用户资质审核未通过"),
	C6000(6000, "服务器内部错误"),
	C6100(6100, "版本暂不支持"),
	C6200(6200, "接口暂时无法访问");

	private int code;

	private String meaning;

	private static final Map<Integer, CodeEnum> CODES = new HashMap<Integer, CodeEnum>();

	static {
		for (CodeEnum statusEnum : CodeEnum.values()) {
			CODES.put(statusEnum.getCode(), statusEnum);
		}
	}

	/**
	 * <默认构造函数>
	 */
	CodeEnum(int code, String meaning) {
		this.code = code;
		this.meaning = meaning;
	}

	/**
	 * @return 返回 code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @return 返回 meaning
	 */
	public String getMeaning() {
		return meaning;
	}

	public static CodeEnum getEnum(Integer code) {
		return CODES.get(code);
	}
}
