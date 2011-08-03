package com.huawei.imp.framework.common.web.controller;

import com.huawei.imp.framework.jee.JEEConstant;

/**
 * Description:
 * 基础控制类接口，所有控制类应该实现该接口。该接口定义了常量。
 * @author ahli
 * Apr 14, 2009
 * 
 */
public interface BaseControllerSupport extends JEEConstant{
	
	/**
	 * 默认的空结果页面，没有引入任何页面和资源
	 */
	public static final String PAGE_BLANK = "blank";
	
	/**
	 * 查询表单参数
	 */
	public static final String ATTRIBUTE_QUERYFORM = "queryForm";
	
	/**
	 * 分页对象传递参数
	 */
	public static final String ATTRIBUTE_PAGERESULT = "pageResult";
	
	/**
	 * 默认表单参数
	 */
	public static final String ATTRIBUTE_FORM = "form";
}
