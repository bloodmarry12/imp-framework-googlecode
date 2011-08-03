/*
 * 文件名：AddToneOrderForm.java
 * 版权：  Copyright 2000-2001 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 描述：  铃音制作单的创建表单
 * 修改人：ahli
 * 修改时间：Jul 13, 2009
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.huawei.imp.framework.model.excel2.mock;


/**
 * 铃音制作单的创建表单
 * @author ahli
 * @version IMPV100R001DA0, Jul 13, 2009 
 * @see java.io.Serializable
 * @since CMS IMPV100R001DA0
 */
public class AddToneOrderForm implements java.io.Serializable
{
	/**
	 * 默认序列号
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * NULL 对象
	 */
	public static final AddToneOrderForm NULL = new AddToneOrderForm();
	
	/**
	 * 派发的版权ID
	 */
	private String copyrightIDs;
	
	/**
	 * 创建铃音的SPID
	 */
	private String spid;
	
	/**
	 * 铃音制作商
	 */
	private Long producers;
	
//	/**
//	 * 铃音类型
//	 */
//	private String[] type;
	
	/**
	 * 版本名称
	 */
	private String versionName;
	

	public String getCopyrightIDs()
	{
		return copyrightIDs;
	}

	public void setCopyrightIDs(String copyrightIDs)
	{
		this.copyrightIDs = copyrightIDs;
	}

	public String getSpid()
	{
		return spid;
	}

	public void setSpid(String spid)
	{
		this.spid = spid;
	}

	public Long getProducers()
	{
		return producers;
	}

	public void setProducers(Long producers)
	{
		this.producers = producers;
	}

//	public String[] getType()
//	{
//		if (type != null)
//		{
//			return type.clone();
//		}
//		else
//		{
//			return null;
//		}
//	}
//
//	public ToneType[] getToneTypes(){
//		if (type != null && type.length > 0)
//		{
//			ToneType[] toneTypes = new ToneType[type.length];
//			for (int i = 0; i < type.length; i++)
//			{
//				toneTypes[i] = ToneType.getInstance(type[i]);
//			}
//			return toneTypes;
//		}
//
//		return null;
//	}
	
//	public void setType(String[] type)
//	{
//		if (type != null)
//		{
//			this.type = type.clone();
//		}
//		else
//		{
//			this.type = null;
//		}
//	}


	public String getVersionName()
	{
		return versionName;
	}

	public void setVersionName(String versionName)
	{
		this.versionName = versionName;
	}
	
	
}
