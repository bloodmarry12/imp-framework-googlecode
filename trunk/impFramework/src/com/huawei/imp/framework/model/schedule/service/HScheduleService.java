package com.huawei.imp.framework.model.schedule.service;

import java.util.List;

import com.huawei.imp.framework.model.schedule.domain.ScheduleDefTO;

/**
 * 定时任务的Hessian业务务接口
 * @author zenggh
 * @version :IMPV100R001DA0,Mar 8, 2010
 * @see 
 * @since CMS IMPV100R001DA0
 */
public interface HScheduleService
{
	/**
	 * 查询制定定时任务对象
	 * @param    定时任务唯一标识ID
	 * @return    时间任务表单对象
	 */
	ScheduleDefTO find(long id);
	
	/**
	 * 设置状态
	 * @param id        定时任务唯一标识ID
	 * @param status    定时任务状态
	 */
	void operate(long id, int status);
	
	/**
	 * 更新相关信息，当前只能更新时间表达式
	 * @param form    时间任务表单对象
	 */
	void update(ScheduleDefTO scheduleTo);
	
	/**
	 * 返回所有的时间Job对象
	 * @return    时间任务表单对象列表
	 */
	List<ScheduleDefTO> findAll();
}
