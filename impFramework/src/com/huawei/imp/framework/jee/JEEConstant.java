package com.huawei.imp.framework.jee;

/**
 * 常量
 * 定义J2EE常量
 * @author ahli
 * @date 2009-8-6
 */
public interface JEEConstant{

	/**
	 * sesssion记录登录信息的token
	 */
	static String SESSION_LOGIN_TOKEN = "session.security.user";
	
	/**
	 * cookie记录登录信息的token
	 */
	static String COOKIE_LOGIN_TOKEN = "cookie.security.user";
	
	/**
	 * sesssion记录后台登录信息的token
	 */
	static String SESSION_FRAMEWORK_LOGIN_TOKEN = "session.framework.security.user";

	/**
	 * 登录随机数
	 */
	public static final String SESSION_RANDNUMBER  = "session.randNumber";
	
	/**
	 * 属性：表单("form")	 
	 */
	static String FORM = "form";
	
	/**
	 * 属性：查询表单("queryForm")	 
	 */
	static String QUERYFORM = "queryForm";
	
	/**
	 * 属性：权限树结构("cms_sys_right_tree")	 
	 */
	static String MODEL_RIGHT_TREE = "cms_sys_right_tree";
	
	/**
	 * MODEL中的导航条数
	 */
	static String MODEL_EXPLORER_TREE = "cms_sys_explorer";
	
	/**
	 * Model标识：分页对象("queryResult")	 
	 */
	static String MODEL_PAGERESULT = "pageResult";
	
	/**
	 * 框架台页面路径
	 */
	static String PAGE_CONSOLE = "framework/jsp/";
	
	/**
	 * 框架台页面路径
	 */
	static String PAGE_CONSOLE_SUCCESS = PAGE_CONSOLE + "success";
	
	static String PAGE_CONSOLE_FAIL = PAGE_CONSOLE + "fail";
	
	/**
	 * 框架台页json处理页面
	 */
	static String PAGE_CONSOLE_JSON = PAGE_CONSOLE + "json";
	
	/**
	 * 控制台Model组建路径
	 */
	static String PAGE_CONSOLE_MODEL = PAGE_CONSOLE + "model/";
	
	/**
	 * Description: 操作类型
	 * 
	 * @author ahli Apr 22, 2009
	 */
	public static enum ControllerType
	{
		AJAX_JSON, // 通过json类型进行的异步
		PAGE
		// 普通页面处理
	};
	
	/**
	 * HttpServletRequest 范围保存调用类型的属性名称
	 */
	static String REQ_ATTR_CONTROLLER_TYPE = "cms.sys.req.attr.type";
}
