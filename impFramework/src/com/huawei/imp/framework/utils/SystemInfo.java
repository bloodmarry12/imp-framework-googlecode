package com.huawei.imp.framework.utils;

import java.util.Properties;

/**
 * 
 * 获取系统信息等信息
 * @author chenll
 * @date 2009-12-2
 */
public class SystemInfo
{
	/**
	 * 系统属性
	 */
	private static final Properties props = System.getProperties();
	
	public static final String OSNAME = "os.name";
	
	public static final String OSVERSION = "os.version";
	
	public static final String JAVAVERSION = "java.version";
	
	/**
	 * 获取操作系统名称 
	 * @return  操作系统名称
	 */
	public static String getSystemOS()
	{
		return props.getProperty(OSNAME);
	}
	
	/**
	 * 获取操作系统版本
	 * @return
	 */
	public static String getSysteOSVersion()
	{
		return props.getProperty(OSVERSION);
	}
	
	/**
	 * 获取java运行时的环境版本
	 * @return
	 */
	public static String getJavaVersion()
	{
		return props.getProperty(JAVAVERSION);
	}

}
