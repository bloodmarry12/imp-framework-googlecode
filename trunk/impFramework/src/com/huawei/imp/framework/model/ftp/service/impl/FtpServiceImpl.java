/**
 * 
 */
package com.huawei.imp.framework.model.ftp.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huawei.imp.framework.model.ftp.FtpConnectionPools;
import com.huawei.imp.framework.model.ftp.dao.FtpDAO;
import com.huawei.imp.framework.model.ftp.domain.FtpConfig;
import com.huawei.imp.framework.model.ftp.service.FtpService;

/**
 * <一句话功能/>
 * <功能描述/>
 * @author ahli
 * @date 2009-8-18
 */
@Service("framework.ftpService")
public class FtpServiceImpl implements FtpService {

	@Autowired
	@Qualifier("framework.ftpDAO")
	private FtpDAO ftpDAO;
	
	@PostConstruct
	@Transactional
	public void initFtpPools(){
		List<FtpConfig> list = ftpDAO.selectAll(FtpConfig.class);
		FtpConnectionPools.initFtpPool(list);
	}

	@Override
	@Transactional
	public List<FtpConfig> findAll()
	{
		return ftpDAO.selectAll(FtpConfig.class);
	}

	@Override
	@Transactional
	public FtpConfig load(String ftpAlias)
	{
		return ftpDAO.select(FtpConfig.class, ftpAlias);
	}

	@Override
	@Transactional
	public void update(FtpConfig config)
	{
		FtpConfig pfc = config.clone();
		ftpDAO.update(pfc);
		FtpConnectionPools.resetFtpObjectFactory(config);
	}
}
