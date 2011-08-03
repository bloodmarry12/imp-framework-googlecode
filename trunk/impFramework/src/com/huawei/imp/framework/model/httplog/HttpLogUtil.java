/*
 * 文件名：HttpLogUtil.java
 * 版权：  Copyright 2000-2009 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 描述：〈描述〉
 * 修改人：ahli
 * 修改时间：Oct 29, 2009
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.huawei.imp.framework.model.httplog;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.huawei.imp.framework.config.ConfigUtil;
import com.huawei.imp.framework.logger.LogFactory;
import com.huawei.imp.framework.logger.Logger;
import com.huawei.imp.framework.model.ftp.FtpConnection;
import com.huawei.imp.framework.model.ftp.FtpConnectionPools;
import com.huawei.imp.framework.model.ftp.FtpConnection.UploadTransType;
import com.huawei.imp.framework.model.httplog.domain.HttpLoggerEntity;
import com.huawei.imp.framework.utils.Constant;
import com.huawei.imp.framework.utils.RandNumGenerator;

/**
 * HTTP日志工具类
 * @author ahli
 * @version IMPV100R001DA0, Oct 29, 2009 
 * @since CMS IMPV100R001DA0
 */

public class HttpLogUtil
{
	private static final ThreadLocal<HttpLoggerEntity> curHttpLoggerEntity
		= new ThreadLocal<HttpLoggerEntity>();
	
	/**
	 * 日志对象
	 */
	private static final Logger log = LogFactory.getLogger(HttpLogUtil.class);
	
	/**
	 * 日志文件
	 */
	private static final String FTP_HTTP_LOGFILE = "HTTP_LOG_FILE";
	
	/**
	 * 上传Excel文件，到FTP的HTTP日志文件备份目录
	 * @param wb
	 */
	public static void uploadHSSFWorkbookFileToLogPath(HSSFWorkbook wb){
		FtpConnection con = null;
		ByteArrayOutputStream os = null;
		Calendar cal = Calendar.getInstance();
		StringBuilder sb = new StringBuilder();
		// 获取配置的根路径
		sb.append(ConfigUtil.getSysConfigValue("imp.copyright.httplog"));
		sb.append("/"+cal.get(Calendar.YEAR));
		sb.append("/");
		sb.append(cal.get(Calendar.MONTH));
		sb.append("/");
		sb.append(cal.get(Calendar.DATE));
		sb.append("/");
		// 使用UUID作为备份的文件名
		String fileName = RandNumGenerator.nextUUID() + ".xls";
		try
		{
			os = new ByteArrayOutputStream(); 
			wb.write(os);
			con = FtpConnectionPools.getFtpController(FTP_HTTP_LOGFILE);
			con.uploadToFtp(sb.toString(), fileName, os.toByteArray(), UploadTransType.VERWRITE);
			// 记录文件备份路径到日志对象
			writeFileDescriptionIntoLogEntity(sb.toString() + fileName);
		}
		catch (Exception e)
		{
			log.error("upload excel file error.");
			writeFileDescriptionIntoLogEntity("upload file error:" + e.toString());
		}
		finally
		{
			if(null != os){
				try
				{
					os.close();
				}
				catch (IOException e){}
			}
			
			if (null != con)
			{
				con.close();
			}
		}
	}
	
	public static boolean isLogEnable(){
		return null != curHttpLoggerEntity.get();
	}
	
	public static HttpLoggerEntity getCurHttpLoggerEntity(){
		return curHttpLoggerEntity.get();
	}
	
	public static void setCurHttpLoggerEntity(HttpLoggerEntity entity){
		curHttpLoggerEntity.set(entity);
	}
	
	private static void writeFileDescriptionIntoLogEntity(String filePath){
		HttpLoggerEntity entity = curHttpLoggerEntity.get();
		if (null == entity)
		{
			throw new NullPointerException("current HttpLoggerEntity is null~");
		}
		
		if (null != entity.getDescription())
		{
			String _desc = entity.getDescription();
			_desc += Constant.LINE_SEPARATOR + "[file:" + filePath + "]";
			entity.setDescription(_desc);
		}
		else
		{
			entity.setDescription(Constant.LINE_SEPARATOR + "[file:" + filePath + "]");
		}
	}
	
	/**
	 * 添加描述信息
	 * @param str
	 */
	public static void addDescription(String str){
		HttpLoggerEntity entity = curHttpLoggerEntity.get();
		if (null == entity)
		{
			throw new NullPointerException("current HttpLoggerEntity is null~");
		}
		
		// 如果描述信息不为null则追加描述
		if (null != entity.getDescription())
		{
			String _desc = entity.getDescription();
			_desc += Constant.LINE_SEPARATOR + "[msg:" + str + "]";
			entity.setDescription(_desc);
		}
		else
		{
			entity.setDescription(Constant.LINE_SEPARATOR + "[msg:" + str + "]");
		}
	}
}

