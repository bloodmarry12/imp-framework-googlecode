/**
 * 
 */
package com.huawei.imp.framework.model.ftp.service;

import java.util.List;

import com.huawei.imp.framework.common.service.CommonService;
import com.huawei.imp.framework.model.ftp.domain.FtpConfig;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author ahli
 * @version IMPV100R001DA0, Aug 26, 2009 
 * @see [相关类/方法]
 * @since CMS IMPV100R001DA0
 */
public interface FtpService extends CommonService{

	/**
	 * @param FtpAlias
	 * @return
	 */
	FtpConfig load(String FtpAlias);
	
	/**
	 * @return
	 */
	List<FtpConfig> findAll();
	
	/**
	 * @param config
	 */
	void update(FtpConfig config);
}
