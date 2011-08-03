package com.huawei.imp.framework.model.ftp;

import java.io.File;
import java.io.OutputStream;
import java.util.List;

import com.huawei.imp.framework.model.ftp.exception.FtpException;

/**
 * Ftp链接类 在inet的FTP对象外面包装了一层，做对象池的操作。
 * 
 * @author ahli
 * @date 2009-8-17
 */
public interface FtpConnection {
	
	/**
	 * FTP上传文件操作传输类型
	 * 支持断点续传(ESUME)，覆盖(VERWRITE)
	 * @author ahli
	 * @version IMPV100R001DA0, 2010-4-4
	 * @since CMS IMPV100R001DA0
	 */
	static enum UploadTransType{
		/**
		 * 续传
		 */
		ESUME,
		/**
		 * 覆盖
		 */
		VERWRITE
	}

	/**
	 * 校验FTP服务器上的指定文件是否存在
	 * @param path    文件路径
	 * @return
	 * @throws FtpException
	 */
	boolean isFtpFileExist(String path) throws FtpException; 
	
	/**
	 * 获取FTP服务器上指定的文件大小
	 * @param filePath    文件路径
	 * @return
	 * @throws FtpException
	 */
	long getFtpFileSize(String filePath) throws FtpException;
	
	/**
	 * 获取FTP服务器上指定的文件大小
	 * @param path        文件路径
	 * @param fileName    文件名
	 * @return
	 * @throws FtpException
	 */
	long getFtpFileSize(String path, String fileName) throws FtpException;
	
	/**
	 * 从FTP上下载文件
	 * @param filePath     文件路径
	 * @return    字节数组
	 * @throws FtpException
	 */
	byte[] downloadFromFtp(String filePath) throws FtpException;
	
	/**
	 * 从FTP上下载文件
	 * @param path        文件所在目录路径
	 * @param fileName    文件名
	 * @return
	 * @throws FtpException
	 */
	byte[] downloadFromFtp(String path, String fileName) throws FtpException;
	
	
	/**
	 * 从FTP上下载文件、写入到输出流中
	 * @param filePath 文件所在目录路径
	 * @param os 输出流
	 * @throws FtpException
	 */
	void downloadFromFtp(String filePath,OutputStream os)throws FtpException;
	
	/**
	 * 上传文件到FTP服务器指定目录
	 * 当transType参数为续传模式，会在既有文件后追加内容。否则都是覆盖；
	 * @param path        指定目录
	 * @param fileName    文件名
	 * @param file        文件字节数组
	 * @param transType   文件上传传输类型
	 * @throws FtpException
	 */
	void uploadToFtp(String path, String fileName, byte[] file, UploadTransType transType) throws FtpException;
	
	
	/**
	 * 上传文件到FTP服务器指定目录,不删除原来的文件,在原来的基础上添加
	 * 该方法将会被void com.huawei.imp.framework.model.ftp.FtpConnection.uploadToFtp(String path, String fileName, byte[] file, UploadTransType transType) throws FtpException
	 * 取代，框架后续版本将不保证该接口支持；
	 * @param path        指定目录
	 * @param fileName    文件名
	 * @param file        文件字节数组
	 * @throws FtpException
	 */
	@Deprecated
	void uploadToFtp2(String path, String fileName, byte[] file) throws FtpException;
	
	/**
	 * 上传文件到FTP服务器指定目录
	 * 该方法将会被void com.huawei.imp.framework.model.ftp.FtpConnection.uploadToFtp(String path, String fileName, byte[] file, UploadTransType transType) throws FtpException
	 * 取代，框架后续版本将不保证该接口支持；
	 * @param path        指定目录
	 * @param fileName    文件名
	 * @param file        文件字节数组
	 * @throws FtpException
	 */
	@Deprecated
	void uploadToFtp(String path, String fileName, byte[] file) throws FtpException;
	
	/**
	 * 上传文件到FTP服务器指定目录,
	 * @param path        指定目录
	 * @param fileName    文件名
	 * @param file        文件字节数组
	 * @throws FtpException
	 */
	void uploadFileToFtp(String path, String fileName, File file) throws FtpException;
	
	/**
	 * 取得ftp地址目录下所有文件名称
	 * @param path ftp地址
	 * @return 文件名称集合、保证了返回集合不为NULL
	 */
	List<String> getFtpFileNames(String path) throws FtpException;
	
	/**
	 * 从FTP上删除指定文件
	 * @param path 文件路径
	 * @throws FtpException
	 */
	void delFileFromFtp(String filePath) throws FtpException;
	
	/**
	 * 将本地文件上传到FTP
	 * @param file
	 * @param filePath
	 * @throws FtpException
	 */
	void uploadFile(File file, String filePath) throws FtpException;
	
	/**
	 * 关闭连接，将当前FTP资源归还给连接池
	 */
	void close();
}
