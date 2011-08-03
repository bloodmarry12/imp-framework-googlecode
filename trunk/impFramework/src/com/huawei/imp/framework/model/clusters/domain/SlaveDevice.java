/**
 * 
 */
package com.huawei.imp.framework.model.clusters.domain;

import java.util.Date;

import com.huawei.imp.framework.common.domain.Namingspace;

/**
 * 从属服务器设备
 * @author ahli
 * @version IMPV100R001DA0, 2009-12-28
 * @since CMS IMPV100R001DA0
 */
@Namingspace("_framework_clusters")
public class SlaveDevice {

	/**
	 * 最后操作状态：1-成功
	 */
	public static final String LAST_STATUS_SUCCESS = "1";
	
	/**
	 * 最后操作状态：0-失败
	 */
	public static final String LAST_STATUS_FAILS = "0";
	
	/**
	 * 从属设备地址
	 */
	private String url;
	
	/**
	 * 从属设备最后状态报告时间
	 */
	private Date lastActiveTime;
	
	/**
	 * 从属设备最后刷新状态
	 * 0-失败；
	 * 1-成功；  
	 */
	private String lastStatus;
	
	/**
	 * 当前应用
	 */
	private String localhost;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getLastActiveTime() {
		if (null != lastActiveTime)
		{
			return (Date) lastActiveTime.clone();
		}
		else
		{
			return null;
		}
	}

	public void setLastActiveTime(Date lastActiveTime) {
		if(null != lastActiveTime){
			this.lastActiveTime = (Date)lastActiveTime.clone();
		}else{
			this.lastActiveTime = null;
		}
	}

	public String getLastStatus() {
		return lastStatus;
	}

	public void setLastStatus(String lastStatus) {
		this.lastStatus = lastStatus;
	}

	public String getLocalhost() {
		return localhost;
	}

	public void setLocalhost(String localhost) {
		this.localhost = localhost;
	}
}
