package com.huawei.imp.framework.config.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.huawei.imp.framework.common.web.controller.BaseControllerSupport;
import com.huawei.imp.framework.config.domain.SysConfig;
import com.huawei.imp.framework.config.service.SysConfigService;
import com.huawei.imp.framework.config.web.validator.SysConfigValidator;

/**
 * Description:
 * 定时修改创建控制器
 * @author ahli
 * Apr 16, 2009
 * 
 */
@Controller
@RequestMapping("/framework/config/update.do")
public class SysConfigUpdateController implements BaseControllerSupport
{

	@Autowired
	private SysConfigService service;
	
	//定义空值的信息
	final static String EMPTY_VALUE="Empty";
	
	@RequestMapping(method = RequestMethod.GET)
	public String setForm(@RequestParam("keyID") String key,Model model)
	{
		SysConfig form = service.loadSysConfig(key);
		model.addAttribute(FORM, form);
		return PAGE_CONSOLE + "config/update";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String submit(@ModelAttribute(FORM) SysConfig form,
			BindingResult result,
			Model model,
			HttpSession session
			)
	{
		// 表单验证
		new SysConfigValidator().validate(form, result);
		if(result.hasErrors())
		{
			return PAGE_CONSOLE + "config/update";
		}
		service.updateSysConfig(form);
		return PAGE_CONSOLE_SUCCESS;
	}
}
