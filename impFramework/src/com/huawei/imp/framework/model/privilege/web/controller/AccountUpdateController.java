package com.huawei.imp.framework.model.privilege.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huawei.imp.framework.common.web.controller.BaseFormController;
import com.huawei.imp.framework.model.privilege.domain.Account;
import com.huawei.imp.framework.model.privilege.domain.Role;
import com.huawei.imp.framework.model.privilege.service.PrivilegeService;
import com.huawei.imp.framework.utils.BeanProperUtil;

/**
 * Description:
 * 权限编辑控制器
 * @author ahli
 * Apr 25, 2009
 * 
 */
@Controller
@RequestMapping("/framework/model/privilege/account/update.do")
public class AccountUpdateController extends BaseFormController<Account>{
	
	/**
	 * 日志对象
	 */
	private static final Logger log = Logger.getLogger(AccountUpdateController.class);
	
	@Autowired
	@Qualifier("privilegeService")
	private PrivilegeService service;
	
	@Override
	public String setForm(Model model, HttpServletRequest request) {
		Long id = Long.valueOf(request.getParameter("id"));
		
		Account account = service.loadAccount(id);
		if(log.isDebugEnabled()){
			log.debug(BeanProperUtil.getBeanDesc(account));
		}
		
		model.addAttribute(FORM, account);
		
		Map<String, String> map = new HashMap<String, String>();
		List<Role> list = service.findAllRole();
		for(Role role : list){
			map.put(String.valueOf(role.getId()), role.getName());
		}
		
		model.addAttribute("rolesIDItems", map);
		return PAGE_CONSOLE_MODEL + "privilege/account/update";
	}

	@Override
	public String submit(Account form, Model model, HttpServletRequest request) {
		if(log.isDebugEnabled()){
			log.debug(BeanProperUtil.getBeanDesc(form));
		}
		service.updateAccountWithoutRight(form);
		model.addAttribute("url", "/framework/model/privilege/account/query.do");
		return PAGE_CONSOLE_SUCCESS;
	}
	
	
}
