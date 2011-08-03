package com.huawei.imp.framework.model.privilege.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.huawei.imp.framework.jee.JEEConstant;
import com.huawei.imp.framework.model.privilege.domain.ExplorerIndex;
import com.huawei.imp.framework.model.privilege.domain.Right;
import com.huawei.imp.framework.model.privilege.service.PrivilegeService;

/**
 * Description:
 * 菜单管理创建控制器
 * @author ahli
 * Apr 25, 2009
 * 
 */
@Controller
@RequestMapping("/framework/model/privilege/explorer/update.do")
public class ExplorerUpdateController implements JEEConstant{
	
	@Autowired
	@Qualifier("privilegeService")
	private PrivilegeService service;
	
	/**
	 * 初始化创建表单
	 * @param rightID
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String setForm(@RequestParam("explorerID") long explorerID,
			Model model){
		ExplorerIndex explorerIndex = service.loadExplorerIndexByID(explorerID);
		if(null == explorerIndex.getRight()){
			explorerIndex.setRight(new Right(false));
		}
		model.addAttribute(FORM, explorerIndex);
		return PAGE_CONSOLE_MODEL + "privilege/explorer/update";
	}
	
	/**
	 * 处理表单提交动作
	 * @param right	
	 * @param result
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String submit(@ModelAttribute(FORM) ExplorerIndex explorerIndex,
			BindingResult result,
			Model model){
		service.updateExplorerIndex(explorerIndex);
		model.addAttribute("url", "/framework/model/privilege/explorer/tree.do");
		return PAGE_CONSOLE_SUCCESS;
	}
}
