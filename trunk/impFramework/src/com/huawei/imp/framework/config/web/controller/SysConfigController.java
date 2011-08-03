package com.huawei.imp.framework.config.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.huawei.imp.framework.common.web.controller.BaseControllerSupport;
import com.huawei.imp.framework.config.domain.SysConfig;
import com.huawei.imp.framework.config.service.SysConfigService;

/**
 * Description:
 * 系统配置控制器
 * @author ahli
 * Apr 16, 2009
 * 
 */
@Controller
public class SysConfigController implements BaseControllerSupport{
	
//	/**
//	 * 日志对象
//	 */
//	private static final Logger log = Logger.getLogger(SysConfigController.class);
	
	@Autowired
	private SysConfigService service;
	
	@RequestMapping("/framework/config/view.do")
	public String view(@RequestParam("keyID") String key,Model model)
	{
		SysConfig form = service.loadSysConfig(key);
		model.addAttribute(FORM, form);
		return PAGE_CONSOLE + "config/view";
	}
	
	@RequestMapping("/framework/config/query.do")
	public String queryList(Model model)
	{
		List<SysConfig> list = service.findSysConfig();
		model.addAttribute("list", list);
		return PAGE_CONSOLE + "config/query";
	}
}
