package com.huawei.imp.framework.cache;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * 缓存数据访问实现类
 * 提供基于缓存的数据访问方法的具体实现
 * @author ahli
 * @version IMPV100R001DA0, Jun 9, 2009 
 * @since CMS IMPV100R001DA0
 */
@Component("cacheDAO")
public class CacheDAOImpl implements CacheDAO
{

	/* (non-Javadoc)
	 * @see com.huawei.cms.utils.commcache.CacheDAO#getSession()
	 */
	@Override
	public CacheSession getSession()
	{
		return CacheSessionFactory.getCacheSession(true);
	}

	/* (non-Javadoc)
	 * @see com.huawei.cms.utils.commcache.CacheDAO#find(java.lang.Class)
	 */
	@Override
	public <T> List<T> find(Class<T> clazz)
	{
		return getSession().find(clazz);
	}

	/* (non-Javadoc)
	 * @see com.huawei.cms.utils.commcache.CacheDAO#load(java.lang.Class, java.io.Serializable)
	 */
	@Override
	public <T> T load(Class<T> clazz, Serializable id)
	{
		return getSession().load(clazz, id);
	}

	/* (non-Javadoc)
	 * @see com.huawei.cms.utils.commcache.CacheDAO#loadUpdateProxy(java.lang.Class, java.io.Serializable)
	 */
	@Override
	public <T> T loadUpdateProxy(Class<T> clazz, Serializable id)
	{
		return getSession().loadUpdateProxy(clazz, id);
	}

	/* (non-Javadoc)
	 * @see com.huawei.cms.utils.commcache.CacheDAO#remove(java.lang.Class, java.io.Serializable)
	 */
	@Override
	public void remove(Class<? extends Object> clazz, Serializable id)
	{
		getSession().remove(clazz, id);
	}

	/* (non-Javadoc)
	 * @see com.huawei.cms.utils.commcache.CacheDAO#removeAll(java.lang.Class<? extends java.lang.Object>[])
	 */
	@Override
	public void removeAll(Class<? extends Object>... clazz)
	{
		getSession().removeAll(clazz);
	}

	/* (non-Javadoc)
	 * @see com.huawei.cms.utils.commcache.CacheDAO#save(java.lang.Object, java.io.Serializable)
	 */
	@Override
	public <T> void save(T obj, Serializable id)
	{
		getSession().save(obj, id);
	}
}
