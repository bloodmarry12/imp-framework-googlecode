package com.huawei.imp.framework.model.ibus.log.dao.impl;

import java.util.Collection;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.huawei.imp.framework.common.dao.impl.IbatisCommonDAOImpl;
import com.huawei.imp.framework.model.ibus.log.dao.IbusLogDAO;
import com.huawei.imp.framework.model.ibus.log.domain.IbusLogerEntity;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author ahli
 * @version IMPV100R001DA0, Oct 12, 2009 
 * @see [相关类/方法]
 * @since CMS IMPV100R001DA0
 */
@Component("ibusLogDAOImpl")
public class IbusLogDAOImpl extends IbatisCommonDAOImpl implements IbusLogDAO
{

	@Override
	public void batchDelete(Collection<IbusLogerEntity> col)
	{
		super.batchDelete(this.warpSqlstatement(IbusLogerEntity.class, _SQL_DELETE), col);

	}

	@Override
	public void batchInsert(Collection<IbusLogerEntity> col)
	{
		super.batchInsert(this.warpSqlstatement(IbusLogerEntity.class, _SQL_INSERT), col);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<IbusLogerEntity> query(Map<String, Object> parameterObject)
	{
		return super.sqlMapClientTemplate.queryForList(this.warpSqlstatement(IbusLogerEntity.class, "query"), parameterObject);
	}
}
