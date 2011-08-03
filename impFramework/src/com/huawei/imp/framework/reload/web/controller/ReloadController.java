/**
 * 
 */
package com.huawei.imp.framework.reload.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.huawei.imp.framework.common.web.controller.BaseControllerSupport;
import com.huawei.imp.framework.jee.JEEConstant;
import com.huawei.imp.framework.reload.ReloadSupport;
import com.huawei.imp.framework.utils.BeanHolder;

/**
 * <p>
 * 内存重新刷新控制器
 * </p>
 * @see 
 * @author aohai.li
 * @version CMSV100R001DB0SP0, 2010-8-25
 * @since CMSV100R001DB0SP0
 */
@Controller
public class ReloadController implements BaseControllerSupport, JEEConstant {
	
	@RequestMapping(value = "/framework/reload/update.do", method = RequestMethod.GET)
	public String setForm(Model model){
		Map<String, ReloadSupport> map = BeanHolder.getBeans(ReloadSupport.class);
		System.out.println(map);
		model.addAttribute("beans", map.entrySet());
		return PAGE_CONSOLE + "reload/update";
	}
	
	@RequestMapping(value = "/framework/reload/update.do", method = RequestMethod.POST)
	public String submit(HttpServletRequest request, Model model){
		String id = request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
			ReloadSupport rs = BeanHolder.getBean(id);
			rs.reload();
		}
		model.addAttribute("url","/framework/reload/update.do");
		return PAGE_CONSOLE + "success";
	}
}
