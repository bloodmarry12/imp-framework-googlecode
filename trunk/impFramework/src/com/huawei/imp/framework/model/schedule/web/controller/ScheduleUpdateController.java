/*
 * 文件名：TimeTaskUpdateController.java
 * 版权：  Copyright 2000-2009 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 描述：  定时任务配置修改controller类
 * 修改人：李谟毫   63800
 * 修改时间：2009-05-05 14:07:25
 * 修改内容：新增
 */

package com.huawei.imp.framework.model.schedule.web.controller;

import javax.servlet.http.HttpSession;

import org.quartz.CronExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.huawei.imp.framework.common.web.controller.BaseControllerSupport;
import com.huawei.imp.framework.model.schedule.domain.ScheduleDef;
import com.huawei.imp.framework.model.schedule.service.ScheduleService;
import com.huawei.imp.framework.model.schedule.web.validator.ScheduleDefValidator;

/**
 * 定时任务配置修改controller类
 * 提供定时任务修改表单的初始化，以及定时任务的修改。
 * @author 李谟毫   63800
 * @version IMPV100R001DA0, 2009-05-05 14:07:25
 * @see com.huawei.cms.common.web.controller.BaseController
 * @since CMS IMPV100R001DA0
 */
@Controller
@RequestMapping("/framework/model/schedule/update.do")
public class ScheduleUpdateController implements BaseControllerSupport
{

	/**
	 * 定时任务修改页面
	 */
	private static final String URL_PAGE_UPDATE = PAGE_CONSOLE_MODEL + "schedule/update";
	
	/**
	 * 定时任务业务对象
	 */
	@Autowired
	private ScheduleService service;
	
	/**
     * 处理通过HTTP的GET、HEAD方法提交的请求，主要用于页面加载时使用
	 * @param jobID    定时任务ID
	 * @param model    模型，主要用于设置会话信息
	 * @return         /URL_PAGE_UPDATE.jsp
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String setForm(@RequestParam("jobID")
	long jobID, Model model)
	{
		ScheduleDef form = service.find(jobID);
		model.addAttribute(FORM, form);
		return URL_PAGE_UPDATE;
	}
	
	/**
	 * 处理通过HTTP的POST方法提交的请求，主要用于更新时用
	 * @param form       TimeJobForm对象，可以通过页面直接转换过来
	 * @param model      模型，主要用于设置会话信息
	 * @param session    http会话
	 * @return           /URL_PAGE_UPDATE.jsp
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String submit(@ModelAttribute(FORM)
			ScheduleDef form,BindingResult result, Model model, HttpSession session)
	{
		//增加一个验证
		new ScheduleDefValidator().validate(form, result);
		if(result.hasErrors())
		{
			return URL_PAGE_UPDATE;
		}
		// 验证克隆表达式
		if (CronExpression.isValidExpression(form.getCexp()))
		{
			service.update(form);
			model.addAttribute("url", "/framework/model/schedule/query.do");
			return PAGE_CONSOLE_SUCCESS;
		}
		else
		{
//			model.addAttribute("url", "");
			return URL_PAGE_UPDATE;
		}
	}
}
