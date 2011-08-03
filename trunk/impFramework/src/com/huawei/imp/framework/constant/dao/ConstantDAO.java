/**
 * 
 */
package com.huawei.imp.framework.constant.dao;

import java.util.List;
import java.util.Map;

import com.huawei.imp.framework.common.dao.IbatisSupportDAO;
import com.huawei.imp.framework.constant.domain.Constant;
import com.huawei.imp.framework.utils.PageResult;

/**
 * 数据常量访问类
 * @author ahli
 * @date 2009-8-16
 */
public interface ConstantDAO extends IbatisSupportDAO {
	
	/**
	 * 分页查询
	 * @param params
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	PageResult query(Map<String, Object> params, int pageNum, int pageSize);
	
	/**
	 * 存入常量
	 * @param c
	 */
	void insert(Constant c);
	
	/**
	 * 删除常量
	 * @param params
	 */
	void delete(Map<String,Object> params);
	
	/**
	 * 更新常量
	 * @param c
	 */
	void update(Constant c);
	
	/**
	 * 按MODELID查询
	 * @param params
	 * @return
	 */
	List<Constant> selectByModel(Map<String,Object> params);
}
