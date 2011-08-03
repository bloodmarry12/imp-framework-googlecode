/*
 * 文件名：HttpLoggerEntity.java
 * 版权：  Copyright 2000-2009 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 描述：〈描述〉
 * 修改人：ahli
 * 修改时间：Oct 27, 2009
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.huawei.imp.framework.model.httplog.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.huawei.imp.framework.common.domain.Namingspace;
import com.huawei.imp.framework.utils.StringUtil;

/**
 * 日志记录实体对象
 * 记录HTTP操作日志的数据bean类
 * @author ahli
 * @version IMPV100R001DA0, Oct 27, 2009 
 * @see java.io.Serializable
 * @since CMS IMPV100R001DA0
 */
@Namingspace("_framework_http_log")
public class HttpLoggerEntity implements Serializable
{
	
	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 操作ID
	 */
	private String id;
	
	/**
	 * 操作时间
	 */
	private Date operateDate;
	
	/**
	 * 请求的本地URL
	 */
	private String url;
	
	/**
	 * 前一个请求URL地址
	 */
	private String lastUrl;
	
	/**
	 * 用户ID
	 */
	private Long uid;
	
	/**
	 * 请求参数
	 */
	private String parameters;
	
	/**
	 * 备注(用于用户记录一些独特的业务日志信息)
	 */
	private String description;
	
	/**
	 * 操作时间
	 */
	private Long operateTime;
	
	/**
	 * 接入，请求IP
	 */
	private String remoteIP;
	
	/**
	 * Web应用服务的会话ID
	 */
	private String sessionID;
	
	/**
	 * 服务名称
	 */
	private String server;
	
	public HttpLoggerEntity(){
		
	}
	
	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public Date getOperateDate()
	{
		if (null != operateDate)
		{
			return (Date) operateDate.clone();
		}
		else
		{
			return null;
		}
	}

	public void setOperateDate(Date operateDate)
	{
		if (null != operateDate)
		{
			this.operateDate = (Date) operateDate.clone();
		}
		else
		{
			this.operateDate = null;
		}
		
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public Long getUid()
	{
		return uid;
	}

	public void setUid(Long uid)
	{
		this.uid = uid;
	}

	public String getParameters()
	{
		return parameters;
	}

	public void setParameters(String parameters)
	{
		if(null != parameters){
			this.parameters = StringUtil.subStringByChar(parameters, 0, 2000);
		}
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Long getOperateTime()
	{
		return operateTime;
	}

	public void setOperateTime(Long operateTime)
	{
		this.operateTime = operateTime;
	}

	public String getRemoteIP()
	{
		return remoteIP;
	}

	public void setRemoteIP(String remoteIP)
	{
		this.remoteIP = remoteIP;
	}

	public String getLastUrl()
	{
		return lastUrl;
	}
	
	public String getSessionID()
	{
		return sessionID;
	}

	public void setSessionID(String sessionID)
	{
		this.sessionID = sessionID;
	}

	public void setLastUrl(String lastUrl)
	{
		if (null != lastUrl)
		{
			int i = lastUrl.lastIndexOf("?");
			if (i > -1)
			{
				lastUrl = lastUrl.substring(0, i);
			}
		}
		this.lastUrl = lastUrl;
	}

	@Override
	public boolean equals(final Object other)
	{
		if (!(other instanceof HttpLoggerEntity))
			return false;
		HttpLoggerEntity castOther = (HttpLoggerEntity) other;
		return new EqualsBuilder().append(id, castOther.id).append(operateDate,
				castOther.operateDate).append(url, castOther.url).append(
				lastUrl, castOther.lastUrl).append(uid, castOther.uid).append(
				parameters, castOther.parameters).append(description,
				castOther.description).append(operateTime,
				castOther.operateTime).append(remoteIP, castOther.remoteIP)
				.append(sessionID, castOther.sessionID).isEquals();
	}

	@Override
	public int hashCode()
	{
		return new HashCodeBuilder().append(id).append(operateDate).append(url)
				.append(lastUrl).append(uid).append(parameters).append(
						description).append(operateTime).append(remoteIP)
				.append(sessionID).toHashCode();
	}

	@Override
	public String toString()
	{
		return new ToStringBuilder(this).append("id", id).append("operateDate",
				operateDate).append("url", url).append("lastUrl", lastUrl)
				.append("uid", uid).append("parameters", parameters).append(
						"description", description).append("operateTime",
						operateTime).append("remoteIP", remoteIP).append(
						"sessionID", sessionID).toString();
	}

	/**
	 * @return the server
	 */
	public String getServer()
	{
		return server;
	}

	/**
	 * @param server the server to set
	 */
	public void setServer(String server)
	{
		this.server = server;
	}
}
