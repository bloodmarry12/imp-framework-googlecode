package com.huawei.imp.framework.model.privilege.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.huawei.imp.framework.jee.JEEConstant;
import com.huawei.imp.framework.model.privilege.domain.Right;
import com.huawei.imp.framework.model.privilege.service.PrivilegeService;
import com.huawei.imp.framework.model.privilege.web.form.RightGenerator;
import com.huawei.imp.framework.model.privilege.web.form.TreeDetailHTML;

/**
 * 框架控制台控制器
 * @author ahli
 * @date 2009-8-15
 */
@Controller()
public class RightController implements JEEConstant{
	
	@Autowired
	@Qualifier("privilegeService")
	private PrivilegeService service;
	
	/**
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/framework/model/privilege/right/tree.do")
	public String tree(Model model){
		Right right = service.loadRight(Right.ROOT_NODE_ID);
		RightGenerator rightGenerator = new RightGenerator(right, new TreeDetailHTML<Right>(){
			public String afterAtag(Right right) {
				if (Right.ROOT_NODE_ID == right.getId()) {
							return "(<a href=\"create.do?parentID="
									+ right.getId() + "\">添加</a>)";
						} else {
							return "(<a href=\"update.do?id="
									+ right.getId() + "\">编辑</a>,"
									+ "<span onclick=\"deleteRight("
									+ right.getId()
									+ ")\">删除</span>,"
									+ "<a href=\"create.do?parentID="
									+ right.getId() + "\">添加</a>)";
						}
			}
			
			public String ahrefUrl(Right right) {
				return " href=\"view.do?id="+right.getId()+"\"";
			}
		});
		model.addAttribute(MODEL_RIGHT_TREE, rightGenerator);
		return PAGE_CONSOLE_MODEL + "privilege/right/tree";
	}
	
	/**
	 * 账户查看页面
	 * @param session
	 * @return
	 */
	@RequestMapping("/framework/model/privilege/right/view.do")
	public String view(@RequestParam("id") Long id, Model model){
		Right right = this.service.loadRight(id);
		model.addAttribute(FORM, right);
		return PAGE_CONSOLE_MODEL + "privilege/right/view";
	}
	
	/**
	 * 删除菜单
	 * @param explorerID	导航ID
	 * @param model			Model
	 * @return
	 */
	@RequestMapping("/framework/model/privilege/right/delete.do")
	public String delete(@RequestParam("id") long id, 
			Model model){
		service.removeRight(id);
		model.addAttribute("url", "/framework/model/privilege/right/tree.do");
		return PAGE_CONSOLE_SUCCESS;
	}
}