/**
 * 
 */
package com.huawei.imp.framework.model.ftp;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.junit.Test;
import org.springframework.util.Assert;

import com.huawei.imp.framework.model.ftp.exception.FtpException;
import com.huawei.imp.framework.test.AbstractFrameworkTests;

/**
 * <一句话功能/>
 * <功能描述/>
 * @author ahli
 * @date 2009-9-16
 */
public class FtpUtilTestCase extends AbstractFrameworkTests{

	private static final String TEST_FTP = "TEST";
	
	private static final String FTP_ALIAS = "TEST";
	
	/**
	 * 测试文件是否存在方法
	 * @throws FtpException     FTP异常
	 */
	@Test
	public void isFtpFileExist() throws FtpException{
		// 测试根目录文件
		Assert.isTrue(FtpUtil.isFtpFileExist(TEST_FTP, "/srv/imp/CMSDEMO/COPYRIGHT/CLAIM/REPORT/200911Test.txt"));
		// 测试子目录文件
		Assert.isTrue(!FtpUtil.isFtpFileExist(TEST_FTP, "/srv/imp/CMSDEMO/COPYRIGHT/CLAIM/REPORT/200911Test1.txt"));
//		// 测试根目录文件
//		Assert.isTrue(FtpUtil.isFtpFileExist(TEST_FTP, "httpd.conf2"));
	}
	
	@Test
	public void testGetFileNames() throws FtpException
	{
		System.setProperty("javax.xml.parsers.DocumentBuilderFactory"," org.apache.crimson.jaxp.DocumentBuilderFactoryImpl");
		List<String> fileNames = FtpUtil.getFtpFileNames(FTP_ALIAS, "/srv/imp/CMSDEMO/COPYRIGHT/CLAIM/REPORT");
		for(String name : fileNames)
		{
			System.out.println(name);
		}
	}
	
	@Test
	public void testDelFileFromFTP() throws FtpException
	{
		FtpUtil.delFormFtp(FTP_ALIAS, "/CMSDEMO/COPYRIGHT/2009-09-01/mv6-cs.txt");
	}
	
	@Test
	public void downloadFromFtp() throws FtpException{
		
		byte[] file = FtpUtil.downloadFromFtp(TEST_FTP, "/test/20090818/apache-log4j-1.2.15.tar.gz");
		Assert.notNull(file);
		Assert.isTrue(file.length > 0);
		System.out.println(file.length);
		
		byte[] file2 = FtpUtil.downloadFromFtp(TEST_FTP, "/test/20090818/", "apache-log4j-1.2.15.tar.gz");
		Assert.notNull(file2);
		Assert.isTrue(file2.length > 0);
		System.out.println(file2.length);
	}
	
	
	@Test
	public void downloadFromFtpTest() throws FtpException   
	{
		OutputStream os = null;
		try
		{
			os = new FileOutputStream("D:\\test.mpeg");
			FtpUtil.downloadFromFtp(TEST_FTP,"/CMSDEMO/COPYRIGHT/2009-11-05/依靠6-林依晨.mpeg" ,os);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(FtpException e)
		{
			e.printStackTrace();
		}finally
		{
			if(null != os)
			{
				try
				{
					os.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		
	}
	
	@Test
	public void getFtpFileSize() throws FtpException{
//		long size = FtpUtil.getFtpFileSize(TEST_FTP, "/test/20090819/","美梦成真-许茹芸2.mp3");
//		Assert.isTrue(size > 0);
//		System.out.println(size);
//		
		long size2 = FtpUtil.getFtpFileSize(TEST_FTP, "/test/20090819/美梦成真-许茹芸2.mp3");
		Assert.isTrue(size2 > 0);
		System.out.println(size2);
		
//		for(int i =0; i<100 ; i++){
//			long t1 = System.currentTimeMillis();
//			FtpUtil.getFtpFileSize(TEST_FTP, "/TONE/600902-2008430/tone/2009/09/21/2009092110/ajax-loader.jpg");
//			System.out.println( "次数" + i + ":" + (System.currentTimeMillis() - t1));
//		}
	}
	
	@Test
	public void test() throws FtpException{
		File file = new File("C:/Users/ahli/Pictures/6ajaxsn.jpg");
		InputStream fis = null;
		ByteArrayOutputStream baos = null;
		try {
			fis = new FileInputStream(file);
			baos = new ByteArrayOutputStream();
			byte[] b = new byte[500];
			while(fis.read(b)!= -1){
				baos.write(b);
			}
//			for(int i =0; i<5 ; i++){
				long t1 = System.currentTimeMillis();
//				FtpUtil.uploadToFtp(TEST_FTP, "/ahli/TONE/600902-2008430/tone/2009/09/21/2009092110", "爱我别走.wma", baos.toByteArray());
//				FtpUtil.uploadToFtp(TEST_FTP, "/ahli/TONE/600902-2008430/tone/2009/09/21/2009092110", "爱我别走.wma", baos.toByteArray());
				FtpUtil.uploadFileToFtp(TEST_FTP, "/ahli/TONE/600902-2008430/tone/2009/09/21/2009092110", "aaa.jpg", file);
//				System.out.println( "次数" + i + ":" + (System.currentTimeMillis() - t1));
//			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(null != fis){
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Test
	public void testUpload2()throws FtpException
	{
		String content  = new String("你好吗!\r\n你好吗!\r\n你好吗!\r\n");
		String filePath = "/srv/imp/CMSDEMO/COPYRIGHT/CLAIM/REPORT";
		FtpUtil.uploadToFtp2(FTP_ALIAS, filePath, "200911Test.txt", content.getBytes());
		
	}
	
	@Test
	public void testUploadFile()throws FtpException
	{
		String fileName = "E:\\CMS资料\\CMS四期\\CP管理\\smallCPBatchImportFile.xls";
		File file = new File(fileName);
		String filePath = "/srv/imp/CMSDEMO/COPYRIGHT/SMALLCP";
		FtpUtil.uploadFileToFtp(FTP_ALIAS, filePath, "smallCPBatchImportErrorFile.xls", file);
		
	}
	
}
