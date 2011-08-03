/**
 * 
 */
package com.huawei.imp.framework.model.excel2.typeHandler;

import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;

import com.huawei.imp.framework.model.excel2.exception.ExcelException;
import com.huawei.imp.framework.model.excel2.typeHandler.TypeHandlerSupport;
import com.huawei.imp.framework.utils.BeanHolder;

/**
 * 浮点类型处理器 <功能描述/>
 * 
 * @author zenggh
 * @date 2009-9-7
 */
public class DoubleTypeHandler implements TypeHandlerSupport
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.huawei.imp.framework.model.excel.typeHandler.TypeHandlerSupport#readHSSFCell(org.apache.poi.hssf.usermodel.HSSFCell)
	 */
	@Override
	public Object readHSSFCell(HSSFCell cell, String regex)
			throws ExcelException
	{
		String cellValue = null;
		if (null != cell)
		{
			if (HSSFCell.CELL_TYPE_NUMERIC == cell.getCellType())
			{
				cellValue = String.valueOf(cell.getNumericCellValue());
				if (cellValue.endsWith(".0")) {
					cellValue = cellValue.substring(0, cellValue.length() - 2);
				}
			}
			else if (HSSFCell.CELL_TYPE_STRING == cell.getCellType())
			{
				cellValue = cell.getRichStringCellValue().getString();
				// 如果值为空,就把初始值设为null
				if("".equals(cellValue))
				{
					cellValue = null;
				}
			}
		}

		// 正则表达式
		if (StringUtils.isNotBlank(regex) && null != cellValue
				&& !Pattern.matches(regex, cellValue))
		{
			String errorMsg = BeanHolder
					.getMessage("imp.cms.copyright.lable.error.doubleType");
			throw new ExcelException(errorMsg + cellValue);
		}

		return null == cellValue ? null : Double.valueOf(cellValue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.huawei.imp.framework.model.excel.typeHandler.TypeHandlerSupport#writeHSSFCell(java.lang.Object,
	 *      org.apache.poi.hssf.usermodel.HSSFCell)
	 */
	@Override
	public void writeHSSFCell(Object obj, HSSFCell cell)
	{
		if (null != obj)
		{
			final Double value = (Double) obj;
			final String strValue = String.valueOf(value);
			cell.setCellValue(new HSSFRichTextString(strValue));
		}
		else
		{
			cell.setCellValue(new HSSFRichTextString(""));
		}
	}
}
