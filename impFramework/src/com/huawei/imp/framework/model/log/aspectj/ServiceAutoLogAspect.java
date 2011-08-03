/*
 * 
 */
package com.huawei.imp.framework.model.log.aspectj;

import java.util.Arrays;
import java.util.Collection;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

import com.huawei.imp.framework.exception.BusinessException;
import com.huawei.imp.framework.logger.LogFactory;
import com.huawei.imp.framework.logger.Logger;
import com.huawei.imp.framework.utils.Constant;

/**
 * 业务层方法拦截器
 * @author ahli
 * @version IMPV100R001DA0, Sep 22, 2009 
 * @since CMS IMPV100R001DA0
 */
@SuppressWarnings("unchecked")
@Aspect
public class ServiceAutoLogAspect
{

	private static final Logger log = LogFactory.getLogger(ServiceAutoLogAspect.class);
	
	private static final Class PRIMITIVE_TYPE_INT = (new int[0]).getClass();
	private static final Class PRIMITIVE_TYPE_LONG = (new long[0]).getClass();
	private static final Class PRIMITIVE_TYPE_BOOLEAN = (new boolean[0]).getClass();
	private static final Class PRIMITIVE_TYPE_CHAR = (new char[0]).getClass();
	private static final Class PRIMITIVE_TYPE_BYTE = (new byte[0]).getClass();
	private static final Class PRIMITIVE_TYPE_DOUBLE = (new double[0]).getClass();
	private static final Class PRIMITIVE_TYPE_SHORT = (new short[0]).getClass();
	private static final Class PRIMITIVE_TYPE_FLOAT = (new float[0]).getClass();
	
	/**
	 * 业务服务
	 */
	@SuppressWarnings("unused")
	@Order(value = 1)
	@Pointcut("execution(public * com.huawei.appstore..service..*(..))")
	private void service(){}
	
	@SuppressWarnings("unused")
	@Order(value = 2)
	@Pointcut("execution(public * com.huawei.framework.model..service..*(..))")
	private void modelService(){}

	/**
	 * 拦截并且打印业务层方法日志
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	
	@Around("service() || modelService()")
	public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable
	{
		// 在代理对象上调用业务代理方法，并获取调用返回值
		Object ret = null;
		StringBuilder logMessageBuilder = null;
		
		// 获取拦截到的对象日志对象
		final Logger targetLogger = LogFactory.getLogger(joinPoint.getTarget().getClass());
		
		try
		{
			if (targetLogger.isDebugEnabled())
			{
				logMessageBuilder = new StringBuilder();
				logMessageBuilder.append(Constant.LINE_SEPARATOR);
				logMessageBuilder.append("Service layer Auto DEBUG Log {");
				logMessageBuilder.append(Constant.LINE_SEPARATOR);
				logMessageBuilder.append(Constant.BLANK);
				logMessageBuilder.append("className:");
				logMessageBuilder.append(joinPoint.getSignature().getDeclaringType());
				logMessageBuilder.append(".");
				logMessageBuilder.append(joinPoint.getSignature().toShortString());
				logMessageBuilder.append("(..)");
				logMessageBuilder.append(Constant.LINE_SEPARATOR);
				Object[] args = joinPoint.getArgs();
				// 打印参数
				appendParamters(logMessageBuilder, args);
			}
			// 执行代理方法
			ret = joinPoint.proceed();

			if (targetLogger.isDebugEnabled() && null != logMessageBuilder)
			{
				logMessageBuilder.append(Constant.BLANK);
				logMessageBuilder.append("result:");
				logMessageBuilder.append(ret);
				logMessageBuilder.append(Constant.LINE_SEPARATOR);
				logMessageBuilder.append("}");
				logMessageBuilder.append(Constant.LINE_SEPARATOR);
			}
			return ret;
		}
		catch (BusinessException e)
		{
			
			// 捕获到业务异常的情况下，判断debug级别
			if (targetLogger.isDebugEnabled() && null != logMessageBuilder)
			{
				logMessageBuilder.append(Constant.LINE_SEPARATOR);
				logMessageBuilder.append(Constant.BLANK);
				logMessageBuilder.append(Constant.BLANK);
				logMessageBuilder.append("exception:");
				logMessageBuilder.append(Constant.LINE_SEPARATOR);
				logMessageBuilder.append(Constant.BLANK);
				logMessageBuilder.append(Constant.BLANK);
				logMessageBuilder.append(e.toString());
				logMessageBuilder.append(Constant.LINE_SEPARATOR);
				logMessageBuilder.append("}");
				logMessageBuilder.append(Constant.LINE_SEPARATOR);
			}
			
			throw e;
		}
		catch (Throwable ex)
		{
			
			// 如果捕获到其他异常，则全部答应异常信息
			if(null == logMessageBuilder){
				logMessageBuilder = new StringBuilder(200);
				logMessageBuilder = new StringBuilder();
				logMessageBuilder.append(Constant.LINE_SEPARATOR);
				logMessageBuilder.append("Service layer Auto EXCEPTION Log {");
				logMessageBuilder.append(Constant.LINE_SEPARATOR);
				logMessageBuilder.append(Constant.BLANK);
				logMessageBuilder.append("className:");
				logMessageBuilder.append(joinPoint.getSignature().getDeclaringType());
				logMessageBuilder.append(".");
				logMessageBuilder.append(joinPoint.getSignature().toShortString());
				logMessageBuilder.append("(..)");
				logMessageBuilder.append(Constant.LINE_SEPARATOR);
				Object[] args = joinPoint.getArgs();
				// 打印参数
				appendParamters(logMessageBuilder, args);
			}

			// 打印异常信息
			logMessageBuilder.append(Constant.LINE_SEPARATOR);
			logMessageBuilder.append(Constant.BLANK);
			logMessageBuilder.append(Constant.BLANK);
			logMessageBuilder.append("exception:");
			logMessageBuilder.append(Constant.LINE_SEPARATOR);
			logMessageBuilder.append(Constant.BLANK);
			logMessageBuilder.append(Constant.BLANK);
			logMessageBuilder.append(ex.toString());
			logMessageBuilder.append(Constant.LINE_SEPARATOR);
			logMessageBuilder.append("}");
			logMessageBuilder.append(Constant.LINE_SEPARATOR);
			
			// 2010-08-25 ahli 当抛出非业务异常的时候，打印错误信息。
			if(log.isErrorEnabled()){
				log.error(logMessageBuilder.toString());
			}
			throw ex;
		}finally{
			if(null != logMessageBuilder){
				targetLogger.debug(logMessageBuilder.toString());
			}
		}
	}

	/**
	 * @param logMessageBuilder
	 * @param args
	 */
	private void appendParamters(StringBuilder logMessageBuilder, Object[] args)
	{
		if(null == logMessageBuilder || args == null){
			return;
		}
		
		try{
			if (null != args)
			{
				for (int i = 0; i < args.length; i++)
				{
					logMessageBuilder.append(Constant.BLANK);
					logMessageBuilder.append(Constant.BLANK);
					logMessageBuilder.append("param_");
					logMessageBuilder.append(i);
					if (null == args[i])
					{
						logMessageBuilder.append(" is ");
						logMessageBuilder.append("null");
					}
					else
					{
						logMessageBuilder.append(":");
						if (args[i].getClass().isArray())
						{
							if(PRIMITIVE_TYPE_INT.equals(args[i].getClass()))
							{
								logMessageBuilder.append(Arrays.toString((int[]) args[i]));
							}
							else if(PRIMITIVE_TYPE_LONG.equals(args[i].getClass()))
							{
								logMessageBuilder.append(Arrays.toString((long[]) args[i]));
							}
							else if(PRIMITIVE_TYPE_BOOLEAN.equals(args[i].getClass()))
							{
								logMessageBuilder.append(Arrays.toString((boolean[]) args[i]));
							}
							else if(PRIMITIVE_TYPE_CHAR.equals(args[i].getClass()))
							{
								logMessageBuilder.append(Arrays.toString((char[]) args[i]));
							}
							else if(PRIMITIVE_TYPE_BYTE.equals(args[i].getClass()))
							{
								logMessageBuilder.append(Arrays.toString((byte[]) args[i]));
							}
							else if(PRIMITIVE_TYPE_DOUBLE.equals(args[i].getClass()))
							{
								logMessageBuilder.append(Arrays.toString((double[]) args[i]));
							}
							else if(PRIMITIVE_TYPE_SHORT.equals(args[i].getClass()))
							{
								logMessageBuilder.append(Arrays.toString((short[]) args[i]));
							}
							else if(PRIMITIVE_TYPE_FLOAT.equals(args[i].getClass()))
							{
								logMessageBuilder.append(Arrays.toString((float[]) args[i]));
							}
							else
							{
								// 如果返回结果过大，则打印数组信息，不打印数组对象内容
								int objsNum = ((Object[])args[i]).length;
								if(objsNum > 100){
									logMessageBuilder.append("Arrays of " + args[i].getClass() + " size: " + objsNum + ".");
								}else{
									logMessageBuilder.append(Arrays.toString((Object[]) args[i]));
								}
							}
						}
						else
						{
							// 如果返回结果过大，则打印数组信息，不打印数组对象内容
							if(args[i] instanceof Collection){
								Collection col = (Collection)args[i];
								logMessageBuilder.append("Collection of " + args[i].getClass() + " size: " + col.size() + ".");
							}else{
								logMessageBuilder.append(args[i].toString());
							}
						}
					}
					logMessageBuilder.append(Constant.LINE_SEPARATOR);
				}
			}
		}catch(Exception e){
			log.error(e.toString());
			logMessageBuilder.append("warp parameters error.");
		}
	}
}
