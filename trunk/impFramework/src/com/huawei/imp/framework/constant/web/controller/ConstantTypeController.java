/*
 * 文件名：ConstantTypeController.java
 * 版权：  Copyright 2000-2009 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 描述：〈描述〉
 * 修改人：liuguoping
 * 修改时间：Aug 27, 2009
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.huawei.imp.framework.constant.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huawei.imp.framework.constant.domain.ConstantType;
import com.huawei.imp.framework.constant.service.ConstantTypeService;
import com.huawei.imp.framework.constant.web.form.ConstantQueryForm;
import com.huawei.imp.framework.jee.JEEConstant;

/**
 * 〈常量所属类型业务控制器〉
 * 〈功能详细描述〉
 * @author liuguoping
 * @version IMPV100R001DA0, Aug 27, 2009 
 * @see [相关类/方法]
 * @since CMS IMPV100R001DA0
 */

@Controller
public class ConstantTypeController implements JEEConstant
{

	@Autowired
	@Qualifier("constantTypeService")
	private ConstantTypeService constantTypeService;
	
	@RequestMapping("/framework/constantType/query.do")
	public String query(@ModelAttribute(QUERYFORM) ConstantQueryForm form, Model model){
		
		List<ConstantType> list = constantTypeService.query();
		model.addAttribute("list", list);			
		return PAGE_CONSOLE + "constantType/queryType";
	}
	
	@RequestMapping("/framework/constantType/remove.do")
	public String remove(HttpServletRequest request, Model model){
		String modelId = request.getParameter("modelIds");
		String[] modelIds = modelId.split(",");
		Integer[] ids = new Integer[modelIds.length];
		for(int i=0;i<modelIds.length;i++)
		{
			ids[i] = Integer.parseInt(modelIds[i]);
		}
		constantTypeService.removeConstantType(ids);
		model.addAttribute("url","/framework/constantType/query.do");
		return PAGE_CONSOLE + "success";
	}
	
	@RequestMapping("/framework/constantType/save.do")
	public String save(HttpServletRequest request, Model model)
	{
		String modelId = request.getParameter("modelid");
		String modelName = request.getParameter("modelname");
		ConstantType ct = new ConstantType();
		ct.setModelID(Integer.parseInt(modelId));
		ct.setModelName(modelName);
		constantTypeService.saveConstantType(ct);
		model.addAttribute("url","/framework/constantType/query.do");
		return PAGE_CONSOLE + "success";
	}
}