package com.huawei.imp.framework.config.web.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.huawei.imp.framework.config.ConfigUtil;

/**
 * 系统配置标签
 * 传入系统配置key，直接在页面打印配置结果，如果配置项参数为空，则不打印任何结果
 * @author ahli
 * @version IMPV100R001DA0, 2010-1-14
 * @see javax.servlet.jsp.tagext.TagSupport
 * @since CMS IMPV100R001DA0
 */
public class SysConfigTag extends TagSupport
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 系统配置值
	 */
	private String key = null;
	
	@Override
	public int doStartTag() throws JspException
	{
		return EVAL_BODY_INCLUDE;
	}
	
	@Override
	public int doEndTag() throws JspException
	{
		if (null != key && !"".equals(key.trim()))
		{
			String value = ConfigUtil.getSysConfigValue(key);

			if (null != key)
			{
				JspWriter out = pageContext.getOut();
				try
				{
					out.print(value);
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		return EVAL_PAGE;
	}

	/**
	 * @return the key
	 */
	public String getKey()
	{
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key)
	{
		this.key = key;
	}
}
