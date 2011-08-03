package com.huawei.imp.framework.model.excel2.domain;

import java.util.Arrays;
import java.util.Map;

import net.sf.cglib.beans.BeanMap;

import org.apache.poi.hssf.usermodel.HSSFCell;

import com.huawei.imp.framework.logger.LogFactory;
import com.huawei.imp.framework.logger.Logger;
import com.huawei.imp.framework.model.excel2.ExcelCellSupport;
import com.huawei.imp.framework.model.excel2.exception.ExcelException;
import com.huawei.imp.framework.model.excel2.typeHandler.TypeHandler;
import com.mchange.v1.util.StringTokenizerUtils;

/**
 * Excel单元格定义
 * 〈功能详细描述〉
 * @author ahli
 * @version IMPV100R001DA0, Jul 20, 2009 
 * @see com.huawei.imp.framework.model.excel2.ExcelCellSupport
 * @since CMS IMPV100R001DA0
 */
public class ExcelCell implements ExcelCellSupport{
	
	private static final String CONSTANTS_BEAN_F = ".";
	
	/**
	 * 日志对象
	 */
	private static final Logger log = LogFactory.getLogger(ExcelCell.class);
	
	/**
	 * 标题单元格，显示内容
	 */
	protected final String title;
	
	/**
	 * 数据处理器
	 */
	protected final TypeHandler typeHandler;

	/**
	 * 对应java对象属性名称，如果是Map，则是key
	 */
	protected final String[] property;
	
	/**
	 * @param name
	 * @param property
	 * @param value
	 */
	public ExcelCell(String title, String property,
			TypeHandler typeHandler) {
		super();
		this.title = title;
		this.typeHandler = typeHandler;
		if(null != property && !"".equals(property.trim())){
			this.property = StringTokenizerUtils.tokenizeToArray(property, CONSTANTS_BEAN_F);
		}else{
			this.property = null;
		}
		if(log.isDebugEnabled()){
			log.debug("excel -> title:" + title + ", typeHandel:" + typeHandler + ", property:" + property);
		}
		
	}
	
	/**
	 * 写数据到Excel
	 * @param cell
	 * @param beanMap
	 * @throws ExcelException 
	 */
	@SuppressWarnings("unchecked")
	public void setHSSFCell(HSSFCell cell, Map beanMap) throws ExcelException{
//		cell.setCellStyle(dataCellStyle);
		if(null != property){
			if(log.isDebugEnabled()){
				log.debug("setHSSFCell -> title:" + title + ", typeHandel:" + typeHandler + ", property:" + Arrays.toString(property));
			}
			
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			Map tmpMap = beanMap;
			int index = 0;
			while(index < property.length - 1)
			{
				tmpMap = BeanMap.create(tmpMap.get(property[index++]));
				if(log.isDebugEnabled()){
					log.debug("getProperty[" + index + "] -> " + (property[index]));
				}
			}
			typeHandler.writeHSSFCell(tmpMap.get(property[index]), cell);
		}
	}
	
	/**
	 * 从Excel读取数据并且包装到beanMap对象中
	 * @param cell      单元格对象
	 * @param target    beanWrapper
	 * @throws ExcelException 
	 */
	@SuppressWarnings("unchecked")
	public void getHSSFCell(HSSFCell cell, Map beanMap) throws ExcelException{
		if(null != property){
			Map tmpMap = beanMap;
			int index = 0;
			while(index < property.length - 1)
			{
				tmpMap = BeanMap.create(tmpMap.get(property[index++]));
			}
			tmpMap.put(property[index], typeHandler.readHSSFCell(cell));
		}
	}
	
	/**
	 * 验证标题是否一致
	 * @param title    标题
	 * @return
	 */
	public boolean validateTitle(String title)
	{
		if (null != this.title && !this.title.equals(title))
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	public String getTitle() {
		return title;
	}
}
