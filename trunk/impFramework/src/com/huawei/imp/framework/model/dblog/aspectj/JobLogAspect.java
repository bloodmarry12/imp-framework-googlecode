/*
 * 
 */
package com.huawei.imp.framework.model.dblog.aspectj;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

import com.huawei.imp.framework.logger.LogFactory;
import com.huawei.imp.framework.logger.Logger;
import com.huawei.imp.framework.utils.Constant;

/**
 * @author User
 *
 */
@Aspect
public class JobLogAspect
{

	private static final Logger log = LogFactory.getLogger(JobLogAspect.class);
	
	@SuppressWarnings("unused")
	@Order(value = 1)
	@Pointcut("execution(public * com.huawei.imp..service..(..))")
	private void service(){}

	@Around("service()")
	public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable
	{
        // 在代理对象上调用业务代理方法，并获取调用返回值
		Object ret = null;
		StringBuffer logMessageBuffer = null;
		try
		{
			if(log.isDebugEnabled()){
				logMessageBuffer = new StringBuffer();
				logMessageBuffer.append("auto log {");
				logMessageBuffer.append(Constant.LINE_SEPARATOR);
				logMessageBuffer.append(Constant.BLANK);
				logMessageBuffer.append("methodName:" + joinPoint.getSignature().toShortString());
				logMessageBuffer.append(Constant.LINE_SEPARATOR);
				logMessageBuffer.append(Constant.BLANK);
				logMessageBuffer.append("param:" + Arrays.toString(joinPoint.getArgs()));
			}
			// 执行代理方法
			ret = joinPoint.proceed();
			
			if(log.isDebugEnabled() && null != logMessageBuffer){
				logMessageBuffer.append(Constant.LINE_SEPARATOR);
				logMessageBuffer.append(Constant.BLANK);
				logMessageBuffer.append("result:");
				logMessageBuffer.append(ret);
				logMessageBuffer.append("}");
				log.debug(joinPoint.getSignature().toShortString());
			}
			
			return ret;
		}
		catch(Throwable ex)
		{
			if(log.isDebugEnabled() && null != logMessageBuffer){
				logMessageBuffer.append(Constant.LINE_SEPARATOR);
				logMessageBuffer.append(Constant.BLANK);
				logMessageBuffer.append("exception:");
				logMessageBuffer.append(ex.toString() + "#" + ex.getMessage());
				logMessageBuffer.append("}");
				log.debug(joinPoint.getSignature().toShortString());
			}
			throw ex;
		}
		
	}
}
