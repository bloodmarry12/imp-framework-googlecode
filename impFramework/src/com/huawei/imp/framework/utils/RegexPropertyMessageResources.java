/*
 * 文件名：RegexPropertyMessageResources.java
 * 版权：  Copyright 2000-2001 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 描述：〈描述〉
 * 修改人：ahli
 * 修改时间：Jun 16, 2009
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.huawei.imp.framework.utils;

import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * 国际化资源加载类
 * 覆盖了父类的setBasenames方法，提供支持通配符配置
 * @author ahli
 * @version IMPV100R001DA0, Jun 16, 2009 
 * @see org.springframework.context.support.ResourceBundleMessageSource
 * @since CMS IMPV100R001DA0
 */

public class RegexPropertyMessageResources extends ResourceBundleMessageSource
{

	/**
	 * 资源文件扩展名
	 */
	public final static String PROPERTY_POSTFIX = ".properties";
	
	/**
	 * 资源文件头
	 */
	public final static String PROPERTY_POSTFIX_PATH = "properties.";
	
	private PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
	
	/* (non-Javadoc)
	 * @see org.springframework.context.support.ResourceBundleMessageSource#setBasenames(java.lang.String[])
	 */
	@Override
	public void setBasenames(String[] baseNames)
	{
		try
		{
			for (String baseName : baseNames)
			{
				// 根据通配符获取资源对象集合
				Resource[] resources = patternResolver.getResources(baseName); //通过通配符取得到所有对应的source
				
				// 申明文件名集合
				String[] _baseNames = new String[resources.length];
				int i = 0;
				for (Resource resource : resources)
				{
					String fileName = resource.getFilename();
					_baseNames[i] = PROPERTY_POSTFIX_PATH + fileName.substring(0, fileName.indexOf(PROPERTY_POSTFIX));
					i = i +1;
				}
				super.setBasenames(_baseNames);
			}
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
}
