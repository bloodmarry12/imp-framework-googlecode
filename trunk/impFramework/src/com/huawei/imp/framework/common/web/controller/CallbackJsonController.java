package com.huawei.imp.framework.common.web.controller;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.ui.Model;

import com.huawei.imp.framework.jee.JEEConstant;


/**
 * Description:
 * 基础异步控制类，所有异步控制类应该继承自该类
 * @author ahli
 * Apr 14, 2009
 * 
 */
public class CallbackJsonController implements BaseControllerSupport, JEEConstant{
	
	/**
	 * 返回码：成功
	 */
	public static final String PARAMETER_NAME = "jsoncallback";
	
	/**
	 * 返回码：成功
	 */
	public static final String PAGE_JSON = "framework/jsp/json";
	
	/**
	 * 框架台页json处理页面
	 */
	public final static String JSON = "form";
	
	/**
	 * 返回JSON对象，子类中处理jsoncallback请求的方法，直接返回该函数
	 * @param model      Model
	 * @param jsonStr    json字符串
	 * @return
	 * 返回框架处理页面
	 */
	protected String returnJsonObject(HttpServletRequest request, Model model, String jsonStr)
	{
		final String methodid = request.getParameter(PARAMETER_NAME);
		if (null != jsonStr && null != methodid && !"".equals(methodid.trim()))
		{
			StringBuilder sb = new StringBuilder();
			sb.append(methodid);
			sb.append("(");
			sb.append(jsonStr.toString());
			sb.append(")");
			model.addAttribute(JSON, sb.toString());
		}
		return PAGE_JSON;
	}
	
	protected String callback(HttpServletRequest request, Model model, String jsonStr){
		return PAGE_JSON;
	}
	
	/**
	 * 返回JSON对象，子类中处理jsoncallback请求的方法，直接返回该函数
	 * @param model         Model
	 * @param jsonObject    json对象
	 * @return
	 * 返回框架处理页面
	 */
	protected String returnJsonObject(HttpServletRequest request, Model model, JSONObject jsonObject)
	{
		return returnJsonObject(request, model, jsonObject.toString());
	}
}
