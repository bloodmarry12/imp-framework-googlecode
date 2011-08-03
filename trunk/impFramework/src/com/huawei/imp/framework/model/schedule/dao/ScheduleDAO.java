/*
 * 文件名：FtpDAO.java
 * 版权：  Copyright 2000-2009 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 描述：  定义定时任务模块配置数据持久化方法
 * 修改人：李谟毫   63800
 * 修改时间：2009-04-16 10:29:41 
 * 修改内容：新增
 */

package com.huawei.imp.framework.model.schedule.dao;

import java.util.List;

import com.huawei.imp.framework.common.dao.IbatisSupportDAO;
import com.huawei.imp.framework.model.schedule.domain.ScheduleDef;

/**
 * 定义定时任务模块配置数据持久化方法
 * @author 李谟毫   63800
 * @version IMPV100R001DA0, 2009-04-16 10:29:41 
 * @since CMS IMPV100R001DA0
 */
public interface ScheduleDAO extends IbatisSupportDAO
{

	/**
	 * 查询出所有持久化的定时任务配置信息
	 * @return    定时任务配置对象列表
	 */
	List<ScheduleDef> find();
	
	/**
	 * 查询指定机器的配置
	 * @param host    指定的机器IP地址
	 * @return
	 */
	List<ScheduleDef> findByHost(String host);
	
	/**
	 * 更新指定id的定时任务状态
	 * @param id        ID
	 * @param status    定时任务状态
	 */
	void updateStatus(long id, int status);
	
	/**
	 * 更新定时任务配置信息
	 * @param id            ID
	 * @param cexp          克隆表达式
	 * @param beanName      ioc中的bean名称
	 * @param methodName    定时任务执行的方法名
	 * @param concurrent    是否是串行
	 * @param host          主机IP
	 * @param parameter     参数
	 */
	void update(long id, String cexp, String beanName, String methodName, int concurrent,String host,String parameter);
	
}
