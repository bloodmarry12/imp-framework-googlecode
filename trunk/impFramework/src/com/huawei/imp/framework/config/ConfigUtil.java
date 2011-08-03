package com.huawei.imp.framework.config;

import com.huawei.imp.framework.config.domain.SysConfig;
import com.huawei.imp.framework.config.service.SysConfigService;
import com.huawei.imp.framework.utils.BeanHolder;

/**
 * Description:
 * 系统配置工具（数据库配置信息）
 * @author ahli
 * Apr 21, 2009
 * 
 */
public class ConfigUtil {

	private static SysConfigService service;
	
	/**
	 * 私有系统配置工具构造函数
	 */
	private ConfigUtil (){}
	
	/**
	 * 获取系统配置项
	 * @param key    配置关键字
	 * @return       配置值
	 */
	public static String getSysConfigValue(String key){
		if(service == null){
			service = (SysConfigService)BeanHolder.getBean("sysConfigService");
		}
		if(service == null){
			return null;
		}
		SysConfig config = service.loadSysConfig(key);
		return config==null?null:config.getValue();
	}
	
	/**
	 * 获取系统配置项
	 * @param key    配置关键字
	 * @return       配置值
	 */
	public static String getSysConfigValue(String key, String def){
		if(service == null){
			service = (SysConfigService)BeanHolder.getBean("sysConfigService");
		}
		if(service == null){
			return def;
		}
		SysConfig config = service.loadSysConfig(key);
		if(null == config){
			return def;
		}
		if(null == config.getValue() || "".equals(config.getValue().trim()) || "null".equals(config.getValue())){
			return def;
		}else{
			return config.getValue();
		}
	}
	
	/**
	 * 获取系统配置项
	 * @param key
	 * @param def
	 * @return
	 */
	public static int getSysConfigIntegerValue(String key, int def){
		final String value = getSysConfigValue(key);
		return value == null? def : Integer.parseInt(value);
	}
	
	/**
	 * 获取系统配置项
	 * @param key
	 * @return
	 */
	public static int getSysConfigIntegerValue(String key){
		final String value = getSysConfigValue(key);
		return Integer.parseInt(value);
	}
	
	/**
	 * 获取系统配置项
	 * @param key
	 * @param def
	 * @return
	 */
	public static long getSysConfigLongValue(String key, long def){
		final String value = getSysConfigValue(key);
		return value == null? def : Long.parseLong(value);
	}
	
	/**
	 * 获取系统配置项
	 * @param key
	 * @return
	 */
	public static long getSysConfigLongValue(String key){
		final String value = getSysConfigValue(key);
		return Long.parseLong(value);
	}
}