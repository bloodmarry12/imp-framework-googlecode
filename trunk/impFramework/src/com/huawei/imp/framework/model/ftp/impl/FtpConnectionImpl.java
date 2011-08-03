package com.huawei.imp.framework.model.ftp.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.huawei.imp.framework.logger.LogFactory;
import com.huawei.imp.framework.logger.Logger;
import com.huawei.imp.framework.model.ftp.FtpConnection;
import com.huawei.imp.framework.model.ftp.FtpConnectionPools;
import com.huawei.imp.framework.model.ftp.domain.FtpConfig;
import com.huawei.imp.framework.model.ftp.exception.FtpException;
import com.huawei.imp.framework.utils.BeanHolder;
import com.jscape.inet.ftp.Ftp;
import com.jscape.inet.ftp.FtpFile;

/**
 * ftp链接实现类
 * FTP链接实现类，包装了apache的ftpclient对象，配合pool做了连接池化。
 * @author ahli
 * @version IMPV100R001DA0, Sep 17, 2009 
 * @see com.huawei.imp.framework.model.ftp.FtpConnection
 * @since CMS IMPV100R001DA0
 */
public class FtpConnectionImpl implements FtpConnection {

	/**
	 * 日志对象
	 */
	private static final Logger log = LogFactory.getLogger(FtpConnectionImpl.class);
	
//	/**
//	 * 当前jvm环境的编码格式
//	 */
//	private static final String CUR_ENCODING = System.getProperty("sun.jnu.encoding");
//	
//	/**
//	 * fpt服务的字符集合，与实际FTP配置无关。是数据交互的一个编码格式
//	 * 一般不用修改。
//	 */
//	private static final String FTP_ENCODING = "ISO-8859-1";
	
	/**
	 * Fpt对象
	 */
	private Ftp ftp = null;

	/**
	 * ftp配置对象
	 */
	private FtpConfig ftpConfig = null;
	
//	// 最后活动时间
//	private long lastActiveTime = System.currentTimeMillis();

	/**
	 * 获取FTP别名
	 * @return
	 */
	public String getFtpAlias(){
		return ftpConfig.getFtpAlias();
	}
	
	/**
	 * 获取ftp配置
	 * @return
	 */
	public FtpConfig getFtpConfig() {
		return ftpConfig;
	}

	/**
	 * 默认构造函数
	 * @param ftpConfig        ftp配置项
	 * @throws FtpException 初始化apache的ftpclient对象的时候失败，会抛出异常
	 */
	public FtpConnectionImpl(FtpConfig ftpConfig) throws FtpException {
		this.ftpConfig = ftpConfig;
		init();
	}

	/**
	 * 初始化ftp链接
	 * @throws FtpException 如果链接失败，则抛出异常
	 */
	private void init() throws FtpException{
		if(log.isDebugEnabled()){
			log.debug("init()");
		}
		try
		{
			ftp = new Ftp(ftpConfig.getIp(), ftpConfig.getUserName(), ftpConfig.getUserPaswd(), ftpConfig.getPort());
			ftp.connect();
			ftp.setBinary();
		}
		catch (com.jscape.inet.ftp.FtpException e)
		{
			log.error(e.toString());
			throw new FtpException(BeanHolder.getMessage("framework.model.exception.init.exp", new Object[]{"Socket"}), e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.model.ftp.FtpConnection#isFtpFileExist(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public boolean isFtpFileExist(String path)
			throws FtpException {
		if (!path.startsWith("/")) {
			path = "/" + path;
		}
		try {
			java.util.Enumeration list = ftp.getNameListing(path);
			while (list.hasMoreElements())
			{
				return true;
			}
			return false;
		} catch (com.jscape.inet.ftp.FtpException e) {
			return false;
		} catch (Exception e){
			throw new FtpException(BeanHolder.getMessage("framework.model.exception.opt.exp", new Object[]{"IO"}), e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.model.ftp.FtpConnection#uploadToFtp(java.lang.String, java.lang.String, byte[])
	 */
	public void uploadToFtp(String path, String fileName, byte[] file) throws FtpException{
		if (!path.startsWith("/")) {
			path = "/" + path;
		}
		String[] paras = path.split("/");
		try
		{
			ftp.setDir("/");
			
			for (int i = 0; i < paras.length; i++)
			{
				if (paras[i].trim().equals(""))
				{
					continue;
				}
				try
				{
					ftp.setDir(paras[i]);
				}
				catch (com.jscape.inet.ftp.FtpException e)
				{
					try
					{
						ftp.makeDir(paras[i]);
					}
					catch (com.jscape.inet.ftp.FtpException ex)
					{
						if(log.isDebugEnabled()){
							log.debug(ex.toString());
						}
					}
					ftp.setDir(paras[i]);
				}
			}

			try
			{
				ftp.deleteFile(fileName);
			}
			catch (com.jscape.inet.ftp.FtpException e)
			{
				if(log.isDebugEnabled()){
					log.debug(e.toString());
				}
			}
			ftp.upload(file, fileName);
		}
		catch (com.jscape.inet.ftp.FtpException e)
		{
			throw new FtpException(BeanHolder.getMessage("framework.model.exception.opt.exp", new Object[]{"FTP"}), e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.model.ftp.FtpConnection#uploadToFtp(java.lang.String, java.lang.String, byte[])
	 */
	public void uploadToFtp2(String path, String fileName, byte[] file) throws FtpException{
		if (!path.startsWith("/")) {
			path = "/" + path;
		}
		String[] paras = path.split("/");
		try
		{
			ftp.setDir("/");
			
			for (int i = 0; i < paras.length; i++)
			{
				if (paras[i].trim().equals(""))
				{
					continue;
				}
				try
				{
					ftp.setDir(paras[i]);
				}
				catch (com.jscape.inet.ftp.FtpException e)
				{
					try
					{
						ftp.makeDir(paras[i]);
					}
					catch (com.jscape.inet.ftp.FtpException ex)
					{
						if(log.isDebugEnabled()){
							log.debug(ex.toString());
						}
					}
					ftp.setDir(paras[i]);
				}
			}
			// 上传文件内容,不删除原来的内容,在原来的基础上添加
			ftp.upload(file, fileName, true);
		}
		catch (com.jscape.inet.ftp.FtpException e)
		{
			throw new FtpException(BeanHolder.getMessage("framework.model.exception.opt.exp", new Object[]{"FTP"}), e);
		}
		
	}
	
	@Override
	public void uploadFile(File file, String filePath) throws FtpException
	{
		if (!filePath.startsWith("/")) {
			filePath = "/" + filePath;
		}
		String[] paras = filePath.split("/");
		try
		{
			ftp.setDir("/");
			
			for (int i = 0; i < paras.length; i++)
			{
				if (paras[i].trim().equals(""))
				{
					continue;
				}
				try
				{
					ftp.setDir(paras[i]);
				}
				catch (com.jscape.inet.ftp.FtpException e)
				{
					try
					{
						ftp.makeDir(paras[i]);
					}
					catch (com.jscape.inet.ftp.FtpException ex)
					{
						if(log.isDebugEnabled()){
							log.debug(ex.toString());
						}
					}
					ftp.setDir(paras[i]);
				}
			}
			// 上传文件内容,不删除原来的内容,在原来的基础上添加
			ftp.upload(file);
		}
		catch (com.jscape.inet.ftp.FtpException e)
		{
			throw new FtpException(BeanHolder.getMessage("framework.model.exception.opt.exp", new Object[]{"FTP"}), e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.model.ftp.FtpConnection#downloadFromFtp(java.lang.String, java.lang.String)
	 */
	public byte[] downloadFromFtp(String path, String fileName) throws FtpException{
//		String _path = path.endsWith("/")? path:path.substring(0, path.length() - 1);
		// 路径无"/"则加上
		String _path = path.endsWith("/")? path:(path + "/");
		return downloadFromFtp(_path + fileName );
	}
	
	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.model.ftp.FtpConnection#downloadFromFtp(java.lang.String)
	 */
	public byte[] downloadFromFtp(String filePath) throws FtpException{
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		if(!filePath.startsWith("/")){
			filePath = "/" + filePath;
		}
		try {
			ftp.download(bout, filePath);
			return bout.toByteArray();
		} catch (Exception e) {
			throw new FtpException(BeanHolder.getMessage("framework.model.exception.opt.exp", new Object[]{"IO"}), e);
		}finally{
			if(null != bout){
				try {
					bout.close();
				} catch (IOException e) {
					log.error("关闭ftp下载输出流失败。", e);
				}
			}
		}
	}
	
	
	public void downloadFromFtp(String filePath,OutputStream os)throws FtpException
	{
		if(!filePath.startsWith("/")){   
			filePath = "/" + filePath;
		}
		try {    
			ftp.download(os,filePath);
		} catch (Exception e) {
			throw new FtpException(BeanHolder.getMessage("framework.model.exception.opt.exp", new Object[]{"IO"}), e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.model.ftp.FtpConnection#getFtpFileSize(java.lang.String, java.lang.String)
	 */
	public long getFtpFileSize(String path, String fileName) throws FtpException{
		return getFtpFileSize(path.endsWith("/")?path + fileName: path + "/" + fileName);
	}
	
	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.model.ftp.FtpConnection#getFtpFileSize(java.lang.String)
	 */
	public long getFtpFileSize(String filePath) throws FtpException{
		long size = 0L;
		if(!filePath.startsWith("/")){
			filePath = "/" + filePath;
		}
		try 
		{
			size = ftp.getFilesize(filePath);
		} 
		catch(com.jscape.inet.ftp.FtpException e)
		{
			log.error(e.toString());
		}
		catch (Exception e) 
		{
			throw new FtpException(BeanHolder.getMessage("framework.model.exception.opt.exp", new Object[]{"IO"}), e);
		}
		return size;
	}

	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.model.ftp.FtpConnection#close()
	 */
	@Override
	public void close() {
		FtpConnectionPools.returnObject(this);
	}

	/**
	 * 释放连接
	 * 
	 * @throws FtpException
	 */
	public void destroy() throws FtpException {
		if (null != ftp && ftp.isConnected()) {
			try {
				ftp.disconnect();
			} catch (Exception e) {
				throw new FtpException(BeanHolder.getMessage("framework.model.exception.destory", new Object[]{"IO"}), e);
			}
		}
		ftp = null;
	}
	
   /**
    * 校验链接
	* @return
	*/
   public boolean isConnected(){
	  return ftp.isConnected();
   }

	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.model.ftp.FtpConnection#getFtpFileNames(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getFtpFileNames(String path) throws FtpException
	{
		List<String> tmpFiles = new ArrayList<String>();;
		try{
			java.util.Enumeration tmp = ftp.getDirListing(path);
			String filename = null;
		    while (tmp.hasMoreElements())
		    {
		    	FtpFile ftpFile = (FtpFile) tmp.nextElement();
				filename = ftpFile.getFilename();
				if (null != filename
						&& (filename.substring(filename.lastIndexOf(".") + 1))
								.trim().length() > 0)
				{
					tmpFiles.add(filename);
				}
		    }
		}catch(Exception e){
			throw new FtpException(BeanHolder.getMessage("framework.model.exception.opt.exp", new Object[]{"IO"}), e);
		}
		return tmpFiles;
	}

	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.model.ftp.FtpConnection#delFileFromFtp(java.lang.String)
	 */
	@Override
	public void delFileFromFtp(String filePath) throws FtpException
	{
		if (!filePath.startsWith("/"))
		{
			filePath = "/" + filePath;
		}
		try
		{
			ftp.deleteFile(filePath);
		}
		catch (com.jscape.inet.ftp.FtpException ex)
		{
			if(log.isDebugEnabled()){
				log.debug(ex.toString());
			}
		}
		catch (Exception e)
		{
			throw new FtpException(BeanHolder.getMessage("framework.model.exception.opt.exp", new Object[]{"IO"}), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.model.ftp.FtpConnection#uploadFileToFtp(java.lang.String, java.lang.String, java.io.File)
	 */
	@Override
	public void uploadFileToFtp(String path, String fileName, File file)
			throws FtpException
	{
		if (!path.startsWith("/")) {
			path = "/" + path;
		}
		String[] paras = path.split("/");
		try
		{
			ftp.setDir("/");
			
			for (int i = 0; i < paras.length; i++)
			{
				if (paras[i].trim().equals(""))
				{
					continue;
				}
				try
				{
					ftp.setDir(paras[i]);
				}
				catch (com.jscape.inet.ftp.FtpException e)
				{
					try
					{
						ftp.makeDir(paras[i]);
					}
					catch (com.jscape.inet.ftp.FtpException ex)
					{
						if(log.isDebugEnabled()){
							log.debug(e.toString());
						}
					}
					ftp.setDir(paras[i]);
				}
			}
			try
			{
				ftp.deleteFile(fileName);
			}
			catch (com.jscape.inet.ftp.FtpException e)
			{
				if(log.isDebugEnabled()){
					log.debug(e.toString());
				}
			}
			
			ftp.upload(file, fileName);
		}
		catch (com.jscape.inet.ftp.FtpException e)
		{
			if(log.isDebugEnabled())
			{
				log.debug(e.toString());
			}
			throw new FtpException(BeanHolder.getMessage("framework.model.exception.opt.exp", new Object[]{"FTP"}), e);
		}
		
	}

	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.model.ftp.FtpConnection#uploadToFtp(java.lang.String, java.lang.String, byte[], com.huawei.imp.framework.model.ftp.FtpConnection.UploadTransType)
	 */
	@Override
	public void uploadToFtp(String path, String fileName, byte[] file,
			UploadTransType transType) throws FtpException
	{
		if(UploadTransType.ESUME.equals(transType))
		{
			uploadToFtp2(path, fileName, file);
		}
		else
		{
			uploadToFtp(path, fileName, file);
		}
		
	}
	
}
