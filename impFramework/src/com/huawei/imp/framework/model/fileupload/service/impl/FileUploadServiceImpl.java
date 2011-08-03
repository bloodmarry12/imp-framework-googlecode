/*
 * 文件名：FileUploadServiceImpl.java
 * 版权：  Copyright 2000-2001 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 描述：  文件上传业务
 * 修改人：heg
 * 修改时间：Nov 2, 2009 
 */
package com.huawei.imp.framework.model.fileupload.service.impl;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

import com.huawei.imp.framework.exception.BusinessException;
import com.huawei.imp.framework.logger.LogFactory;
import com.huawei.imp.framework.logger.Logger;
import com.huawei.imp.framework.model.fileupload.IMPProgressListenerImpl;
import com.huawei.imp.framework.model.fileupload.IMPServletFileUpload;
import com.huawei.imp.framework.model.fileupload.domain.TempFileBean;
import com.huawei.imp.framework.model.fileupload.exception.UserInterruptedException;
import com.huawei.imp.framework.model.fileupload.service.FileUploadService;
import com.huawei.imp.framework.model.fileupload.service.ProgressMap;
import com.huawei.imp.framework.utils.BeanHolder;

/**
 * 
 * 文件上传到应用服务器本地目录
 * @author ahli
 * @version IMPV100R001DA0, Nov 2, 2009 
 * @see com.huawei.imp.framework.model.fileupload.service.FileUploadService
 * @since CMS IMPV100R001DA0
 */
public class FileUploadServiceImpl implements FileUploadService {

	private static final Logger log = LogFactory.getLogger(FileUploadServiceImpl.class);
	
	/**
	 * 进度报告
	 */
	private final ProgressMap progressMap = new ProgressMap();
	
	/**
	 * 默认缓冲块大小：50 * 1024byte
	 */
	public static final int DEFAULT_BUFFER_SIZE = 50 * 1024;
	
	/**
	 * common apache的fileupload磁盘文件处理工厂
	 */
	private DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
	
	/**
	 * IMP文件上传进度监听器
	 */
	private IMPProgressListenerImpl progressListener = null;
	
	/**
	 * 初始化方法，用于初始化该类的属性
	 */
	public FileUploadServiceImpl(){
		
		progressListener = new IMPProgressListenerImpl(progressMap);
		// 设置文件处理缓冲区大小
		diskFileItemFactory.setSizeThreshold(DEFAULT_BUFFER_SIZE);
	}
	
	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.model.fileupload.service.FileUploadService#loadUploadProcess(java.lang.String)
	 */
	@Override
	public int loadUploadProcess(String uuid) {
		Integer value = progressMap.get(uuid);
		return value == null ? 0 : value.intValue();
	}

	@Override
	public void cancelProcess(String uuid)
	{
		progressMap.cancel(uuid);
	}

	@Override
	public File getFileFromLocal(String filePath)
	{
		return new File(filePath);
	}

	@Override
	public boolean delTmpFile(String filePath)
	{
		File file = new File(filePath); 
		if(file.exists() && file.isFile())
		{
			return file.delete();
		}
		return false;
	}
	
	@Override
	public void batchDelTmpFile(List<String> localFilePaths)
	{
		for(String filePath : localFilePaths)
		{
			File file = new File(filePath); 
			if(file.exists() && file.isFile())
			{
				boolean deleted = file.delete();
				if(!deleted)
				{
					log.error("fail to delete file <" + file.getAbsolutePath() + "/" + file.getName() + ">");
				}
			}
		}
	}

	@Override
	public TempFileBean uploadTmpFile(HttpServletRequest request)
	{
		// 如果不是文件
		if (!ServletFileUpload.isMultipartContent(request)) {
			throw new BusinessException(BeanHolder.getMessage("imp.fileupload.requestTypeError"));
		}
		
		IMPServletFileUpload impServletFileUpload = new IMPServletFileUpload(this.diskFileItemFactory, this.progressMap, this.progressListener);
		try
		{
			return impServletFileUpload.parseRequestForIMPFileupload(new ServletRequestContext(request));
		}
		catch (FileUploadException e)
		{
			e.printStackTrace();
			throw new BusinessException(BeanHolder.getMessage("imp.fileupload.fail"));
		}
		catch (UserInterruptedException be){
			throw be;
		}
		finally{
			this.progressListener.clean();
		}
	}
}
