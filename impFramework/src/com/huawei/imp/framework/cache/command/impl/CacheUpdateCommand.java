/*
 * FileName: CacheUpdateCommand.java
 * Author:   ahli
 * createDate: 2009-6-7
 * Description: 
 * 
 */
package com.huawei.imp.framework.cache.command.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;

import com.huawei.imp.framework.cache.command.CacheCommand;
import com.huawei.imp.framework.cache.container.CacheContainer;
import com.huawei.imp.framework.cache.exception.CacheException;

/**
 * 缓存更新命令
 * @author ahli
 * @version IMPV100R001DA0, Aug 10, 2009 
 * @see com.huawei.cms.utils.commcache.command.CacheCommand
 * @since CMS IMPV100R001DA0
 */
public class CacheUpdateCommand<T> implements CacheCommand {

	/**
	 * 日志对象
	 */
	private static final Logger log = org.apache.log4j.Logger.getLogger(CacheUpdateCommand.class);
	
	/**
	 * 更新后的值
	 */
	private Object newVal = null;
	/**
	 * 更新前的值
	 */
	private Object oldVal = null;
	
	/**
	 * get方法
	 */
	private Method getMethod = null;
	
	/**
	 * set方法
	 */
	private Method setMethod = null;
	
	/**
	 * bean对象
	 */
	private T obj = null;
	
	/**
	 * get方法名开头
	 */
	public static final String METHOD_NAME_GET = "get";
	
	/**
	 * set方法名开头
	 */
	public static final String METHOD_NAME_SET = "set";
	
	/**
	 * 缓存对象更新操作
	 * @param clazz           更新的缓存对象类型
	 * @param id              更新的缓存对象ID
	 * @param propertyName    属性名称
	 * @param newVal          更新后的值
	 */
	public CacheUpdateCommand(Class<T> clazz, Serializable id, String propertyName, Object newVal){
		this.newVal = newVal;
		
		obj = CacheContainer.load(clazz, id);
		
		// 对象属性值获取方法名
		final String getMethodName = METHOD_NAME_GET
				+ Character.toUpperCase(propertyName.charAt(0))
				+ propertyName.substring(1);
		
		// 对象属性值设置方法名
		final String setMethodName = METHOD_NAME_SET
				+ Character.toUpperCase(propertyName.charAt(0))
				+ propertyName.substring(1);
		
		Method[] methods  = clazz.getMethods();
		for(Method method : methods){
			if(method.getName().equals(getMethodName)){
				getMethod = method;
			}
			if(method.getName().equals(setMethodName)){
				setMethod = method;
			}
			
			if(null != getMethod && null != setMethod){
				break;
			}
		}
		
		
	}
	
	/* (non-Javadoc)
	 * @see ahli.ahcacheModel.command.CacheCommand#doCommand()
	 */
	@Override
	public void doCommand() {
		
		//记录修改前的数值
		try
		{
			oldVal = getMethod.invoke(obj, new Object[]{});
			setMethod.invoke(obj, new Object[]{newVal});
		}
		catch (IllegalArgumentException e)
		{
			if (log.isDebugEnabled())
			{
				e.printStackTrace();
			}
			throw new CacheException(e);
		}
		catch (IllegalAccessException e)
		{
			if (log.isDebugEnabled())
			{
				e.printStackTrace();
			}
			throw new CacheException(e);
		}
		catch (InvocationTargetException e)
		{
			if (log.isDebugEnabled())
			{
				e.printStackTrace();
			}
			throw new CacheException(e);
		}
	}

	/* (non-Javadoc)
	 * @see ahli.ahcacheModel.command.CacheCommand#undoCommand()
	 */
	@Override
	public void undoCommand() {
		try
		{
			setMethod.invoke(obj, new Object[]{oldVal});
			
		}
		catch (IllegalArgumentException e)
		{
			if (log.isDebugEnabled())
			{
				e.printStackTrace();
			}
			throw new CacheException(e);
		}
		catch (IllegalAccessException e)
		{
			if (log.isDebugEnabled())
			{
				e.printStackTrace();
			}
			throw new CacheException(e);
		}
		catch (InvocationTargetException e)
		{
			if (log.isDebugEnabled())
			{
				e.printStackTrace();
			}
			throw new CacheException(e);
		}
	}

}
