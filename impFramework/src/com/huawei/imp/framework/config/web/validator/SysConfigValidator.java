/*
 * 文件名：SysConfigValidator.java
 * 版权：  Copyright 2000-2009 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 描述：  系统配制表单验证
 * 修改人: zhangj
 * 修改时间：Sep 1, 2009
 * 修改内容：新增
 */
package com.huawei.imp.framework.config.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.huawei.imp.framework.config.domain.SysConfig;
import com.huawei.imp.framework.utils.BeanHolder;

/**
 * 系统配制表单验证,对用户名，配制值，描述的空和长度进行了判断
 * @author zhangj
 * @version IMPV100R001DA0,Sep 1, 2009
 * @see com.huawei.imp.framework.config.web.validator
 * @since CMS IMPV100R001DA0
 */
public class SysConfigValidator implements Validator
{

	/**
	 * 配置参数名称
	 */
	private String name = BeanHolder.getMessage("common.sysConfig.name");
	/**
	 * 配置参数值
	 */
	private String value = BeanHolder.getMessage("common.sysConfig.value");
	/**
	 * 配置参数描述
	 */
	private String description = BeanHolder.getMessage("common.sysConfig.description");
	/**
	 * 参数名称长度
	 */
	private final static Integer name_length = 64;
	/**
	 * 参数值长度
	 */
	private final static Integer value_length = 128;
	/**
	 * 描述长度
	 */
	private final static Integer description_length = 128;
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean supports(Class arg0)
	{
		return SysConfig.class.equals(arg0);
	}

	@Override
	public void validate(Object arg0, Errors errors)
	{
		//检验是否是空值
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
				"common.um.exception.labelIsEmpty", new Object[]{name});
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "value",
				"common.um.exception.labelIsEmpty", new Object[]{value});
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description",
				"common.um.exception.labelIsEmpty", new Object[]{description});
		SysConfig sysConfig = (SysConfig) arg0;
		//长度的校验
		if(sysConfig.getName().length()>name_length)
		{
			errors.reject("common.sysConfig.length.exception", new Object[]{name,name_length}, null);
		}
		else if(sysConfig.getValue().length()>value_length)
		{
			errors.reject("common.sysConfig.length.exception", new Object[]{value,value_length}, null);
		}
		else if(sysConfig.getDescription().length()>description_length)
		{
			errors.reject("common.sysConfig.length.exception", new Object[]{description,description_length}, null);
		}
	}

}
