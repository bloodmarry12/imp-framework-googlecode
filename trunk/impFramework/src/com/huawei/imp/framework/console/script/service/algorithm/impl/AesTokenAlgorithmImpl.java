/*
 * 文件名：    AesTokenAlgorithmImpl.java
 * 版权:      Copyright 2000-2008 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 创建人:    申卿
 * 文件描述:  
 * 修改时间:   Jun 2, 2010 11:43:08 AM
 * 修改内容：  新增
 */
package com.huawei.imp.framework.console.script.service.algorithm.impl;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.huawei.imp.framework.exception.BusinessException;
import com.huawei.imp.framework.logger.LogFactory;
import com.huawei.imp.framework.logger.Logger;
import com.huawei.imp.framework.model.ftp.FtpUtil;
import com.huawei.imp.framework.console.script.domain.Script;
import com.huawei.imp.framework.console.script.service.algorithm.Algorithm;
import com.huawei.imp.framework.console.script.service.algorithm.AlgorithmManager;
import com.huawei.imp.framework.utils.BeanHolder;
import com.huawei.imp.framework.console.script.service.algorithm.CryptUtil;
import com.huawei.imp.framework.utils.MD5Util;

/**
 * <br>类描述： 利用aes算法生成token</br>
 * @author  申卿
 * @version Jun 2, 2010 11:43:08 AM
 * @see
 * @since
 */
@Component("aesTokenAlgorithmImpl")
public class AesTokenAlgorithmImpl implements Algorithm
{
	//算法名称
	private static final String NAME = "Token算法";
	//算法标志
	private static final String TAG = "Token:AES_MD5";
	
	/**
	 * 空字符
	 */
	private static final String SPECIAL_CHAR_NULL = "\\r|\n|\r\n";
	/**
	 * key文件的ftpAlias名称
	 */
	private static final String KEYFILE_FTPALIAS = "TOKEN_KEY_FILE";
	/**
	 * key文件的后缀名
	 */
	private static final String KEYFILE_SUFFIX = ".ke";
	/**
	 * 默认的key文件存放路径
	 */
	private static final String DEFAULT_KEYFILE_PATH = "/tokenkeyfiles";
	/**
	 * 默认的key文件名称
	 */
	private static final String DEFAULT_KEYFILE_NAME = "license.ke";
	/**
	 * 默认的key文件解密密钥
	 */
	private static final String DEFAULT_KEYFILE_KEY = "be90f045-7509-4e87-91b0-24475d1a04a0";
	
	/**
	 * 日志对象
	 */
	private static final Logger log = LogFactory.getLogger(AesTokenAlgorithmImpl.class);
		
	@PostConstruct
	public void regist(){
		AlgorithmManager.regist(this);
	}
	
	@Override
	public Object createToken(Script script, String keyTag) throws BusinessException
	{
		//去掉script中的换行符和回车等特殊符号
		Pattern p = Pattern.compile(SPECIAL_CHAR_NULL);
		Matcher m = p.matcher(script.getScript().trim());
		String after = m.replaceAll(""); 
		  
		String aes = aes(after, keyTag);
		String token = md5(aes);
		return token;
	}
	private String aes(String script, String keyTag) throws BusinessException
	{
		CryptUtil cru = CryptUtil.getInstance();
		try
		{
			return cru.encrypt_aes(script.trim(), getKey(keyTag));
		}
		catch(Exception e)
		{
			throw new BusinessException(BeanHolder.getMessage("exception.console.script.aes.encryptfailed") + "\n" + e.toString());
		}
	}
	
	private String md5(String script)
	{
		return MD5Util.getMd5Str(script);
	}
	
	private String getKey(String keyTag) throws BusinessException
	{		
		String filePath = getKeyFilePath(keyTag);
		
		byte[] keyBytes = null;
		try
		{
			keyBytes = FtpUtil.downloadFromFtp(KEYFILE_FTPALIAS, filePath);
		}
		catch(Exception e)
		{
			try
			{
				keyBytes = getKeyBytesFromLocal(filePath);
			}
			catch(Exception ee)
			{
				throw new BusinessException(BeanHolder.getMessage("exception.console.script.keyfile.get.failed")+e.toString() + "\n" + ee.toString());
			}
		}
		
		if(null == keyBytes || keyBytes.length == 0)
		{
			return null;
		}
		else
		{
			try
			{
				CryptUtil cru = CryptUtil.getInstance();
				String keyFileKey = BeanHolder.getMessage("token.key.file.key");
				
				if(null == keyFileKey)
				{
					keyFileKey = DEFAULT_KEYFILE_KEY;
				}
				
				return cru.decrypt_aes(keyBytes, BeanHolder.getMessage("token.key.file.key"));
			}
			catch(Exception e)
			{
				throw new BusinessException(BeanHolder.getMessage("exception.console.script.keyfile.decrypt.failed") + "\n" + e.toString());
			}
		}
	}
	
	/**
	 * 方法描述：通过keyTag获取key文件的ftp地址
	 * @param keyTag
	 * @return
	 * @author  申卿
	 * @version Jun 8, 2010 9:58:05 AM
	 * @修改方式：新增
	 */
	private String getKeyFilePath(String keyTag)
	{
		String filePath = BeanHolder.getMessage("token.key.ftp.path");
		
		if(null == filePath)
		{
			filePath = DEFAULT_KEYFILE_PATH;
		}
		
		if(null == keyTag || "".equals(keyTag.trim()))
		{
			String defaultname = BeanHolder.getMessage("token.key.ftp.default.filename");
			if(null == defaultname)
			{
				filePath += "/" + DEFAULT_KEYFILE_NAME;
			}
			else
			{
				filePath += "/" + BeanHolder.getMessage("token.key.ftp.default.filename");
			}
		}
		else
		{
			filePath += "/" + keyTag;
			if(!keyTag.toLowerCase().endsWith(KEYFILE_SUFFIX))
			{
				filePath += KEYFILE_SUFFIX;
			}
		}
		return filePath;
	}
	
	/**
	 * 方法描述：从本地获取密钥文件信息
	 * @param filePath
	 * @return
	 * @author  申卿
	 * @version Jun 8, 2010 10:13:10 AM
	 * @修改方式：新增
	 */
	private byte[] getKeyBytesFromLocal(String filePath) throws BusinessException
	{
		byte[] keyBytes = null;
		
		FileInputStream input = null;
		File keyFile = new File(".."+filePath);
		try
		{
			input = new FileInputStream(keyFile);
		}
		catch (FileNotFoundException e1)
		{
			try
			{
				throw new BusinessException(BeanHolder.getMessage("exception.console.script.keyfile.notfound", new String[]{keyFile.getCanonicalPath()}) + "\n" + e1.toString());
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		BufferedInputStream inBuff=new BufferedInputStream(input);
		try
		{
			byte[] b = new byte[1024 * 5];
			List<Byte> buffers = new ArrayList<Byte>();
			int len = 0;
			while ((len =inBuff.read(b)) != -1) {
				for(int i=0;i<len;i++)
				{
					buffers.add(b[i]);
				}
			}
			
			keyBytes = new byte[buffers.size()];
			for(int i=0;i<buffers.size();i++)
			{
				keyBytes[i] = (Byte)buffers.get(i);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(input != null)
			{
				try
				{
					input.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			if(inBuff != null)
			{
				try
				{
					inBuff.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		
		return keyBytes;
	}
	
	@Override
	public Class getAlgorithmClass()
	{
		return this.getClass();
	}
	@Override
	public String getName()
	{
		return NAME;
	}
	@Override
	public String getTag()
	{
		return TAG;
	}
}
