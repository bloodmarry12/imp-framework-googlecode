package com.huawei.imp.framework.jee.webserver.config;

/**
 * 应用服务器配置地址
 * @author ahli
 *
 */
public interface ApplicationServerXML
{
	
	static final String BLANK = "";
	
	/**
	 * 表示windows操作系统
	 */
	static final String WINDOWSOS = "Windows";
	
	/**
	 * 从应用服务器的XML配置文件中得到应用的上下文根
	 * @return
	 */
	String getContextRoot();
	
	/**
	 * 从应用服务器的XML配置文件中得到应用的上下文根所使用的端口号
	 * @return
	 */
	String getPort();

}
