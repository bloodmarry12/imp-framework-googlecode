/**
 * 
 */
package com.huawei.imp.framework.model.excel2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Before;
import org.junit.Test;

import com.huawei.imp.framework.constant.ConstantValue;
import com.huawei.imp.framework.constant.domain.Constant;
import com.huawei.imp.framework.model.excel2.config.ExcelConfig;
import com.huawei.imp.framework.model.excel2.domain.ExcelCell;
import com.huawei.imp.framework.model.excel2.exception.ExcelException;
import com.huawei.imp.framework.model.excel2.mock.Copyright;
import com.huawei.imp.framework.model.excel2.typeHandler.TypeHandler;
import com.huawei.imp.framework.utils.BeanProperUtil;


/**
 * <一句话功能/>
 * <功能描述/>
 * @author ahli
 * @date 2009-9-9
 */
public class ConfigTestCase {

	private static final String TEST_FILE_NAME2 = "test2";
	private static final String TEST_FILE_PATH = "e:/test/";
	
	@Before
	public void initConfig() throws ExcelException{
		new ExcelConfig();
		List<Constant> list = new ArrayList<Constant>();
		
		Constant con = new Constant();
		con.setId(1L);
		con.setModelID(1);
		con.setName("中文");
		con.setOrder(1);
		con.setValue("1");
		
		list.add(con);
		
		ConstantValue.initValueObjectBeans(list);
	}
	
	@Test
	public void exportEmptyFile() throws ExcelException{
		File file = new File(TEST_FILE_PATH + "test2.xls");
		HSSFWorkbook wb = ExcelUtil.createEmptyHSSFWorkbook("test2");
		OutputStream os = null;
		try{
			os = new FileOutputStream(file);
			wb.write(os);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(os != null){
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	private static List<ExcelCellSupport> createDynamicExcelCell(){
		List<ExcelCellSupport> list = new ArrayList<ExcelCellSupport>(10);
		list.add(new ExcelCell("发行时间(必填)" , "time" , new TypeHandler(ExcelConfig.typeHandlerSupportContainer.get("date"), null)));
		return list;
	}
	
	@Test
	public void exportEmptyFileWithDy() throws ExcelException{
		File file = new File(TEST_FILE_PATH + "test2.xls");
		HSSFWorkbook wb = ExcelUtil.createEmptyHSSFWorkbook("test2",createDynamicExcelCell());
		OutputStream os = null;
		try{
			os = new FileOutputStream(file);
			wb.write(os);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(os != null){
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	@Test
	public void exportEmptyFileWithData() throws ExcelException{
		File file = new File(TEST_FILE_PATH + "test2.xls");
		List list = new ArrayList<Map<String, Object>>();
		
		Map map = new HashMap();
		map.put("name", "名称1");
		list.add(map);
		OutputStream os = null;
		try{
			HSSFWorkbook wb = ExcelUtil.createDataHSSFWorkbook("test2", list);
			os = new FileOutputStream(file);
			wb.write(os);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(os != null){
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	@Test
	public void importFile() throws IOException, ExcelException{
		File file = new File(TEST_FILE_PATH + "test2.xls");
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			HSSFWorkbook wb = new HSSFWorkbook(is);
			List<HashMap> list = ExcelUtil.readDataHSSFWorkbook(TEST_FILE_NAME2, wb, null, HashMap.class);
			for(Object obj : list){
				System.out.println(obj);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	@Test
	public void importFileWithDynamic() throws IOException, ExcelException{
		File file = new File(TEST_FILE_PATH + "test2.xls");
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			HSSFWorkbook wb = new HSSFWorkbook(is);
			List<HashMap> list = ExcelUtil.readDataHSSFWorkbook(TEST_FILE_NAME2, wb, createDynamicExcelCell(), HashMap.class);
			for(Object obj : list){
				System.out.println(obj);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	@Test
	public void importFile3() throws IOException, ExcelException{
		File file = new File(TEST_FILE_PATH + "test2.xls");
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			HSSFWorkbook wb = new HSSFWorkbook(is);
			List<Copyright> list = ExcelUtil.readDataHSSFWorkbook("test3", wb, null, Copyright.class);
			for(Object obj : list){
				System.out.println(BeanProperUtil.getBeanDesc(obj));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	@Test
	public void readFile4() throws IOException, ExcelException{
		File file = new File(TEST_FILE_PATH + "musicClaimConditionTemplate.xls");
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			HSSFWorkbook wb = new HSSFWorkbook(is);
			List<Copyright> list = ExcelUtil.readDataHSSFWorkbook("test3", wb, null, Copyright.class);
			for(Object obj : list){
				System.out.println(BeanProperUtil.getBeanDesc(obj));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
