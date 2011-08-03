package com.huawei.imp.framework.transaction.dao.impl;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.huawei.imp.framework.common.dao.impl.MDSSupportIbatisCommonDAOImpl;
import com.huawei.imp.framework.transaction.dao.MultDataSourceTestDAO;
import com.huawei.imp.framework.utils.BeanHolder;

@Component("multDataSourceDerbyTestDAO")
public class MultDataSourceDerbyTestDAOImpl extends MDSSupportIbatisCommonDAOImpl implements MultDataSourceTestDAO {

	public MultDataSourceDerbyTestDAOImpl() {
		super((SqlMapClientTemplate)BeanHolder.getBean("sqlMapClientTemplateDerby"));
	}
}
