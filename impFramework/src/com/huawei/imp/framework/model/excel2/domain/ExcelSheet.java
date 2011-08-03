package com.huawei.imp.framework.model.excel2.domain;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.beans.BeanMap;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.huawei.imp.framework.model.excel2.ExcelCellSupport;
import com.huawei.imp.framework.model.excel2.exception.ExcelException;
import com.huawei.imp.framework.model.excel2.exception.ValidateExcelException;

/**
 * Excel表格对象
 * 定义了Excel表格的属性，以及单元格属性
 * @author ahli
 * @version IMPV100R001DA0, Jul 20, 2009 
 * @since CMS IMPV100R001DA0
 */
public class ExcelSheet{

	/**
	 * 日志对象
	 */
	private static final Logger log = org.apache.log4j.Logger.getLogger(ExcelSheet.class);
	
	/**
	 * sheet的名字
	 */
	private final String name;
	
	/**
	 * 对应单元格
	 */
	private final List<ExcelCellSupport> excelCells = new ArrayList<ExcelCellSupport>(10);

	/**
	 * 默认构造函数
	 * @param name    sheet的名称
	 */
	public ExcelSheet(String name, List<ExcelCell> excelCells) {
		super();
		this.name = name;
		this.excelCells.addAll(excelCells);
	}
	
	/**
	 * 校验HSSFWorkbook对象中是否包含该sheet定义的表格，如果没有，则创建一个。
	 * @param wb
	 */
	private HSSFSheet getHSSFSheet(HSSFWorkbook wb){
		HSSFSheet hSSFSheet = wb.getSheet(name);
		if(null == hSSFSheet){
			hSSFSheet = wb.createSheet(name);
		}
		return hSSFSheet;
	}
	
	/**
	 * 写第一行标题行
	 * @param wb           POI工作薄
	 * @param plugCells    扩展单元格属性定义
	 */
	
	public void setTitleHSSFRow(HSSFWorkbook wb, Collection<ExcelCellSupport> dynamicExcelCells) {
		if(log.isDebugEnabled()){
			log.debug("开始加载表头单元格数据。");
		}
		
		HSSFSheet hSSFSheet = getHSSFSheet(wb);
		HSSFCellStyle titleCellStyle = createTitleHSSFCellStyle(wb);
		
		// 创建单元格
		HSSFRow hSSFRow = hSSFSheet.createRow(0);
		
		int cellIndex = 0;//单元格索引
		for(ExcelCellSupport excelCell : excelCells){
			HSSFCell cell = hSSFRow.createCell(cellIndex);
			hSSFSheet.setColumnWidth(cellIndex, (excelCell.getTitle().length()+1) * 750);
			hSSFSheet.setDefaultRowHeight((short)2000);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			// 设置样式
			cell.setCellStyle(titleCellStyle);
			cell.setCellValue(new HSSFRichTextString(excelCell.getTitle()));
			cellIndex ++;
		}
		
		if(log.isDebugEnabled()){
			log.debug("加载静态单元格配置: " + excelCells.size() +" 项。");
		}
		
		// 打印扩展单元格索引
		if(null != dynamicExcelCells){
			for(ExcelCellSupport excelCell : dynamicExcelCells){
				HSSFCell cell = hSSFRow.createCell(cellIndex);
				hSSFSheet.setColumnWidth(cellIndex, (excelCell.getTitle().length()+1) * 750);
				hSSFSheet.setDefaultRowHeight((short)2000);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(titleCellStyle);
				cell.setCellValue(new HSSFRichTextString(excelCell.getTitle()));
				cellIndex ++;
			}
			
			if(log.isDebugEnabled()){
				log.debug("加载静态单元格配置: " + dynamicExcelCells.size() +" 项。");
			}
		}
	}
	
	/**
	 * 从第二行开始写数据行到Excel
	 * @param <T>
	 * @param wb
	 * @param plugCells
	 * @param objs
	 * @throws ExcelException 
	 */
	@SuppressWarnings("unchecked")
	public void setHSSFSheetValue(HSSFWorkbook wb, Collection<ExcelCellSupport> dynamicExcelCells, Collection<? extends Object> objs) throws ExcelException {
		
		// 获取指定的sheet对象
		HSSFSheet hSSFSheet = getHSSFSheet(wb);
		
		// 遍历行索引
		int rowIndex = 1;
		
		// 判断输入的对象参数不为空，则开始迭代取
		if(null != objs){
			HSSFRow hSSFRow = null;
			HSSFCell cell = null;
			Map objMap = null; // 定义映射对象
			// 遍历对象
			for(Object obj : objs){
				if(obj instanceof Map){
					objMap = (Map)obj;
				}else{
					objMap = BeanMap.create(obj);
				}
				
				// 单元格遍历索引
				int cellIndex = 0;
				
				// 根据索引获取当前操作的行集合
				hSSFRow = hSSFSheet.createRow(rowIndex ++);
				
				for(ExcelCellSupport excelCell : excelCells){
					cell = hSSFRow.createCell(cellIndex ++);
					excelCell.setHSSFCell(cell, objMap);
				}
				
				// 如果有扩展单元格
				if(null != dynamicExcelCells){
					for(ExcelCellSupport excelCell : dynamicExcelCells){
						cell = hSSFRow.createCell(cellIndex ++);
						excelCell.setHSSFCell(cell, objMap);
					}
				}
				obj = null;
			}
		}
	}
	
	/**
	 * 从Excel中解析数据到对象列表中
	 * @param <T>    泛型
	 * @param wb     HSSFWorkbook对象
	 * @param plugCells    扩展单元格
	 * @param clazz    泛型类型
	 * @return
	 * @throws ExcelException 
	 */
	@SuppressWarnings("unchecked")
	public <T extends Object> List<T> getHSSFSheetValue(HSSFWorkbook wb, Collection<ExcelCellSupport> dynamicExcelCells, Class<T> clazz) throws ExcelException{
		List<T> returnValue = new ArrayList<T>(100);
		HSSFSheet hSSFSheet = getHSSFSheet(wb);
		
		// 获取所有有效的行数
		int totalRowNum = hSSFSheet.getLastRowNum();
		
		// 验证标题行
		HSSFRow titleHSSFRow = hSSFSheet.getRow(0);
		
		// 如果上传的模板，读取不到标题行，则直接抛出异常
		if(null == titleHSSFRow)
		{
			final String errorMessage = "未读取到表头标题行，请确认上传的模板是否正确";
			throw new ValidateExcelException(errorMessage);
		}
		
		int cellIndex = 0;// 单元格索引
		for (ExcelCellSupport cell : excelCells) {
			HSSFCell hSSFCell = titleHSSFRow.getCell( cellIndex++);
			final String title;
			if(null != hSSFCell){
				title = hSSFCell.getRichStringCellValue().toString();
			}else{
				title = null;
			}
			
			if(!cell.validateTitle(title)){
				final String errorMessage = MessageFormat.format("标题行验证失败,第{0}列标题应该为{1},而实际是{2}", new Object[]{cellIndex, cell.getTitle(), title});
				throw new ValidateExcelException(errorMessage);
			}
		}
		
		if(null != dynamicExcelCells){
			
			for (ExcelCellSupport cell : dynamicExcelCells) {
				HSSFCell hSSFCell = titleHSSFRow.getCell( cellIndex++);
				final String title;
				if(null != hSSFCell){
					title = hSSFCell.getRichStringCellValue().toString();
				}else{
					title = null;
				}
				
				if(!cell.validateTitle(title)){
					final String errorMessage = MessageFormat.format("标题行验证失败,第{0}列标题应该为{1},而实际是{2}", new Object[]{cellIndex, cell.getTitle(), title});
					throw new ValidateExcelException(errorMessage);
				}
			}
		}
		
		// 从第一个(跳过标题行开始读取)
		int rowIndex = 1;
		cellIndex = 0;
		for (int i = 0; i < totalRowNum; i++) {
			HSSFRow hSSFRow = hSSFSheet.getRow(rowIndex++);

			// 校验单元行是否存在，不存在，则跳出本次循环，进入下个循环体
			if (null == hSSFRow) {
				continue;
			}

			Object obj = createInstance(clazz); // java对象Map
			Map beanMap;
			if (obj instanceof Map) {
				beanMap = (Map) obj;
			} else {
				beanMap = BeanMap.create(obj);
			}

			cellIndex = 0;// 单元格索引

			// 遍历读取单元格指定数据，并且设置到对象中
			for (ExcelCellSupport cell : excelCells) {
				HSSFCell hSSFCell = hSSFRow.getCell(cellIndex++);
				try{
					cell.getHSSFCell(hSSFCell, beanMap);
				}catch(ExcelException e){
					throw new ExcelException("在读取单元格行号:\"" + rowIndex + "\", 列名:\"" + cell.getTitle() + "\",的时候发生如下异常 [" + e.getMessage() + "]");
				}
			}

			 if(null != dynamicExcelCells){
				 
				// 遍历读取单元格指定数据，并且设置到对象中
					for (ExcelCellSupport cell : dynamicExcelCells) {
						HSSFCell hSSFCell = hSSFRow.getCell(cellIndex++);
						try{
							cell.getHSSFCell(hSSFCell, beanMap);
						}catch(ExcelException e){
							throw new ExcelException("在读取单元格行号:\"" + rowIndex + "\", 列名:\"" + cell.getTitle() + "\",的时候发生如下异常 [" + e.getMessage() + "]");
						}	
					}
			 }

			// 包装好的对象添加到列表中
			returnValue.add((T) obj);
		}
		return returnValue;
	}
	
	@SuppressWarnings("unchecked")
	public static Object createInstance(Class clazz){
		BeanGenerator bg = new BeanGenerator(); 
		bg.setSuperclass(clazz);
		return bg.create();
	}
	
	private HSSFCellStyle createTitleHSSFCellStyle(HSSFWorkbook wb){
		HSSFCellStyle titleCellStyle = wb.createCellStyle();
		HSSFFont titleFont = wb.createFont();
		
		titleFont.setFontHeightInPoints( (short) 12);
		titleFont.setFontName("宋体");
		titleFont.setColor(HSSFColor.WHITE.index);
		titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		
		titleCellStyle.setFont(titleFont);
		titleCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		titleCellStyle.setFillForegroundColor(HSSFColor.GREEN.index);
		titleCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		titleCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边�
		titleCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN); //左边�
		titleCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN); //右边�
		titleCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN); //上边
		
		return titleCellStyle;
	}
	
	
}
