package com.huawei.imp.framework.model.console.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huawei.imp.framework.jee.JEEConstant;

@Controller("com.huawei.imp.framework.model.console.web.controller.C3P0ConnectionStatusController")
public class ConsolePageController implements JEEConstant{
	
	/**
	 * @param model
	 * @return
	 */
	@RequestMapping("/framework/model/console/view.do")
	public String setForm(){
		return PAGE_CONSOLE_MODEL + "console/view";
	}
}
