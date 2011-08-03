/*
 * 文件名：TimeTaskController.java
 * 版权：  Copyright 2000-2009 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 描述：  定时任务方法控制器
 * 修改人：李谟毫   63800
 * 修改时间：2009-05-05 10:56:45
 * 修改内容：新增
 */

package com.huawei.imp.framework.model.schedule.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.huawei.imp.framework.common.web.controller.BaseControllerSupport;
import com.huawei.imp.framework.model.schedule.domain.ScheduleDef;
import com.huawei.imp.framework.model.schedule.service.ScheduleService;

/**
 * 定时任务方法控制器
 * 提供数据展现的页面初始化方法实现
 * @author 李谟毫   63800
 * @version IMPV100R001DA0, 2009-05-05 10:56:45
 * @see com.huawei.cms.common.web.controller.BaseController
 * @since CMS IMPV100R001DA0
 */
@Controller
public class ScheduleController implements BaseControllerSupport
{
	
	/**
	 * 定时任务业务对象
	 */
	@Autowired
	private ScheduleService service;
	
	/**
	 * 显示定时任务配置信息，单个定时任务的展现
	 * @param jobID    任务ID
	 * @param model    模型，主要用于设置会话信息
	 * @return /common/console/timetask/view.jsp
	 */
	@RequestMapping("/framework/model/schedule/view.do")
	public String view(@RequestParam("jobID")
	long jobID, Model model)
	{
		ScheduleDef form = service.find(jobID);
		model.addAttribute(FORM, form);
		return PAGE_CONSOLE_MODEL + "schedule/view";
	}
	
	/**
	 * 显示分页列表，查询所有定时任务
	 * @param model    模型，主要用于设置会话信息
	 * @return  /common/console/timetask/list.jsp
	 */
	@RequestMapping("/framework/model/schedule/query.do")
	public String queryList(Model model)
	{
		List<ScheduleDef> list = service.findAll();
		model.addAttribute("list", list);
		return PAGE_CONSOLE_MODEL + "schedule/query";
	}
}
