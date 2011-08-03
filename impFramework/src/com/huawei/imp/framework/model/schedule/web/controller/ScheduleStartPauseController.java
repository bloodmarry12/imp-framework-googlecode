/*
 * 文件名：TimeTaskStartPause.java
 * 版权：  Copyright 2000-2009 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 描述：  异步处理定时任务的启动和暂停
 * 修改人：李谟毫   63800
 * 修改时间：2009-05-05 12:05:10
 * 修改内容：新增
 */

package com.huawei.imp.framework.model.schedule.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.huawei.imp.framework.common.web.controller.BaseAjaxJsonController;
import com.huawei.imp.framework.exception.BusinessException;
import com.huawei.imp.framework.exception.FrameworkBusinessException;
import com.huawei.imp.framework.model.schedule.domain.ScheduleDef;
import com.huawei.imp.framework.model.schedule.service.ScheduleService;

/**
 * 异步处理定时任务的启动和暂停
 * 提供异步的定时任务启动与暂停功能；
 * @author 李谟毫   63800
 * @version IMPV100R001DA0, 2009-05-05 12:05:10
 * @see com.huawei.cms.common.web.controller.BaseAjaxJsonController
 * @since CMS IMPV100R001DA0
 */
@Controller("timeTaskStartPause")
public class ScheduleStartPauseController extends BaseAjaxJsonController
{
	
	/**
	 * 定时任务业务对象（注入）
	 */
	@Autowired
	private ScheduleService service;
	
	/**
	 * 启动定时任务
	 * @param jobID    定时任务ID
	 * @param model    模型，主要用于设置会话信息
	 * @return         返回JSON数据，页面通过AJAX（JQUERY）处理
	 */
	@RequestMapping("/framework/model/schedule/start.do")
	public String start(@RequestParam("jobID")
	long jobID, Model model)
	{
		try{
			service.operate(jobID, ScheduleDef.STATUS_RUN);
			model.addAttribute(FORM, DEFAULT_SUCCESS);
		}catch(FrameworkBusinessException e){
			model.addAttribute(FORM, genJSONObject(e.getMessage()));
		}
		return PAGE_CONSOLE_JSON;
	}
	
	/**
	 * 暂停定时任务
	 * @param jobID    定时任务ID
	 * @param model    模型，主要用于设置会话信息
	 * @return         返回JSON数据，页面通过AJAX（JQUERY）处理
	 */
	@RequestMapping("/framework/model/schedule/pause.do")
	public String pause(@RequestParam("jobID")
	long jobID, Model model)
	{
		try{
			service.operate(jobID, ScheduleDef.STATUS_STOP);
			model.addAttribute(FORM, DEFAULT_SUCCESS);
		}catch(BusinessException e){
			model.addAttribute(FORM, genJSONObject(e.getMessage()));
		}
		return PAGE_CONSOLE_JSON;
	}
}
