package com.huawei.imp.framework.model.excel2.domain;

import java.util.Collection;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.huawei.imp.framework.model.excel2.ExcelCellSupport;
import com.huawei.imp.framework.model.excel2.exception.ExcelException;

public class ExcelFile{

//	/**
//	 * 日志对象
//	 */
//	private static final Logger log = org.apache.log4j.Logger.getLogger(ExcelFile.class);
	
	/**
	 * 空对象
	 */
	public static final ExcelFile NULL = new ExcelFile(null);
	
	/**
	 * 电子表格
	 */
	private ExcelSheet sheet;

	/**
	 * @param fileKey
	 * @param sheet
	 */
	public ExcelFile(ExcelSheet sheet) {
		super();
		this.sheet = sheet;
	}
	
	public void writeDataExcelFile(HSSFWorkbook wb, Collection<ExcelCellSupport> dynamicExcelCells, Collection<? extends Object> objs) throws ExcelException{
		sheet.setTitleHSSFRow(wb, dynamicExcelCells);
		sheet.setHSSFSheetValue(wb, dynamicExcelCells, objs);
	}
	
	public <E extends Object> List<E> readDataExcelFile(HSSFWorkbook wb, Collection<ExcelCellSupport> dynamicExcelCells, Class<E> clazz) throws ExcelException{
		return sheet.getHSSFSheetValue(wb, dynamicExcelCells, clazz);
	}
}
