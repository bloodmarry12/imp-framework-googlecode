/**
 * 
 */
package com.huawei.imp.framework.model.privilege.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.huawei.imp.framework.config.FileConfigUtil;
import com.huawei.imp.framework.jee.JEEConstant;
import com.huawei.imp.framework.model.privilege.web.form.ConsoleLoginForm;
import com.huawei.imp.framework.utils.CryptUtil;
import com.huawei.imp.framework.utils.CryptUtil.CipherType;

/**
 * <一句话功能/> <功能描述/>
 * 
 * @author ahli
 * @date 2009-8-14
 */
@Controller("ahli.framework.model.privilege.web.controller.ConsoleLoginController")
@RequestMapping("/framework/login.do")
public class ConsoleLoginController implements JEEConstant {

	/**
	 * 密钥
	 */
	private static final String DEFALUT_KEY = "C1ZZsBNd0JY/8Yx2nPEg2g==";

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String setForm(Model model) {
		model.addAttribute(FORM, ConsoleLoginForm.NULL);
		return PAGE_CONSOLE_MODEL + "privilege/login/login";
	}

	/**
	 * @param form
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String setForm(@ModelAttribute(FORM)
	ConsoleLoginForm form, Model model, HttpSession session) {
		final String name = FileConfigUtil
				.getStringTrim("configuration.framework.model.privilege.login.user");
		final String password = FileConfigUtil
				.getStringTrim("configuration.framework.model.privilege.login.password");

		if (CryptUtil.getInstance().decrypt(name, DEFALUT_KEY, CipherType.AES)
				.equals(form.getName())
				&& CryptUtil.getInstance().decrypt(password, DEFALUT_KEY,
						CipherType.AES).equals(form.getPassword())) {
			session
					.setAttribute(SESSION_FRAMEWORK_LOGIN_TOKEN, Long
							.valueOf(0));
		}

		return "redirect:/framework/index.do";
	}
	
	public static void main(String[] args) {
		System.out.println(CryptUtil.getInstance().encrypt("wapadmin", DEFALUT_KEY, CipherType.AES));
		System.out.println(CryptUtil.getInstance().encrypt("wapadmin&*()%", DEFALUT_KEY, CipherType.AES));
	}
}
