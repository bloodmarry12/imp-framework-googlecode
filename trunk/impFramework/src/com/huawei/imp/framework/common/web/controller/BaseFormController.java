package com.huawei.imp.framework.common.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Description:
 * 基础控制类
 * @author ahli
 * Apr 14, 2009
 * 
 */
public abstract class BaseFormController<T> implements BaseControllerSupport{
	
	/**
	 * 处理get请求，初始化页面
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String doGetMethod(Model model, HttpServletRequest request){
		return setForm(model, request);
	}
	
	
	/**
	 * 处理post请求
	 * @param form
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String doPostMethod(@ModelAttribute(FORM) T form, Model model, HttpServletRequest request){
		return submit(form, model, request);
	}
	
	/**
	 * @param model
	 * @param request
	 * @return
	 */
	abstract public String setForm(Model model, HttpServletRequest request);
	
	/**
	 * @param form
	 * @param model
	 * @param request
	 * @return
	 */
	abstract public String submit(T form, Model model, HttpServletRequest request);
}
