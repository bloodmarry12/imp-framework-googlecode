package com.huawei.imp.framework.model.dblog.domain;

import java.util.Date;

/**
 * <P>
 * 类描述：数据库日志基类
 * </P>
 * 
 * @author yangl
 * @version IMP V100R001DA0
 */
public class LogBean
{
	/**
	 * 日志ID，一个操作流程的唯一标识
	 */
	private String logID;
	
	/**
	 * 操作者账号
	 */
	private String oprCode;
	
	/**
	 * 操作模块描述
	 */
	private String moduleName;
	
	/**
	 * 操作时间
	 */
	private Date oprTime;
	
	/**
	 * 操作机器IP
	 */
	private String oprIP;
	
	/**
	 * 输入参数字串
	 */
	private String inParams;

	/**
	 * 返回参数字串
	 */
	private String outParams;
	
	/**
	 * 操作结果
	 */
	private int result = 0;
	
	public LogBean()
	{
		oprTime = new Date(System.currentTimeMillis());
	}
	
	public LogBean(String logID)
	{
		oprTime = new Date(System.currentTimeMillis());
		this.logID = logID;
	}

	public String getOprCode()
	{
		return oprCode;
	}
	
	public void setOprCode(String string)
	{
		this.oprCode = string;
	}

	public Date getOprTime()
	{
		Date _oprTime = null;
		if (null != oprTime)
		{
			oprTime = (Date) oprTime.clone();
		}
		return _oprTime;
	}

	public void setOprTime(Date oprTime)
	{
		if (null != oprTime)
		{
			this.oprTime = (Date) oprTime.clone();
		}
		else
		{
			this.oprTime = null;
		}
	}
	
	public String getLogID()
	{
		return logID;
	}

	public void setLogID(String logID)
	{
		this.logID = logID;
	}
	

	public String getOprIP()
	{
		return oprIP;
	}

	public void setOprIP(String oprIP)
	{
		this.oprIP = oprIP;
	}

	public int getResult()
	{
		return result;
	}

	public void setResult(int result)
	{
		this.result = result;
	}

	public String getInParams()
	{
		return inParams;
	}

	public void setInParams(String inParams)
	{
		this.inParams = inParams;
	}

	public String getOutParams()
	{
		return outParams;
	}

	public void setOutParams(String outParams)
	{
		this.outParams = outParams;
	}

	public String getModuleName()
	{
		return moduleName;
	}

	public void setModuleName(String moduleName)
	{
		this.moduleName = moduleName;
	}
	
}
