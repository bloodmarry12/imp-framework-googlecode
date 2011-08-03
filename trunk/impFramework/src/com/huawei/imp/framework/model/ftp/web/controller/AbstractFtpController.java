/*
 * 文件名：AbstractFtpController.java
 * 版权：  Copyright 2000-2009 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 描述：  抽象FTP模块配置模块页面Controller
 * 修改人：李谟毫  63800
 * 修改时间：2009-04-25 10:12:25
 * 修改内容：新增
 */

package com.huawei.imp.framework.model.ftp.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.huawei.imp.framework.common.web.controller.BaseControllerSupport;
import com.huawei.imp.framework.model.ftp.service.FtpService;

/**
 * 抽象FTP模块配置模块页面Controller
 * 继承自BaseController，ioc容器注入FtpService业务对象
 * @author 李谟毫  63800
 * @version IMPV100R001DA0 2009-04-25 10:12:25
 * @see com.huawei.imp.framework.common.web.controller.BaseControllerSupport
 * @see com.huawei.cms.common.ftp.service.FtpService
 * @since CMS IMPV100R001DA0
 */
public class AbstractFtpController implements BaseControllerSupport
{
	/**
	 * 注入ftp服务，如果出现异常则系统在启动时期抛出异常
	 */
	@Autowired
	@Qualifier("framework.ftpService")
	protected FtpService service;
}
