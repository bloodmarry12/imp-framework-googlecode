package com.huawei.imp.framework.jee.webserver.config.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.huawei.imp.framework.jee.Path;
import com.huawei.imp.framework.jee.webserver.config.ApplicationServerXML;
import com.huawei.imp.framework.utils.SystemInfo;

/**
 * 解析tomcat下配置文件
 * 得到web应用上下文根以及端口等 url路径在配置文件中注入
 * @author cll
 *
 */
public class TomcatXML implements ApplicationServerXML
{
	
	// 日志对象
	private final static Logger log = Logger.getLogger(TomcatXML.class);
	
	/**
	 * 协议
	 */
	public static final String PROTOCOL = "HTTP/1.1";
	
	public final static String DEFAULT_FILE_URL_TOMCAT = "conf/server.xml";
	
	/**
	 * 文档对象
	 */
	private Document document ;
	
	/**
	 * 初始化标签
	 */
	private boolean initialized = false;
	
	private String url;
	
	public void init(){
		String fullPath = Path.getWebRootPath();
		//判断当前是否是tomcat环境
		if (fullPath.contains("webapps"))
		{
			String osName = SystemInfo.getSystemOS();
			String tomcatDir ="";
			if (osName.contains(WINDOWSOS))
				//windows操作系统从第二个字符截取
			{
				tomcatDir = fullPath.substring(1,fullPath.indexOf("webapps")); //tomcat根目录
			}else{
				tomcatDir = fullPath.substring(0,fullPath.indexOf("webapps")); //tomcat根目录
			}
			
			String realPath = tomcatDir + url;
			realPath = realPath.replace(" ", "%20"); //替换路径中的空格，有空格读取文档会出错
			SAXReader reader = new SAXReader();
			try
			{
				document = reader.read(realPath);
			}
			catch (DocumentException e)
			{
				log.error("read xml file fail ：" + e.toString());
			}
		}
	}
	
    /**
     * 默认构造函数
     * @param url  相对路径
     */
	public TomcatXML(String url)
	{
		if(null == url || "".equals(url.trim())){
			this.url = DEFAULT_FILE_URL_TOMCAT;
		}
		else
		{
			this.url = url;
		}
	}
	
	/**
     * 默认构造函数
     */
	public TomcatXML()
	{
		this(null);
	}

	@Override
	public String getContextRoot()
	{
		synchronized (this)
		{
			if (!this.initialized)
			{
				this.init();
			}
		}
		String fullPath = Path.getWebRootPath();//获取全路径
		String path = fullPath.substring(0, fullPath.length()-1);//去掉最后一个斜杠
		path =path.substring(path.lastIndexOf("/") + 1) ;
		return path;
	}

	/*
	 * (non-Javadoc)
	 * @see com.huawei.imp.cms.config.xml.ApplicationServerXML#getPort()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String getPort()
	{
		synchronized (this)
		{
			if (!this.initialized)
			{
				this.init();
			}
		}
		List<Node> list = document.selectNodes("//Server/Service/Connector");
		for (Node node : list)
		{
			if (PROTOCOL.equals(node.valueOf("@protocol")))
			{
				return node.valueOf("@port");
			}
		}
		return BLANK;
	}

}
