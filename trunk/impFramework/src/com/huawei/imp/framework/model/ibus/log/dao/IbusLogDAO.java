package com.huawei.imp.framework.model.ibus.log.dao;

import java.util.Collection;
import java.util.Map;

import com.huawei.imp.framework.common.dao.IbatisSupportDAO;
import com.huawei.imp.framework.model.ibus.log.domain.IbusLogerEntity;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author ahli
 * @version IMPV100R001DA0, Oct 12, 2009 
 * @see [相关类/方法]
 * @since CMS IMPV100R001DA0
 */
public interface IbusLogDAO extends IbatisSupportDAO
{
	/**
	 * 查询
	 * @param params
	 * @return
	 */
	Collection<IbusLogerEntity> query(Map<String, Object> params);
	
	/**
	 * 批量插入日志对象
	 * @param col
	 */
	void batchInsert(Collection<IbusLogerEntity> col);
	
	/**
	 * 批量删除日志对象
	 * @param col
	 */
	void batchDelete(Collection<IbusLogerEntity> col);
}
