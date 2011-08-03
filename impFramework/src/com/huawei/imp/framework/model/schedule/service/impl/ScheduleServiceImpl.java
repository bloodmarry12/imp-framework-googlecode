/*
 * 文件名：CMSQuartzServiceImpl.java
 * 版权：  Copyright 2000-2009 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 描述：  定时任务业务实现
 * 修改人：李谟毫   63800
 * 修改时间：2009-04-25 10:18:58
 * 修改内容：新增
 */

package com.huawei.imp.framework.model.schedule.service.impl;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huawei.imp.framework.config.ConfigUtil;
import com.huawei.imp.framework.exception.FrameworkBusinessException;
import com.huawei.imp.framework.jee.webserver.Environment;
import com.huawei.imp.framework.model.schedule.Constants;
import com.huawei.imp.framework.model.schedule.dao.ScheduleDAO;
import com.huawei.imp.framework.model.schedule.domain.ScheduleDef;
import com.huawei.imp.framework.model.schedule.job.BaseTimeTaskJob;
import com.huawei.imp.framework.model.schedule.job.BaseTimeTaskStatefulJob;
import com.huawei.imp.framework.model.schedule.service.ScheduleService;
import com.huawei.imp.framework.reload.ReloadSupport;
import com.huawei.imp.framework.utils.SeriUtils;

/**
 * 定时任务业务实现
 * 实现定时任务接口方法定义，实现具体的定时任务业务逻辑
 * @author 李谟毫   63800
 * @version IMPV100R001DA0, 2009-04-25 10:18:58 
 * @see com.huawei.ScheduleService.common.timeTask.service.CMSQuartzService
 * @since CMS IMPV100R001DA0
 */
@Service
public class ScheduleServiceImpl implements ScheduleService, Constants, ReloadSupport
{
	private static final String JOBDETAIL_TASKNAME_D = "_detail0";

	/**
	 * 日志对象
	 */
	private static final Logger log = Logger.getLogger(ScheduleServiceImpl.class);

	/**
	 * 定时任务配置数据访问对象
	 */
	@Autowired
	private ScheduleDAO scheduleDAO;
	
	@Autowired
	@Qualifier("environment")
	private Environment environment;

	/**
	 * 调度工厂
	 */
	private SchedulerFactory schedulerFactory = new StdSchedulerFactory();

	/**
	 * 调度对象
	 */
	private Scheduler scheduler;

	/**
	 * 缓存列表
	 */
	private List<ScheduleDef> list = new ArrayList<ScheduleDef>();

	/**
	 * 构造函数
	 */
	public ScheduleServiceImpl()
	{
	}

	/**
	 * 构造定时任务
	 * @throws SchedulerException
	 * 调用Quartz的schedulerFactory的getScheduler()函数以及scheduler的start()函数时，会抛出异常
	 */
	@PostConstruct
	@Transactional
	public void initTimeTask() throws SchedulerException
	{
		if(log.isDebugEnabled()){
			log.debug("初始化调度模块。");
		}
		
		scheduler = schedulerFactory.getScheduler();
		list = scheduleDAO.findByHost(environment.getLocalWebURL());
		for (ScheduleDef def : list)
		{
			if(log.isDebugEnabled()){
				log.debug("定时任务:" + def);
			}
			// 创建定时任务
			setSchedulerJob(def);
		}
		// 设置定时任务启动延时，如果没有配置，则使用120秒的默认配置
		scheduler.startDelayed(ConfigUtil.getSysConfigIntegerValue("imp.schedule.delayed", 60));
		//scheduler.start();
	}
	
	/**
	 * 设置Run状态下的定时任务，Stop状态下的定时任务不做任何处理
	 * @param scheduler
	 * @param sd
	 * @throws SchedulerException 
	 */
	private void setSchedulerJob(ScheduleDef def) throws SchedulerException{
		try {
			// 如果当前定时任务设置为执行状态
			if(STATUS_RUN == def.getStatus()){
				// 从Quzrtz中查询是否存在任务信息，如果存在，则先将任务从计划中删除掉。然后添加新的任务
				JobDetail jobDetail = scheduler.getJobDetail(new JobKey(def.getTaskName() + JOBDETAIL_TASKNAME_D, Scheduler_GROUP_NAME));
				if(null != jobDetail){
					scheduler.unscheduleJob(new TriggerKey(def.getTaskName(), Scheduler_GROUP_NAME));
				}
				jobDetail = createJobDetail(def);
				CronTrigger trigger = createCronTrigger(def);
				scheduler.scheduleJob(jobDetail, trigger);
			}else if(STATUS_STOP == def.getStatus()){
				JobDetail jobDetail = scheduler.getJobDetail(new JobKey(def.getTaskName() + JOBDETAIL_TASKNAME_D, Scheduler_GROUP_NAME));
				if(null != jobDetail){
					scheduler.pauseJob(new JobKey(def.getTaskName() + JOBDETAIL_TASKNAME_D, Scheduler_GROUP_NAME));
					scheduler.unscheduleJob(new TriggerKey(def.getTaskName(), Scheduler_GROUP_NAME));
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 创建任务
	 * @param def
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private JobDetail createJobDetail(ScheduleDef def){
		Class clazz = null;
		if (CONCURRENT_SINGLE == def.getConcurrent())
		{
			clazz = BaseTimeTaskStatefulJob.class;
		}
		else
		{
			clazz = BaseTimeTaskJob.class;
		}
		JobDetail jobDetail = newJob(clazz).withIdentity(def.getTaskName() + JOBDETAIL_TASKNAME_D,Scheduler_GROUP_NAME).build();
		jobDetail.getJobDataMap().put(JOB_DATA_MAP_BEAN, def.getBeanName());
		jobDetail.getJobDataMap().put(JOB_DATA_MAP_METHOD, def.getMethodName());
		jobDetail.getJobDataMap().put(JOB_DATA_MAP_PARAMETER, def.getParameters());
		return jobDetail;
	}
	
	/**
	 * 创建触发器
	 * @param def
	 * @return
	 */
	private CronTrigger createCronTrigger(ScheduleDef def){
		
		CronTrigger cronTrigger = null; 
		try
		{
			// 设置克隆表达式
			cronTrigger = newTrigger().withIdentity(def.getTaskName(),Scheduler_GROUP_NAME).withSchedule(cronSchedule(def.getCexp())).build();
		}
		catch (ParseException e)
		{
			// 出现异常，则抛出业务层异常信息。
			throw new FrameworkBusinessException("common.timetask.exception.cexpError",
					new Object[]{def.getCexp()});
		}
		return cronTrigger;
	}
	
	/* (non-Javadoc)
	 * @see com.huawei.cms.common.timeTask.service.CMSQuartzService#findAllTimeJobForm()
	 */
	@Override
	@Transactional
	public List<ScheduleDef> findAll()
	{
		List<ScheduleDef> emp = new ArrayList<ScheduleDef>();
		for (ScheduleDef def : list)
		{
			ScheduleDef form = SeriUtils.byteClone(def);
			emp.add(form);
		}
		return emp;
	}

	/* (non-Javadoc)
	 * @see com.huawei.cms.common.timeTask.service.CMSQuartzService#findTimeJobForm(long)
	 */
	@Override
	@Transactional
	public ScheduleDef find(long id)
	{
		for (ScheduleDef def : list)
		{
			if (def.getId() == id)
			{
				ScheduleDef form = SeriUtils.byteClone(def);
				return form;
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.huawei.cms.common.timeTask.service.CMSQuartzService#operate(long, int)
	 */
	@Override
	@Transactional
	public void operate(long id, int status)
	{
		ScheduleDef def = load(id);
		try
		{
			if (null != def)
			{
				def.setStatus(status);
				setSchedulerJob(def);
				scheduleDAO.updateStatus(id, status);
			}
		}
		catch (SchedulerException e)
		{
			throw new FrameworkBusinessException(e);
		}
		catch (RuntimeException e)
		{
			throw new FrameworkBusinessException(e);
		}
	}

	/**
	 * 获取定时任务对象，如果通过id没有能够找到对象，则返回null
	 * @param id    定时任务唯一标识
	 * @return    定时任务配置对象
	 * 
	 */
	@Transactional
	private ScheduleDef load(long id)
	{
		for (ScheduleDef def : list)
		{
			if (def.getId() == id)
			{
				return def;
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.huawei.cms.common.timeTask.service.CMSQuartzService#update(com.huawei.cms.common.timeTask.web.form.TimeJobForm)
	 */
	@Override
	@Transactional
	public void update(ScheduleDef form)
	{
		ScheduleDef def = load(form.getId());
		if (def != null)
		{
			// 如果定时对象的状态正在运行中，抛出异常
			if (STATUS_RUN == def.getStatus())
			{
				throw new FrameworkBusinessException(EXCEPTION_CANTUPDATERUNNINGTASK,
						new Object[]
						{
							def.getTaskName()
						});
			}

			// 持久化修改内容
			scheduleDAO.update(form.getId(), form.getCexp(), form
					.getBeanName(), form.getMethodName(), form.getConcurrent(),form.getHost(),form.getParameter());
			
			// 更新缓存
			def.setConcurrent(form.getConcurrent());
			def.setBeanName(form.getBeanName());
			def.setMethodName(form.getMethodName());
			def.setCexp(form.getCexp());
			def.setHost(form.getHost());
			def.setParameter(form.getParameter());
		}
	}

	@Transactional
	@Override
	public void reload() {
		List<ScheduleDef> _list = scheduleDAO.findByHost(environment.getLocalWebURL());
		for (ScheduleDef def : _list)
		{
			if(log.isInfoEnabled()){
				log.info("加载定时任务配置-> [TaskName:" + def.getTaskName() + "]");
			}else if(log.isDebugEnabled()){
				log.debug("定时任务:" + def);
			}
			// 创建定时任务
			try {
				setSchedulerJob(def);
			} catch (SchedulerException e) {
				throw new FrameworkBusinessException("设置定时任务失败。", e);
			}
		}
		this.list = _list;
	}
}
