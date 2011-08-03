package com.huawei.imp.framework.model.ftp.domain;

import com.huawei.imp.framework.common.domain.Namingspace;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * Description:
 * FPT配置对象
 * @author ahli
 * May 9, 2009
 * 
 */
@Namingspace("_framework_ftp")
public class FtpConfig implements Cloneable
{

	/**
	 * ftp别名
	 */
	private String ftpAlias;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 密码
	 */
	private String userPaswd;
	/**
	 * 端口
	 */
	private int port;
	/**
	 * IP地址
	 */
	private String ip;
	
	/**
	 * 默认空构造函数
	 */
	public FtpConfig(){}
	
	/**
	 * 配置对象构造函数
	 * @param ftpAlias	FTP别名(唯一)
	 * @param userName	用户名
	 * @param userPaswd	密码
	 * @param port		端口
	 * @param ip		IP地址
	 */
	public FtpConfig(String ftpAlias, String userName, String userPaswd,
			int port, String ip)
	{
		super();
		this.ftpAlias = ftpAlias;
		this.userName = userName;
		this.userPaswd = userPaswd;
		this.port = port;
		this.ip = ip;
	}

	public String getFtpAlias()
	{
		return ftpAlias;
	}

	public void setFtpAlias(String ftpAlias)
	{
		this.ftpAlias = ftpAlias;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getUserPaswd()
	{
		return userPaswd;
	}

	public void setUserPaswd(String userPaswd)
	{
		this.userPaswd = userPaswd;
	}

	public int getPort()
	{
		return port;
	}

	public void setPort(int port)
	{
		this.port = port;
	}

	public String getIp()
	{
		return ip;
	}

	public void setIp(String ip)
	{
		this.ip = ip;
	}
	
	@Override
	public FtpConfig clone() {
		FtpConfig config = null;
		try {
			config = (FtpConfig)super.clone();
			config.setFtpAlias(ftpAlias);
			config.setIp(ip);
			config.setPort(port);
			config.setUserName(userName);
			config.setUserPaswd(userPaswd);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return config;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(200);
		sb.append("ftpAlias:" + ftpAlias);
		sb.append(" ,userName:" + userName);
		sb.append(" ,userPaswd:" + userPaswd);
		sb.append(" ,port:" + port);
		sb.append(" ,ip:" + ip);
		return sb.toString();
	}
	
	@Override
	public boolean equals(final Object other)
	{
		if (!(other instanceof FtpConfig))
			return false;
		FtpConfig castOther = (FtpConfig) other;
		return new EqualsBuilder().append(ftpAlias, castOther.ftpAlias).append(
				userName, castOther.userName).append(userPaswd,
				castOther.userPaswd).append(port, castOther.port).append(ip,
				castOther.ip).isEquals();
	}

	@Override
	public int hashCode()
	{
		return new HashCodeBuilder().append(ftpAlias).append(userName).append(
				userPaswd).append(port).append(ip).toHashCode();
	}
}
