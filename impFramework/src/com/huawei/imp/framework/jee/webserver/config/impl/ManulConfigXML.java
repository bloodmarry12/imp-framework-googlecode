package com.huawei.imp.framework.jee.webserver.config.impl;

import com.huawei.imp.framework.jee.webserver.config.ApplicationServerXML;

/**
 * 手动配置文件
 * 得到web应用上下文根以及端口等 url路径在配置文件中注入
 * @author cll
 *
 */
public class ManulConfigXML implements ApplicationServerXML
{

	private String contextRoot;
	
	private String port;
	
	/**
	 * @param contextRoot the contextRoot to set
	 */
	public void setContextRoot(String contextRoot)
	{
		this.contextRoot = contextRoot;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(String port)
	{
		this.port = port;
	}

	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.jee.webserver.config.ApplicationServerXML#getContextRoot()
	 */
	@Override
	public String getContextRoot()
	{
		return contextRoot;
	}

	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.jee.webserver.config.ApplicationServerXML#getPort()
	 */
	@Override
	public String getPort()
	{
		return port;
	}

}
