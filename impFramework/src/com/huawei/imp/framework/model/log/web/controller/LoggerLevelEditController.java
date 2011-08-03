package com.huawei.imp.framework.model.log.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.huawei.imp.framework.jee.JEEConstant;
import com.huawei.imp.framework.model.log.web.form.LevelEditForm;

@Controller("ahli.framework.model.log.web.controller.LoggerLevelEditController")
@RequestMapping("/framework/model/logger/level/edit.do")
public class LoggerLevelEditController implements JEEConstant{
	
	public static final Map<String, String> levelMap = new HashMap<String, String>(2);
	
	private static final Logger rootLog = Logger.getRootLogger();
	
	// 框架包
	private static final Logger fkLog = Logger.getLogger("com.huawei.imp.framework");
	
	static{
		levelMap.put(String.valueOf(Level.DEBUG_INT), "DEBUG");
		levelMap.put(String.valueOf(Level.INFO_INT), "INFO");
		levelMap.put(String.valueOf(Level.WARN_INT), "WARN");
	}
	
	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String setForm(Model model){
		LevelEditForm form = new LevelEditForm();
		form.setRootLevel(rootLog.getLevel().toInt());
		form.setFkLevel(fkLog.getLevel() == null?rootLog.getLevel().toInt():fkLog.getLevel().toInt());
		model.addAttribute(FORM, form);
		model.addAttribute("rootLevels", levelMap.entrySet());
		return PAGE_CONSOLE_MODEL + "log/level/edit";
	}
	
	/**
	 * @param form
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String setForm(@ModelAttribute(FORM) LevelEditForm form,  Model model){
		rootLog.setLevel(Level.toLevel(form.getRootLevel()));
		fkLog.setLevel(Level.toLevel(form.getFkLevel()));
		model.addAttribute("rootLevels", levelMap.entrySet());
		return PAGE_CONSOLE_MODEL + "log/level/edit";
	}
}
