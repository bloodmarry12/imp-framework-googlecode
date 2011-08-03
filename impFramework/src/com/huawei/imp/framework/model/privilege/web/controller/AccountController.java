package com.huawei.imp.framework.model.privilege.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.huawei.imp.framework.jee.JEEConstant;
import com.huawei.imp.framework.model.privilege.domain.Account;
import com.huawei.imp.framework.model.privilege.service.PrivilegeService;
import com.huawei.imp.framework.model.privilege.web.form.AccountQueryForm;
import com.huawei.imp.framework.utils.PageResult;

/**
 * 框架控制台控制器
 * @author ahli
 * @date 2009-8-15
 */
@Controller("ahli.framework.model.privilege.web.controller.AccountController")
public class AccountController implements JEEConstant{
	
	@Autowired
	@Qualifier("privilegeService")
	private PrivilegeService service;
	
	/**
	 * 账户分页查询页面
	 * @param session
	 * @return
	 */
	@RequestMapping("/framework/model/privilege/account/query.do")
	public String query(@ModelAttribute(QUERYFORM) AccountQueryForm form, Model model){
		PageResult pr = service.queryAccount(form.getPageSize(), form.getPageNum(), form.getParams());
		model.addAttribute(MODEL_PAGERESULT, pr);
		return PAGE_CONSOLE_MODEL + "privilege/account/query";
	}
	
	/**
	 * 账户查看页面
	 * @param session
	 * @return
	 */
	@RequestMapping("/framework/model/privilege/account/view.do")
	public String view(@RequestParam("id") Long id, Model model){
		Account account = service.loadAccount(id);
		model.addAttribute(FORM, account);
		return PAGE_CONSOLE_MODEL + "privilege/account/view";
	}
	
	/**
	 * 删除帐号
	 * @param ids
	 * @param model
	 * @return
	 */
	@RequestMapping("/framework/model/privilege/account/delete.do")
	public String delete(@RequestParam("id")long[] ids, Model model) {
		service.removeAccount(ids);
		model.addAttribute("url", "/framework/model/privilege/account/query.do");
		return PAGE_CONSOLE_SUCCESS;
	}
	
	/**
	 * 重置密码
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/framework/model/privilege/account/resetPassword.do")
	public String resetPassword(@RequestParam("id")long id, Model model) {
		Account ac = service.loadAccount(id);
		if(null != ac){
			service.changePassword(ac.getId(), "123456");
		}
		model.addAttribute("url", "/framework/model/privilege/account/query.do");
		return PAGE_CONSOLE_SUCCESS;
	}
}