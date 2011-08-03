/*
 * FileName: CacheSession.java
 * Author:   ahli
 * createDate: 2009-6-6
 * Description: 缓存会话接口
 * 
 */
package com.huawei.imp.framework.cache;

import java.io.Serializable;
import java.util.List;

import com.huawei.imp.framework.cache.command.CacheCommand;


/**
 *  缓存会话
 *  缓存会话提供了基于缓存的数据存储策略接口
 * @author ahli
 * @version IMPV100R001DA0, Jun 10, 2009 
 * @since CMS IMPV100R001DA0
 */
public interface CacheSession {

	/**
	 * 从缓存中获取动态代理对象，通过该对象对代理数据进行实时的update操作。
	 * 该操作支持undo会话操作回滚；
	 * @param <T>      泛型
	 * @param clazz    代理对象类型
	 * @param id       代理对象流水号
	 * @return    动态代理对象
	 */
	<T> T loadUpdateProxy(Class<T> clazz, Serializable id);
	
	/**
	 * 从缓存中获取对象
	 * @param <T>      泛型
	 * @param clazz    对象类型
	 * @param id      对象流水号
	 * @return    对象
	 */
	<T> T load(Class<T> clazz, Serializable id);
	
	/**
	 * 保存缓存对象
	 * @param <T> 	 被缓存类泛型
	 * @param obj    被缓存对象
	 * @param id     缓存的ID
	 */
	<T> void save(T obj, Serializable id);
	
	/**
	 * 从缓存中获取一个类型的所有对象列表
	 * @param <T>    被缓存类泛型
	 * @param clazz  缓存对象类型
	 * @return       缓存对象列表
	 */
	<T> List<T> find(Class<T> clazz);
	
	/**
	 * 从缓存中释放指定类型的所有缓存对象
	 * @param clazz    要释放的对象类型
	 */
	void removeAll(Class<? extends Object>... clazz);
	
	/**
	 * 从缓存中删除指定类型的对象
	 * @param clazz    缓存对象类型
	 * @param id       缓存对象ID
	 */
	void remove(Class<? extends Object> clazz, Serializable id);
	
	/**
	 * 回滚当前缓存会话的操作
	 */
	void undo();
	
	/**
	 * 刷新，即删除命令列表。该操作执行后，当前线程中之前的所有命令将无法回滚；
	 */
	void flush();
	
	/**
	 * 向当前缓存会话中添加缓存操作命令
	 * @param command    操作命令对象
	 */
	void addCacheCommand(CacheCommand command);
}
