/*
 * 文件名：SessionLoadInterceptor.java 版权： Copyright 2000-2001 Huawei Tech. Co. Ltd.
 * All Rights Reserved. 描述：〈描述〉 修改人：ahli 修改时间：Jun 7, 2009 跟踪单号： 修改单号： 修改内容：
 */

package com.huawei.imp.framework.cache.interceptor;

import java.io.Serializable;
import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import com.huawei.imp.framework.cache.CacheSessionFactory;
import com.huawei.imp.framework.cache.command.impl.CacheUpdateCommand;
import com.huawei.imp.framework.cache.session.CacheSessionImpl;

/**
 * 缓存代理对象操作拦截器
 * 拦截缓存代理对象的方法调用，如果是非set方法，则直接调用并且返回
 * 从被代理对象的的相关方法调用。如果是set方法，则创建一个
 * updatecommand的对象，并且添加到当前缓存会话中。
 * @author ahli
 * @version IMPV100R001DA0, Jun 7, 2009
 * @see net.sf.cglib.proxy.MethodInterceptor
 * @since CMS IMPV100R001DA0
 */

public class SessionLoadInterceptor implements MethodInterceptor
{
	/**
	 * get方法前缀
	 */
	public static final String METHOD_NAME_GET = "get";

	/**
	 * set方法前缀
	 */
	public static final String METHOD_NAME_SET = "set";

	/**
	 * 代理对象在缓存中的序列号
	 */
	private Serializable id = null;
	
	/**
	 * 代理对象
	 */
	private Object target = null;
	
	/**
	 * 构造函数，构造拦截器
	 * @param id        被代理对象在缓存中的序列号
	 * @param target    被代理对象
	 */
	public SessionLoadInterceptor(Serializable id, Object target){
		this.id = id;
		this.target = target;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see net.sf.cglib.proxy.MethodInterceptor#intercept(java.lang.Object,
	 *      java.lang.reflect.Method, java.lang.Object[],
	 *      net.sf.cglib.proxy.MethodProxy)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object intercept(Object object, Method method, Object[] args,
			MethodProxy methodProxy) throws Throwable
	{
		// 获取对象映射
		final String methodName = method.getName();
		
		// 如果是获取对象方法
		if(methodName.startsWith(METHOD_NAME_GET)){
			return methodProxy.invoke(target, args);
		}else if(methodName.startsWith(METHOD_NAME_SET)){
			
			// 参数不为null，
			if(args != null && args.length > 0){
				Object param = args[0];
				CacheSessionImpl cacheSessionImpl = (CacheSessionImpl)CacheSessionFactory.getCacheSession(true);
				
				CacheUpdateCommand command = new CacheUpdateCommand(target.getClass(), id, 
						method.getName().substring(3),  param);
				cacheSessionImpl.pushAndDoCommand(command);
			}
			return methodProxy.invokeSuper(object, args);
		}else{
			return methodProxy.invoke(target, args);
		}
	}
}
