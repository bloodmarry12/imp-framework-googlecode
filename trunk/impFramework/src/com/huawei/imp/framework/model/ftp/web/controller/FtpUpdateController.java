/*
 * 文件名：FtpUpdateController.java
 * 版权：  Copyright 2000-2009 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 描述：  FTP模块配置修改页面Controller
 * 修改人：李谟毫  63800
 * 修改时间：2009-04-25 21:07:39
 * 修改内容：新增
 */

package com.huawei.imp.framework.model.ftp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.huawei.imp.framework.model.ftp.domain.FtpConfig;
import com.huawei.imp.framework.model.ftp.web.validator.FtpConfigValidator;

/**
 * FTP模块配置修改页面Controller
 * 该类负责初始化ftp配置管理修改页面数据，并且处理ftp配置管理修改表单提交业务逻辑
 * service通过父类注入，不需要做空值检查。
 * @author 李谟毫  63800
 * @version IMPV100R001DA0 2009-04-25 21:07:39
 * @see com.huawei.cms.common.ftp.web.controller.AbstractFtpController
 * @since CMS IMPV100R001DA0
 */
@Controller
@RequestMapping("/framework/model/ftp/update.do")
public class FtpUpdateController extends AbstractFtpController
{
	/**
	 * 修改页面地址
	 */
	public static final String PAGE_UPDATE = PAGE_CONSOLE_MODEL + "ftp/update";

	/**
	 * 处理通过HTTP的GET、HEAD方法提交的请求，主要用于页面加载时使用
	 * @param ftpAlias  ftp配置别名
	 * @param model   模型，主要用于设置会话信息
	 * @return  /common/console/ftp/update.jsp
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String setForm(@RequestParam("id")
	String ftpAlias, Model model)
	{
		FtpConfig config = service.load(ftpAlias);
		model.addAttribute(FORM, config);
		return PAGE_UPDATE;
	}

	/**
	 * 处理通过HTTP的POST方法提交的请求，主要用于更新时用
	 * @param form FTP配置BEAN，可以通过页面直接转换过来
	 * @param model 模型，主要用于设置会话信息
	 * @return   /common/console/ftp/update.jsp
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String submit(@ModelAttribute("form")
	FtpConfig form,BindingResult result, Model model)
	{
		// 表单验证
		new FtpConfigValidator().validate(form, result);
		if(result.hasErrors())
		{
			return PAGE_CONSOLE + "model/ftp/update";
		}
		service.update(form);
		// 创建默认的页面消息窗口
		model.addAttribute("url", "/framework/model/ftp/query.do");
		return PAGE_CONSOLE_SUCCESS;
	}
}
