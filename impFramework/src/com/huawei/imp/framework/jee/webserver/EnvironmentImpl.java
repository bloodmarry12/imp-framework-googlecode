/*
 * 
 */
package com.huawei.imp.framework.jee.webserver;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.PostConstruct;

import com.huawei.imp.framework.jee.webserver.config.ApplicationServerXML;
import com.huawei.imp.framework.logger.LogFactory;
import com.huawei.imp.framework.logger.Logger;
import com.huawei.imp.framework.logger.LoggerUtil;

/**
 * 环境实现类
 * 提供环境获取方法实现
 * @author ahli
 * @version IMPV100R001DA0, 2010-3-20
 * @see com.huawei.imp.framework.jee.webserver.Environment
 * @since CMS IMPV100R001DA0
 */
public class EnvironmentImpl implements Environment{

	private ApplicationServerXML applicationServerXML;
	
	/**
	 * @param applicationServerXML the applicationServerXML to set
	 */
	public void setApplicationServerXML(ApplicationServerXML applicationServerXML)
	{
		this.applicationServerXML = applicationServerXML;
	}

	/**
	 * 日志对象
	 */
	private static final Logger log = LogFactory.getLogger(EnvironmentImpl.class);
	
	/**
	 * 当前使用端口
	 */
	private String localPort = null;
	
	/**
	 * 上下文
	 */
	private String localContext = null;
	
	/**
	 * 本地服务路径
	 */
	private String localWebURL = null;
	
	/**
	 * 本地IP
	 */
	private String localIP = null;
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init(){
		localPort = applicationServerXML.getPort();
		localContext = applicationServerXML.getContextRoot();
		localWebURL = PREURL + getLocalIP() + SEPARATOR + getLocalPort() + "/" + getLocalContext();
		
		LoggerUtil.setEnvironment(this);
		
		if(log.isDebugEnabled()){
			java.util.Properties p = System.getProperties();
			Set keys = p.keySet();
			for (Iterator iter = new TreeSet(keys).iterator(); iter.hasNext();)
			{
				String key = (String) iter.next();
				String val = (String) p.get(key);
				log.debug(key + "=" + val);
			}
		}
	}
	
	public String getLocalIP()
	{
		if(null == localIP){
			String ip[] = null;
			try
			{
				InetAddress ia = InetAddress.getLocalHost();
				String inetAddr = ia.toString();
				ip = inetAddr.split("/");
			}
			catch(UnknownHostException e)
			{
				log.error("excute method getLogIP throw UnknownHostException", e);
			}
			localIP = (ip!=null && ip.length > 0)? ip[1]:null;
		}
		return localIP;
	}
	
	public String getLocalPort(){
		return localPort;
	}
	
	public String getLocalContext(){
		return localContext;
	}
	
	public String getLocalWebURL(){
		return localWebURL;
	}
}
