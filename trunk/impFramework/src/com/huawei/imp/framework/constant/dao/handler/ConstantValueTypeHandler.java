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

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.huawei.imp.framework.constant.ConstantValue;
import com.ibatis.sqlmap.engine.type.BaseTypeHandler;
import com.ibatis.sqlmap.engine.type.TypeHandler;

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
public abstract class ConstantValueTypeHandler extends BaseTypeHandler implements
		TypeHandler
{

	private Integer modelID;
	
	/**
	 * 模式ID
	 * @param modelID
	 */
	public ConstantValueTypeHandler(Integer modelID){
		this.modelID = modelID;
	}
	
	/* (non-Javadoc)
	 * @see com.ibatis.sqlmap.engine.type.TypeHandler#getResult(java.sql.ResultSet, java.lang.String)
	 */
	@Override
	public Object getResult(ResultSet resultset, String columnIndex) throws SQLException
	{
		ConstantValue cv = new ConstantValue(modelID);
		cv.setValue(resultset.getString(columnIndex));
		return cv;
	}

	/* (non-Javadoc)
	 * @see com.ibatis.sqlmap.engine.type.TypeHandler#getResult(java.sql.ResultSet, int)
	 */
	@Override
	public Object getResult(ResultSet resultset, int columnIndex) throws SQLException
	{
		ConstantValue cv = new ConstantValue(modelID);
		cv.setValue(resultset.getString(columnIndex));
		return cv;
	}

	/* (non-Javadoc)
	 * @see com.ibatis.sqlmap.engine.type.TypeHandler#getResult(java.sql.CallableStatement, int)
	 */
	@Override
	public Object getResult(CallableStatement arg0, int arg1)
			throws SQLException
	{
		ConstantValue cv = new ConstantValue(modelID);
		cv.setValue(arg0.getString(arg1));
		return cv;
	}

	/* (non-Javadoc)
	 * @see com.ibatis.sqlmap.engine.type.TypeHandler#setParameter(java.sql.PreparedStatement, int, java.lang.Object, java.lang.String)
	 */
	@Override
	public void setParameter(PreparedStatement preparedstatement, int i, Object obj,
			String arg3) throws SQLException
	{
		 preparedstatement.setString(i, ((ConstantValue) obj).getValue());
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
