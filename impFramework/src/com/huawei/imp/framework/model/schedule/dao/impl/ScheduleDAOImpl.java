/*
 * 文件名：CMSQuartzDAOImpl.java
 * 版权：  Copyright 2000-2009 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 描述：  提供基于Ibatis的数据库持久化实现方法
 * 修改人：李谟毫   63800
 * 修改时间：2009-04-16 10:32:22 
 * 修改内容：新增
 */

package com.huawei.imp.framework.model.schedule.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.huawei.imp.framework.common.dao.impl.IbatisCommonDAOImpl;
import com.huawei.imp.framework.model.schedule.dao.ScheduleDAO;
import com.huawei.imp.framework.model.schedule.domain.ScheduleDef;

/**
 * 提供基于Ibatis的数据库持久化实现方法
 * 定时任务配置数据库访问实现类，从基类注入sqlMapClientTemplate
 * @author 李谟毫   63800
 * @version IMPV100R001DA0, 2009-04-16 10:32:22 
 * @see com.huawei.cms.common.dao.impl.CommonDAOImpl
 * @see com.huawei.ScheduleDAO.common.timeTask.dao.CMSQuartzDAO
 * @since CMS IMPV100R001DA0
 */
@Component("scheduleDAO")
public class ScheduleDAOImpl extends IbatisCommonDAOImpl implements ScheduleDAO
{

	/* (non-Javadoc)
	 * @see com.huawei.cms.common.timeTask.dao.CMSQuartzDAO#find()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ScheduleDef> find()
	{
		final String sql = warpSqlstatement(ScheduleDef.class, "query");
		return sqlMapClientTemplate.queryForList(sql);
	}

	/*
	 * (non-Javadoc)
	 * @see com.huawei.cms.common.timeTask.dao.CMSQuartzDAO#update(long,
	 *      java.lang.String, java.lang.String, java.lang.String, int)
	 */
	@Override
	public void update(long id, String cexp, String beanName,
			String methodName, int concurrent,String host,String parameter)
	{
		final String sql = warpSqlstatement(ScheduleDef.class, "update");
        //初始化查询map，并设置查询条件,后期需要优化成常量
		Map<String, Object> map = new HashMap<String, Object>(5);
		map.put("id", id);
		map.put("cexp", cexp);
		map.put("beanName", beanName);
		map.put("methodName", methodName);
		map.put("concurrent", concurrent);
		map.put("host", host);
		map.put("parameter", parameter);
		
		sqlMapClientTemplate.update(sql, map);
	}

	/* (non-Javadoc)
	 * @see com.huawei.cms.common.timeTask.dao.CMSQuartzDAO#updateStatus(long, int)
	 */
	@Override
	public void updateStatus(long id, int status)
	{   
		final String sql = warpSqlstatement(ScheduleDef.class, "updateStatus");
		
		//初始化查询map，并设置查询条件,后期需要优化成常量
		Map<String, Object> map = new HashMap<String, Object>(2);
		map.put("id", id);
		map.put("status", status);
		sqlMapClientTemplate.update(sql, map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ScheduleDef> findByHost(String host)
	{
		final String sql = warpSqlstatement(ScheduleDef.class, "queryByHost");
		return sqlMapClientTemplate.queryForList(sql, host);
	}
}
