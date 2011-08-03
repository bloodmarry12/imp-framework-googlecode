/**
 * 
 */
package com.huawei.imp.framework.model.ftp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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
public class FtpUtilTestCaseWithoutIOC
{

	public static final String IMP110 = "TEST";
	public static final String TEST200 = "TEST200";
	public static final String TEST242 = "TEST200";
	
	public static final String[] FTP_ALIAS = new String[]{IMP110, TEST200};
	
	public FtpUtilTestCaseWithoutIOC(){
		list.add(new FtpConfig(IMP110, "imp", "imp",
				21, "218.200.160.110"));
		list.add(new FtpConfig(TEST200, "tester", "tester",
				21, "218.200.227.200"));
		list.add(new FtpConfig(TEST242, "testftp", "testftp",
				21, "218.200.174.242"));
		
		PropertyUtil.setFieldValue(ftpService, "ftpDAO", FtpDAO.class, ftpDao);
		((FtpServiceImpl)ftpService).initFtpPools();
	}
	
	/**
	 * 测试判断文件是否存在
	 * @throws Exception
	 */
	@Test
	public void testFileSize() throws Exception{
		testIsFtpFileExist(FTP_ALIAS);
	}
	
	
	@Before
	public void before() throws FtpException{
		before(FTP_ALIAS);
	}
	
	private void before(String[] ftpAlias) throws FtpException{
		for(String ftpAlia : ftpAlias){
			// 做测试数据
			FtpUtil.delFormFtp(ftpAlia, "/单元测试/getFileSize/测试文件1.txt");
			
			// 
			FtpUtil.uploadToFtp(ftpAlia, "/单元测试/getFileSize", "测试文件1.txt", "测试文件1".getBytes());
		}
	}
	
	/**
	 * 测试获取文件大小
	 * @throws Exception
	 */
	@Test
	public void testGetFileSize() throws Exception{
		testGetFileSize(FTP_ALIAS);
	}
	
	private void testGetFileSize(String[] ftpAlias) throws Exception{
		for(String ftpAlia : ftpAlias){
			Assert.isTrue(FtpUtil.getFtpFileSize(ftpAlia, "/单元测试/getFileSize/测试文件1.txt") > 0L);
			Assert.isTrue(FtpUtil.getFtpFileSize(ftpAlia, "/单元测试/getFileSize/测试文件2.txt") == 0L);
		}
		Assert.isTrue(FtpUtil2.getFtpFileSize("ftp://tester:tester@218.200.227.200/单元测试/getFileSize/测试文件1.txt") > 0L);
	}
	
	
	
	/**
	 * 测试判断文件是否存在
	 * @throws Exception
	 */
	@Test
	public void testIsFtpFileExist() throws Exception{
		testIsFtpFileExist(FTP_ALIAS);
	}
	
	private void testIsFtpFileExist(String[] ftpAlias) throws Exception{
		for(String ftpAlia : ftpAlias){
			Assert.isTrue( FtpUtil.isFtpFileExist(ftpAlia, "/单元测试/getFileSize/测试文件1.txt"));
			Assert.isTrue(!FtpUtil.isFtpFileExist(ftpAlia, "/单元测试/getFileSize/测试文件2.txt"));
		}
	}
	
	public void test(){
//		FtpUtil.isFtpFileExist(IMP110, "/liy/2010/04/12/版权/music/红豆-李子.mp3");
	}
	
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
