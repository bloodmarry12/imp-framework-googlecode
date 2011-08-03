package com.huawei.imp.framework.jee.webserver.config.impl;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.huawei.imp.framework.jee.Path;
import com.huawei.imp.framework.jee.webserver.config.ApplicationServerXML;
import com.huawei.imp.framework.utils.SystemInfo;


/**
 * 解析glassfish下配置文件
 * 得到web应用上下文根以及端口等 url路径在配置文件中注入
 * @author cll
 *
 */
public class GlassFishXML implements ApplicationServerXML
{
	
	// 日志对象
	private final static Logger log = Logger.getLogger(GlassFishXML.class);
	
	/**
	 * web模块路径
	 */
	public static final String  WEBMODULE = "//domain/applications/web-module";
	
	/**
	 * server-ref模块路径
	 */
	public static final String  SERVERREF = "//domain/servers/server/application-ref";
	
	/**
	 * 虚拟服务器路径
	 */
	public static final String VIRSERVER="//domain/configs/config/http-service/virtual-server";
	
	/**
	 * 监听器路径
	 */
	public static final String LISTENER = "//domain/configs/config/http-service/http-listener";
	
	public static final String DEFAULT_FILE_URL = "config/domain.xml";
	
	/**
	 * 初始化标签
	 */
	private boolean initialized = false;
	
	private String url;
	
	/**
	 * 文档对象
	 */
	private Document document ;
	
	
	public void init(){
		String fullPath = Path.getWebRootPath();
		
		if(log.isDebugEnabled())
		{
			log.debug("fullPath:"+fullPath);
		}
		
		//当前路径是glassfish才执行
		if(fullPath.contains("domains"))
		{
			
			String osName = SystemInfo.getSystemOS();
			String glassFishDir ="";
			if (osName.contains(WINDOWSOS))
			//如果是windows操作系统
			{
				 glassFishDir = fullPath.substring(1,fullPath.indexOf("domain1")+8); //glassfish的domain1目录
			}else
			{
				 glassFishDir = fullPath.substring(0,fullPath.indexOf("domain1")+8); //glassfish的domain1目录
			}
			
			String realPath = glassFishDir + url;
			realPath = realPath.replace(" ", "%20"); //替换路径中的空格，有空格读取文档会出错
			if(log.isDebugEnabled())
			{
				log.debug("realPath:"+realPath);
			}
			
			
			SAXReader reader = new SAXReader();
			
			// add 2010-03-15  用于解决加载web服务器环境使用applicationServerXML_glassfish注入无效的问题
			reader.setValidation(false);
			reader.setEntityResolver(new EntityResolver() {
			    public InputSource resolveEntity(
			      java.lang.String publicId,
			      java.lang.String systemId) throws SAXException, java.io.IOException 
				    {
				     if (systemId.equals("http://www.sun.com/software/appserver/dtds/sun-domain_1_3.dtd"))
				      return new InputSource(new ByteArrayInputStream(
				        "<?xml version='1.0' encoding='UTF-8'?>".getBytes()));
				     else
				      return null;
				    }
			   });
			
			try
			{
				document = reader.read(realPath);
				log.debug(document.toString());
			}
			catch (DocumentException e)
			{
				e.printStackTrace();
				log.error("获取XML文档对象失败:"+e.toString());
			}
		}
	}
	
	public GlassFishXML(String url)
	{
		if (null == url || "".equals(url.trim()))
		{
			this.url = DEFAULT_FILE_URL;
		}
		else
		{
			this.url = url;
		}
	}
	
	public GlassFishXML(){
		this(null);
	}

	/**
	 * 根据路径得到节点集合
	 * @param xPath  （//domain/applications/web-module）
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<Node> getByXpath(String xPath)
	{
		return document.selectNodes(xPath);
	}
	
	
	/**
	 * 获取上下文根
	 * @return
	 * @throws DocumentException
	 */
	public String getContextRoot() 
	{
		synchronized (this)
		{
			if (!this.initialized)
			{
				this.init();
			}
		}
		return findContextRootNode().valueOf("@context-root");
	}
	
	/**
	 * 获取上下文根节点
	 * @return
	 */
	private  Node findContextRootNode() 
	{
		
		List<Node> list = getByXpath(WEBMODULE);
		for (Node node : list)
		{
			if ("user".equals(node.valueOf("@object-type")))
			{
				return node;
			}
		}
		return null;
	}
	
	/**
	 * 根据上下文根节点查找到所使用的虚拟服务名
	 * @param contextRootNode
	 * @return
	 * @throws DocumentException
	 */
	private String findServer(Node contextRootNode)
	{
		String appName = contextRootNode.valueOf("@name");
		List<Node> list = getByXpath(SERVERREF);
		for (Node node : list)
		{
			if (appName.equals(node.valueOf("@ref")))
			{
				return node.valueOf("@virtual-servers");
			}
		}
		return BLANK;
	}
	
	/**
	 * 根据虚拟服务名查找所使用的监听器
	 * 如果使用多个监听器，默认取第一个监听器
	 * @param serverName    
	 * @return
	 * @throws DocumentException
	 */
	private String findListener(String serverName)
	{
		List<Node> list = getByXpath(VIRSERVER);
		for (Node node : list)
		{
			if (serverName.equals(node.valueOf("@id")))
			{
				return node.valueOf("@http-listeners").split(",")[0];
			}
		}
		return BLANK;
	}
	
	/**
	 * 获取上下文使用的端口号端口号
	 * @param listenerName
	 * @return
	 * @throws DocumentException
	 */
	public String getPort() 
	{
		synchronized (this)
		{
			if (!this.initialized)
			{
				this.init();
			}
		}
		String listenerName = findListener(findServer(findContextRootNode()));
		
		List<Node> list = getByXpath(LISTENER);
		for (Node node : list)
		{
			if (listenerName.equals(node.valueOf("@id")))
			{
				return node.valueOf("@port");
			}
		}
		return BLANK;
	}

}
