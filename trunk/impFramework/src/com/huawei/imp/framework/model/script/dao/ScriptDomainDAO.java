/**
 * 
 */
package com.huawei.imp.framework.model.script.dao;

import java.util.List;
import java.util.Map;

import org.oproject.framework.orm.ibatis.bytecode.codegenerator.annotations.DynamicIbatisDAO;

import com.huawei.imp.framework.model.script.domain.ScriptDomain;
import com.huawei.imp.framework.utils.PageResult;

/**
 * 脚本对象数据访问接口
 * @author ahli
 * @version V1.0 2010-7-26
 * @since FRAMEWORK2
 */
@DynamicIbatisDAO(value="FK_ScriptDomainDAO", sqlMapClientTemplate = "sqlMapClientTemplate0")
public interface ScriptDomainDAO {

	/**
	 * 查询脚本对象
	 * @param params
	 * @return
	 */
	List<ScriptDomain> queryScriptDomainForList(Map<String, Object> params);
	
	/**
	 * 单个加载脚本对象
	 * @param id
	 * @return
	 */
	ScriptDomain load(String id);
	
	/**
	 * 分页查询脚本对象
	 * @return
	 */
	PageResult queryScriptDomainForPageResult(ScriptDomain sc, int pageNum, int pageSize);
	
}
