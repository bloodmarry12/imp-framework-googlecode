package com.huawei.imp.framework.model.privilege.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.huawei.imp.framework.jee.JEEConstant;
import com.huawei.imp.framework.model.privilege.domain.ExplorerIndex;
import com.huawei.imp.framework.model.privilege.service.PrivilegeService;

/**
 * Description:
 * 菜单管理创建控制器
 * @author ahli
 * Apr 26, 2009
 * 
 */
@Controller
@RequestMapping("/framework/model/privilege/explorer/create.do")
public class ExplorerCreateController implements JEEConstant{
	
	@Autowired
	@Qualifier("privilegeService")
	private PrivilegeService service;

	/**
	 * 初始化表单对象
	 * @param parentID
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String setForm(
			@RequestParam("parentID") long parentID,
			Model model){
		ExplorerIndex explorerIndex = new ExplorerIndex();
		explorerIndex.getParent().setId(parentID);
		model.addAttribute(FORM, explorerIndex);
		return PAGE_CONSOLE_MODEL + "privilege/explorer/create";
	}
	

	/**
	 * 处理表单提交动作
	 * @param explorerIndexBO
	 * @param result
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String submit(@ModelAttribute(FORM) ExplorerIndex explorerIndex,
			Model model){
		service.saveExplorerIndex(explorerIndex);
		model.addAttribute("url", "/framework/model/privilege/explorer/tree.do");
		return PAGE_CONSOLE_SUCCESS;
	}
}
