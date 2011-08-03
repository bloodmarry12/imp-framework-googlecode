/*
 * 文件名：ConstantValueHandler.java
 * 版权：  Copyright 2000-2009 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 描述：  抽象的常量类型处理器
 * 修改人：ahli
 * 修改时间：Sep 6, 2009
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.huawei.imp.framework.constant.dao.handler;

import java.sql.SQLException;

import com.huawei.imp.framework.constant.ConstantValue;
import com.ibatis.sqlmap.client.extensions.ParameterSetter;
import com.ibatis.sqlmap.client.extensions.ResultGetter;
import com.ibatis.sqlmap.client.extensions.TypeHandlerCallback;

/**
 * 抽象的常量类型处理器
 * 用于处理ibatis操作的常量字段处理。当返回结果不是一个javaBean对象的时候，无法通过javaBean的set方法对常量进行处理，这时，需要调用
 * 该处理器的子类，来实现特殊类型的处理。子类必须继承该类，实现一个空构造函数，并且调用该类的带参数构造函数，以实现各种类型。
 * @author ahli
 * @version IMPV100R001DA0, Sep 6, 2009 
 * @see com.ibatis.sqlmap.engine.type.BaseTypeHandler
 * @see com.ibatis.sqlmap.engine.type.TypeHandler
 * @since CMS IMPV100R001DA0
 */
public abstract class ConstantValueTypeValueHandler implements
		TypeHandlerCallback
{

	private Integer modelID;
	
	/**
	 * 模式ID
	 * @param modelID
	 */
	public ConstantValueTypeValueHandler(String modelID){
		this.modelID = Integer.valueOf(modelID);
	}

	@Override
	public Object getResult(ResultGetter arg0) throws SQLException {
		ConstantValue cv = new ConstantValue(modelID);
		cv.setValue(arg0.getString());
		return cv;
	}

	@Override
	public void setParameter(ParameterSetter arg0, Object arg1)
			throws SQLException {
		arg0.setString(((ConstantValue) arg1).getValue());
	}

	/* (non-Javadoc)
	 * @see com.ibatis.sqlmap.engine.type.TypeHandler#valueOf(java.lang.String)
	 */
	@Override
	public Object valueOf(String arg0)
	{
		ConstantValue cv = new ConstantValue(modelID);
		cv.setValue(arg0);
		return cv;
	}
}
