/**
 * 
 */
package com.huawei.imp.framework.logger;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.MessageFormat;

import org.apache.log4j.Logger;

import com.huawei.imp.framework.jee.webserver.Environment;
import com.huawei.imp.framework.utils.RandNumGenerator;

/**
 * <一句话功能/>
 * <功能描述/>
 * @author ahli
 * @date 2009-8-29
 */
public class LoggerUtil implements LogConstants {

	private static final Logger log = Logger.getLogger(LoggerUtil.class);
	
	/**
	 * 线程变量
	 */
	private static final ThreadLocal<Long> tl = new ThreadLocal<Long>();
	
	private static Environment environment = null;
	
	public static void setEnvironment(Environment _environment)
	{
		environment = _environment;
	}
	
	/**
	 * 创建并且返回一个线程日志号
	 * @return
	 */
	public static long createThreadLocalLog(){
		tl.set(RandNumGenerator.nextNumber());
		return tl.get();
	}
	
	/**
	 * 获取当前线程日志ID，19位Long值。
	 * 如果createAble == true 或者 线程变量没有记录logID，那么系统会重新生成一个logID，并且保存到
	 * 线程变量中。否则系统会沿用当前线程ID。仅仅在程序入口，例如http请求入口filter中、定时任务启动
	 * 的时候重新设置logID，其他时候，都应该沿用既有的logid。
	 * @param createAble    是否要创建新的日志ID
	 */
	public static long getThreadLocalLog(boolean createAble){
		if(createAble || null == tl.get() ){
			tl.set(RandNumGenerator.nextNumber());
		}
		return tl.get();
	}
	
	/**
	 * @return
	 */
	public static long getThreadLocalLog(){
		return getThreadLocalLog(false);
	}
	
	/**
	 * @return
	 */
	public static long getThreadLocalLogReadOnly(){
		return tl.get();
	}
	
	/**
	 * 格式化日志信息
	 * @param moduleName
	 * @param resourceID
	 * @param appInfo
	 * @param isEncrypt
	 * @return
	 */
	public static String formInfo(String msg)
	{
		final String str = MessageFormat.format(LOG_INFO_FORMAT, 
				String.valueOf(getThreadLocalLog()),
				getLocalIP(),
				msg
		);
		return str;
	}
	
	public static String getLocalIP()
	{
		String ip[] = null;
		try
		{
			if (null != environment)
			{
				return environment.getLocalWebURL();
			}
			else
			{
				InetAddress ia = InetAddress.getLocalHost();
				String inetAddr = ia.toString();
				ip = inetAddr.split("/");
				return ip[1];
			}
		}
		catch(UnknownHostException e)
		{
			log.error("excute method getLogIP throw UnknownHostException", e);
			return null;
		}
	}
}
