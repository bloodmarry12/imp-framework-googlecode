/**
 * 
 */
package com.huawei.imp.framework.common.util;

import java.util.Properties;
import java.util.regex.Pattern;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.huawei.imp.framework.utils.CryptUtil;

/**
 * 支持属性信息加密的属性替换配置对象
 * 提供基于jdk基础包实现的
 * @author ahli
 * @version IMPV100R001DA0, Nov 18, 2009 
 * @see org.springframework.beans.factory.config.PropertyPlaceholderConfigurer
 * @since CMS IMPV100R001DA0
 */
public class EncodingSupportPropertyPlaceholderConfigurer extends
		PropertyPlaceholderConfigurer {
	
	/**
	 * 加密属性正则表达式
	 */
	private String[] encryptionProperties = new String[0]; 

	/**
	 * 密钥
	 */
	private static final String DEFALUT_KEY = "C1ZZsBNd0JY/8Yx2nPEg2g==";
	
	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.config.PropertyPlaceholderConfigurer#resolvePlaceholder(java.lang.String, java.util.Properties)
	 * 覆盖基类方法，如果当前的属性符合正则表达式，则进行解密
	 */
	protected String resolvePlaceholder(String placeholder, Properties props) {

		if (null != encryptionProperties && encryptionProperties.length > 0)
		{
			for (String propertyName : encryptionProperties)
			{
				if (Pattern.matches(propertyName, placeholder))
				{
					return decrypt(props.getProperty(placeholder));
				}
			}
			return props.getProperty(placeholder);
		}
		else
		{
			return props.getProperty(placeholder);
		}
	}

	/**
	 * 解密属性
	 * @param source    密文 
	 * @return          解密后的
	 */
	private String decrypt(String source)
	{
		return CryptUtil.getInstance().decrypt(source, DEFALUT_KEY,
				CryptUtil.CipherType.AES);
	}
	
	/**
	 * 设置加密属性过滤正则表达式
	 * @param encryptionProperties
	 */
	public void setEncryptionProperties(String[] encryptionProperties)
	{
		if (encryptionProperties != null)
		{
			this.encryptionProperties = encryptionProperties.clone();
		}
		else
		{
			this.encryptionProperties = null;
		}
	}
}
