/**
 * 
 */
package com.huawei.imp.framework.model.ftp;

import org.apache.commons.lang.StringUtils;

import com.huawei.imp.framework.logger.LogFactory;
import com.huawei.imp.framework.logger.Logger;
import com.huawei.imp.framework.model.ftp.domain.FtpConfig;
import com.huawei.imp.framework.model.ftp.exception.FtpException;
import com.huawei.imp.framework.model.ftp.impl.FtpConnectionImpl;

/**
 * FtpUtil2作为FtpUtil的FTP工具类的补充，提供了基于绝对路径
 * 的FTP操作API。与FtpUtil最大的不同之处在于该实现没有使用池
 * 化。并且提供的是基于绝对路径操作API
 * @author ahli
 * @version IMPV100R001DA0, 2010-4-4
 * @since CMS IMPV100R001DA0
 */
public class FtpUtil2
{
	/**
	 * 私有构造函数，避免被实例化
	 */
	private FtpUtil2(){}
	
	private static final Logger log = LogFactory.getLogger(FtpUtil2.class);
	
	/**
	 * 获取FTP服务器上指定的文件大小
	 * @param absolutePath    FTP地址绝对路径
	 * @return
	 * @throws FtpException 
	 */
	public static long getFtpFileSize(String absolutePath) throws FtpException{
		FtpConfig fc = readFtpConfigFromAbsolutePath(absolutePath);
		if(log.isDebugEnabled())
		{
			log.debug("FtpConfig: " + fc);
		}
		
		FtpConnectionImpl fcon = null;
		try 
		{
			fcon = new FtpConnectionImpl(fc);
			if(log.isDebugEnabled())
			{
				log.debug("FtpConnection isConnected: " + fcon.isConnected());
			}
			String filePath = absolutePath.substring(absolutePath.indexOf("/", 6) + 1);
			if(!filePath.startsWith("/")){
				filePath = "/" + filePath;
			}
			if(log.isDebugEnabled())
			{
				log.debug("absolutePath: " + filePath);
			}
			return fcon.getFtpFileSize(filePath);
		}catch (Exception e) {
			if(log.isDebugEnabled())
			{
				log.debug(e.toString());
			}
			throw new FtpException(e);
		}
		finally 
		{
			if (null != fcon)
			{
				fcon.destroy();
			}
		}
	}
	
	/**
	 * 获取FTP服务器上指定的文件大小
	 * @param absolutePath    FTP地址绝对路径
	 * @return
	 * @throws FtpException 
	 */
	public static byte[] download(String absolutePath) throws FtpException{
		FtpConfig fc = readFtpConfigFromAbsolutePath(absolutePath);
		if(log.isDebugEnabled())
		{
			log.debug("FtpConfig: " + fc);
		}
		
		FtpConnectionImpl fcon = null;
		try 
		{
			fcon = new FtpConnectionImpl(fc);
			if(log.isDebugEnabled())
			{
				log.debug("FtpConnection isConnected: " + fcon.isConnected());
			}
			String filePath = absolutePath.substring(absolutePath.indexOf("/", 6) + 1);
			if(!filePath.startsWith("/")){
				filePath = "/" + filePath;
			}
			if(log.isDebugEnabled())
			{
				log.debug("absolutePath: " + filePath);
			}
			System.out.println(filePath.substring(1));
			return fcon.downloadFromFtp(filePath.substring(1));
		}catch (Exception e) {
			if(log.isDebugEnabled())
			{
				log.debug(e.toString());
			}
			throw new FtpException(e);
		}
		finally 
		{
			if (null != fcon)
			{
				fcon.destroy();
			}
		}
	}
	
	private static FtpConfig readFtpConfigFromAbsolutePath(String absolutePath) throws FtpException{
		if (StringUtils.isEmpty(absolutePath))
		{
			throw new FtpException("absolutePath is Empty : <" + absolutePath + ">");
		}
		if (!absolutePath.startsWith("ftp://"))
		{
			throw new FtpException("absolutePath is not start with \"ftp://\" : <" + absolutePath + ">");
		}
		// 构造一个StringBuilder对象，用于截取FTPConfig参数
		StringBuilder sb = new StringBuilder(absolutePath.substring(5));
		FtpConfig fc = new FtpConfig();
		// 用户名、密码
		int upIndex = sb.indexOf("@");
		//通过"@"关键字，判断，sb中包含了登录信息
		if(upIndex > 0){
			String signStr = sb.substring(0, upIndex);
			// 用户名、密码分割符号
			int f = signStr.indexOf(":");
			fc.setUserName(signStr.substring(1, f));
			fc.setUserPaswd(signStr.substring(f + 1));
			
			// 删除路径地址中，登录信息
			sb.delete(0, upIndex + 1);	
		}else{
			// 删除第一个"/"
			sb.delete(0, 1);	
		}
		log.info(sb.toString());
		
		// 读取IP
		int urlIndex = sb.indexOf("/");
		//通过"/"关键字，判断，sb中包含了登录信息
		if(urlIndex > 0)
		{
			String urlStr = sb.substring(0, urlIndex);
			
			// 读取端口
			int portIndex = urlStr.indexOf(":");
			if(portIndex > 0){
				fc.setIp(urlStr.substring(0, portIndex));
				fc.setPort(Integer.parseInt(urlStr.substring(portIndex + 1)));
			}else{
				fc.setIp(urlStr);
				// 设置默认值21
				fc.setPort(21);
			}
		}
		
		// 读取 ftp上的文件路径
		return fc;
	}
}
