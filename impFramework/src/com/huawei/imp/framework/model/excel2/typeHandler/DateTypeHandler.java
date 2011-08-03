/**
 * 
 */
package com.huawei.imp.framework.model.excel2.typeHandler;

import java.text.ParseException;
import java.util.Date;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;

import com.huawei.imp.framework.model.excel2.exception.ExcelException;

/**
 * 长整形类型处理器
 * <功能描述/>
 * @author ahli
 * @date 2009-9-7
 */
public class DateTypeHandler implements TypeHandlerSupport {

	/**
	 * 日期对象格式
	 */
	private static final String[] parsePatterns = {"yyyy-MM-dd"};
	
	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.model.excel.typeHandler.TypeHandlerSupport#readHSSFCell(org.apache.poi.hssf.usermodel.HSSFCell)
	 */
	@Override
	public Object readHSSFCell(HSSFCell cell, String regex) throws ExcelException {
		String cellValue = "";
		Date date = null;
		// 单元格不为空
		if(null != cell ){
			// 如果对象为
			if (HSSFCell.CELL_TYPE_NUMERIC == cell.getCellType()) {
//				date = cell.getDateCellValue();
//				
//				if(null!= date && StringUtils.isNotBlank(regex)){
//					String _dateStr = DateFormatUtils.format(date, parsePatterns[0]);
//					if(!Pattern.matches(regex, _dateStr)){
//						throw new ExcelException("时期数据格式错误：" + cellValue);
//					}
//				}
				throw new ExcelException("日期列请先设置单元格格式为文本,再输入正确格式:yyyy-MM-dd");
			} else if (HSSFCell.CELL_TYPE_STRING == cell.getCellType()) {
				cellValue = cell.getRichStringCellValue().getString();
				// 正则表达式验证
				if (StringUtils.isNotBlank(regex) && 
						null != cellValue && !Pattern.matches(regex, cellValue))
				{
					throw new ExcelException("日期列数据格式错误：" + cellValue);
				}
				
				if(StringUtils.isNotBlank(cellValue)){
					try
					{
						date = DateUtils.parseDate(cellValue, parsePatterns);
					}
					catch (ParseException e)
					{
						throw new ExcelException("日期对象解析失败:" + cellValue);
					}
				}
			}else{
				throw new ExcelException("日期类型数据必须使用日期格式或者是字符串格式。");
			}
		}
		return date;
	}

	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.model.excel.typeHandler.TypeHandlerSupport#writeHSSFCell(java.lang.Object, org.apache.poi.hssf.usermodel.HSSFCell)
	 */
	@Override
	public void writeHSSFCell(Object obj, HSSFCell cell) {
		if(null != obj){
			final Date value = (Date)obj;
			final String strValue = DateFormatUtils.format(value, parsePatterns[0]);
			cell.setCellValue(new HSSFRichTextString(strValue));
		}else{
			cell.setCellValue(new HSSFRichTextString(""));
		}
	}
}
