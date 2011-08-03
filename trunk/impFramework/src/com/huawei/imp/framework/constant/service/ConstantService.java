/**
 * 
 */
package com.huawei.imp.framework.constant.service;

import java.util.List;
import java.util.Map;

import com.huawei.imp.framework.constant.domain.Constant;
import com.huawei.imp.framework.utils.PageResult;

/**
 * <一句话功能/>
 * <功能描述/>
 * @author ahli
 * @date 2009-8-16
 */
public interface ConstantService {

	/**
	 * 分页查询
	 * @param params
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	PageResult query(Map<String, Object> params, int pageNum, int pageSize);
	
	/**
	 * 保存常量
	 * @param c
	 */
	void save(Constant c);
	
	/**
	 * 删除常量
	 * @param params
	 */
	void remove(Map<String,Object> params);
	
	/**
	 * 更新常量
	 * @param c
	 */
	void update(Constant c);
	
	/**
	 * 查询
	 * @param params
	 * @return
	 */
	List<Constant> queryConstants(Map<String, Object> params);
}
