/*
 * 文件名：CryptUtil.java 版权： Copyright 2000-2009 Huawei Tech. Co. Ltd. All Rights
 * Reserved. 描述：〈描述〉 修改人：ahli 修改时间：Nov 18, 2009 跟踪单号： 修改单号： 修改内容：
 */

package com.huawei.imp.framework.console.script.service.algorithm;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 加密、解密工具类 包装cipher对象的操作
 * 基于javax.crypto.*、java.security.*、org.apache.commons.codec.binary.Base64实现
 * 解密、加密包装。该工具类仅仅是对底层方法的包装，提供更友好的API使用，本身不包含任何加密、解密
 * 算法；所有加密、解密算法均依赖于JDK提供的包实现；
 * @author ahli
 * @version IMPV100R001DA0, Nov 18, 2009
 * @since CMS IMPV100R001DA0
 */
public class CryptUtil
{
	/**
	 * 单态实例对象
	 */
	private static final CryptUtil instance = new CryptUtil();

	/**
	 * Base64解码对象
	 */
	private static final BASE64Decoder base64De = new BASE64Decoder();
	
	/**
	 * Base64加码对象
	 */
	private static final BASE64Encoder base64En = new BASE64Encoder();
	
	/**
	 * 密码类型
	 * @author ahli
	 * @version IMPV100R001DA0, Nov 18, 2009 
	 * @since CMS IMPV100R001DA0
	 */
	public enum CipherType{
		AES,
		DES
	}
	
	/**
	 * 私有构造函数，避免被外部实例化
	 */
	private CryptUtil()
	{
	};

	/**
	 * 获取单态实例对象
	 * @return
	 */
	public static CryptUtil getInstance()
	{
		return instance;
	}

	/**
	 * 产生随机的密钥
	 * 例如：“xjPk2rOSU1n5v70a84M+vw==”
	 * @return    随机密钥
	 */
	public String genRandomKeyStr(CipherType type)
	{
		SecretKey key;
		try
		{
			key = KeyGenerator.getInstance(type.toString()).generateKey();
			return base64En.encode(key.getEncoded());
		}
		catch (NoSuchAlgorithmException e)
		{
			return null;
		}
	}

	/**
	 * 加密文本
	 * @param str      明文
	 * @param key      密钥
	 * @param type     密码类型
	 * @return         密文
	 */
	public String encrypt(String str, String key, CipherType type)
	{
		try
		{
			Cipher cipher = Cipher.getInstance(type.toString());
			cipher.init(Cipher.ENCRYPT_MODE, initKey(type.toString(), key));
			return new String(Base64.encodeBase64(cipher.doFinal(str.getBytes("UTF-8"))));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (GeneralSecurityException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密
	 * @param str     密文
	 * @param key     密钥
	 * @param type    密码算法
	 * @return        明文
	 */
	public String decrypt(String str, String key, CipherType type)
	{
		try
		{
			Cipher cipher = Cipher.getInstance(type.toString());
			cipher.init(Cipher.DECRYPT_MODE, initKey(type.toString(), key));
			return new String(cipher.doFinal( base64De.decodeBuffer(str)), "UTF-8");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (GeneralSecurityException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 初始化密钥
	 * @param cryptType    加密算法类型
	 * @param key          密钥
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private Key initKey(String cryptType, String key) throws UnsupportedEncodingException{
		SecretKey secretKey = new SecretKeySpec(Base64.decodeBase64(key.getBytes("UTF-8")), cryptType);
		return secretKey;
	}
	
	/**
	 * 方法描述：初始化AES加密算法的密钥，这种方式不受密钥长度限制
	 * 
	 * @param key
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @author 申卿
	 * @version Jun 2, 2010 10:23:34 AM
	 * @修改方式：新增
	 */
	private Key initKeyForAES(String key) throws NoSuchAlgorithmException
	{
		if(null == key || key.length() == 0)
		{
			throw new NullPointerException("密钥不能为空");
		}
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		kgen.init(128, new SecureRandom(key.getBytes()));
		SecretKey secretKey = kgen.generateKey();
		byte[] enCodeFormat = secretKey.getEncoded();
		SecretKeySpec key2 = new SecretKeySpec(enCodeFormat, "AES");
		return key2;
	}

	/**
	 * 方法描述：AES加密算法，不受密钥长度限制
	 * 
	 * @param content
	 * @param key
	 * @return
	 * @author 申卿
	 * @version Jun 2, 2010 10:09:02 AM
	 * @修改方式：新增
	 */
	public String encrypt_aes(String content, String key)
	{
		try
		{
			SecretKeySpec secretKey = (SecretKeySpec) initKeyForAES(key);
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);// 初始化
			byte[] result = cipher.doFinal(byteContent);
			return asHex(result); // 加密
		}
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		catch (NoSuchPaddingException e)
		{
			e.printStackTrace();
		}
		catch (InvalidKeyException e)
		{
			e.printStackTrace();
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		catch (IllegalBlockSizeException e)
		{
			e.printStackTrace();
		}
		catch (BadPaddingException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 方法描述：aes解密算法，，不受密钥长度限制
	 * 
	 * @param content
	 * @param key
	 * @return
	 * @author 申卿
	 * @version Jun 2, 2010 10:28:03 AM
	 * @修改方式：新增/修改
	 */
	public String decrypt_aes(byte[] content, String key)
	{
		try
		{
			SecretKeySpec secretKey = (SecretKeySpec) initKeyForAES(key);
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, secretKey);// 初始化
			byte[] result = cipher.doFinal(content);
			try
			{
				return new String(result,"UTF-8");
			}
			catch (UnsupportedEncodingException e)
			{
				e.printStackTrace();
			} 
		}
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		catch (NoSuchPaddingException e)
		{
			e.printStackTrace();
		}
		catch (InvalidKeyException e)
		{
			e.printStackTrace();
		}
		catch (IllegalBlockSizeException e)
		{
			e.printStackTrace();
		}
		catch (BadPaddingException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 方法描述：将2进制数值转换为16进制字符串
	 * 
	 * @param buf
	 * @return
	 * @author 申卿
	 * @version Jun 2, 2010 10:28:33 AM
	 * @修改方式：新增
	 */
	public String asHex(byte buf[])
	{
		StringBuffer strbuf = new StringBuffer(buf.length * 2);
		int i;

		for (i = 0; i < buf.length; i++)
		{
			if (((int) buf[i] & 0xff) < 0x10)
				strbuf.append("0");

			strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
		}

		return strbuf.toString();
	}
	
	/**
	 * 方法描述：将16进制转换为2进制数组
	 * @param hexStr
	 * @return
	 * @author  申卿
	 * @version Jun 2, 2010 10:31:01 AM
	 * @修改方式：新增
	 */
	public byte[] asBytes(String hexStr)
	{
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++)
		{
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
					16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}
	
	public static void main(String[] args)
	{
//		String randomKey = CryptUtil.getInstance().genRandomKeyStr(CipherType.AES);
		String randomKey = "C1ZZsBNd0JY/8Yx2nPEg2g==";
		System.out.println("randomKey:" + randomKey);
		String message = "cms4a";
		System.out.println("加密前明文:" + message);
		String s1 = CryptUtil.getInstance().encrypt(message, randomKey, CipherType.AES);
		System.out.println("加密后的信息：" + s1);
		String s2 = CryptUtil.getInstance().decrypt(s1, randomKey, CipherType.AES);
		System.out.println("解密后的信息：" + s2);
	}
}
