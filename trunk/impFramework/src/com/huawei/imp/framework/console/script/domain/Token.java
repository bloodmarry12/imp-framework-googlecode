/*
 * 文件名： Token.java 版权: Copyright 2000-2008 Huawei Tech. Co. Ltd. All Rights
 * Reserved. 创建人: 申卿 文件描述: 修改时间: Jun 2, 2010 11:16:48 AM 修改内容： 新增
 */
package com.huawei.imp.framework.console.script.domain;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.huawei.imp.framework.common.domain.Namingspace;
import com.huawei.imp.framework.console.script.service.algorithm.Algorithm;
import com.huawei.imp.framework.console.script.service.algorithm.AlgorithmManager;
import com.huawei.imp.framework.utils.BeanHolder;

/**
 * <br>
 * 类描述： token实例类</br>
 * 
 * @author 申卿
 * @version Jun 2, 2010 11:16:48 AM
 * @see
 * @since
 */
@Namingspace(value = "_framework_token")
public class Token
{
	/**
	 * token的分隔符
	 */
	public static final String TOKEN_SEPARATOR = "_";
	/**
	 * token中保存有效期的格式
	 */
	public static final String TOKEN_DATAFORMAT = "yyyyMMdd";
	/**
	 * token默认的失效时间，单位为妙
	 */
	private static final int DEFALUT_INVALID_TIME = 300;
	/**
	 * 操作ID
	 */
	private String operateID;

	/**
	 * 用于生成token的script
	 */
	private Script script;

	/**
	 * token值
	 */
	private String token;

	/**
	 * 生成token的密钥文件的标志，通常为密钥文件的文件名
	 */
	private String keyTag;

	/**
	 * token的创建时间
	 */
	private Date createTime;

	/**
	 * token的失效时间
	 */
	private Date disableTime;

	/**
	 * token的使用者id
	 */
	private String userID;

	/**
	 * 生成token使用的算法
	 */
	private Algorithm tokenAlgorithm;

	/**
	 * 该token被使用的次数，初始值为1
	 */
	private int count = 1;

	public Token()
	{
	}

	/**
	 * @param script
	 *            脚本对象
	 */
	public Token(Script script)
	{
		this.setTokenAlgorithm(AlgorithmManager.getAlgorithm(BeanHolder
				.getMessage("token.algorithm")));
		this.setScript(script);

		Date now = new Date();
		this.setCreateTime(now);
		this.setDisableTime(createDisableTime(now, getInvalidTimeConfig()));
	}

	public Token(Script script, String keyTag)
	{
		this.setKeyTag(keyTag);
		this.setTokenAlgorithm(AlgorithmManager.getAlgorithm(BeanHolder
				.getMessage("token.algorithm")));

		this.setScript(script);

		Date now = new Date();
		this.setCreateTime(now);
		this.setDisableTime(createDisableTime(now, getInvalidTimeConfig()));
	}

	/**
	 * @param script
	 *            脚本对象
	 * @param tokenAlgorithm
	 *            算法
	 * @param disableTimeLength
	 *            失效时间的长度，单位为秒
	 */
	public Token(Script script, Algorithm tokenAlgorithm, int disableTimeLength)
	{
		this.setTokenAlgorithm(tokenAlgorithm);
		this.setScript(script);

		Date now = new Date();
		this.setCreateTime(now);
		this.setDisableTime(createDisableTime(now, disableTimeLength));
	}

	/**
	 * 方法描述：获取失效时间
	 * 
	 * @param now
	 *            当前时间
	 * @param disableTimeLength
	 *            失效时间的长度，单位为秒
	 */
	private Date createDisableTime(Date now, int disableTimeLength)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		int second = cal.get(Calendar.SECOND);
		cal.set(Calendar.SECOND, second + disableTimeLength);

		return cal.getTime();
	}

	public String toString()
	{
		return new ToStringBuilder(this).append("userID", userID).append(
				"token", token).append("createTime", createTime).append(
				"disableTime", disableTime).append("script",
				script.getType() + "-->" + script.getScript()).toString();
	}

	/**
	 * 方法描述：定义为private的，用于保证一个Token只能针对同一个Script
	 * 
	 * @param script
	 * @author 申卿
	 * @version Jun 2, 2010 6:27:55 PM
	 * @修改方式：新增
	 */
	private void setScript(Script script)
	{
		this.script = script;
		this.token = (String) tokenAlgorithm.createToken(script, this
				.getKeyTag())
				+ "_" + (this.getKeyTag() == null ? "" : this.getKeyTag());
	}

	public String getToken()
	{
		return token;
	}

	public void setToken(String token)
	{
		this.token = token;
	}

	public String getKeyTag()
	{
		return keyTag;
	}

	public void setKeyTag(String keyTag)
	{
		this.keyTag = keyTag;
	}

	public Date getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(Date createTime)
	{
		if (null != createTime)
		{
			this.createTime = (Date) createTime.clone();
		}
		else
		{
			this.createTime = null;
		}
	}

	public Date getDisableTime()
	{
		return disableTime;
	}

	public void setDisableTime(Date disableTime)
	{
		if (null != disableTime)
		{
			this.disableTime = (Date) disableTime.clone();
		}
		else
		{
			this.disableTime = null;
		}
	}

	public String getUserID()
	{
		return userID;
	}

	public void setUserID(String userID)
	{
		this.userID = userID;
	}

	public Algorithm getTokenAlgorithm()
	{
		return tokenAlgorithm;
	}

	public void setTokenAlgorithm(Algorithm tokenAlgorithm)
	{
		this.tokenAlgorithm = tokenAlgorithm;
	}

	public int getCount()
	{
		return count;
	}

	public void setCount(int count)
	{
		if (this.count < count)
		{
			this.count = count;
		}
	}

	public String getOperateID()
	{
		return operateID;
	}

	public void setOperateID(String operateID)
	{
		this.operateID = operateID;
	}

	/**
	 * 方法描述：重置失效时间
	 * 
	 * @author 申卿
	 * @version Jun 2, 2010 6:25:20 PM
	 * @修改方式：新增
	 */
	public void reSetDisableTime()
	{
		this.setDisableTime(createDisableTime(new Date(), getInvalidTimeConfig()));
	}

	public Script getScript()
	{
		return script;
	}

	/**
	 * 方法描述：token字符串中解析出keyTag
	 * 
	 * @param token
	 *            从token中，解析出keyTag
	 * @return
	 * @author 申卿
	 * @version Jun 4, 2010 5:35:05 PM
	 * @修改方式：新增/修改
	 */
	public static String parseKeyTag(String token)
	{
		// oldToken的格式为：d2740735c65e0409390957fbb05339bb_申卿_1275636083468_20100629
		if (null == token || "".equals(token.trim())
				|| (token.indexOf("_") < 0)
				|| (token.indexOf("_") == (token.length() - 1)))
		{
			return null;
		}
		String fileName = token.substring(token.indexOf("_") + 1);
		return fileName;
	}
	
	/**
	 * 方法描述：获取失效时间设置
	 * @return
	 * @author  申卿
	 * @version Jun 8, 2010 1:46:03 PM
	 * @修改方式：新增
	 */
	private int getInvalidTimeConfig()
	{
		String invalidTimeConfig = BeanHolder.getMessage("token.invalid.time").trim();
		if(null == invalidTimeConfig)
		{
			return DEFALUT_INVALID_TIME;
		}
		else
		{
			return Integer.parseInt(invalidTimeConfig);
		}
	}
}
