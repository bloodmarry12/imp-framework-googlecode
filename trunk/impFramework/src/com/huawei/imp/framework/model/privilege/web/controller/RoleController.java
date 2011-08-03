package com.huawei.imp.framework.model.privilege.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.huawei.imp.framework.jee.JEEConstant;
import com.huawei.imp.framework.model.privilege.domain.Right;
import com.huawei.imp.framework.model.privilege.domain.Role;
import com.huawei.imp.framework.model.privilege.service.PrivilegeService;
import com.huawei.imp.framework.model.privilege.web.form.AccountQueryForm;
import com.huawei.imp.framework.model.privilege.web.form.RightGenerator;
import com.huawei.imp.framework.model.privilege.web.form.TreeDetailHTML;
import com.huawei.imp.framework.utils.PageResult;

/**
 * 框架控制台控制器
 * @author ahli
 * @date 2009-8-15
 */
@Controller()
public class RoleController implements JEEConstant{
	
	@Autowired
	@Qualifier("privilegeService")
	private PrivilegeService service;
	
	/**
	 * 账户分页查询页面
	 * @param session
	 * @return
	 */
	@RequestMapping("/framework/model/privilege/role/query.do")
	public String query(@ModelAttribute(QUERYFORM) AccountQueryForm form, Model model){
		PageResult pr = service.queryRole(form.getPageSize(), form.getPageNum());
		model.addAttribute(MODEL_PAGERESULT, pr);
		
		return PAGE_CONSOLE_MODEL + "privilege/role/query";
	}
	
	/**
	 * 账户查看页面
	 * @param session
	 * @return
	 */
	@RequestMapping("/framework/model/privilege/role/view.do")
	public String view(@RequestParam("id") Long id, Model model){
		Role role = service.loadRole(id);
		model.addAttribute(FORM, role);
		
//		Right right = service.loadRightWidthRoleID(Right.ROOT_NODE_ID, id);
		Right right = service.loadRight(Right.ROOT_NODE_ID);
		Set<Right> rights = role.getRights();
		final List<Long> rightIDs = new ArrayList<Long>(rights.size());
		for(Right _right : rights){
			rightIDs.add(_right.getId());
		}
		
		if(null != right){
			RightGenerator rightGenerator = new RightGenerator(right, new TreeDetailHTML<Right>(){
				public String afterAtag(Right right) {
					String checked = rightIDs.contains(right.getId())?"checked":"";
					return "<input name=\"rightIDs\" type=\"checkbox\" id=\"right_" + right.getId() + "\" value=\""+right.getId()+"\" "
					+ " onclick=\"onclickCheckBox('" + right.getId() + "')\" "+ checked+" disable=\"true\"></input>";
				}
			});
			model.addAttribute(MODEL_RIGHT_TREE, rightGenerator);
		}
		
		return PAGE_CONSOLE_MODEL + "privilege/role/view";
	}
	
	/**
	 * 删除角色
	 * @param explorerID	角色ID
	 * @param model			Model
	 * @return
	 */
	@RequestMapping("/framework/model/privilege/role/delete.do")
	public String delete(@RequestParam("id") long roleID, 
			Model model){
		service.removeRole(roleID);
		model.addAttribute("url", "/framework/model/privilege/role/query.do");
		return PAGE_CONSOLE_SUCCESS;
	}
}