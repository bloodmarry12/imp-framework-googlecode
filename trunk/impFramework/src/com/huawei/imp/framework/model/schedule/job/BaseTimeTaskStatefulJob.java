/*
 * 文件名：BaseCMSTimeTaskStatefulJob.java
 * 版权：  Copyright 2000-2009 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 描述：  CMS基础定时任务调用类(用于并行定时任务)
 * 修改人：李谟毫   63800
 * 修改时间：2009-04-17 15:41:11
 * 修改内容：新增
 */

package com.huawei.imp.framework.model.schedule.job;

import java.lang.reflect.InvocationTargetException;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;

import com.huawei.imp.framework.logger.LogFactory;
import com.huawei.imp.framework.logger.Logger;
import com.huawei.imp.framework.logger.LoggerUtil;
import com.huawei.imp.framework.model.schedule.Constants;
import com.huawei.imp.framework.utils.BeanHolder;
import com.huawei.imp.framework.utils.MethodUtil;

/**
 * CMS基础定时任务调用类(用于串行定时任务),同时支持事务
 * 通过基于cglib包的方法委派
 * @author 李谟毫   63800
 * @version IMPV100R001DA0, 2009-04-17 15:41:11 
 * @see org.quartz.Job
 * @see org.quartz.StatefulJob
 * @since CMS IMPV100R001DA0
 */
@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public class BaseTimeTaskStatefulJob implements Job, Constants
{
	
	/**
	 * 日志对象
	 */
	private static Logger log = LogFactory.getLogger(BaseTimeTaskStatefulJob.class);
	
	/* (non-Javadoc)
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	@SuppressWarnings({"rawtypes" })
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException
	{
		// 进入http请求，增加一个线程ID
		LoggerUtil.createThreadLocalLog();
		
		final String beanName = (String) arg0.getJobDetail().getJobDataMap()
				.get(JOB_DATA_MAP_BEAN);
		final String methodName = (String) arg0.getJobDetail().getJobDataMap()
				.get(JOB_DATA_MAP_METHOD);
		final String[] parameters = (String[]) arg0.getJobDetail().getJobDataMap()
				.get(JOB_DATA_MAP_PARAMETER);
		Object obj = BeanHolder.getBean(beanName);
		Class objClazz = obj.getClass();
		
		try
		{
			if(log.isInfoEnabled())
			{
				log.info("定时任务:"+arg0.getJobDetail().getKey().getName()+"--start");
			}
			if(log.isDebugEnabled()){
				log.debug("invoke time task[jobName:\"" + arg0.getJobDetail().getKey().getName() + "\", beanName:" + beanName + ", methodName:" + methodName + "]. --start");
			}
			MethodUtil.invokeMethod(obj, objClazz, methodName, parameters);
			if(log.isDebugEnabled()){
				log.debug("invoke time task[jobName:\"" + arg0.getJobDetail().getKey().getName() + "\", beanName:" + beanName + ", methodName:" + methodName + "]. --end");
			}
			if(log.isInfoEnabled())
			{
				log.info("定时任务:"+arg0.getJobDetail().getKey().getName()+"--end");
			}
		}
		catch (NoSuchMethodException e)
		{
			log.error("task[jobName:\"" + arg0.getJobDetail().getKey().getName() + "\", beanName:" + beanName + ", methodName:" + methodName);
			log.error(e.toString());
			if(log.isDebugEnabled()){
				e.printStackTrace();
			}
		}
		catch (InvocationTargetException e)
		{
			log.error("task[jobName:\"" + arg0.getJobDetail().getKey().getName() + "\", beanName:" + beanName + ", methodName:" + methodName);
			log.error(e.toString());
			if(log.isDebugEnabled()){
				e.printStackTrace();
			}
		}
	}
}
