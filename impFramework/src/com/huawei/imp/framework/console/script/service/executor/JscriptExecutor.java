/*
 * description: 
 * date:        下午10:59:12
 * author:      ahli
 */
package com.huawei.imp.framework.console.script.service.executor;

/**
 * <p><strong></strong></p>
 * <p></p>
 * @see
 * @version v1.0
 * @since v2.0
 * @author ahli (edit in 2010-5-27)
 */
public interface JscriptExecutor {

	/**
	 * 
	 * @param script
	 * @return    返回结果
	 */
	String execute(String script);
	
	String getDataSourceName();
	
	void setDataSourceName(StringBuilder script, String datasource);
}
