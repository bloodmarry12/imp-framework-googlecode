/*
 * 
 */
package com.huawei.imp.framework.logger.impl;

import org.apache.log4j.Level;

import com.huawei.imp.framework.logger.Logger;
import com.huawei.imp.framework.logger.LoggerUtil;


/**
 * 应用服务日志实现类
 * @author ahli
 */
public class AppLogImpl implements Logger
{

	private org.apache.log4j.Logger logger;

	public AppLogImpl(String clazzName, Level level)
	{
		logger = org.apache.log4j.Logger.getLogger(clazzName);
//		logger.setLevel(level);
	}

	public boolean isDebugEnabled()
	{
		return logger.isDebugEnabled();
	}

	public boolean isErrorEnabled()
	{
		return logger.isEnabledFor(Level.ERROR);
	}
	
	public boolean isWarnEnabled()
	{
		return logger.isEnabledFor(Level.WARN);
	}
	
	public boolean isInfoEnabled()
	{
		return logger.isInfoEnabled();
	}

	public void debug(String message)
	{
		message = LoggerUtil.formInfo(message);
		logger.debug(message);
	}

	public void debug(String message, Throwable e)
	{
		message = LoggerUtil.formInfo(message);
		logger.debug(message, e);
	}

	@Override
	public void error(String message)
	{
		message = LoggerUtil.formInfo(message);
		logger.error(message);
	}

	@Override
	public void error(String message, Throwable e)
	{
		message = LoggerUtil.formInfo(message);
		logger.error(message, e);
	}

	@Override
	public void warn(String message)
	{
		message = LoggerUtil.formInfo(message);
		logger.warn(message);
	}

	@Override
	public void warn(String message, Throwable e)
	{
		message = LoggerUtil.formInfo(message);
		logger.warn(message, e);
	}

	@Override
	public void info(String message)
	{
		message = LoggerUtil.formInfo(message);
		logger.info(message);
	}

	@Override
	public void info(String message, Throwable e)
	{
		message = LoggerUtil.formInfo(message);
		logger.info(message, e);
	}

	public void setLevel(Level level) {
		logger.setLevel(level);
	}
	
}
