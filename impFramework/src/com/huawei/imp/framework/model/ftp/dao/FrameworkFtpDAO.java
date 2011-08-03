/**
 * 
 */
package com.huawei.imp.framework.model.ftp.dao;

import java.util.List;
import java.util.Map;

import org.oproject.framework.orm.ibatis.bytecode.codegenerator.annotations.DynamicIbatisDAO;

import com.huawei.imp.framework.model.ftp.domain.FtpConfig;

/**
 * FTP数据访问接口
 * @author ahli
 * @version IMPV100R001DB0SP02, 2010-6-23
 * @since CMS IMPV100R001DB0SP02
 */
@DynamicIbatisDAO(value="frameworkFtpDAO", sqlMapClientTemplate = "sqlMapClientTemplate0")
public interface FrameworkFtpDAO
{
	List<FtpConfig> queryFtpConfigForList(Map<String, Object> params);

	/**
	 * 更新FTP配置
	 * @param config
	 */
	void update(FtpConfig config);

	/**
	 * 加载FTP配置对象
	 * @param ftpAlias
	 * @return
	 */
	FtpConfig select(String ftpAlias);
	
}
