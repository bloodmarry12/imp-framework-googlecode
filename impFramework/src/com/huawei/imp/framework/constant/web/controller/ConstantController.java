/*
 * 文件名：ConstantController.java
 * 版权：  Copyright 2000-2009 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 描述：〈描述〉
 * 修改人：ahli
 * 修改时间：Aug 27, 2009
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.huawei.imp.framework.constant.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huawei.imp.framework.constant.domain.Constant;
import com.huawei.imp.framework.constant.domain.ConstantType;
import com.huawei.imp.framework.constant.service.ConstantService;
import com.huawei.imp.framework.constant.service.ConstantTypeService;
import com.huawei.imp.framework.constant.web.form.ConstantQueryForm;
import com.huawei.imp.framework.jee.JEEConstant;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author ahli liuguoping
 * @version IMPV100R001DA0, Aug 27, 2009 
 * @see [相关类/方法]
 * @since CMS IMPV100R001DA0
 */

@Controller
public class ConstantController implements JEEConstant
{

	@Autowired
	@Qualifier("constantService")
	private ConstantService service;
	
	@Autowired
	@Qualifier("constantTypeService")
	private ConstantTypeService constantTypeService;
	
	/**
	 * 查询常量
	 * @param form
	 * @param model
	 * @return
	 */
	@RequestMapping("/framework/constant/query.do")
	public String query(@ModelAttribute(QUERYFORM) ConstantQueryForm form, Model model){
		if(null == form.getModelID()){
			form.setModelID(1);
		}		
		List<ConstantType> modelIDArray = constantTypeService.query();
		model.addAttribute("modelIDs", modelIDArray);
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("modelid", form.getModelID());
		List<Constant> list = service.queryConstants(params);
		model.addAttribute("list", list);
		return PAGE_CONSOLE + "constant/query";
	}
	
	/**
	 * 保存常量
	 * @param form
	 * @param model
	 * @return
	 */
	@RequestMapping("/framework/constant/save.do")
	public String saveConstant(@ModelAttribute(FORM) ConstantQueryForm form,Model model)
	{
		model.addAttribute(FORM, ConstantQueryForm.NULL);
		model.addAttribute(QUERYFORM, ConstantQueryForm.NULL);
		Constant c = new Constant();		
		c.setModelID(form.getModelID());
		c.setName(form.getName());
		c.setOrder(form.getOrder());
		c.setValue(form.getValue());
		service.save(c);	
		model.addAttribute("url","/framework/constant/query.do");
		return PAGE_CONSOLE + "success";
	}
	
	/**
	 * 删除常量
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/framework/constant/remove.do")
	public String removeConstant(HttpServletRequest request,Model model)
	{
		model.addAttribute(FORM, ConstantQueryForm.NULL);
		model.addAttribute(QUERYFORM, ConstantQueryForm.NULL);
		
		String id = request.getParameter("ids");
		String[] ids = id.split(",");
		Integer[] idsNum = new Integer[ids.length];
		for(int i=0;i<ids.length;i++)
		{
			idsNum[i] = Integer.parseInt(ids[i]);
		}
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("ids", idsNum);
		service.remove(params);
		model.addAttribute("url","/framework/constant/query.do");
		return PAGE_CONSOLE + "success";
	}
	
}