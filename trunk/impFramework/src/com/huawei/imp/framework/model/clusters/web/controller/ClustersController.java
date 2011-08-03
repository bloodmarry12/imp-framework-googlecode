/*
 * 文件名：TimeTaskController.java
 * 版权：  Copyright 2000-2009 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 描述：  定时任务方法控制器
 * 修改人：李谟毫   63800
 * 修改时间：2009-05-05 10:56:45
 * 修改内容：新增
 */

package com.huawei.imp.framework.model.clusters.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huawei.imp.framework.common.web.controller.BaseControllerSupport;
import com.huawei.imp.framework.model.clusters.service.ClustersService;

/**
 * 集群控制器
 * 集群后台控制器
 * @author ahli
 * @version IMPV100R001DA0, 2009-12-23
 * @see com.huawei.imp.framework.common.web.controller.BaseControllerSupport
 * @since CMS IMPV100R001DA0
 */
@Controller
public class ClustersController implements BaseControllerSupport
{
	/**
	 * 定时任务业务对象
	 */
	@Autowired
	@Qualifier("clustersService")
	private ClustersService clustersService;
	
	/**
	 * 
	 */
	@RequestMapping("/framework/model/clusters/list.do")
	public String list(Model model)
	{
		model.addAttribute("list", clustersService.queryForAllSlaveDevice());
		return PAGE_CONSOLE_MODEL + "clusters/list";
	}
//	
//	@RequestMapping("/framework/model/clusters/check.do")
//	public String check(@RequestParam("url") String url, Model model){
//		clustersService.checkSlaveDevice(url);
//		return list(model);
//	}
//	
//	@RequestMapping("/framework/model/clusters/delete.do")
//	public String delete(@RequestParam("url") String url, Model model){
//		clustersService.deleteSlaveDevice(url);
//		return list(model);
//	}
//	
//	@RequestMapping("/framework/model/clusters/add.do")
//	public String add(@RequestParam("url") String url, Model model){
//		SlaveDevice device = new SlaveDevice();
//		device.setUrl(url);
//		clustersService.addSlaveDevice(device);
//		return list(model);
//	}
//	
//	@RequestMapping("/framework/model/clusters/flush.do")
//	public String add(@RequestParam("url") String url,
//			@RequestParam("key") String key, Model model){
//		clustersService.flushSlaveDevice(url, key);
//		return list(model);
//	}
}
