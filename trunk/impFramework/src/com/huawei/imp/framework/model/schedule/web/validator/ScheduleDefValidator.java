/*
 * 文件名：ScheduleDefValidator.java
 * 版权：  Copyright 2000-2009 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 描述：  定时任务配置校验
 * 修改人: zhangj
 * 修改时间：Sep 1, 2009
 * 修改内容：新增
 */
package com.huawei.imp.framework.model.schedule.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.huawei.imp.framework.model.schedule.domain.ScheduleDef;
import com.huawei.imp.framework.utils.BeanHolder;

/**
 * 定时任务配置校验，对IOC容器中的beanName，对象方法，参数，服务器IP地址进行了写法和空值以及长度的判断
 * @author zhangj
 * @version IMPV100R001DA0,Sep 1, 2009
 * @see com.huawei.imp.framework.model.schedule.web.validator
 * @since CMS IMPV100R001DA0
 */
public class ScheduleDefValidator implements Validator
{
	/**
	 * 对应ioc容器中的对象名称
	 */
	private String beanName = BeanHolder.getMessage("common.timetask.task.beanName");
	
	/**
	 * 对象方法
	 */
	private String methodName = BeanHolder.getMessage("common.timetask.task.methodName");
	
//	/**
//	 * 服务器
//	 */
//	private String host = BeanHolder.getMessage("common.timetask.task.host");
	

	@SuppressWarnings("unchecked")
	@Override
	public boolean supports(Class arg0)
	{
		return ScheduleDef.class.equals(arg0);
	}

	@Override
	public void validate(Object arg0, Errors errors)
	{
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "beanName",
				"common.um.exception.labelIsEmpty", new Object[]{beanName});
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "methodName",
				"common.um.exception.labelIsEmpty", new Object[]{methodName});
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "parameter",
//				"common.um.exception.labelIsEmpty", new Object[]{parameter});
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "host",
//				"common.um.exception.labelIsEmpty", new Object[]{host});
		//验证ip，本来service中已经验证，但是是甩出异常，所以这里先验证
//		ScheduleDef scheduleDef = (ScheduleDef)arg0;
//		Pattern p_host = Pattern.compile("((?:(?:25[0-5]|2[0-4]\\d|[01]?\\d?\\d)\\.){3}(?:25[0-5]|2[0-4]\\d|[01]?\\d?\\d))");
//		Matcher m_host = p_host.matcher(String.valueOf(scheduleDef.getHost()));
//		if(!m_host.matches())
//		{
//			errors.reject("common.type.exception",new Object[]{host},null);
//		}
	}

}
