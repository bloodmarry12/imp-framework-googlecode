/*
 * 文件名：FileBean.java
 * 版权：  Copyright 2000-2001 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 描述：〈描述〉
 * 修改人：ahli
 * 修改时间：Jul 11, 2009
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.huawei.imp.framework.model.fileupload.domain;

import java.io.File;

/**
 * 包装文件
 * @author ahli
 * @version IMPV100R001DA0, Jul 11, 2009 
 * @since CMS IMPV100R001DA0
 */

public class FileBean
{
	// 文件对象
	private File file;
	
	// 文件长度
	private long length;
	
	/**
	 * 系统文件名
	 */
	private String fileName;
	
	/**
	 * 文件在服务器存储路径
	 */
	private String filePath;
	
	/**
	 * 文件内容
	 */
	private byte[] content;
	
	/**
	 * 该文件对应版权对象属性名称
	 */
	private String propertyName;
	
	
	/**
	 * 文件类型ID
	 */
	private String fileTypeID;
	
	/**
	 * 是否必须
	 */
	private boolean isNeed;
	
	public boolean isNeed() {
		return isNeed;
	}
	public void setNeed(boolean isNeed) {
		this.isNeed = isNeed;
	}
	/**
	 * 默认构造函数
	 * @param fileName    文件名称
	 * @param content     文件内容
	 * @param propertyName    文件路径对应版权属性
	 * @param fileTypeID  文件类型ID
	 */
	@Deprecated
	public FileBean(String fileName, byte[] content, String propertyName,String fileTypeID,boolean isNeed)
	{
		super();
		this.fileName = fileName;
		this.setContent(content);
		this.length = content.length;
		this.setPropertyName(propertyName);
		this.setFileTypeID(fileTypeID);
		this.setNeed(isNeed);
	}
	
	/**
	 * 构造方法
	 * @param file
	 * @param propertyName
	 * @param fileTypeID
	 * @param isNeed
	 * @param path 
	 */
	public FileBean(File file,String propertyName,String fileTypeID,boolean isNeed)
	{
		this.file = file;
		this.filePath = file.getPath();
		this.fileName = file.getName();
		this.length = file.length();
		this.propertyName = propertyName;
		this.fileTypeID = fileTypeID;
		this.isNeed = isNeed;
	}
	
	/**
	 * 上传文件至FTP时去除文件名中多余的UUID
	 * @return
	 */
	public String getRealFileName()
	{
		if(fileName.length() > 32 && fileName.contains("_"))
		{
			int index = fileName.indexOf("_");
			String tempFileName = fileName.substring(index+1);
			return tempFileName;
		}
		return fileName;
	}
	
	public String getFileName()
	{
		return fileName;
	}
	
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}
	
	@Deprecated
	public byte[] getContent()
	{
		if(null != content){
			return content.clone();
		}else{
			return null;
		}
	}
	
	@Deprecated
	public void setContent(byte[] content)
	{
		if(null != content){
			this.content = content.clone();
		}
	}
	public String getPropertyName()
	{
		return propertyName;
	}
	public void setPropertyName(String propertyName)
	{
		this.propertyName = propertyName;
	}
	public String getFileTypeID()
	{
		return fileTypeID;
	}
	public void setFileTypeID(String fileTypeID)
	{
		this.fileTypeID = fileTypeID;
	}


	public File getFile()
	{
		return file;
	}


	public void setFile(File file)
	{
		this.file = file;
	}


	public long getLength()
	{
		return length;
	}


	public void setLength(long length)
	{
		this.length = length;
	}
	public String getFilePath()
	{
		return filePath;
	}
	public void setFilePath(String filePath)
	{
		this.filePath = filePath;
	}
}
