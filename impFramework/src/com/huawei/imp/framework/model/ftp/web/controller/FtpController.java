/*
 * 文件名：FtpController.java
 * 版权：  Copyright 2000-2009 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 描述：  FTP模块配置模块页面Controller
 * 修改人：李谟毫  63800
 * 修改时间：2009-04-25 16:54:46
 * 修改内容：新增
 */

package com.huawei.imp.framework.model.ftp.web.controller;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.huawei.imp.framework.common.web.util.JSONAjaxUtils;
import com.huawei.imp.framework.model.ftp.domain.FtpConfig;

/**
 * 定义一个WEB控制器，FTP功能模块配置管理controller
 * 提供非表单提交操作、非异步方式的页面控制方法实现。由基类负责从ioc容器中注入业务类
 * service，不需要做空值检查。
 * @author 李谟毫  63800
 * @version IMPV100R001DA0 2009-04-25 16:54:46
 * @see com.huawei.cms.common.ftp.web.controller.AbstractFtpController
 * @since CMS IMPV100R001DA0
 */
@Controller
public class FtpController extends AbstractFtpController
{
	/**
	 * Ftp配置列表页面
	 */
	public static final String PAGE_LIST = PAGE_CONSOLE_MODEL + "ftp/query";

	/**
	 * Ftp配置信息查看页面
	 */
	public static final String PAGE_VIEW = PAGE_CONSOLE_MODEL + "ftp/view";

	/**
	 * 查询所有Ftp配置信息列表，并返回JSP视图
	 * @param model   模型，主要用于设置会话信息
	 * @return        /console/ftp/query.do对应的/common/console/ftp/list.jsp
	 */
	@RequestMapping("/framework/model/ftp/query.do")
	public String query(Model model)
	{
		List<FtpConfig> list = service.findAll();
		model.addAttribute("list", list);
		return PAGE_LIST;
	}

	/**
	 * 查询所有Ftp配置信息列表，并返回JSP视图
	 * @param model   模型，主要用于设置会话信息
	 * @return        /console/ftp/query.do对应的/common/console/ftp/list.jsp
	 */
	@RequestMapping("/framework/model/ftp/ajax/query.do")
	public String queryForAjax(Model model)
	{
		final List<FtpConfig> list = service.findAll();
		JSONObject jobj = JSONAjaxUtils.content(new JSONAjaxUtils.ResContentSupport()
		{
			@Override
			public void warpResContent(JSONObject resContent)
			{
				JSONArray jsonArray = new JSONArray();
				if(null != list && !list.isEmpty()){
					for(FtpConfig ftp : list){
						JSONObject jsonFtp = new JSONObject();
						jsonFtp.put("ftpAlias", ftp.getFtpAlias());
						jsonFtp.put("ip", ftp.getIp());
						jsonFtp.put("port", ftp.getPort());
						jsonFtp.put("userName", ftp.getUserName());
						jsonFtp.put("paswd", ftp.getUserPaswd());
						jsonArray.add(jsonFtp);
					}
				}
				resContent.put("ftpList", jsonArray);
			}
		});
		
		model.addAttribute(JSONAjaxUtils.MODEL_ATTRIBUTE, jobj);
		return JSONAjaxUtils.RETURN_PAGE;
	}
	
	/**
	 * 查询所有Ftp配置信息，并返回JSP视图
	 * @param ftpAlias    ftp配置别名
	 * @param model       模型，主要用于设置会话信息
	 * @return            /console/ftp/view.do对应/common/console/ftp/view.jsp
	 */
	@RequestMapping("/framework/model/ftp/view.do")
	public String view(@RequestParam("name")
	String ftpAlias, Model model)
	{
		FtpConfig config = service.load(ftpAlias);
		model.addAttribute(FORM, config);
		return PAGE_VIEW;
	}
}
