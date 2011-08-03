/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.huawei.imp.framework.model.privilege.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.huawei.imp.framework.jee.session.ServletSessionUtil;
import com.huawei.imp.framework.model.privilege.annotation.PrivilegeMethod;
import com.huawei.imp.framework.model.privilege.exception.PrivilegeException;
import com.huawei.imp.framework.model.privilege.service.PrivilegeService;

/**
 * Description:
 * 用户权限拦截器
 * @author ahli
 * Apr 16, 2009
 * 
 */
@Aspect
public class PrivilegeInterception {
	
	@Autowired
	@Qualifier("privilegeService")
	private PrivilegeService service;
	
	@Before("@annotation(rightMethod)")
	public void beforeDoService(JoinPoint point, PrivilegeMethod rightMethod) throws Throwable
	{
		//拦截相关方法，如果没有足够的权限，则抛出权限异常
		final long accountID = ServletSessionUtil.getLoginID();
		if(!service.validatePersission(accountID, rightMethod.value())){
			throw new PrivilegeException(PrivilegeException.EXCEPTION_NO_PERMISSIONS);
		}
	}
}