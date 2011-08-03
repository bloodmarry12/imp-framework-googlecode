/**
 * 
 */
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
import com.huawei.imp.framework.model.privilege.domain.Status;
import com.huawei.imp.framework.model.privilege.service.PrivilegeService;
import com.huawei.imp.framework.utils.BeanProperUtil;

/**
 * <一句话功能/>
 * <功能描述/>
 * @author ahli
 * @date 2009-8-21
 */
@Controller()
@RequestMapping("/framework/model/privilege/account/create.do")
public class AccountCreateController extends BaseFormController<Account> {

	private static final Logger log = Logger.getLogger(AccountCreateController.class);
	
	@Autowired
	@Qualifier("privilegeService")
	private PrivilegeService service;
	
	@Override
	public String setForm(Model model, HttpServletRequest request) {
		Account account = new Account();
		
		account.getStatus().setValue((Status.Element.NEW.getValue()));
		model.addAttribute(FORM, account);
		
		Map<String, String> map = new HashMap<String, String>();
		List<Role> list = service.findAllRole();
		for(Role role : list){
			map.put(String.valueOf(role.getId()), role.getName());
		}
		
		model.addAttribute("rolesIDItems", map);
		return PAGE_CONSOLE_MODEL + "privilege/account/create";
	}

	@Override
	public String submit(Account form, Model model, HttpServletRequest request) {
		// 如果是debug级别，则打印输入值
		if(log.isDebugEnabled()){
			log.debug(BeanProperUtil.LINE_SEPARATOR + BeanProperUtil.getBeanDesc(form));
			log.debug(BeanProperUtil.getBeanDesc(form.getRole()));
		}
		
		service.saveAccount(form);
		model.addAttribute("url", "/framework/model/privilege/account/query.do");
		return PAGE_CONSOLE_SUCCESS;
	}
}
