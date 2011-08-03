package com.huawei.imp.framework.model.ftp;

import java.io.File;
import java.io.OutputStream;
import java.util.List;

import com.huawei.imp.framework.logger.LogFactory;
import com.huawei.imp.framework.logger.Logger;
import com.huawei.imp.framework.model.ftp.FtpConnection.UploadTransType;
import com.huawei.imp.framework.model.ftp.exception.FtpException;

/**
 * Description: FTP工具类,以来Spring的IOC容器中的ftpServiceImpl实例来获取Ftp配置数据
 * 
 * @author ahli May 5, 2009
 */
public class FtpUtil
{

	/**
	 * 日志对象
	 */
	private static final Logger log = LogFactory.getLogger(FtpUtil.class);
	
	/**
	 * 私有构造，避免被实例化
	 */
	private FtpUtil(){}
	
	/**
	 * 校验FTP服务器上的指定文件是否存在
	 * @param ftpAlias    Ftp配置别名
	 * @param path        文件路径
	 * @return    boolean-文件是否存在的
	 * @throws FtpException
	 */
	public static boolean isFtpFileExist(String ftpAlias, String path) throws FtpException{
		FtpConnection con = null;
		try {
			con = FtpConnectionPools.getFtpController(ftpAlias);
			if(log.isDebugEnabled())
			{
				log.debug("getFtpController():" + con);
			}
			return con.isFtpFileExist(path);
		} catch (Exception e) {
			// 如果异常是来自于FtpConnection的FtpException，则直接转型想上层抛出
			if (e instanceof FtpException)
			{
				throw (FtpException) e;
			}
			// 其他类型异常，在这里重新FtpException后，向上抛
			throw new FtpException(e);
		}finally {
			if(null != con){
				con.close();
			}
		}
	}
	
	/**
	 * 获取FTP服务器上指定的文件大小
	 * @param ftpAlias    Ftp配置别名
	 * @param filePath    文件路径
	 * @return
	 * @throws FtpException
	 */
	public static long getFtpFileSize(String ftpAlias, String filePath) throws FtpException{
		FtpConnection con = null;
		if(log.isDebugEnabled()){
			log.debug("getFtpFileSize()");
		}
		
		try
		{
			con = FtpConnectionPools.getFtpController(ftpAlias);
			if(log.isDebugEnabled()){
				log.debug("getFtpController():" + con);
			}
			return con.getFtpFileSize(filePath);
		}
		catch (Exception e)
		{
			// 如果异常是来自于FtpConnection的FtpException，则直接转型想上层抛出
			if (e instanceof FtpException)
			{
				throw (FtpException) e;
			}
			// 其他类型异常，在这里重新FtpException后，向上抛
			throw new FtpException(e);
		}
		finally
		{
			if (null != con)
			{
				con.close();
			}
		}
	}
	
	/**
	 * 获取FTP服务器上指定的文件大小
	 * @param path        文件路径
	 * @param fileName    文件名
	 * @return
	 * @throws FtpException 
	 */
	public static long getFtpFileSize(String ftpAlias, String path, String fileName) throws FtpException{
		FtpConnection con = null;
		try {
			con = FtpConnectionPools.getFtpController(ftpAlias);
			return con.getFtpFileSize(path, path);
		} catch (Exception e) {
			// 如果异常是来自于FtpConnection的FtpException，则直接转型想上层抛出
			if (e instanceof FtpException)
			{
				throw (FtpException) e;
			}
			// 其他类型异常，在这里重新FtpException后，向上抛
			throw new FtpException(e);
		}finally {
			if(null != con){
				con.close();
			}
		}
	}
	
	/**
	 * 从FTP上下载文件
	 * @param ftpAlias    ftp别名
	 * @param filePath     文件路径
	 * @return    字节数组
	 * @throws FtpException 
	 */
	public static byte[] downloadFromFtp(String ftpAlias, String filePath) throws FtpException{
		FtpConnection con = null;
		try {
			con = FtpConnectionPools.getFtpController(ftpAlias);
			return con.downloadFromFtp(filePath);
		} catch (Exception e) {
			// 如果异常是来自于FtpConnection的FtpException，则直接转型想上层抛出
			if (e instanceof FtpException)
			{
				throw (FtpException) e;
			}
			// 其他类型异常，在这里重新FtpException后，向上抛
			throw new FtpException(e);
		}finally {
			if(null != con){
				con.close();
			}
		}
	}
	
	/**
	 * 从FTP下载文件
	 * @param ftpAlias    ftp别名
	 * @param path        文件所在目录路径
	 * @param os    	  输出流
	 * @throws FtpException
	 */
	public static void downloadFromFtp(String ftpAlias, String filePath,OutputStream os)throws FtpException
	{
		FtpConnection con = null;
		
		try {
			con = FtpConnectionPools.getFtpController(ftpAlias);
			con.downloadFromFtp(filePath,os);
		} catch (Exception e) {
			// 如果异常是来自于FtpConnection的FtpException，则直接转型想上层抛出
			if (e instanceof FtpException)
			{
				throw (FtpException) e;
			}
			// 其他类型异常，在这里重新FtpException后，向上抛
			throw new FtpException(e);
		}finally {
			if(null != con){
				con.close();
			}
		}
	}
	
	/**
	 * 从FTP上下载文件
	 * @param ftpAlias    ftp别名
	 * @param path        文件所在目录路径
	 * @param fileName    文件名
	 * @return    			文件字节码
	 * @throws FtpException 
	 */
	public static byte[] downloadFromFtp(String ftpAlias, String path, String fileName) throws FtpException{
		FtpConnection con = null;
		try {
			con = FtpConnectionPools.getFtpController(ftpAlias);
			return con.downloadFromFtp(path, fileName);
		} catch (Exception e) {
			// 如果异常是来自于FtpConnection的FtpException，则直接转型想上层抛出
			if (e instanceof FtpException)
			{
				throw (FtpException) e;
			}
			// 其他类型异常，在这里重新FtpException后，向上抛
			throw new FtpException(e);
		}finally {
			if(null != con){
				con.close();
			}
		}
	}
	
	/**
	 * 上传文件到FTP服务器指定目录
	 * @param ftpAlias    ftp别名
	 * @param path        指定目录
	 * @param fileName    文件名
	 * @param file        文件字节数组
	 * @throws FtpException 
	 */
	public static void uploadToFtp(String ftpAlias, String path, String fileName, byte[] file) throws FtpException{
		FtpConnection con = null;
		try {
			con = FtpConnectionPools.getFtpController(ftpAlias);
			con.uploadToFtp(path, fileName, file, UploadTransType.VERWRITE);
		} catch (Exception e) {
			// 如果异常是来自于FtpConnection的FtpException，则直接转型想上层抛出
			if (e instanceof FtpException)
			{
				throw (FtpException) e;
			}
			// 其他类型异常，在这里重新FtpException后，向上抛
			throw new FtpException(e);
		}finally {
			if(null != con){
				con.close();
			}
		}
	}
	
	/**
	 * 上传文件到FTP服务器指定目录,不删除原来的文件,在原来的基础上添加
	 * @param ftpAlias    ftp别名
	 * @param path        指定目录
	 * @param fileName    文件名
	 * @param file        文件字节数组
	 * @throws FtpException 
	 */
	public static void uploadToFtp2(String ftpAlias, String path, String fileName, byte[] file) throws FtpException{
		FtpConnection con = null;
		try {
			con = FtpConnectionPools.getFtpController(ftpAlias);
			con.uploadToFtp(path, fileName, file, UploadTransType.ESUME);
		} catch (Exception e) {
			// 如果异常是来自于FtpConnection的FtpException，则直接转型想上层抛出
			if (e instanceof FtpException)
			{
				throw (FtpException) e;
			}
			// 其他类型异常，在这里重新FtpException后，向上抛
			throw new FtpException(e);
		}finally {
			if(null != con){
				con.close();
			}
		}
	}
	
	/**
	 * 上传File文件到FTP服务器指定目录
	 * @param ftpAlias    ftp别名
	 * @param path        指定目录
	 * @param fileName    文件名
	 * @param file        文件字节数组
	 * @throws FtpException 
	 */
	public static void uploadFileToFtp(String ftpAlias, String path, String fileName, File file) throws FtpException{
		FtpConnection con = null;
		try {
			con = FtpConnectionPools.getFtpController(ftpAlias);
			con.uploadFileToFtp(path, fileName, file);
		} catch (Exception e) {
			// 如果异常是来自于FtpConnection的FtpException，则直接转型想上层抛出
			if (e instanceof FtpException)
			{
				throw (FtpException) e;
			}
			// 其他类型异常，在这里重新FtpException后，向上抛
			throw new FtpException(e);
		}finally {
			if(null != con){
				con.close();
			}
		}
	}
	
	/**
	 * 取得ftp对应路径下的所有文件名称
	 * @param ftpAlias ftp别名
	 * @param path 路径
	 * @return 文件名称集合、不会为NULL
	 * @throws FtpException 
	 */
	public static List<String> getFtpFileNames(String ftpAlias,String path) throws FtpException
	{
		FtpConnection con = null;
		try {
			con = FtpConnectionPools.getFtpController(ftpAlias);
			return con.getFtpFileNames(path);
		} catch (Exception e) {
			// 如果异常是来自于FtpConnection的FtpException，则直接转型向上层抛出
			if (e instanceof FtpException)
			{
				throw (FtpException) e;
			}
			// 其他类型异常，在这里重新FtpException后，向上抛
			throw new FtpException(e);
		}finally {
			if(null != con){
				con.close();
			}
		}
	}
	
	/**
	 * 从FTP上删除指定文件
	 * @param ftpAlias FTP别名
	 * @param filePath 文件在FTP上的路径
	 * @throws FtpException
	 */
	public static void delFormFtp(String ftpAlias,String filePath) throws FtpException
	{
		FtpConnection con = null;
		try {
			con = FtpConnectionPools.getFtpController(ftpAlias);
			con.delFileFromFtp(filePath);
		} catch (Exception e) {
			// 如果异常是来自于FtpConnection的FtpException，则直接转型向上层抛出
			if (e instanceof FtpException)
			{
				throw (FtpException) e;
			}
			// 其他类型异常，在这里重新FtpException后，向上抛
			throw new FtpException(e);
		}finally {
			if(null != con){
				con.close();
			}
		}
	}

}
