package com.huawei.imp.framework.model.privilege.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Description:
 * 权限方法定义
 * @author ahli
 * Apr 16, 2009
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD) 
public @interface PrivilegeMethod {

	/**
	 * 权限rightCode
	 */
	public String value() ;
}
