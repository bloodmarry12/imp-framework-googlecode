package com.huawei.imp.framework.model.excel2.typeHandler;

import org.apache.poi.hssf.usermodel.HSSFCell;

import com.huawei.imp.framework.model.excel2.exception.ExcelException;

/**
 * 类型处理器
 * 用于处理各种数据类型
 * @author ahli
 * @date 2009-9-7
 */
public interface TypeHandlerSupport {

	/**
	 * 从单元格读取数据
	 * @param cell     单元格对象
	 * @param regex    验证正则表达式
	 * @return
	 * @throws Throwable 
	 */
	Object readHSSFCell(HSSFCell cell, String regex) throws ExcelException;
	
	/**
	 * 将对象写入单元格
	 * @param obj     值对象
	 * @param cell    单元格
	 */
	void writeHSSFCell(Object obj, HSSFCell cell) throws ExcelException;
}
