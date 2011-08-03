/*
 * 文件名：CMSQuartzDef.java
 * 版权：  Copyright 2000-2009 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 描述：  定时任务配置业务类
 * 修改人：李谟毫   63800
 * 修改时间：2009-04-16 10:21:14 
 * 修改内容：新增
 */

package com.huawei.imp.framework.model.schedule.domain;

import java.io.Serializable;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;

import com.huawei.imp.framework.common.domain.Namingspace;
import com.huawei.imp.framework.model.schedule.Constants;
import com.huawei.imp.framework.utils.BeanProperUtil;

/**
 * 定时任务配置业务类
 * 定时任务配置管理业务对象，拥有基于quartz框架的定时任务相关操作行为。比如启动定时任务，
 * 暂停定时任务等。
 * @author 李谟毫   63800
 * @version IMPV100R001DA0, 2009-04-16 10:21:14 
 * @since CMS IMPV100R001DA0
 */
@Namingspace("_framework_schedule")
public class ScheduleDef implements Constants, Serializable
{

	/**
	 * 默认的序列号
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
	 * 返回 任务流水号
	 * @return 任务流水号
	 */
	public long getId()
	{
		return id;
	}

	/**
	 * 设置 任务流水号
	 * @param id 任务流水号
	 */
	public void setId(long id)
	{
		this.id = id;
	}

	/**
	 * 返回 任务名称
	 * @return 任务名称
	 */
	public String getTaskName()
	{
		return taskName;
	}

	/**
	 * 设置   任务名称
	 * @param taskName   任务名称
	 */
	public void setTaskName(String taskName)
	{
		this.taskName = taskName;
	}

	/**
	 * 返回 任务描述信息
	 * @return 任务描述信息
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * 设置 任务描述信息
	 * @param description 任务描述信息
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * 返回 定时任务状态
	 * @return 定时任务状态
	 */
	public int getStatus()
	{
		return status;
	}

	/**
	 * 设置 定时任务状态
	 * @param status  定时任务状态
	 */
	public void setStatus(int status)
	{
		this.status = status;
	}

	/**
	 * 返回  配置定时任务的Cron 表达式
	 * @return   配置定时任务的Cron 表达式
	 */
	public String getCexp()
	{
		return cexp;
	}

	/**
	 * 设置  配置定时任务的Cron 表达式
	 * @param cexp  配置定时任务的Cron 表达式
	 */
	public void setCexp(String cexp)
	{
		this.cexp = cexp;
	}

	/**
	 * 返回 ioc容器中的对象名称
	 * @return ioc容器中的对象名称
	 */
	public String getBeanName()
	{
		return beanName;
	}

	/**
	 * 设置 ioc容器中的对象名称
	 * @param beanName ioc容器中的对象名称
	 */
	public void setBeanName(String beanName)
	{
		this.beanName = beanName;
	}

	/**
	 * 返回 IOC容器中的对象对应的对象方法
	 * @return IOC容器中的对象对应的对象方法
	 */
	public String getMethodName()
	{
		return methodName;
	}

	/**
	 * 设置 IOC容器中的对象对应的对象方法
	 * @param methodName IOC容器中的对象对应的对象方法
	 */
	public void setMethodName(String methodName)
	{
		this.methodName = methodName;
	}

	/**
	 * 返回 并发开关1为支持并发，0为不支持
	 * @return 并发开关1为支持并发，0为不支持
	 */
	public int getConcurrent()
	{
		return concurrent;
	}

	/**
	 * 设置 并发开关1为支持并发，0为不支持
	 * @param concurrent 并发开关1为支持并发，0为不支持
	 */
	public void setConcurrent(int concurrent)
	{
		this.concurrent = concurrent;
	}

	/**
	 * 返回 服务器IP
	 * @return    服务器IP
	 */
	public String getHost()
	{
		return host;
	}

	/**
	 * 设置服务器ID
	 * @param host    服务器IP
	 */
	public void setHost(String host)
	{
		this.host = host;
	}

//	/**
//	 * 初始化
//	 * @param scheduler    调度对象
//	 * @throws SchedulerException    在设置调度任务
//	 *          （org.quartz.Scheduler.scheduleJob(JobDetail
//	 *             arg0,Trigger arg1) ） 的时候，会抛出该异常
//	 */
//	public void init(Scheduler scheduler) throws SchedulerException
//	{
//		this.scheduler = scheduler;
//
//		// 如果状态不是启动，则暂停
//		if (STATUS_RUN == getStatus())
//		{
//			Calendar cal = Calendar.getInstance();
//			cal.setTimeInMillis(System.currentTimeMillis() + JOB_LAZY_TIME);
//			JobDetail jobDetail = createNewJobDetail(getConcurrent());
//			CronTrigger cronTrigger = createCronTrigger(getCexp(), cal
//					.getTime());
//			scheduler.scheduleJob(jobDetail, cronTrigger);
//		}
//	}

//	/**
//	 * 启动该定时任务
//	 */
//	public void start() {
//		// 只有停止状态的任务才能启动
//		if (STATUS_STOP == status) {
//			JobDetail jobDetail = createNewJobDetail(getConcurrent());
//			CronTrigger cronTrigger = createCronTrigger(getCexp());
//			try {
//				scheduler.scheduleJob(jobDetail, cronTrigger);
//			} catch (SchedulerException e) {
//				// 出现异常，则抛出业务层异常信息。
//				throw new FrameworkBusinessException(
//						"common.timetask.exception.startFail",
//						new Object[] { getTaskName() });
//			}
//			setStatus(STATUS_RUN);
//		}
//	}

//	/**
//	 * 创建一个新的同步任务对象
//	 * @param sync    并发：0-非；1-并发
//	 * @return    任务配置对象
//	 */
//	@SuppressWarnings("unchecked")
//	private JobDetail createNewJobDetail(int sync)
//	{
//		Class clazz = null;
//		if (CONCURRENT_SINGLE == sync)
//		{
//			clazz = BaseTimeTaskStatefulJob.class;
//		}
//		else
//		{
//			clazz = BaseTimeTaskJob.class;
//		}
//		JobDetail jobDetail = new JobDetail(getTaskName(),
//				Scheduler.DEFAULT_GROUP, clazz);
//		jobDetail.getJobDataMap().put(JOB_DATA_MAP_BEAN, getBeanName());
//		jobDetail.getJobDataMap().put(JOB_DATA_MAP_METHOD, getMethodName());
//		jobDetail.getJobDataMap().put(JOB_DATA_MAP_PARAMETER, getParameters())  ;
//		return jobDetail;
//	}
//
//	/**
//	 * 克隆表达式触发器
//	 * @param cronExpression    表达式
//	 * @return    触发器
//	 */
//	private CronTrigger createCronTrigger(String cronExpression)
//	{
//		return createCronTrigger(cronExpression, null);
//	}
//
//	/**
//	 * 克隆表达式触发器
//	 * @param cronExpression    表达式
//	 * @param date              第一次运行时间
//	 * @return    触发器
//	 */
//	private CronTrigger createCronTrigger(String cronExpression, Date date)
//	{
//		CronTrigger cronTrigger = new CronTrigger("cronTrigger_"
//				+ getTaskName(), "triggerGroup1");
//		if (null != date)
//		{
//			cronTrigger.setStartTime(date);
//		}
//		try
//		{
//			// 设置克隆表达式
//			cronTrigger.setCronExpression(cronExpression);
//		}
//		catch (ParseException e)
//		{
//			// 出现异常，则抛出业务层异常信息。
//			throw new FrameworkBusinessException("common.timetask.exception.cexpError",
//					new Object[]
//					{
//						cronExpression
//					});
//		}
//		return cronTrigger;
//	}
//
//	/**
//	 * 停止该定时任务
//	 */
//	public void stop()
//	{
//		// 只有当当前状态为运行状态，才能进行操作。
//		if (STATUS_RUN == status)
//		{
//			try
//			{
//				// 暂停Job
//				scheduler.pauseJob(getTaskName(), Scheduler.DEFAULT_GROUP);
//				// 删除Job
//				scheduler.deleteJob(getTaskName(), Scheduler.DEFAULT_GROUP);
//			}
//			catch (SchedulerException e)
//			{
//				throw new FrameworkBusinessException(
//						"common.timetask.exception.cantStop", new Object[]
//						{
//							getTaskName()
//						});
//			}
//			// 设置当前状态为停止状态
//			setStatus(STATUS_STOP);
//		}
//	}
	
	@Override
	public String toString()
	{
		return BeanProperUtil.getBeanDesc(this);
	}

	public String getParameter()
	{
		return parameter;
	}

	public void setParameter(String parameter)
	{
		this.parameter = parameter;
	}
	
	/**
	 * 返回字符串数组
	 * @return
	 */
	public String[] getParameters(){
		String[] params  = new String[0];
		if (StringUtils.isNotEmpty(parameter))
		{
			StringTokenizer st = new StringTokenizer(parameter, "|");
			int count = st.countTokens();
			int icount = parameter.endsWith("|")?count + 1:count;
			params = new String[icount];
			for(int i = 0 ; i < count ; i ++){
				params[i] = st.nextToken();
			}
		}
		return params;
	}
}
