package com.huawei.imp.framework.model.excel2;

import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;

import com.huawei.imp.framework.model.excel2.exception.ExcelException;

/**
 * Excel单元格定义
 * 〈功能详细描述〉
 * @author ahli
 * @version IMPV100R001DA0, Jul 20, 2009 
 * @see [相关类/方法]
 * @since CMS IMPV100R001DA0
 */
public interface ExcelCellSupport{
	
	/**
	 * 属性路径间隔符号
	 */
	static String CONSTANTS_BEAN_F = ".";
	
	/**
	 * 获取标题数组
	 * @param cell
	 */
	String getTitle();
	
	/**
	 * 写数据到Excel
	 * @param cell
	 * @param beanMap
	 * @throws ExcelException 
	 */
	@SuppressWarnings("unchecked")
	void setHSSFCell(HSSFCell cell, Map beanMap) throws ExcelException;
	
	/**
	 * 从Excel读取数据并且包装到BeanWarpper对象中
	 * @param cell      单元格对象
	 * @param target    beanWrapper
	 * @throws ExcelException 
	 */
	@SuppressWarnings("unchecked")
	void getHSSFCell(HSSFCell cell, Map beanMap) throws ExcelException;
	
	/**
	 * 校验标题行内容
	 * @param title
	 * @return
	 */
	boolean validateTitle(String title);
	
}
