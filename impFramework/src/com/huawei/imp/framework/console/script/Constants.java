/*
 * 文件名：    Constants.java
 * 版权:      Copyright 2000-2008 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 创建人:    申卿
 * 文件描述:  
 * 修改时间:   Jun 3, 2010 6:35:03 PM
 * 修改内容：  新增
 */
package com.huawei.imp.framework.console.script;
/**
 * <br>类描述： </br>
 * @author  申卿
 * @version Jun 3, 2010 6:35:03 PM
 * @see
 * @since
 */
public interface Constants
{
	/**
	 * 上一次执行时间
	 */
	static String LAST_EXECUTE_TIME = "lastExecuteTime";
	/**
	 * 上一次执行类型
	 */
	static String LAST_EXECUTE_TYPE = "lastExecuteType";
	/**
	 * 上一次执行使用的token
	 */
	static String LAST_EXECUTE_TOKEN = "lastExecuteToken";
	
	/**
	 * 异常描述：对象不支持{0}方法
	 */
	static String EXCEPTION_NOT_SUPPORT_METHOD = "ahli.framework.exception.nosupportsetmethod";
}
