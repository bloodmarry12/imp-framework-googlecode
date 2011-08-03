/*
 * 文件名：    TokenService.java
 * 版权:      Copyright 2000-2008 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 创建人:    申卿
 * 文件描述:  
 * 修改时间:   Jun 2, 2010 11:27:31 AM
 * 修改内容：  新增
 */
package com.huawei.imp.framework.console.script.service.facade;

import com.huawei.imp.framework.exception.BusinessException;
import com.huawei.imp.framework.console.script.domain.Script;
import com.huawei.imp.framework.console.script.domain.Token;

/**
 * <br>类描述： </br>
 * @author  申卿
 * @version Jun 2, 2010 11:27:31 AM
 * @see
 * @since
 */
public interface TokenService
{
	/**
	 * 方法描述：根据执行的脚本，生成token
	 * @param script
	 * @return
	 * @author  申卿
	 * @version Jun 2, 2010 11:28:52 AM
	 * @修改方式：新增
	 */
	Token createToken(Script script) throws BusinessException;
	
	/**
	 * 方法描述：根据执行的脚本，和传如的token值，生成新的token
	 * @param script
	 * @param keyTag 密钥文件标志，通常为密钥文件的文件名称
	 * @return
	 * @author  申卿
	 * @version Jun 2, 2010 11:28:52 AM
	 * @修改方式：新增
	 */
	Token createToken(Script script, String keyTag) throws BusinessException;
}
