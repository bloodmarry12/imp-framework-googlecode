package com.huawei.imp.framework.model.fileupload.service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.huawei.imp.framework.model.fileupload.domain.TempFileBean;


/**
 * 文件上传服务
 * @author ahli
 * @version IMPV100R001DA0, Jun 9, 2009 
 * @since CMS IMPV100R001DA0
 */
public interface FileUploadService  
{

	/**
	 * 获取指定操作的进度
	 * @param uuid    操作关键字
	 * @return    处理进度(?%)
	 */
	int loadUploadProcess(String uuid);
	
	/**
	 * 上传文件到临时目录
	 * @param request    HTTP请求对象
	 * @return
	 */
	TempFileBean uploadTmpFile(HttpServletRequest request);
	
	/**
	 * 中断文件上传操作
	 * @param uuid    UUID
	 */
	void cancelProcess(String uuid);
	
	/**
	 * 从服务器本地端取得File
	 * @param filePath
	 * @return
	 */
	File getFileFromLocal(String filePath);
	
	/**
	 * 从服务器本地删除File
	 * @param filePath
	 */
	boolean delTmpFile(String filePath);
	
	/**
	 * 批量删除本地文件
	 * @param localFilePaths 存放文件路径
	 */
	void batchDelTmpFile(List<String> localFilePaths);
	
	
}
