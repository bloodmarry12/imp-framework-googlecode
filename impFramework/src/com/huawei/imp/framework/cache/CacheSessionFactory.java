/*
 * FileName: CacheSessionFactory.java
 * Author:   ahli
 * createDate: Jun 10, 2009 
 * Description: 缓存会话工厂
 * 
 */
package com.huawei.imp.framework.cache;

import org.apache.log4j.Logger;

import com.huawei.imp.framework.cache.session.CacheSessionImpl;

/**
 * 会话工厂
 * 负责维护ThreadLocal中的缓存会话；通过该工厂方法获取当前线程中的CacheSession对象
 * @author ahli
 * @version IMPV100R001DA0, 
 * @since CMS IMPV100R001DA0
 */
public class CacheSessionFactory {
	
	/**
	 * 日志对象
	 */
	private static final Logger log = Logger.getLogger(CacheSessionFactory.class);

	/**
	 * 当前线程变量
	 */
	private static ThreadLocal<CacheSession> localCacheSession = new ThreadLocal<CacheSession>();

	/**
	 * 获取当前线程的缓存会话对象,如果当前线程中还没有缓存会话，则创建一个新的缓存会话并且返回
	 * 
	 * @return 当前缓存会话对象
	 */
	public synchronized static CacheSession getCacheSession(boolean flag)
	{
		if(log.isDebugEnabled()){
			log.debug("getCacheSession()");
		}
		
		if(flag){
			// 如果当前线程没有缓存会话对象，则创建一个新的缓存会话对象，并且关联到当前线程变量中
			if (null == localCacheSession.get())
			{
				if(log.isDebugEnabled()){
					log.debug("localCacheSession is null, create new CacheSession in localCacheSession.");
				}
				localCacheSession.set(createCacheSession());
			}
		}
		return localCacheSession.get();
	}

	/**
	 * 创建缓存会话对象
	 * 
	 * @return 缓存会话对象
	 */
	private static CacheSession createCacheSession()
	{
		return new CacheSessionImpl();
	}
	
	/**
	 * 销毁当前的session对象
	 */
	public static void destoryCacheSession(){
		CacheSession cs = localCacheSession.get();
		if(null != cs)
		{
			cs = null;
			localCacheSession.set(null);
		}
	}
}
