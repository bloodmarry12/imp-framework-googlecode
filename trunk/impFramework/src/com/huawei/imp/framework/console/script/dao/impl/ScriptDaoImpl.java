/*
 * 文件名：    ScriptDao.java
 * 版权:      Copyright 2000-2008 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 创建人:    申卿
 * 文件描述:  
 * 修改时间:   Jun 3, 2010 12:03:49 PM
 * 修改内容：  新增
 */
package com.huawei.imp.framework.console.script.dao.impl;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.huawei.imp.framework.console.script.domain.Script;

/**
 * <br>类描述： </br>
 * @author  申卿
 * @version Jun 3, 2010 12:03:49 PM
 * @see
 * @since
 */
public class ScriptDaoImpl<T extends Script> extends IbatisCommonDAOImpl
{        
	public ScriptDaoImpl(SqlMapClientTemplate sqlMapClientTemplate)
    {
		super(sqlMapClientTemplate);
		
    }
}
