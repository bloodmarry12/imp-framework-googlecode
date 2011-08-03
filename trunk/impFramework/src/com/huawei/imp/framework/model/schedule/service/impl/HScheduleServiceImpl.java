package com.huawei.imp.framework.model.schedule.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huawei.imp.framework.model.schedule.domain.ScheduleDef;
import com.huawei.imp.framework.model.schedule.domain.ScheduleDefTO;
import com.huawei.imp.framework.model.schedule.service.HScheduleService;
import com.huawei.imp.framework.model.schedule.service.ScheduleService;
import com.huawei.imp.framework.utils.BeanUtilTool;

/**
 * 定时任务Hessian业务实现
 * @author zenggh
 * @version :IMPV100R001DA0,Mar 8, 2010
 * @see com.huawei.imp.framework.model.schedule.service.HScheduleService
 * @since CMS IMPV100R001DA0
 */
@Service("hScheduleService")
public class HScheduleServiceImpl implements HScheduleService
{

	@Autowired
	private ScheduleService service;
	
	@Override
	public ScheduleDefTO find(long id)
	{
		// 获取定时任务对象
		ScheduleDef schedule = service.find(id);
		// 定义定时任务传输对象
		ScheduleDefTO scheduleTo = new ScheduleDefTO();
		// 把定时任务对象值赋给传输对象
		BeanUtilTool.copyProperties(scheduleTo, schedule);
		return scheduleTo;
	}

	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.model.schedule.service.HScheduleService#findAll()
	 */
	@Override
	public List<ScheduleDefTO> findAll()
	{
		// 定义定时任务传输对象集合
		List<ScheduleDefTO> scheduleToList = new ArrayList<ScheduleDefTO>();
		// 获取所有定时任务
		List<ScheduleDef> scheduleList = service.findAll();
		// 把定时任务对象集合转换文传输对象集合
		for(ScheduleDef tmp : scheduleList)
		{
			ScheduleDefTO to = new ScheduleDefTO();
			BeanUtilTool.copyProperties(to, tmp);
			scheduleToList.add(to);
		}
		return scheduleToList;
	}

	@Override
	public void operate(long id, int status)
	{
		service.operate(id, status);
	}

	@Override
	public void update(ScheduleDefTO scheduleTo)
	{
		// 定义定时任务传输对象
		ScheduleDef schedule = new ScheduleDef();
		// 把定时任务传输对象值赋给po对象
		BeanUtilTool.copyProperties(schedule,scheduleTo );
		// 更新定时任务
		service.update(schedule);
	}
    
}
