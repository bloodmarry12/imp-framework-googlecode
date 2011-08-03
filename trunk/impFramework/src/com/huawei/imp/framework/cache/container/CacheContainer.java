/*
 * FileName: CacheContainer.java
 * Author:   ahli
 * createDate: 2009-6-6
 * Description: 缓存容器
 * 
 */
package com.huawei.imp.framework.cache.container;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.huawei.imp.framework.cache.exception.CacheException;


/**
 * 缓存容器
 * @author ahli
 * @version IMPV100R001DA0, Jun 10, 2009 
 * @since CMS IMPV100R001DA0
 */
public class CacheContainer {
	
	/**
	 * 默认初始化容器容量值
	 */
	public static final int CAPACITY_DEFAULT_VAL = 2000;
	
	/**
	 * 缓存容器
	 */
	private static Map<String, Map<Serializable, Object>> globMap = new HashMap<String, Map<Serializable, Object>>(CAPACITY_DEFAULT_VAL);
	
	/**
	 * 私有构造函数
	 */
	private CacheContainer(){}
	
	/**
	 * 从缓存容器中读取对象
	 * @param <T>
	 * @param clazz
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T load(Class<T> clazz, Serializable id){
		final String key = clazz.getName();
		Map<Serializable, T> map = (Map<Serializable, T>)globMap.get(key);
		if(null == map){
//			throw new CacheException("no cached data with Class#" + clazz.getName());
			return null;
		}
		return map.get(id);
	}
	
	/**
	 * 将对象保存到缓存容器中
	 * @param <T>
	 * @param obj
	 * @param id
	 */
	public static <T> void save(T obj, Serializable id){
		synchronized (globMap) {
			if(null == obj){
				throw new CacheException("can't save null object into cache");
			}
			final String key = obj.getClass().getName();
			Map<Serializable, Object> map = globMap.get(key);
			if(null == map){
				map = new HashMap<Serializable, Object>();
				globMap.put(key, map);
			}
			map.put(id, obj);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> find(Class<T> clazz) {
		Map<Serializable, Object> map = globMap.get(clazz.getName());
		List<T> list = new LinkedList<T>();
		if(null != map){
			Iterator<Map.Entry<Serializable, Object>> it = map.entrySet().iterator();
			T t = null;
			
			while(it.hasNext()){
				t = (T)it.next().getValue();
				list.add(t);
			}
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> Map<Serializable, T> loadMap(Class<T> clazz){
		Map<Serializable, T> map = (Map<Serializable, T>)globMap.get(clazz.getName());
		return map;
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends Object> void saveMap(Class<T> clazz, Map<Serializable, T> map){
		globMap.put(clazz.getName(), (Map<Serializable, Object>)map);
	}
	
	/**
	 * 删除所有对象
	 * @param index
	 */
	public static void removeAll(Class<? extends Object> clazz){
		globMap.put(clazz.getName(), null);
	}
	
	/**
	 * 删除单个对象
	 * @param index
	 */
	public static void remove(Class<? extends Object> clazz, Serializable id){
		synchronized (globMap) {
			Map<Serializable, Object> map = globMap.get(clazz.getName());
			if(null == map){
				throw new CacheException("has't cache any object:[" + clazz.getName() + "]");
			}
			map.remove(id);
		}
	}
}
