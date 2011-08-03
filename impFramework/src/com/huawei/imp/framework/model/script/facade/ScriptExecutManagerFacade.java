/**
 * 
 */
package com.huawei.imp.framework.model.script.facade;

import java.util.Map;

import javax.script.CompiledScript;

import com.huawei.imp.framework.model.script.exception.ScriptExecuteException;

/**
 * 脚本执行管理装饰接口
 * @author ahli
 * @version V1.0 2010-7-26
 * @since FRAMEWORK2
 */
public interface ScriptExecutManagerFacade {

	/**
	 * 调用、并且执行脚本
	 * @param id
	 * @param script
	 * @throws ScriptExecuteException 
	 */
	void executeScript(String id, String engineName) throws ScriptExecuteException;
	
	/**
	 * 调用、并且执行脚本
	 * @param id            脚本ID
	 * @param engineName    引擎名称，例如:js
	 * @param params        参数
	 * @return
	 * @throws ScriptExecuteException 
	 */
	void executeScript(String id, String engineName, Map<String, Object> params) throws ScriptExecuteException;
	
	/**
	 * 获取已经编译的脚本对象
	 * @param id
	 * @param engineName
	 * @return
	 * @throws ScriptExecuteException 
	 */
	CompiledScript getCompiledScript(String id, String engineName) throws ScriptExecuteException;
}
