/*
 * 
 */
package com.huawei.imp.framework.model.privilege.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.huawei.imp.framework.common.web.controller.BaseControllerSupport;
import com.huawei.imp.framework.model.privilege.domain.Right;
import com.huawei.imp.framework.model.privilege.service.PrivilegeService;

/**
 * 权限修改控制器
 * @author ahli
 * @date 2009-8-21
 */
@Controller("framework.rightUpdateController")
@RequestMapping("/framework/model/privilege/right/update.do")
public class RightUpdateController implements BaseControllerSupport {

	@Autowired
	@Qualifier("privilegeService")
	private PrivilegeService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public String setForm(@RequestParam("id") long id, Model model) {
		Right right = service.loadRight(id);
		model.addAttribute(FORM, right);
		return PAGE_CONSOLE_MODEL + "privilege/right/update";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String submit(@ModelAttribute(FORM) Right form, Model model, HttpServletRequest request) {
		service.updateRight(form);
		model.addAttribute("url", "/framework/model/privilege/right/tree.do");
		return PAGE_CONSOLE_SUCCESS;
	}
}
