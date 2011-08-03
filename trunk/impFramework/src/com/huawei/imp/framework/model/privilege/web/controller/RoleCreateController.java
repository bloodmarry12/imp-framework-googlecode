/**
 * 
 */
package com.huawei.imp.framework.model.privilege.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huawei.imp.framework.common.web.controller.BaseFormController;
import com.huawei.imp.framework.model.privilege.domain.Right;
import com.huawei.imp.framework.model.privilege.domain.Role;
import com.huawei.imp.framework.model.privilege.service.PrivilegeService;
import com.huawei.imp.framework.model.privilege.web.form.RightGenerator;
import com.huawei.imp.framework.model.privilege.web.form.TreeDetailHTML;

/**
 * <一句话功能/>
 * <功能描述/>
 * @author ahli
 * @date 2009-8-21
 */
@Controller()
@RequestMapping("/framework/model/privilege/role/create.do")
public class RoleCreateController extends BaseFormController<Role> {

	@Autowired
	@Qualifier("privilegeService")
	private PrivilegeService service;
	
	@Override
	public String setForm(Model model, HttpServletRequest request) {
		model.addAttribute(FORM, new Role());
		
		//显示权限树
		Right right = service.loadRight(Right.ROOT_NODE_ID);
		RightGenerator rightGenerator = new RightGenerator(right, new TreeDetailHTML<Right>(){
			public String afterAtag(Right right) {
				return "<input name=\"rightIDs\" type=\"checkbox\" id=\"right_" + right.getId() + "\" value=\""+right.getId()+"\" "
				+ " onclick=\"onclickCheckBox('" + right.getId() + "')\"></input>";
						
			}
		});
		model.addAttribute(MODEL_RIGHT_TREE, rightGenerator);
		
		return PAGE_CONSOLE_MODEL + "privilege/role/create";
	}

	@Override
	public String submit(@ModelAttribute(FORM) Role form, 
			Model model, 
			HttpServletRequest request) {
		
		// 获取request中的权限ID
		final String[] rightIDStr = request.getParameterValues("rightIDs");
		
		// 加载角色对应权限属性
		List<Long> rightIDs = new ArrayList<Long>(rightIDStr.length);
		for(String rightID : rightIDStr){
			rightIDs.add(Long.valueOf(rightID));
		}
		service.saveRole(form, rightIDs);
		model.addAttribute("url", "/framework/model/privilege/role/query.do");
		return PAGE_CONSOLE_SUCCESS;
	}
}
