/**
 * 
 */
package com.huawei.imp.framework.model.ftp;

import org.apache.commons.pool.BasePoolableObjectFactory;

import com.huawei.imp.framework.logger.LogFactory;
import com.huawei.imp.framework.logger.Logger;
import com.huawei.imp.framework.model.ftp.domain.FtpConfig;
import com.huawei.imp.framework.model.ftp.impl.FtpConnectionImpl;

/**
 * Ftp对象
 * <功能描述/>
 * @author ahli
 * @date 2009-8-17
 */
public class FtpObjectFactory extends BasePoolableObjectFactory{

	/**
	 * 日志对象
	 */
	private static final Logger log = LogFactory.getLogger(FtpObjectFactory.class);
	
	/**
	 * FTP配置对象
	 */
	private FtpConfig config;
	
	/**
	 * 保护类型构造函数，避免在该包意外的地方被实例化
	 * @param config    ftp配置对象
	 */
	protected FtpObjectFactory(FtpConfig config){
		this.config = config;
	}
	
	public FtpConfig getConfig() {
		if(null != config){
			return config.clone();
		}else{
			return null;
		}
	}

	public void setConfig(FtpConfig config) {
		if(null != config){
			this.config = config.clone();
		}else{
			this.config = null;
		}
	}
	
	/* (non-Javadoc)
	 * @see org.apache.commons.pool.BasePoolableObjectFactory#makeObject()
	 */
	@Override
	public Object makeObject() throws Exception {
		FtpConnection fc = new FtpConnectionImpl(config);
		return fc;
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.pool.BasePoolableObjectFactory#destroyObject(java.lang.Object)
	 */
	@Override
	public void destroyObject(Object obj) throws Exception {
		FtpConnectionImpl fc = (FtpConnectionImpl)obj;
		fc.destroy();
		fc = null;
		if(log.isDebugEnabled()){
			log.debug("destroyObject(" + obj + ")");
		}
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.pool.BasePoolableObjectFactory#validateObject(java.lang.Object)
	 */
	@Override
	public boolean validateObject(Object obj) {
		FtpConnectionImpl ftpCon = (FtpConnectionImpl) obj;
		boolean flag = false;
		if(ftpCon.isConnected() && ftpCon.getFtpConfig().equals(config)){
			flag = ftpCon.isConnected();
		}
		if(log.isDebugEnabled()){
			log.debug("validateObject(" + obj + ") return " + flag + ";");
		}
		return flag;
	}
}
