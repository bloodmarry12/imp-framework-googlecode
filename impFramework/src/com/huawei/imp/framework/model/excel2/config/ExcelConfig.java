package com.huawei.imp.framework.model.excel2.config;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.cglib.reflect.FastClass;

import org.dom4j.Element;

import com.huawei.imp.framework.jee.Path;
import com.huawei.imp.framework.model.excel2.domain.ExcelCell;
import com.huawei.imp.framework.model.excel2.domain.ExcelFile;
import com.huawei.imp.framework.model.excel2.domain.ExcelSheet;
import com.huawei.imp.framework.model.excel2.exception.ExcelException;
import com.huawei.imp.framework.model.excel2.typeHandler.DateTypeHandler;
import com.huawei.imp.framework.model.excel2.typeHandler.DoubleTypeHandler;
import com.huawei.imp.framework.model.excel2.typeHandler.IntegerTypeHandler;
import com.huawei.imp.framework.model.excel2.typeHandler.LongTypeHandler;
import com.huawei.imp.framework.model.excel2.typeHandler.StringTypeHandler;
import com.huawei.imp.framework.model.excel2.typeHandler.TypeHandler;
import com.huawei.imp.framework.model.excel2.typeHandler.TypeHandlerSupport;
import com.huawei.imp.framework.utils.FileXmlTool;

/**
 * Description:
 * Excel配置管理器,容器启动的时候，加载该类。类构造函数负责从指定的配置文件中获取Excel文件配置模板
 * 定义。
 * @author ahli
 * Apr 30, 2009
 * 
 */
public class ExcelConfig
{
	/**
	 * 静态容器，保存加载的Excel文件配置信息
	 */
	public final static Map<String, ExcelFile> configMap = new HashMap<String, ExcelFile>(20);
	
	/**
	 * 数据类型处理器
	 */
	public final static Map<String, TypeHandlerSupport> typeHandlerSupportContainer = new HashMap<String, TypeHandlerSupport>();
	
	/**
	 * 定义Excel文件
	 */
	private static final FileXmlTool customerConfig = new FileXmlTool(Path.getWebRootPath()+ "/WEB-INF/conf/cus.excel2.xml");
//	private static final FileXmlTool customerConfig = new FileXmlTool("D:/四期SVNView/工程/CMS4DEMO/trunk/FRAMEWORK/WebRoot" + "/WEB-INF/conf/cus.excel2.xml");
	/**
	 * excelConfig根节点
	 */
	private static final String XML_ELEMENT_ROOT = "excelConfig";
	
	/**
	 * excelConfig根节点
	 */
	private static final String XML_ELEMENT_TYPEHANDLERS = "typeHandlers";
	
	/**
	 * 
	 */
	private static final String XML_ELEMENT_TYPEHANDLER = "typeHandler";
	
	/**
	 * 
	 */
	private static final String XML_ELEMENT_FILES = "files";
	
	/**
	 * 
	 */
	private static final String XML_ELEMENT_FILE = "file";
	
	// 静态加载数据类型处理
	static {
		typeHandlerSupportContainer.put("long", new LongTypeHandler());
		typeHandlerSupportContainer.put("date", new DateTypeHandler());
		typeHandlerSupportContainer.put("int", new IntegerTypeHandler());
		typeHandlerSupportContainer.put("string", new StringTypeHandler());
		typeHandlerSupportContainer.put("double", new DoubleTypeHandler());
	}

	/**
	 * 配置文件根节点
	 */
	final private Element root;
	
	/**
	 * 构造的时候，加载配置文件
	 * @throws ExcelException 
	 */
	public ExcelConfig() throws ExcelException{
		
		// 加载配置文件excelConfig根节点
		root = customerConfig.getRoot(XML_ELEMENT_ROOT);
		
		// 加载类型配置项
		loadTypeHandlerConfig();
		
		// 加载标题样式
		loadTitleCellStyle();
		
		// 加载Excel文件配置对象
		loadExcelFileConfig();
		
	}
	
	/**
	 * 加载类型处理映射
	 * @throws ExcelException 
	 */
	@SuppressWarnings("unchecked")
	private void loadTypeHandlerConfig() throws ExcelException{
		Element typeHandlers = root.element(XML_ELEMENT_TYPEHANDLERS);
		
		String className = null;
		String type = null;
		String constructParameter = null;
		
		// 校验
		if(null != typeHandlers){
			List<Element> list = typeHandlers.elements(XML_ELEMENT_TYPEHANDLER);
			
			for(Element typeHandler : list){
				if(null != typeHandler){
					className = typeHandler.attributeValue("class");
					type = typeHandler.attributeValue("type");
					constructParameter = typeHandler.attributeValue("constructParameter");
					try {
						TypeHandlerSupport obj = null;
						FastClass fc = FastClass.create(Class.forName(className));
						if(constructParameter == null){
							obj = (TypeHandlerSupport)fc.getConstructor((Class[])null).newInstance();
						}else{
							obj = (TypeHandlerSupport)fc.getConstructor(new Class[]{String.class}).newInstance(new Object[]{constructParameter});
						}
						// 将解析后的类型处理器放入到全局映射中
						typeHandlerSupportContainer.put(type, obj);
					} catch (ClassNotFoundException e) {
						throw new ExcelException("加载类型数据失败。[type=" + type + ", class=" + className + "]", e);
					} catch (InvocationTargetException e) {
						throw new ExcelException("加载类型数据失败。[type=" + type + ", class=" + className + "]", e);
					}
				}
			}
		}
	}
	
	/**
	 * 加载标题单元格样式
	 */
	private void loadTitleCellStyle(){
//		HSSF style = HSSFStyle;
		
		
	}
	
	@SuppressWarnings("unchecked")
	private void loadExcelFileConfig() throws ExcelException{
		Element files = root.element(XML_ELEMENT_FILES);
		
		String fileName = null;
		
		String column_title = null;       // 单元格名称
		String column_type = null;        // 单元格数据类型
		String column_property = null;    // 单元格对应java对象属性名称
		String column_reg = null;         // 单元格正则表达式
		
		if(null !=  files){
			ExcelFile excelFile = null;
			ExcelSheet excelSheet = null;
			
			List<Element> list = files.elements(XML_ELEMENT_FILE);
			
			for(Element file : list){
				fileName = file.attributeValue("name");
//				configMap.put(id, createExcelFile(file));
				
				Element sheet = file.element("sheet");
				String sheetName = null;
				if(null != sheet){
					sheetName = sheet.attributeValue("name");
					
					List<ExcelCell> excelCells = new ArrayList<ExcelCell>(20);
					List<Element> columns = sheet.elements("column");
					
					// 迭代单元格
					for(Element column : columns){
						column_title = column.attributeValue("title");
						column_type = column.attributeValue("type");
						column_property = column.attributeValue("property");
						column_reg = column.attributeValue("reg");
						
						if(null == column_type){
							column_type = "string";
						}
						
						// 在循环体内部声明变量，避免该引用关系被用来修改，导致TypeHandler属性，修改，同时
						// 
						TypeHandlerSupport _typeHandlerSupport = typeHandlerSupportContainer.get(column_type);
						if(null == _typeHandlerSupport){
							throw new ExcelException("创建ExcelFile对象失败:file_id=" + fileName + ", Column=" + column_title);
						}
						
						ExcelCell cell = new ExcelCell(column_title, column_property, new TypeHandler(_typeHandlerSupport, column_reg));
						excelCells.add(cell);
					}
					
					excelSheet = new ExcelSheet(sheetName, excelCells);
				}
				
				excelFile = new ExcelFile (excelSheet);
				configMap.put(fileName, excelFile);
			}
		}
	}
	
	/**
	 * 返回Excel文件配置对象，如果不存在，返回ExcelFile的null object，避免空指针；
	 * @param fileName	配置关键字
	 * @return
	 * 	
	 */
	public static ExcelFile loadExcelFile(String fileName){
		ExcelFile obj = configMap.get(fileName);
		return  obj == null ? ExcelFile.NULL:obj;
	}
}
