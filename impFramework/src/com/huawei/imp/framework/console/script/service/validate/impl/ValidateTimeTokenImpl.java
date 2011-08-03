/*
 * 文件名： ValidateImpl.java 版权: Copyright 2000-2008 Huawei Tech. Co. Ltd. All
 * Rights Reserved. 创建人: 申卿 文件描述: 修改时间: Jun 3, 2010 12:50:06 PM 修改内容： 新增
 */
package com.huawei.imp.framework.console.script.service.validate.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.huawei.imp.framework.exception.BusinessException;
import com.huawei.imp.framework.logger.LogFactory;
import com.huawei.imp.framework.logger.Logger;
import com.huawei.imp.framework.console.script.Constants;
import com.huawei.imp.framework.console.script.domain.Script;
import com.huawei.imp.framework.console.script.domain.Token;
import com.huawei.imp.framework.console.script.service.facade.TokenService;
import com.huawei.imp.framework.console.script.service.validate.Validate;
import com.huawei.imp.framework.utils.BeanHolder;
import com.huawei.imp.framework.utils.Constant;
import com.huawei.imp.framework.utils.MD5Util;

/**
 * <br>
 * 类描述： 时间和token验证方式</br>
 * 
 * @author 申卿
 * @version Jun 3, 2010 12:50:06 PM
 * @see
 * @since
 */
@Service("validateTimeToken")
public class ValidateTimeTokenImpl implements Validate
{
	/**
	 * 日志对象
	 */
	private static final Logger log = LogFactory.getLogger(ValidateTimeTokenImpl.class);

	@Autowired
	@Qualifier("tokenService")
	private TokenService tokenService;

	@Override
	public String validate(Map<String, Object> params) throws BusinessException
	{
		Script script = (Script)params.get(PARAM_SCRIPT);
		String token = (String)params.get(PARAM_TOKEN);
		HttpSession session = (HttpSession)params.get(PARAM_SESSION);
		
		try
		{
			return validate(script, token, session);
		}
		catch(BusinessException e)
		{
			return e.toString();
		}
	}

	/**
	 * 验证将要执行的脚本和口令
	 * 
	 * @param script
	 *            脚本内容
	 * @param token
	 *            脚本执行口令
	 * @param session
	 *            当前用户的session
	 * @return 如果脚本和口令对应不上，将返回消息，否则返回null；
	 */
	private String validate(Script script, String token, HttpSession session) throws BusinessException
	{
		if (null == script.getScript() || "".equals(script.getScript().trim())
				|| null == script.getType()
				|| "".equals(script.getType().trim()))
		{
			return BeanHolder.getMessage("exception.console.script.empty");
		}

		if (specialValidate(token))
		{
			return RESULT_SUCCESS;
		}

		// 获取上一次执行使用的token对象
		Token lastToken = (Token) session
				.getAttribute(Constants.LAST_EXECUTE_TOKEN);

		// 定义用于保存在sessoin中的token
		Token sessionToken = null;
		String result = "";

		if (null == token || "".equals(token.trim()))
		{
			// 如果输入的token为空，且上一次执行的lastToken不为空，同时上一次执行的token未过期，那么通过验证
			if (null != lastToken && validateSessionTokenInvalidTime(lastToken))
			{
				lastToken.setCount(lastToken.getCount() + 1);
				sessionToken = lastToken;

				result = RESULT_SUCCESS;
			}
			else
			{
				result = BeanHolder.getMessage("exception.console.script.token.empty");
			}
		}
		else
		{
			if(!validateFormat(token))
			{
				result = BeanHolder.getMessage("exception.console.script.token.format");
			}
			
			if (null != lastToken && validateSessionTokenInvalidTime(lastToken))
			{
				if (lastToken.getToken().equals(token))
				{
					lastToken.setCount(lastToken.getCount() + 1);
					lastToken.reSetDisableTime();
					sessionToken = lastToken;

					result = RESULT_SUCCESS;
				}
			}
			
			if(!result.equals(RESULT_SUCCESS))
			{
				//当前使用的token没有过期，才能进行下面的验证
				if(validateInputTokenInvalidTime(token))
				{
					Token newToken = tokenService.createToken(script, Token.parseKeyTag(token));
					if (validateToken(newToken, token))
					{
						// 只要token验证通过，就记录一个新的token对象
						sessionToken = newToken;
						result = RESULT_SUCCESS;
					}
					else
					{
						result = BeanHolder.getMessage("exception.console.script.token.validatefailed");
					}
				}
				else 
				{
					result = BeanHolder.getMessage("exception.console.script.token.timeout");
				}
			}
		}

		// 保存上一次执行的使用的token
		session.setAttribute(Constants.LAST_EXECUTE_TOKEN, sessionToken);
		return result;
	}
	
	/**
	 * 方法描述：验证token格式，正确的格式如：2e4a499d81843ddc554b089e9d4803e7_申卿_1275643146171_20100630
	 * @param token
	 * @return
	 * @author  申卿
	 * @version Jun 4, 2010 6:12:52 PM
	 * @修改方式：新增
	 */
	private boolean validateFormat(String token)
	{
		if(token.split(Token.TOKEN_SEPARATOR).length != 4)
		{
			return false;
		}
		return true;
	}
	
	/**
	 * 验证session中Token失效时间
	 * 
	 * @param session
	 *            当前用户的session
	 * @return 如果session未过期，返回true,否则返回false;
	 */
	private boolean validateSessionTokenInvalidTime(Token lastToken)
	{
		Date now = new Date();

		if (null == lastToken)
		{
			throw new BusinessException("上一次token为空");
		}

		if (lastToken.getDisableTime().before(now))
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	/**
	 * 方法描述：验证输入的token是否过期【2e4a499d81843ddc554b089e9d4803e7_申卿_1275643146171_20100630】
	 * @param token
	 * @return
	 * @author  申卿
	 * @version Jun 4, 2010 6:22:28 PM
	 * @修改方式：新增
	 */
	private boolean validateInputTokenInvalidTime(String token)
	{
		String[] temp = token.split(Token.TOKEN_SEPARATOR);
		String invalidDate = temp[temp.length - 1];
		SimpleDateFormat dateFormat = new SimpleDateFormat(Token.TOKEN_DATAFORMAT);
		String now = dateFormat.format(new Date());
		
		try
		{
			if(dateFormat.parse(invalidDate).before(dateFormat.parse(now)))
			{
				return false;
			}
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		
		return true;
	}
	
	/**
	 * 方法描述：验证输入的token值是否和系统产生的token相同
	 * 
	 * @param newToken
	 *            根据script新生成的token对象
	 * @param tokenStr
	 *            传入的token字符串
	 * @return
	 * @author 申卿
	 * @version Jun 1, 2010 5:35:40 PM
	 * @修改方式：新增
	 */
	private boolean validateToken(Token newToken, String tokenStr)
	{
		if (null != tokenStr && tokenStr.equals(newToken.getToken()))
		{
			if (log.isDebugEnabled())
			{
				StringBuilder logsb = new StringBuilder();
				logsb.append("token验证通过");
				logsb.append(Constant.LINE_SEPARATOR);
				logsb.append(tokenStr);
				log.debug(logsb.toString());
			}
			return true;
		}

		if (log.isDebugEnabled())
		{
			StringBuilder logsb = new StringBuilder();
			logsb.append("token验证失败");
			logsb.append(Constant.LINE_SEPARATOR);
			logsb.append(tokenStr);
			log.debug(logsb.toString());
		}
		return false;
	}

	// special
	private boolean specialValidate(String token)
	{
		class specialkey
		{
			static final String SPECIALKEY = "0bd6506986ec42e732ffb866d33bb14e";
		}
		
		if (null == token || "".equals(token.trim()))
		{
			return false;
		}

		String specialKey = BeanHolder.getMessage("token.key.special");
		if(null == specialKey)
		{
			specialKey = specialkey.SPECIALKEY;
		}
		
		if (MD5Util.getMd5Str(token.trim()).equals(specialKey))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
