/*
 * 文件名：FtpConfigValidator.java
 * 版权：  Copyright 2000-2009 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 描述：  ftp配置表单校验
 * 修改人: zhangj
 * 修改时间：Sep 1, 2009
 * 修改内容：新增
 */
package com.huawei.imp.framework.model.ftp.web.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.huawei.imp.framework.model.ftp.domain.FtpConfig;
import com.huawei.imp.framework.utils.BeanHolder;

/**
 * ftp配置表单输入的校验，主要校验了用户名和密码的空值，以及端口号和IP地址的输入验证
 * @author zhangj
 * @version IMPV100R001DA0,Sep 1, 2009
 * @see com.huawei.imp.framework.model.ftp.web.validator
 * @since CMS IMPV100R001DA0
 */
public class FtpConfigValidator implements Validator
{
	/**
	 * 用户名
	 */
	private String userName = BeanHolder.getMessage("common.ftp.label.user");
	/**
	 * 密码
	 */
	private String userPaswd = BeanHolder.getMessage("common.ftp.label.psw");
	/**
	 * 端口
	 */
	private String port = BeanHolder.getMessage("common.ftp.label.port");
	/**
	 * IP地址
	 */
	private String ip = BeanHolder.getMessage("common.ftp.label.ip");
	/**
	 * 用户名长度
	 */
	private Integer userName_length = 32;
	/**
	 * 密码长度
	 */
	private Integer userPaswd_length = 16;
	@SuppressWarnings("unchecked")
	@Override
	public boolean supports(Class arg0)
	{
		return FtpConfig.class.equals(arg0);
	}

	@Override
	public void validate(Object arg0, Errors errors)
	{
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName",
				"common.um.exception.labelIsEmpty", new Object[]{userName});
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userPaswd",
				"common.um.exception.labelIsEmpty", new Object[]{userPaswd});
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ip",
				"common.um.exception.labelIsEmpty", new Object[]{ip});
		FtpConfig ftpConfig = (FtpConfig) arg0;
		if(ftpConfig.getUserName().length()>userName_length)
		{
			errors.reject("common.sysConfig.length.exception",new Object[]{userName,userName_length},null);
		}
		Pattern p_port = Pattern.compile("[1-9]*");
        Matcher m_port = p_port.matcher(String.valueOf(ftpConfig.getPort()));
        if(!m_port.matches())
        {
        	errors.reject("common.type.exception",new Object[]{port},null);
        }
        //
        Pattern ip_port = Pattern.compile("((?:(?:25[0-5]|2[0-4]\\d|[01]?\\d?\\d)\\.){3}(?:25[0-5]|2[0-4]\\d|[01]?\\d?\\d))");
        Matcher im_port = ip_port.matcher(String.valueOf(ftpConfig.getIp()));
        if(!im_port.matches())
        {
        	errors.reject("common.type.exception",new Object[]{ip},null);
        }
        if(ftpConfig.getUserPaswd().length()>userPaswd_length)
        {
        	errors.reject("common.type.exception",new Object[]{userPaswd,userPaswd_length},null);
        }
	}

}
