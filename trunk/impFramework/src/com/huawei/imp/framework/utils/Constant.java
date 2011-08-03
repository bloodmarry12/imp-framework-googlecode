package com.huawei.imp.framework.utils;

/**
 * 常量
 * 定义基础常量
 * @author ahli
 * @date 2009-8-6
 */
public interface Constant {
	/**
	 * 换行符
	 */
	static String LINE_SEPARATOR = System.getProperty("line.separator");
	
	/**
	 * 4个空格符
	 */
	static String BLANK = "    ";
	
	/**
	 * 默认的StringBuffer容量
	 */
	static int DEFAULT_SB_CAP = 500;
	
	
	/**
	 * 基本数据类型int
	 */
	static String CLASS_int = "int";
	
	/**
	 * 基本数据类型long
	 */
	static String CLASS_long = "long";
	
	/**
	 * 数组分割符
	 */
	static String ARRAYS_SEPARATOR = ",";

}
