/*
 * description: 
 * date:        下午03:22:11
 * author:      ahli
 */
package com.huawei.imp.framework.console.script.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.huawei.imp.framework.console.script.service.executor.DataSourceManager;
import com.huawei.imp.framework.console.script.service.facade.JscriptService;
import com.huawei.imp.framework.console.script.web.WebConstants;
import com.huawei.imp.framework.console.script.web.form.JscriptForm;

/**
 * <p><strong>Javascript脚本控制器</strong></p>
 * <p>提供基于Java的script引擎实现的脚本输入执行页面</p>
 * @see ahli.framework.web.controller.ControllerSupport
 * @see ahli.framework.web.WebConstants
 * @version v1.0
 * @since v2.0
 * @author ahli (edit in 2010-5-26)
 */
@Controller("jscriptController")
public class JscriptController implements WebConstants {

	private static final String TYPES = "types";
	private static final String DATASOURCES = "datasources";
	private static final String MESSAGE = "message";
	
	@Autowired
	@Qualifier("jscriptService")
	private JscriptService jscriptService;
	
	/**
	 * 初始化页面
	 * @return
	 */
	@RequestMapping(value="/framework/console/script/jscript.do", method=RequestMethod.GET)
	public String setForm(Model model){
		// 设置表单对象
		model.addAttribute(MODEL_FORM, JscriptForm.NULL);
		model.addAttribute(TYPES, jscriptService.getSupportTypeArray());
		model.addAttribute(DATASOURCES, DataSourceManager.getSupportDataSource());
		return PAGEPATH_FRAMEWORK_ROOT + "/script/jscript";
	}
	
	/**
	 * 初始化页面
	 * @return
	 */
	@RequestMapping(value="/framework/console/script/jscript.do", method=RequestMethod.POST)
	public String submit(@ModelAttribute(MODEL_FORM) JscriptForm form,
			Model model, HttpSession session){
		
		// 设置表单对象
		model.addAttribute(TYPES, jscriptService.getSupportTypeArray());
		model.addAttribute(DATASOURCES, DataSourceManager.getSupportDataSource());
		
		final String out = jscriptService.executeScript(form.getScript(), form.getDatasource(), form.getType(), form.getToken(), session);
		model.addAttribute(MESSAGE, out);
		return PAGEPATH_FRAMEWORK_ROOT + "/script/jscript";
	}
	
//	public String setForm(@ModelAttribute(FORM)
//			ConsoleLoginForm form, Model model, HttpSession session) {
}
