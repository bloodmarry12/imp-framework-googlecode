package com.huawei.imp.framework.common.dao.impl;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.huawei.imp.framework.common.dao.IbatisSupportDAO;
import com.huawei.imp.framework.utils.BeanHolder;

/**
 * 抽象了Ibatis基础数据访问层实现类，所有基于Ibatis事先的数据访问都应该继承自该类。
 * 实现了基于命名空间适配功能
 * @author ahli
 * @date 2009-8-15
 */
public abstract class IbatisCommonDAOImpl extends MDSSupportIbatisCommonDAOImpl implements IbatisSupportDAO {
	
	
	/**
	 * 默认构造函数，默认注入sqlMapClientTemplate0
	 */
	public IbatisCommonDAOImpl() {
		super((SqlMapClientTemplate)BeanHolder.getBean("sqlMapClientTemplate0"));
	}

	/**
	 * 默认构造函数，注入MDSSupportIbatisCommonDAOImpl构造需要的参数
	 * @param sqlMapClientTemplate
	 */
	public IbatisCommonDAOImpl(SqlMapClientTemplate sqlMapClientTemplate)
	{
		super(sqlMapClientTemplate);
	}
}
