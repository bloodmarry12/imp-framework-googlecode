package com.huawei.imp.framework.config.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huawei.imp.framework.common.web.controller.BaseControllerSupport;
import com.huawei.imp.framework.config.service.SysConfigService;

/**
 * Description: 基于DB的系统配置控制器
 * 
 * @author ahli Apr 26, 2009
 */
@Controller
@RequestMapping("/framework/config/flush.do")
public class FlushDBConfigController implements BaseControllerSupport
{

//	/**
//	 * 日志对象
//	 */
//	private static final Logger log = Logger
//			.getLogger(FlushDBConfigController.class);

	/**
	 * 系统配置服务
	 */
	@Autowired
	protected SysConfigService service;
	
	/**
	 * 刷新系统DB缓存提交页面
	 */
	public static final String PAGE_FLUSH = "common/sysConfig/flushDBConfig";

//	/**
//	 * 刷新缓存
//	 * @param token
//	 *            token
//	 * @param model
//	 *            Model
//	 * @return
//	 */
//	@RequestMapping(method = RequestMethod.POST)
//	public String submit(@ModelAttribute(FORM)FlushCacheForm form, Model model)
//	{
////		log.debug("FlushDBConfigController(" + form.getToken() + ")");
////		final String localToken = ConfigFileUtil.getConfigCommonFile()
////				.getString(CMS4ConfigConstants.FILE_SYSCONFIG_FlushToken);
////		log.debug("FlushDBConfigController(" + localToken + ")");
////		// 比对口令
////		if (localToken.equals(form.getToken()))
////		{
//			service.flushConfigCache();
////		}
////		else
////		{
////			// 口令不一致，直接返回错误信息
//////			new Dialog("token error").setModel(model);
////		}
//		return PAGE_FLUSH;
//	}
//	
//	/**
//	 * 页面跳转到刷新缓存页面
//	 * @return
//	 */
//	@RequestMapping(method = RequestMethod.GET)
//	public String setForm(Model model)
//	{
//		//页面赋值
//		model.addAttribute(FORM, FlushCacheForm.NULL);
//		return PAGE_FLUSH;
//	}
}
