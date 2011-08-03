/**
 * 
 */
package com.huawei.imp.framework.transaction.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.huawei.imp.framework.model.ftp.domain.FtpConfig;
import com.huawei.imp.framework.transaction.aspectj.annotation.MultTransactional;
import com.huawei.imp.framework.transaction.dao.MultDataSourceTestDAO;
import com.huawei.imp.framework.transaction.service.MultDataSourceTestService;

/**
 * 多数据源测试实例
 * @author ahli
 *
 */
@Service("multDataSourceTestService")
public class MultDataSourceTestServiceImpl implements MultDataSourceTestService {

	@Autowired
	@Qualifier("multDataSource0TestDAO")
	private MultDataSourceTestDAO multDataSource0TestDAO;
	
	@Autowired
	@Qualifier("multDataSource1TestDAO")
	private MultDataSourceTestDAO multDataSource1TestDAO;
	
	@Autowired
	@Qualifier("multDataSourceDerbyTestDAO")
	private MultDataSourceTestDAO multDataSourceDerbyTestDAO;
	
	@Autowired
	@Qualifier("multDataSourceDerbyMTestDAO")
	private MultDataSourceTestDAO multDataSourceDerbyMTestDAO;
	
	@PostConstruct
	public void init(){
//		multDataSourceDerbyTestDAO.
	}
	
	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.transaction.test.service.MultDataSourceTestService#testAddMultDatasourceData()
	 */
	@MultTransactional
	@Override
	public void testAddMultDatasourceData() {
		FtpConfig fc = new FtpConfig();
		fc.setFtpAlias("test1");
		fc.setIp("169.254.1.2");
		fc.setPort(21);
		fc.setUserName("ahli");
		fc.setUserPaswd("ahli");
		multDataSourceDerbyMTestDAO.insert(fc);
		multDataSource1TestDAO.insert(fc);
		multDataSource0TestDAO.insert(fc);
		multDataSourceDerbyTestDAO.insert(fc);
	}

	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.transaction.test.service.MultDataSourceTestService#testAddMultDatasourceDataWithExp()
	 */
	@MultTransactional
	@Override
	public void testAddMultDatasourceDataWithExp() {
		FtpConfig fc = new FtpConfig();
		fc.setFtpAlias("test1");
		fc.setIp("169.254.1.2");
		fc.setPort(21);
		fc.setUserName("ahli");
		fc.setUserPaswd("ahli");
		
		multDataSource0TestDAO.insert(fc);
		multDataSource1TestDAO.insert(fc);
//		multDataSourceDerbyTestDAO.insert(fc);
		multDataSourceDerbyMTestDAO.insert(fc);
	}

}
