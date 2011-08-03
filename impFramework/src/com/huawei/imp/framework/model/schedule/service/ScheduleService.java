/*
 * 文件名：CMSQuartzService.java
 * 版权：  Copyright 2000-2009 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 描述：  定时任务业务接口
 * 修改人：李谟毫   63800
 * 修改时间：2009-04-26 11:40:13
 * 修改内容：新增
 */

package com.huawei.imp.framework.model.schedule.service;

import java.util.List;

import com.huawei.imp.framework.model.schedule.domain.ScheduleDef;

/**
 * 定时任务业务接口
 * @author 李谟毫   63800
 * @version IMPV100R001DA0, 2009-04-26 11:40:13
 * @since CMS IMPV100R001DA0
 */
public interface ScheduleService
{
	
	/**
	 * 查询制定定时任务对象
	 * @param    定时任务唯一标识ID
	 * @return    时间任务表单对象
	 */
	ScheduleDef find(long id);
	
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
	void update(ScheduleDef form);
	
	/**
	 * 返回所有的时间Job对象
	 * @return    时间任务表单对象列表
	 */
	List<ScheduleDef> findAll();
}
