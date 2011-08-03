/**
 * 
 */
package com.huawei.imp.framework.model.excel2.typeHandler;

import java.text.DecimalFormat;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;

import com.huawei.imp.framework.model.excel2.exception.ExcelException;

/**
 * 文本类型处理器
 * 处理Excel单元格的文本信息
 * @author ahli
 * @date 2009-9-7
 */
public class StringTypeHandler implements TypeHandlerSupport {

	private DecimalFormat defaultDecimalFormat = new DecimalFormat("####################.######");
	
	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.model.excel.typeHandler.TypeHandlerSupport#readHSSFCell(org.apache.poi.hssf.usermodel.HSSFCell)
	 */
	@Override
	public Object readHSSFCell(HSSFCell cell, String regex) throws ExcelException {
		String cellValue = "";
		if(null != cell){
			if (HSSFCell.CELL_TYPE_NUMERIC == cell.getCellType()) {
				cellValue = defaultDecimalFormat.format(cell.getNumericCellValue());
			} else if (HSSFCell.CELL_TYPE_STRING == cell.getCellType()) {
				cellValue = cell.getRichStringCellValue().getString();
			} else if(HSSFCell.CELL_TYPE_FORMULA == cell.getCellType()){
				cellValue = cell.getRichStringCellValue().getString();;
			} else if(HSSFCell.CELL_TYPE_BOOLEAN == cell.getCellType()){
				cellValue = String.valueOf(cell.getBooleanCellValue());
			}
		}
		
		// 正则表达式
		if (StringUtils.isNotBlank(regex) && 
				null != cellValue && !Pattern.matches(regex, cellValue))
		{
			throw new ExcelException("字符串格式错误：" + cellValue);
		}
		
		return cellValue;
	}

	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.model.excel.typeHandler.TypeHandlerSupport#writeHSSFCell(java.lang.Object, org.apache.poi.hssf.usermodel.HSSFCell)
	 */
	@Override
	public void writeHSSFCell(Object obj, HSSFCell cell) {
		if(null != obj){
			final String strValue = (String)obj;
			cell.setCellValue(new HSSFRichTextString(strValue));
		}else{
			cell.setCellValue(new HSSFRichTextString(""));
		}
	}
}
