package com.huawei.imp.framework.common.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.huawei.imp.framework.utils.PageResult;


/**
 * 公共数据访问接口
 * 所有的DAO接口都要继承自该接口。
 * @author ahli
 * @date 2009-8-15
 */
public interface IbatisSupportDAO {
	

	/**
	 * 
	 * @param <T>
	 * @param clazz
	 * @param id
	 * @return
	 */
	<T> T select(Class<T> clazz, Object parameter);
	
	/**
	 * @param obj
	 * @return
	 */
	void update(Object obj);
	
	/**
	 * @param clazz
	 * @param parameter
	 * @return
	 */
	void delete(Class<? extends Object> clazz, Object parameter);
	
	/**
	 * 
	 * @param obj
	 */
	Object insert(Object obj);
	
	/**
	 * @param <T>
	 * @param clazz
	 * @return
	 */
	<T> List<T> selectAll(Class<T> clazz);
	
	/**
	 * @param <T>
	 * @param clazz
	 * @return
	 */
	<T> Long selectSequence(Class<T> clazz);

	/**
	 * 批量插入 
	 * @param <T>         泛型对象
	 * @param sqlLabel    插入语句
	 * @param col         参数集合
	 */
	<T> void batchInsert(String sqlLabel, Collection<T> col);
	
	/**
	 * 批量更新
	 * @param <T>         泛型对象
	 * @param sqlLabel    插入语句
	 * @param col         参数集合
	 */
	<T> void batchUpdate(String sqlLabel, Collection<T> col);
	
	/**
	 * 批量删除
	 * @param <T>         泛型对象
	 * @param sqlLabel    插入语句
	 * @param col         参数集合
	 */
	<T> void batchDelete(String sqlLabel, Collection<T> col);
	
	/**
     * 根据查询参数从数据库中查询出对象集合
     * @param params  查询参数
     * @param clazz   类型
     * @return   查询出的对象的集合
     */
	List<? extends Object> queryForList(Map<String, Object> params,Class<? extends Object> clazz);
	
	/**
	 * 查询版权分页
	 * @param params
	 * @param pageSize
	 * @param pageNum
	 * @param clazz
	 * @return
	 */
	PageResult queryForPageResult(Map<String, Object> params, int pageSize,
			int pageNum,Class<? extends Object> clazz);
	
//	/**
//	 * 获取查询结果集合
//	 * @param params         参数
//	 * @param sql            查询类型
//	 * @param startRow       开始行数 
//	 * @param fetchSize      获取的结果集行数
//	 * @return
//	 */
//	List<? extends Object> queryForList(Map<String, Object> params, String sql, int startRow, int fetchSize);
}
