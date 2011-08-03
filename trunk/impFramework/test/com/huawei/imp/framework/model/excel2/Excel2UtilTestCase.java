package com.huawei.imp.framework.model.excel2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

import com.huawei.imp.framework.model.excel2.exception.ExcelException;
import com.huawei.imp.framework.model.excel2.mock.AddToneOrderForm;
import com.huawei.imp.framework.test.AbstractFrameworkTests;

public class Excel2UtilTestCase extends AbstractFrameworkTests{

	@Test
	public void readDataHSSFWorkbook() throws ExcelException{
		File file = new File("C:/BatchImport_1.xls");
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			HSSFWorkbook wb = new HSSFWorkbook(is);
			List list = ExcelUtil.readDataHSSFWorkbook("toneBatchImportFile1", wb, null, HashMap.class);
			for(Map map : (List<Map>)list){
				System.out.println(map);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(null != is){
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	// 
	@Test
	public void readDataHSSFWorkbook2() throws ExcelException{
		File file = new File("E:/createBatchAddOrder.xls");
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			HSSFWorkbook wb = new HSSFWorkbook(is);
			List list = ExcelUtil.readDataHSSFWorkbook("createBatchAddOrder", wb, null, AddToneOrderForm.class);
			for(AddToneOrderForm form : (List<AddToneOrderForm>)list){
				System.out.println(form.getCopyrightIDs());
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(null != is){
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
	public void test() throws ExcelException{
		File file = new File("E:/1.xls");
		
		HSSFWorkbook wb = ExcelUtil.createEmptyHSSFWorkbook("createBatchAddOrder");
		OutputStream os = null;
		try
		{
			os = new FileOutputStream(file);
			wb.write(os);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(null != os){
				try
				{
					os.close();
				}
				catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testDataCell() throws ExcelException, IOException{
		File file = new File("E:/test.xls");
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			HSSFWorkbook wb = new HSSFWorkbook(is);
			List<HashMap> list = ExcelUtil.readDataHSSFWorkbook("testDate", wb, null, HashMap.class);
			for(Map form : list){
				System.out.println(form.get("date") + "/" + form.get("str"));
			}
		}finally{
			if(null != is){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
