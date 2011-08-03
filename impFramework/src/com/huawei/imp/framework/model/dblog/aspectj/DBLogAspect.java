/*
 * 
 */
package com.huawei.imp.framework.model.dblog.aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;


/**
 * 数据库级别日志AOP切面拦截
 * 〈功能详细描述〉
 * @author ahli
 * @version IMPV100R001DA0, Aug 28, 2009 
 * @see [相关类/方法]
 * @since CMS IMPV100R001DA0
 */
@Aspect
public class DBLogAspect 
{
	
//	/**
//	 * 日志对象
//	 */
//	private final static Logger log = Logger.getLogger(DBLogAspect.class);
		
	// 拦截所有cms包下所有的service子包
	@SuppressWarnings("unused")
	@Order(value = 1)
	@Pointcut("execution(public * com.huawei.imp..service..*(..))")
	private void service(){}
	

	// 拦截所有cms包下所有的dao子包
	@SuppressWarnings("unused")
	@Order(value = 2)
	@Pointcut("execution(public * com.huawei.imp..dao..*(..))")
	private void dao(){}

	
}