/**
 * 
 */
package com.huawei.imp.framework.constant.dao;

import java.util.List;
import java.util.Map;

import com.huawei.imp.framework.common.dao.IbatisSupportDAO;
import com.huawei.imp.framework.constant.domain.ConstantType;

/**
 * 数据常量类型访问类
 * @author liuguoping
 * @date 2009-8-16
 */
public interface ConstantTypeDAO extends IbatisSupportDAO {
	
	/**
	 * 保存常量所属类型
	 * @param ct
	 */
	void insertConstantType(ConstantType ct);
	
	/**
	 * 删除常量所属类型
	 * @param modelId
	 */
	void deleteConstantType(Map<String,Object> params);
	
	/**
	 * 更新常量所属类型
	 * @param ct
	 */
	void updateConstantType(ConstantType ct);
	
	/**
	 * 分页查询常量所属类型
	 * @param params
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<ConstantType> queryConstantType();
}
