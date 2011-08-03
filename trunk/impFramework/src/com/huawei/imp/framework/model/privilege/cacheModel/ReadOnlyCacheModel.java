package com.huawei.imp.framework.model.privilege.cacheModel;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 公共缓存模型2
 * </p>
 * @see 
 * @author aohai.li
 * @version CMSV100R001DB0SP04, 2010-8-17
 * @since CMSV100R001DB0SP04
 */
public interface ReadOnlyCacheModel
{
	/**
	 * 加载缓存的对象
	 * @param <T>      缓存的泛型
	 * @param clazz    类型
	 * @param id       关键字
	 * @return    返回的业务对象
	 */
	<T extends Serializable> T load(Class<T> clazz, Serializable id);
	
	/**
	 * 加载对象
	 * @param <T>
	 * @param hadler
	 * @return
	 */
	<T extends Serializable> T load(Class<T> clazz, QueryHandler<T> hadler);
	
	/**
	 * 查询数据集合
	 * @param <T>
	 * @param handler
	 * @return
	 */
	<T extends Serializable> List<T> query(Class<T> clazz, QueryHandler<T> handler);
	
	/**
	 * 直接在原始对象中执行，不进行克隆
	 * @param <E>
	 * @param <T>
	 * @param handler
	 * @return
	 */
	<E, T extends Serializable> E executeWithoutClone(Class<T> clazz, ExecuteHandler<E, T> handler);
	
	/**
	 * <p>
	 * 查询条件
	 * </p>
	 * @see 
	 * @author aohai.li
	 * @version CMSV100R001DB0SP0, 2010-8-17
	 * @since CMSV100R001DB0SP0
	 */
	interface QueryHandler<T extends Serializable>{
		/**
		 * 条件过滤
		 * @param list
		 * @return
		 */
		boolean filter(T obj);
	}
	
	interface ExecuteHandler<E, T extends Serializable>{
		
		E execute(Map<Serializable, T> container);
	}
}