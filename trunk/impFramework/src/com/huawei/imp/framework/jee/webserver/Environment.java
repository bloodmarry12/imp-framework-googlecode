package com.huawei.imp.framework.jee.webserver;


/**
 * 应用服务环境对象
 * @author ahli
 *
 */
public interface Environment {
	
	/**
	 * HTTP请求地址头
	 */
	static String PREURL = "http://";
	
	/**
	 * 分隔符
	 */
	static String SEPARATOR = ":";
	
	/**
	 * 返回当前应用服务ip地址
	 * @return
	 */
	String getLocalIP();
	
	/**
	 * @return
	 */
	public String getLocalPort();
	
	/**
	 * @return
	 */
	public String getLocalContext();
	
	/**
	 * 返回当前应用服务的全路径地址
	 * @return
	 */
	String getLocalWebURL();
	
}
