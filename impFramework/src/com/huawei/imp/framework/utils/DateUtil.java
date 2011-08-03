package com.huawei.imp.framework.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;

/**
 * 日期工具类
 * 提供了date类型与string类型的相互转换
 * @author ahli
 * @date 2009-8-5
 */
public class DateUtil
{
	/**
	 * 默认的格式:yyyy-MM-dd
	 */
	public static final String PATTERN_DEFAULT = "yyyy-MM-dd";
	
	/**
	 * 转换日期格式
	 * @param dateStr    日期字符串
	 * @param src        日期源格式
	 * @param dest       日期转后后的目标格式
	 * @return
	 *      转换格式后的日期字符串
	 * @throws ParseException 如果格式转换失败，则抛出异常
	 */
	public static String changeDateStrFormate(String dateStr, String src,
			String dest) throws ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat(src);
		Date date = sdf.parse(dateStr);
		sdf = new SimpleDateFormat(dest);
		return sdf.format(date);
	}


	/**
	 * 获取指定格式的当前时间字符串
	 * @param pattern
	 * @return
	 */
	public static String getCurDateStr(String pattern)
	{
		String dateStr = null;
		try
		{
			if (StringUtils.isNotBlank(pattern))
			{
				dateStr = DateFormatUtils.format(new Date(), pattern);
			}
			else
			{
				dateStr = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return dateStr;
	}
	
	/**
	 * 将字符串转换为Date类型
	 * @param dateStr    日期字符串
	 * @param pattern    格式
	 * @return     Date对象
	 * @throws ParseException    当进行格式化的时候失败，抛出该异常。
	 */
	public static Date getDateByString(String dateStr, String pattern) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.parse(dateStr);
	}
	
	/**
	 * 将Date类型转换为String类型
	 * @param date       日期
	 * @param pattern    格式
	 * @return    日期对应格式的字符串
	 * @throws ParseException
	 * 当格式转换出现异常，抛出
	 */
	public static String getStringByDate(Date date, String pattern) throws ParseException{
		if (StringUtils.isBlank(pattern))
		{
			return getDefaultDateFormat().format(date);
		}
		else
		{
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.format(date);
		}
	}
	
	/**
	 * 获取默认格式对象
	 * @return    简单的时间格式化对象
	 */
	private static SimpleDateFormat getDefaultDateFormat(){
		return new SimpleDateFormat(PATTERN_DEFAULT);
	}
}
