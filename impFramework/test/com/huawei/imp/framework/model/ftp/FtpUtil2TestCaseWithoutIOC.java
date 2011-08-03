/**
 * 
 */
package com.huawei.imp.framework.model.ftp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

import com.huawei.imp.framework.model.ftp.dao.FtpDAO;
import com.huawei.imp.framework.model.ftp.domain.FtpConfig;
import com.huawei.imp.framework.model.ftp.exception.FtpException;
import com.huawei.imp.framework.model.ftp.service.FtpService;
import com.huawei.imp.framework.model.ftp.service.impl.FtpServiceImpl;
import com.huawei.imp.framework.utils.PageResult;
import com.huawei.imp.framework.utils.PropertyUtil;

/**
 * Ftp工具类单元测试用例(脱离IOC测试)
 * @author ahli
 * @version IMPV100R001DA0, 2010-2-27
 * @since CMS IMPV100R001DA0
 */
public class FtpUtil2TestCaseWithoutIOC
{

	public static final String TEST200 = "TEST200";
	public static final String TEST200_URL = "TEST200";
	public static final String TEST242 = "TEST242";
	
	public FtpUtil2TestCaseWithoutIOC(){
		list.add(new FtpConfig(TEST200, "tester", "tester",
				21, "218.200.227.200"));
		list.add(new FtpConfig(TEST242, "testftp", "testftp",
				21, "218.200.174.242"));
		PropertyUtil.setFieldValue(ftpService, "ftpDAO", FtpDAO.class, ftpDao);
		((FtpServiceImpl)ftpService).initFtpPools();
		
	}
	
	private void before() throws FtpException{
			// 做测试数据
			FtpUtil.delFormFtp(TEST200, "/单元测试/getFileSize/测试文件1.txt");
			FtpUtil.uploadToFtp(TEST200, "/单元测试/getFileSize", "测试文件1.txt", "测试文件1".getBytes());
	}
	
	/**
	 * 测试获取文件大小
	 * @throws Exception
	 */
	@Test
	public void testGetFileSize() throws Exception{
		org.junit.Assert.assertTrue(FtpUtil2.getFtpFileSize("ftp://tester:tester@218.200.227.200/单元测试/getFileSize/测试文件1.txt") > 0L);
		org.junit.Assert.assertTrue(FtpUtil2.getFtpFileSize("ftp://tester:tester@218.200.227.200:21/单元测试/getFileSize/测试文件1.txt") > 0L);
		org.junit.Assert.assertTrue(FtpUtil2.getFtpFileSize("ftp://tester:tester@218.200.227.200/单元测试/getFileSize/测试文件2.txt")== 0);
		org.junit.Assert.assertTrue(FtpUtil2.getFtpFileSize("ftp://218.200.227.200/单元测试/getFileSize/测试文件2.txt")== 0);
	}
	
	/**
	 * 测试获取文件大小
	 * @throws Exception
	 */
	@Test
	public void testGetFileSize2() throws Exception{
		PropertyConfigurator.configure("WebRoot/WEB-INF/log4j.properties");
		org.junit.Assert.assertTrue(FtpUtil2.getFtpFileSize("ftp://testftp:testftp@218.200.174.242/public/600902-2008430/tone/2010/0dfd000afbdf4c68bcf21996dfaf5945.xls") > 0L);
	}
//	try{
//		readFtpConfigFromAbsolutePath(null);
//		Assert.fail("未捕获参数为空异常");
//	}catch(FtpException e){
//		Assert.assertEquals("absolutePath is Empty : <null>", e.getMessage());
//	}
//	
//	try{
//		readFtpConfigFromAbsolutePath("imp:imp@218.200.160.110/home/ftpuser/TONE/600902-2008430/tone/2010/03/17/2010031715/爱的抱抱-宣传照-郭书瑶-09 .gif");
//		Assert.fail("未捕获参数异常:绝对路径未以\"ftp://\"开头");
//	}catch(FtpException e){
//		Assert.assertEquals("absolutePath is not start with \"ftp://\" : <imp:imp@218.200.160.110/home/ftpuser/TONE/600902-2008430/tone/2010/03/17/2010031715/爱的抱抱-宣传照-郭书瑶-09 .gif>", e.getMessage());
//	}
//	
//	try{
//		FtpConfig fc = readFtpConfigFromAbsolutePath("ftp://imp:123@218.200.160.110:23/home/ftpuser/TONE/600902-2008430/tone/2010/03/17/2010031715/爱的抱抱-宣传照-郭书瑶-09 .gif");
//		Assert.assertEquals(new FtpConfig(null, "imp", "123", 23, "218.200.160.110"), fc);
//	}catch(FtpException e){
//		Assert.fail(e.toString());
//	}
//	
//	try{
//		FtpConfig fc = readFtpConfigFromAbsolutePath("ftp://218.200.160.110:23/home/ftpuser/TONE/600902-2008430/tone/2010/03/17/2010031715/爱的抱抱-宣传照-郭书瑶-09 .gif");
//		Assert.assertEquals(new FtpConfig(null, null, null, 23, "218.200.160.110"), fc);
//	}catch(FtpException e){
//		Assert.fail(e.toString());
//	}
//	
//	try{
//		long size = getFtpFileSize("ftp://tester:tester@218.200.227.200/CMS4-第二轮测试/Linux基础命令教程豪华版.chm");
//		Assert.assertTrue(size > 0);
//		System.out.println(size);
//	}catch(FtpException e){
//	}
	

	private final List<FtpConfig> list = new ArrayList<FtpConfig>();
	
	private FtpService ftpService = new FtpServiceImpl();
	
	private FtpDAO ftpDao = new FtpDAO()
	{
		
		@Override
		public void update(Object obj)
		{
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public <T> Long selectSequence(Class<T> clazz)
		{
			// TODO Auto-generated method stub
			return null;
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public <T> List<T> selectAll(Class<T> clazz)
		{
			return (List<T>)list;
		}
		
		@Override
		public <T> T select(Class<T> clazz, Object parameter)
		{
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public PageResult queryForPageResult(Map<String, Object> params,
				int pageSize, int pageNum, Class<? extends Object> clazz)
		{
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public List<? extends Object> queryForList(Map<String, Object> params,
				Class<? extends Object> clazz)
		{
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Object insert(Object obj)
		{
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public void delete(Class<? extends Object> clazz, Object parameter)
		{
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public <T> void batchUpdate(String sqlLabel, Collection<T> col)
		{
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public <T> void batchInsert(String sqlLabel, Collection<T> col)
		{
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public <T> void batchDelete(String sqlLabel, Collection<T> col)
		{
			// TODO Auto-generated method stub
			
		}
	};
}
