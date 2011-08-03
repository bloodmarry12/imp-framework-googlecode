/*
 * description: date: 下午10:52:52 author: ahli
 */
package com.huawei.imp.framework.console.script.service.facade.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.huawei.imp.framework.logger.LogFactory;
import com.huawei.imp.framework.logger.Logger;
import com.huawei.imp.framework.console.script.dao.DaoSupport;
import com.huawei.imp.framework.console.script.domain.Script;
import com.huawei.imp.framework.console.script.service.executor.JscriptExecutor;
import com.huawei.imp.framework.console.script.service.facade.JscriptService;
import com.huawei.imp.framework.console.script.service.validate.Validate;
import com.huawei.imp.framework.utils.BeanHolder;
import com.huawei.imp.framework.utils.RandNumGenerator;

/**
 * <p>
 * <strong></strong>
 * </p>
 * <p>
 * </p>
 * 
 * @see ahli.framework.model.script.service.facade.JscriptService
 * @version v1.0
 * @since v2.0
 * @author ahli (edit in 2010-5-27)
 */
@Service("jscriptService")
public class JscriptServiceImpl implements JscriptService
{
	/**
	 * 日志对象
	 */
	private static final Logger log = LogFactory
			.getLogger(JscriptServiceImpl.class);

	private Map<String, JscriptExecutor> executorMapper = new HashMap<String, JscriptExecutor>();
	
	@Autowired
	@Qualifier("validateTimeToken")
	private Validate validateTimeToken;

	@Autowired
	@Qualifier("scriptDao")
	private DaoSupport scriptDao;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see ahli.framework.model.script.service.facade.JscriptService#executeScript(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	public String executeScript(String script, String dataSource, String type, String token,
			HttpSession session)
	{
		String result = "";
		Script scriptO = new Script(script.trim(), type);
		String operateId = String.valueOf(RandNumGenerator.nextNumber());
		// 验证口令
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(Validate.PARAM_SCRIPT, scriptO);
		params.put(Validate.PARAM_TOKEN, token.trim());
		params.put(Validate.PARAM_SESSION, session);
		
		final String validateResult = validateTimeToken.validate(params);
		
		if (!Validate.RESULT_SUCCESS.equals(validateResult))
		{
			result = validateResult;
		}
		
		if(Validate.RESULT_SUCCESS.equals(validateResult))
		{
			// 调用脚本执行器
			JscriptExecutor executor = executorMapper.get(type);
			if (null == executor)
			{
				result = BeanHolder.getMessage("exception.console.script.notsupported", new String[]{type});
			}
			
			//设置数据源
			StringBuilder scriptSB = new StringBuilder(script);
			executor.setDataSourceName(scriptSB, dataSource);
			dataSource = executor.getDataSourceName();
			// 执行脚本并返回
			result = executor.execute(scriptSB.toString().trim());
		}
		
		scriptO.setToken(token);
		scriptO.setResult(result);
		scriptO.setOperateID(operateId);
		scriptO.setDatasource(dataSource);
		scriptDao.save(scriptO);
		
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see ahli.framework.model.script.service.facade.JscriptService#registJscriptExecutor(java.lang.String,
	 *      ahli.framework.model.script.service.executor.JscriptExecutor)
	 */
	public void registJscriptExecutor(String type, JscriptExecutor executor)
	{
		executorMapper.put(type, executor);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ahli.framework.model.script.service.facade.JscriptService#getSupportTypeArray()
	 */
	public List<String> getSupportTypeArray()
	{
		List<String> typeArray = new ArrayList<String>();
		Set<String> typeSet = this.executorMapper.keySet();
		if (!typeSet.isEmpty())
		{
			for (String type : typeSet)
			{
				typeArray.add(type);
			}

			// 对数组进行排序
			Collections.sort(typeArray);
		}
		return typeArray;
	}
}
