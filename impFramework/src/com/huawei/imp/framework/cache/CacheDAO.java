/*
 * 文件名：CacheDAO.java
 * 版权：  Copyright 2000-2009 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 描述：  缓存数据访问接口
 * 修改人：ahli  
 * 修改时间：Jun 9, 2009 
 * 修改内容：新增
 */
package com.huawei.imp.framework.cache;

import java.io.Serializable;
import java.util.List;

/**
 * 缓存数据访问接口
 * 定于缓存的数据访问操作
 * @author ahli
 * @version IMPV100R001DA0, Jun 9, 2009  
 * @since CMS IMPV100R001DA0
 */
public interface CacheDAO
{
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
	 * 缓存对象
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
	 * 获取当前操作的session，如果session不存在，则返回一个新的session
	 * @return
	 */
	CacheSession getSession();
}
