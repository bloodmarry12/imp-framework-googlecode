/**
 * 
 */
package com.huawei.imp.framework.constant.service;

import java.util.List;

import com.huawei.imp.framework.constant.domain.ConstantType;

/**
 * 负责增删改常量所属类型对象
 * @author liuguoping
 * @date 2009-8-16
 */
public interface ConstantTypeService {
	
	/**
	 * 查询业务方法
	 * @param params
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<ConstantType> query();
	
	/**
	 * 删除
	 * @param modelIds
	 */
	void removeConstantType(Integer[] modelIds);
	
	/**
	 * 保存
	 * @param ct
	 */
	void saveConstantType(ConstantType ct);
	
	/**
	 * 更新
	 * @param ct
	 */
	void refreshConstantType(ConstantType ct);
}
