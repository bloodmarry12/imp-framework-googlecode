package com.huawei.imp.framework.common.web.controller;

import net.sf.json.JSONObject;

import com.huawei.imp.framework.jee.JEEConstant;
import com.huawei.imp.framework.utils.BeanHolder;


/**
 * Description:
 * 基础异步控制类，所有异步控制类应该继承自该类
 * @author ahli
 * Apr 14, 2009
 * 
 */
public class BaseAjaxJsonController implements BaseControllerSupport, JEEConstant{
	
	/**
	 * 返回码：成功
	 */
	public static final String RESULT_CODE_DONE = "1";
	/**
	 * 返回码：失败
	 */
	public static final String RESULT_CODE_FAIL = "0";
	
	/**
	 * 处理结果
	 */
	public static final String JSON_RESULT = "result";
	/**
	 * 处理消息
	 */
	public static final String JSON_MESSAGE = "message";
	
	/**
	 * 返消息：成功
	 */
	public static final String RESULT_MESSAGE_DONE = BeanHolder
			.getMessage("common.label.operate.done");

	/**
	 * 返消息：失败
	 */
	public static final String RESULT_MESSAGE_FAIL = BeanHolder
			.getMessage("common.label.operate.fail");
	
	/**
	 * 默认的成功返回值
	 */
	public JSONObject DEFAULT_SUCCESS = new JSONObject();

	/**
	 * 默认构造函数
	 */
	public BaseAjaxJsonController(){
		//初始化对象
		DEFAULT_SUCCESS.put(JSON_RESULT, RESULT_CODE_DONE);
		DEFAULT_SUCCESS.put(JSON_MESSAGE, BeanHolder.getMessage("common.label.operate.done"));
	}
	
	/**
	 * 生成一个对象
	 * @param result
	 * @param message
	 * @return
	 */
	protected JSONObject genJSONObject(String message){
		JSONObject jobj = new JSONObject();
		jobj.put(JSON_RESULT, RESULT_CODE_DONE);
		jobj.put(JSON_MESSAGE, message);
		return jobj;
	}
}
