/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */

package com.huawei.imp.framework.utils;

import java.util.Locale;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Spring IOC容器工具类
 * 用于提供加载对象、国际化等接口
 * @author ahli
 * @version IMPV100R001DB0, 2010-6-28
 * @see org.springframework.context.ApplicationContextAware
 * @since CMS IMPV100R001DB0
 */
public class BeanHolder implements ApplicationContextAware
{
	/**
	 * Spring 应用上下文
	 */
	private static ApplicationContext context = null;
	
	/**
	 * 构造函数
	 */
	public BeanHolder(){
	
	}

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException
	{
		setApplicationContextStatic(arg0);
	}

	/**
	 * 私有设置方法，避免findBug的错误。
	 * @param arg0
	 */
	private static void setApplicationContextStatic(ApplicationContext arg0){
		context = arg0;
	}
	
	@SuppressWarnings("unchecked")
	public static <E> E getBean(String name)
	{
		if(null == context){
			return null;
		}else{
			return (E)context.getBean(name);
		}
	}

	/**
	 * 返回该类型的所有实现类
	 * @param <E>      泛型
	 * @param clazz    类型
	 * @return    返回IOC容器中所有该类型实例对象
	 */
	/*
	 * edit by ahli,20100825 新增
	 */
	public static <E> Map<String, E> getBeans(Class<E> clazz){
		if(null == context){
			return null;
		}else{
			return context.getBeansOfType(clazz);
		}
	}
	
	public static String getMessage(String msg, Object[] args, Locale locale)
	{
		if(null == context){
			return msg;
		}else{
			return context.getMessage(msg, args, msg, locale);
		}
	}

	public static String getMessage(String msg, Object[] args)
	{
		if(null == context){
			return msg;
		}else{
			return context.getMessage(msg, args, msg, Locale.getDefault());
		}
	}

	public static String getMessage(String msg)
	{
		if(null == context){
			return msg;
		}else{
			return context.getMessage(msg, null, msg, Locale.getDefault());
		}
	}

	public static String getMessage(String msg, Locale locale)
	{
		if(null == context){
			return msg;
		}else{
			return context.getMessage(msg, null, msg, locale);
		}
	}
}
