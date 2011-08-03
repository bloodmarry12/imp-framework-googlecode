package com.huawei.imp.framework.model.excel2;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.huawei.imp.framework.model.excel2.config.ExcelConfig;
import com.huawei.imp.framework.model.excel2.domain.ExcelFile;
import com.huawei.imp.framework.model.excel2.exception.ExcelException;

/**
 * Excel工具类，提供简单的数据格式的Excel文件与javaBean的相互转换。
 * @author ahli
 * @version IMPV100R001DA0, Jul 20, 2009 
 * @since CMS IMPV100R001DA0
 */
public class ExcelUtil
{

	/**
	 * 日志对象
	 */
	private static final Logger log = org.apache.log4j.Logger.getLogger(ExcelUtil.class);

	/**
	 * 根据配置文件关键字创建HSSFWorkbook对象
	 * @param key    配置关键字
	 * @param plugExcelColumn    扩展单元格
	 * @return
	 * @throws ExcelException 
	 */
	public static HSSFWorkbook createEmptyHSSFWorkbook(String key) throws ExcelException
	{
		log.debug("start create empty HSSFWorkbook : " + key );
		// 创建一个HSSFWorkbook对象
		HSSFWorkbook wb = genHSSFWorkbook();
		ExcelFile file = ExcelConfig.loadExcelFile(key);
		file.writeDataExcelFile(wb, null, null);
		return wb;
	}
	
	public static HSSFWorkbook createEmptyHSSFWorkbook(String key, Collection<ExcelCellSupport> dynamicExcelCells) throws ExcelException
	{
		log.debug("start create empty HSSFWorkbook : " + key );
		// 创建一个HSSFWorkbook对象
		HSSFWorkbook wb = genHSSFWorkbook();
		ExcelFile file = ExcelConfig.loadExcelFile(key);
		file.writeDataExcelFile(wb, dynamicExcelCells, null);
		return wb;
	}
	
	public static HSSFWorkbook createDataHSSFWorkbook(String key, List<? extends Object> objs) throws ExcelException
	{
		return createDataHSSFWorkbook(key, null, objs);
	}
	
	/**
	 * @param key
	 * @param plugCells
	 * @param objs
	 * @return
	 * @throws ExcelException 
	 */
	public static HSSFWorkbook createDataHSSFWorkbook(String key, Collection<ExcelCellSupport> dynamicExcelCells, List<? extends Object> objs) throws ExcelException
	{
		log.debug("start create data HSSFWorkbook : " + key );
		// 创建一个HSSFWorkbook对象
		HSSFWorkbook wb = genHSSFWorkbook();
		ExcelFile file = ExcelConfig.loadExcelFile(key);
		file.writeDataExcelFile(wb, dynamicExcelCells, objs);
		return wb;
	}
	
	/**
	 * 从Excel文件对象中读取并且声称对象
	 * @param <T>    泛型
	 * @param key    关键字
	 * @param wb     HSSFWorkbook对象
	 * @param plugCells    扩展单元格
	 * @param clazz        泛型类型
	 * @return
	 * @throws ExcelException 
	 */
	public static <T> List<T> readDataHSSFWorkbook(String key, HSSFWorkbook wb, Collection<ExcelCellSupport> dynamicExcelCells, Class<T> clazz) throws ExcelException
	{
		log.debug("start create data HSSFWorkbook : " + key );
		ExcelFile file = ExcelConfig.loadExcelFile(key);
		return file.readDataExcelFile(wb, dynamicExcelCells, clazz);
	}
	
	/**
	 * 创建一个空白的HSSFWorkbook对象
	 * 
	 * @return
	 */
	private static HSSFWorkbook genHSSFWorkbook()
	{
		HSSFWorkbook wb = new HSSFWorkbook();
		return wb;
	}
}
