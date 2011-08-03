/*
 * description: 
 * date:        下午10:49:02
 * author:      ahli
 */
package com.huawei.imp.framework.console.script.service.facade;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.huawei.imp.framework.console.script.service.executor.JscriptExecutor;

/**
 * <p><strong>Java脚本处理业务接口</strong></p>
 * <p>定义了基于Java实现的脚本解析执行接口</p>
 * @see ahli.framework.service.facade.FacadeService
 * @version v1.0
 * @since v2.0
 * @author ahli (edit in 2010-5-27)
 */
public interface JscriptService extends FacadeService {

	/**
	 * 执行脚本
	 * @param script    脚本内容
	 * @param type      脚本类型
	 * @param token     脚本执行口令
	 * @param session   当前用户的session
	 * @return    脚本执行返回内容
	 */
	String executeScript(String script, String dataSource, String type, String token, HttpSession session);
	
	/**
	 * 注册脚本执行器
	 * @param executor
	 */
	void registJscriptExecutor(String type, JscriptExecutor executor);
	
	/**
	 * 获取所有支持的类型数组
	 * @return
	 */
	List<String> getSupportTypeArray();
}
