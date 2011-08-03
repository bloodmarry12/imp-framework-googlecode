package com.huawei.imp.framework.model.privilege.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huawei.imp.framework.jee.JEEConstant;

/**
 * 框架控制台控制器
 * @author ahli
 * @date 2009-8-15
 */
@Controller("ahli.framework.model.privilege.web.controller.ConsoleController")
public class ConsoleController implements JEEConstant{

	/**
	 * 框架控制台首页
	 * @param session
	 * @return
	 */
	@RequestMapping("/framework/index.do")
	public String index(HttpSession session){
		if(null == session.getAttribute(SESSION_FRAMEWORK_LOGIN_TOKEN)){
			return "redirect:/framework/login.do";
		}else{
			return "framework/jsp/frame";
		}
	}
	
	/**
	 * 注销当前登录框架控制台用户
	 * @param session
	 * @return
	 */
	@RequestMapping("/framework/logout.do")
	public String logout(HttpSession session){
		session.removeAttribute(SESSION_FRAMEWORK_LOGIN_TOKEN);
		return "redirect:/framework/login.do";
	}
}