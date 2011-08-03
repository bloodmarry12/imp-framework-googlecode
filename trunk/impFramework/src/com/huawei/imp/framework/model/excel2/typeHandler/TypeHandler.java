package com.huawei.imp.framework.model.excel2.typeHandler;

import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;

import com.huawei.imp.framework.model.excel2.exception.ExcelException;

/**
 * <一句话功能/>
 * <功能描述/>
 * @author ahli
 * @date 2009-9-7
 */
public class TypeHandler {

	private final TypeHandlerSupport typeHandlerSupport;
	
	private String regex = null;
	
	public TypeHandler(TypeHandlerSupport typeHandlerSupport, String regex){
		this.typeHandlerSupport = typeHandlerSupport;
		this.regex = regex;
	}
	
	/**
	 * 从单元格读取数据
	 * @param cell
	 * @return
	 * @throws Throwable
	 */
	public Object readHSSFCell(HSSFCell cell) throws ExcelException{
		return typeHandlerSupport.readHSSFCell(cell, regex);
	}
	
	/**
	 * 将对象写入单元格
	 * @param obj     值对象
	 * @param cell    单元格
	 * @throws ExcelException 
	 */
	public void writeHSSFCell(Object obj, HSSFCell cell) throws ExcelException{
		if(null != cell){
			typeHandlerSupport.writeHSSFCell(obj, cell);
		}
	}
	
	/**
	 * 正则表达式校验方法
	 * @param source
	 * @param regex
	 * @return
	 */
	public static final boolean validate(String source, String regex){
		boolean ret = false;
		if(null != source){
			ret = Pattern.matches(regex, source);
		}
		return ret;
	}
}
