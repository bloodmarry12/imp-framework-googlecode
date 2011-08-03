/*
 * description: 
 * date:        下午08:56:06
 * author:      ahli
 */
package com.huawei.imp.framework.console.script.dao;

import java.io.Serializable;
import java.util.Map;

import com.huawei.imp.framework.utils.PageResult;

/**
 * <p><strong>数据访问层接口</strong></p>
 * <p>定义了抽象的DAO接口</p>
 * @see
 * @version v1.0
 * @since v2.0
 * @author ahli (edit in 2010-5-29)
 */
public interface DaoSupport {

	/**
	 * 持久化业务实体
	 * @param <T>       业务类型
	 * @param entity    业务对象
	 */
	<T> void save(T entity);
	
	/**
	 * 加载持久化对象,如果对象不存在，则抛出异常
	 * @param <T>      业务对象类型
	 * @param clazz    业务对象类型
	 * @param id       ID
	 * @return
	 * @throws BusinessException 数据库中不存在该数据
	 */
	<T> T load(Class<T> clazz, Serializable id);
	
	/**
	 * 加载持久化对象,如果对象不存在，则返回null
	 * @param <T>      业务对象类型
	 * @param clazz    业务对象类型
	 * @param id       ID
	 * @return
	 */
	<T> T get(Class<T> clazz, Serializable id);
	
	/**
	 * 更新业务对象
	 * @param <T>    业务对象类型
	 * @param t      业务对象
	 * @return       返回本次操作影响的记录数
	 */
	<T> int update(T t);
	
	/**
	 * 删除业务对象
	 * @param <T>    业务对象类型
	 * @param t      业务对象
	 * @return       返回本次删除操作影响的记录数
	 */
	<T> int remove(T t);
	
	/**
	 * 查询版权分页
	 * @param params      查询参数，Map类型；
	 * @param pageSize    每页显示记录数
	 * @param pageNum     当前获取的页码
	 * @param clazz       类型
	 * @return            分页查询结果
	 */
	<T> PageResult queryForPageResult(Map<String, Object> params, int pageSize,
			int pageNum,Class<T> clazz);
	
	/**
	 * 查询指定行数范围的记录
	 * @param params       查询参数，Map类型；
	 * @param startRow     查询的开始行数
	 * @param fetchSize    获取的记录数量
	 * @param clazz        类型
	 * @return             分页查询结果
	 */
	<T> PageResult queryForList(Map<String, Object> params, int startRow,
			int fetchSize, Class<? extends Object> clazz);
}
