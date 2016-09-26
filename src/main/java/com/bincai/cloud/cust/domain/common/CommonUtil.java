package com.bincai.cloud.cust.domain.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class CommonUtil {
	
	/**
	 * 将对象转成json格式的字符串并添加操作是否成功的信息
	 * @param obj 要转换成json格式字符串的对象
	 * @param flag 操作是否成功的标志
	 * @param mesg 操作产生的信息
	 * @return 转换后的字符串
	 */
	public static String setResult(Object obj, boolean flag, String mesg){
		JSONObject result = JSON.parseObject(JSON.toJSONString(obj));
		result.put("result", flag);
		result.put("mesg", mesg);
		return result.toJSONString();
	}
}
