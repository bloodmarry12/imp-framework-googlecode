/*
 * 
 */
package com.huawei.imp.framework.logger;


/**
 * 应用程序日志
 * @author ahli
 * @date 2009-8-12
 */
public interface Logger extends LogEnabled
{
	/**
	 * 打印临时调试信息
	 * @param message
	 */
	public void info(String message);

	/**
	 * 打印临时调试信息
	 * @param message
	 */
	public void info(String message, Throwable e);
	
	/**
	 * 打印临时调试信息
	 * @param message
	 */
	public void debug(String message);

	/**
	 * 打印临时调试信息
	 * @param message
	 */
	public void debug(String message, Throwable e);
	
	/**
	 * 打印错误信息
	 * @param appInfo 消息内容
	 */
	public void error(String message);

	/**
	 * 打印错误信息
	 * @param appInfo 消息内容
	 */
	public void error(String message, Throwable e);
	
	/**
	 * 打印警告信息
	 * @param appInfo 消息内容
	 */
	public void warn(String message);

	/**
	 * 打印警告信息
	 * @param appInfo 消息内容
	 */
	public void warn(String message, Throwable e);
}
