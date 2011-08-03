/**
 * 
 */
package com.huawei.imp.framework.model.privilege.cacheModel.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.huawei.imp.framework.exception.BusinessException;
import com.huawei.imp.framework.model.privilege.cacheModel.ReadOnlyCacheModel;
import com.huawei.imp.framework.utils.SeriUtils;

/**
 * <p>
 * 
 * </p>
 * @see 
 * @author aohai.li
 * @version CMSV100R001DB0SP0, 2010-8-17
 * @since CMSV100R001DB0SP0
 */
@Component("PrivilegeCacheModel")
public class PrivilegeCacheModelImpl implements ReadOnlyCacheModel {

	/**
	 * 缓存映射容器
	 */
	private Map<Class<? extends Serializable>, Map<Serializable, Serializable>> cacheMap = new HashMap<Class<? extends Serializable>, Map<Serializable, Serializable>>();
	
	/**
	 * 加载缓存中的原始对象
	 * @param <T>      泛型
	 * @param clazz    类型
	 * @param id       ID
	 * @return         缓存中的对象引用
	 */
	@SuppressWarnings("unchecked")
	public <T extends Serializable> T loadOrign(Class<T> clazz, Serializable id){
		Map<Serializable, Serializable> objMap = cacheMap.get(clazz);
		return (T)objMap.get(id);
	}

	/* (non-Javadoc)
	 * @see org.framework2.cache.ReadCacheFacade#load(java.lang.Class, java.io.Serializable)
	 */
	@Override
	public <T extends Serializable> T load(Class<T> clazz, Serializable id) {
		T t = this.loadOrign(clazz, id);
		if(null != t){
			return SeriUtils.byteClone(t);
		}else{
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.model.privilege.cacheModel.ReadOnlyCacheModel#load(com.huawei.imp.framework.model.privilege.cacheModel.ReadOnlyCacheModel.QueryForObjectHandler)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends Serializable> T load(Class<T> clazz, QueryHandler<T> handler) {
		for(Class<? extends Serializable> cacheClazz : cacheMap.keySet()){
			if(clazz.isAssignableFrom(cacheClazz)){
				Map<Serializable, T> objMap = (Map<Serializable, T>)cacheMap.get(cacheClazz);
				Collection<T> collection = objMap.values();
				for(T serial : collection){
					if(serial != null && handler.filter(serial)){
						return SeriUtils.byteClone(serial); 
					}
				}
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.model.privilege.cacheModel.ReadOnlyCacheModel#query(com.huawei.imp.framework.model.privilege.cacheModel.ReadOnlyCacheModel.QueryForListHandler)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends Serializable> List<T> query(Class<T> clazz, QueryHandler<T> handler) {
		List<T> resultArrayList = new ArrayList<T>();
		for(Class<? extends Serializable> cacheClazz : cacheMap.keySet()){
			if(clazz.isAssignableFrom(cacheClazz)){
				Map<Serializable, T> objMap = (Map<Serializable, T>)cacheMap.get(cacheClazz);
				Collection<T> collection = objMap.values();
				for(T serial : collection){
					if(handler == null || (null != serial && handler.filter(serial))){
						resultArrayList.add(serial);
					}
				}
			}
		}
		// 返回深度克隆对象
		return SeriUtils.byteClone(resultArrayList);
	}
	
	/**
	 * 将对象保存到缓存中
	 * @param obj    被缓存对象
	 * @param id     id
	 * @exception NullPointerException 当obj参数为null的时候抛出.
	 */
	public <T extends Serializable> void save(T obj, Serializable id){
		if(null == obj){
			throw new NullPointerException("obj is null.");
		}
		Class<T> clazz = this.getClassType(obj);
		
		Map<Serializable, Serializable> objMap = cacheMap.get(clazz);
		// 如果没有获取到值，则根创建该类型的容器
		if(null == objMap){
			objMap = new HashMap<Serializable, Serializable>();
			cacheMap.put(clazz, objMap);
		}
		objMap.put(id, obj);
	}
	
	/**
	 * 从对象中获取类型
	 * @param obj    业务对象
	 * @return    对象类型
	 */
	@SuppressWarnings("unchecked")
	private <T extends Serializable> Class<T> getClassType(T obj){
		return (Class<T>)obj.getClass();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T extends Serializable> E executeWithoutClone(Class<T> clazz, ExecuteHandler<E, T> handler) {
		for(Class<? extends Serializable> cacheClazz : cacheMap.keySet()){
			if(clazz.isAssignableFrom(cacheClazz)){
				Map<Serializable, T> objMap = (Map<Serializable, T>)cacheMap.get(cacheClazz);
				return handler.execute(objMap);
			}
		}
		throw new BusinessException("no cached any object for type " + clazz + ".");
	}
}
