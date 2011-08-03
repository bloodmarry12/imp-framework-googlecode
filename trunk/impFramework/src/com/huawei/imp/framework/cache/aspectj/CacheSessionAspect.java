package com.huawei.imp.framework.cache.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.huawei.imp.framework.cache.CacheSession;
import com.huawei.imp.framework.cache.CacheSessionFactory;
import com.huawei.imp.framework.cache.aspectj.annotation.CacheSessionTrans;
import com.huawei.imp.framework.logger.LogFactory;
import com.huawei.imp.framework.logger.Logger;

/**
 * 缓存事务拦截器
 * @author ahli
 * @version IMPV100R001DA0, 2010-3-29
 * @since CMS IMPV100R001DA0
 */
@Aspect
public class CacheSessionAspect
{
	
	private static final ThreadLocal<Integer> curNumTL = new ThreadLocal<Integer>();
	
	private static final Logger log = LogFactory.getLogger(CacheSessionAspect.class);
	
	@Around("@annotation(cacheSessionTrans)")
	public Object invokeService(ProceedingJoinPoint joinPoint, 
			CacheSessionTrans cacheSessionTrans) throws Throwable
	{
		CacheSession cacheSession = CacheSessionFactory.getCacheSession(false);;
		
		try {
			Integer curNum = curNumTL.get();
			if(log.isDebugEnabled()){
				log.debug("invokeService start ->" + curNum);
			}
			// 第一个拦截
			if(null == curNum || 0 == curNum.intValue()){
				curNumTL.set(1);
				if(null != cacheSession){
					CacheSessionFactory.destoryCacheSession();
				}
				cacheSession = CacheSessionFactory.getCacheSession(true);
			}
			else
			{
				curNumTL.set(curNum.intValue() + 1);
			}
			return joinPoint.proceed();
		} 
		catch (RuntimeException e) 
		{
			if(log.isDebugEnabled())
			{
				log.debug("invokeService exception ->" + e.toString());
			}
			
			if(null != cacheSession){
				cacheSession.undo();
			}
			throw e;
		}
		finally
		{
			Integer curNum = curNumTL.get();
			if(log.isDebugEnabled())
			{
				log.debug("invokeService end ->" + curNum);
			}
			if(null == curNum|| curNum.intValue() == 0 || curNum.intValue() == 1)
			{
				if(null != cacheSession)
				{
					cacheSession.flush();
					CacheSessionFactory.destoryCacheSession();
					curNumTL.set(null);
				}
			}
			else
			{
				curNumTL.set(curNum.intValue() - 1);
			}
			
		}
	}
}
