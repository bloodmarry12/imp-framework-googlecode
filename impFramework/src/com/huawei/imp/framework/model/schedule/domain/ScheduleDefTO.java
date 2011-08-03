/*
 * 文件名：CMSQuartzDefTO.java
 * 版权：  Copyright 2000-2009 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 描述：  定时任务对象远程传输类
 * 修改人：zenggh   63800
 * 修改时间：2010-03-09 10:47:14 
 */

package com.huawei.imp.framework.model.schedule.domain;

import java.io.Serializable;

import org.quartz.Scheduler;

import com.huawei.imp.framework.utils.BeanProperUtil;

/**
 * 定时任务对象远程传输类
 * @author 李谟毫   63800
 * @version IMPV100R001DA0, 2010-03-09 10:47:14 
 * @since CMS IMPV100R001DA0
 */
public class ScheduleDefTO implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 任务流水号
	 */
	private long id;

	/**
	 * 任务名称
	 */
	private String taskName;

	/**
	 * 任务描述信息
	 */
	private String description;

	/**
	 * Cron 表达式
	 */
	private String cexp;

	/**
	 * 状态:
	 */
	private int status;

	/**
	 * 对应ioc容器中的对象名称
	 */
	private String beanName;

	/**
	 * 对象方法
	 */
	private String methodName;

	/**
	 * 并发开关
	 */
	private int concurrent;

	/**
	 * 服务器
	 */
	private String host;
	
	/**
	 * 参数
	 */
	private String parameter;
	
	/**
	 * 调度对象
	 */
	private Scheduler scheduler;
	
	@Override
	public String toString()
	{
		return BeanProperUtil.getBeanDesc(this);
	}


	/**
	 * 获取id
	 * @return the id
	 */
	public long getId()
	{
		return id;
	}


	/**
	 * 设置id
	 * @param id the id to set
	 */
	public void setId(long id)
	{
		this.id = id;
	}


	/**
	 * 获取taskName
	 * @return the taskName
	 */
	public String getTaskName()
	{
		return taskName;
	}


	/**
	 * 设置taskName
	 * @param taskName the taskName to set
	 */
	public void setTaskName(String taskName)
	{
		this.taskName = taskName;
	}


	/**
	 * 获取description
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}


	/**
	 * 设置description
	 * @param description the description to set
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}


	/**
	 * 获取cexp
	 * @return the cexp
	 */
	public String getCexp()
	{
		return cexp;
	}


	/**
	 * 设置cexp
	 * @param cexp the cexp to set
	 */
	public void setCexp(String cexp)
	{
		this.cexp = cexp;
	}


	/**
	 * 获取status
	 * @return the status
	 */
	public int getStatus()
	{
		return status;
	}


	/**
	 * 设置status
	 * @param status the status to set
	 */
	public void setStatus(int status)
	{
		this.status = status;
	}


	/**
	 * 获取beanName
	 * @return the beanName
	 */
	public String getBeanName()
	{
		return beanName;
	}


	/**
	 * 设置beanName
	 * @param beanName the beanName to set
	 */
	public void setBeanName(String beanName)
	{
		this.beanName = beanName;
	}


	/**
	 * 获取methodName
	 * @return the methodName
	 */
	public String getMethodName()
	{
		return methodName;
	}


	/**
	 * 设置methodName
	 * @param methodName the methodName to set
	 */
	public void setMethodName(String methodName)
	{
		this.methodName = methodName;
	}


	/**
	 * 获取concurrent
	 * @return the concurrent
	 */
	public int getConcurrent()
	{
		return concurrent;
	}


	/**
	 * 设置concurrent
	 * @param concurrent the concurrent to set
	 */
	public void setConcurrent(int concurrent)
	{
		this.concurrent = concurrent;
	}


	/**
	 * 获取host
	 * @return the host
	 */
	public String getHost()
	{
		return host;
	}


	/**
	 * 设置host
	 * @param host the host to set
	 */
	public void setHost(String host)
	{
		this.host = host;
	}


	/**
	 * 获取parameter
	 * @return the parameter
	 */
	public String getParameter()
	{
		return parameter;
	}


	/**
	 * 设置parameter
	 * @param parameter the parameter to set
	 */
	public void setParameter(String parameter)
	{
		this.parameter = parameter;
	}


	/**
	 * 获取scheduler
	 * @return the scheduler
	 */
	public Scheduler getScheduler()
	{
		return scheduler;
	}


	/**
	 * 设置scheduler
	 * @param scheduler the scheduler to set
	 */
	public void setScheduler(Scheduler scheduler)
	{
		this.scheduler = scheduler;
	}
	
	
}
