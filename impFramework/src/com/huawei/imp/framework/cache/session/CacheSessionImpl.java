package com.huawei.imp.framework.cache.session;

import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import net.sf.cglib.proxy.Enhancer;

import org.apache.log4j.Logger;

import com.huawei.imp.framework.cache.CacheSession;
import com.huawei.imp.framework.cache.command.CacheCommand;
import com.huawei.imp.framework.cache.command.CacheRemoveAllCommand;
import com.huawei.imp.framework.cache.command.impl.CacheRemoveCommand;
import com.huawei.imp.framework.cache.command.impl.CacheSaveCommand;
import com.huawei.imp.framework.cache.container.CacheContainer;
import com.huawei.imp.framework.cache.exception.CacheException;
import com.huawei.imp.framework.cache.interceptor.SessionLoadInterceptor;

/**
 * 缓存会话实现类
 * 缓存会话提供了基于缓存的数据存储策略接口具体实现
 * @author ahli
 * @version IMPV100R001DA0, Jun 10, 2009 
 * @see com.huawei.cms.utils.commcache.CacheSession
 * @since CMS IMPV100R001DA0
 */
public class CacheSessionImpl implements CacheSession
{

	/**
	 * 缓存会话命令堆栈
	 */
	private Deque<CacheCommand> commands = new LinkedList<CacheCommand>();

	/**
	 * 日志对象
	 */
	private static final Logger log = Logger.getLogger(CacheSessionImpl.class);

	/* (non-Javadoc)
	 * @see ahli.ahcacheModel.CacheSession#load(java.lang.Class, java.io.Serializable)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T loadUpdateProxy(Class<T> clazz, Serializable id)
	{
		Enhancer enhancer = new Enhancer();

		//从缓存中获取缓存的对象
		T t = CacheContainer.load(clazz, id);

		//如果获取的对象不存在，则抛出异常；
		if (null == t)
		{
			throw new CacheException("no data with ID " + clazz.getName() + "#"
					+ id);
		}

		//创建拦截器
		SessionLoadInterceptor interceptor = new SessionLoadInterceptor(id, t);
		enhancer.setSuperclass(clazz);
		enhancer.setCallback(interceptor);

		//创建代理对象
		T proxy = (T) enhancer.create();
		log.debug("loadUpdateProxy() :return Dyproxy " + proxy);
		return proxy;
	}

	@Override
	public <T> T load(Class<T> clazz, Serializable id)
	{
		log.debug("load():[class='" + clazz + "',id='" + id + "']");
		return CacheContainer.load(clazz, id);
	}

	/* (non-Javadoc)
	 * @see ahli.ahcacheModel.CacheSession#save(java.lang.Object, java.io.Serializable)
	 */
	@Override
	public <T> void save(T obj, Serializable id)
	{
		log.debug("save():[obj='" + obj + "',id='" + id + "']");
		CacheCommand command = new CacheSaveCommand(obj, id);
		pushAndDoCommand(command);
	}

	/* (non-Javadoc)
	 * @see ahli.ahcacheModel.CacheSession#find(java.io.Serializable, java.lang.Class)
	 */
	@Override
	public <T> List<T> find(Class<T> clazz)
	{
		log.debug("find():[clazz='" + clazz + "']");
		return CacheContainer.find(clazz);
	}

	/* (non-Javadoc)
	 * @see ahli.ahcacheModel.CacheSession#remove(java.lang.Class, java.io.Serializable)
	 */
	@Override
	public void remove(Class<? extends Object> clazz, Serializable id)
	{
		log.debug("remove():[clazz='" + clazz + "',id='" + id + "']");
		CacheCommand command = new CacheRemoveCommand(clazz, id);
		pushAndDoCommand(command);
	}

	/* (non-Javadoc)
	 * @see ahli.ahcacheModel.CacheSession#removeAll(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void removeAll(Class<? extends Object>... clazzes)
	{
		for (Class<? extends Object> clazz : clazzes)
		{
			CacheCommand command = new CacheRemoveAllCommand(clazz);
			pushAndDoCommand(command);
		}
	}

	/**
	 * @param command
	 */
	public void pushAndDoCommand(CacheCommand command)
	{
		log.debug("pushAndDoCommand():[command='" + command + "']");
		commands.push(command);
		command.doCommand();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ahli.ahcacheModel.CacheSession#undo()
	 */
	@Override
	public void undo()
	{
		log.debug("undo()");

		// 回退操作就是将缓存会话中的命令的回退方法依次执行。
		while (!commands.isEmpty())
		{
			commands.pop().undoCommand();
		}
	}

	/* (non-Javadoc)
	 * @see com.huawei.cms.utils.commcache.CacheSession#flush()
	 */
	@Override
	public void flush()
	{
		log.debug("flush()");

		//清空当前命令列表
		commands.clear();
	}

	/* (non-Javadoc)
	 * @see com.huawei.cms.utils.commcache.CacheSession#addCacheCommand(com.huawei.cms.utils.commcache.command.CacheCommand)
	 */
	@Override
	public void addCacheCommand(CacheCommand command)
	{
		log.debug("addCacheCommand():[command='" + command + "']");
		pushAndDoCommand(command);
	}
}
