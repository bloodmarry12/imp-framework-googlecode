/*
 * 文件名： TokenServiceImpl.java 版权: Copyright 2000-2008 Huawei Tech. Co. Ltd. All
 * Rights Reserved. 创建人: 申卿 文件描述: 修改时间: Jun 2, 2010 11:29:42 AM 修改内容： 新增
 */
package com.huawei.imp.framework.console.script.service.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.huawei.imp.framework.exception.BusinessException;
import com.huawei.imp.framework.logger.LogFactory;
import com.huawei.imp.framework.logger.Logger;
import com.huawei.imp.framework.utils.BeanHolder;
import com.huawei.imp.framework.console.script.domain.Script;
import com.huawei.imp.framework.console.script.domain.Token;
import com.huawei.imp.framework.console.script.service.facade.TokenService;

/**
 * <br>
 * 类描述：token业务类</br>
 * 
 * @author 申卿
 * @version Jun 2, 2010 11:29:42 AM
 * @see
 * @since
 */
@Service("tokenService")
public class TokenServiceImpl implements TokenService
{
	/**
	 * 日志对象
	 */
	private static final Logger log = LogFactory
			.getLogger(TokenServiceImpl.class);

	@Override
	public Token createToken(Script script) throws BusinessException
	{
		return new Token(script);
	}

	@Override
	public Token createToken(Script script, String keyTag) throws BusinessException
	{
		try
		{
			return new Token(script, keyTag);
		}
		catch(Exception e)
		{
			throw new BusinessException(BeanHolder.getMessage("exception.console.script.token.createfailed") + "\n" + e.toString());
		}
		
	}
}
