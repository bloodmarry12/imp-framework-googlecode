/*
 * description: 
 * date:        下午11:04:41
 * author:      ahli
 */
package com.huawei.imp.framework.console.script.service.executor.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.huawei.imp.framework.logger.LogFactory;
import com.huawei.imp.framework.logger.Logger;
import com.huawei.imp.framework.console.script.service.executor.DataSourceManager;
import com.huawei.imp.framework.console.script.service.executor.JscriptExecutor;
import com.huawei.imp.framework.console.script.service.facade.JscriptService;
import com.huawei.imp.framework.utils.Constant;
import com.ibatis.common.jdbc.ScriptRunner;

/**
 * <p><strong>数据库脚本执行器</strong></p>
 * <p></p>
 * @see
 * @version v1.0
 * @since v2.0
 * @author ahli (edit in 2010-5-27)
 */
@Component("com.huawei.imp.framework.console.script.service.executor.impl.DBscriptRunnerExecutor")
public class DBscriptRunnerExecutor implements JscriptExecutor {

	/**
	 * 脚本类型
	 */
	public static final String TYPE = "DBscriptRunner";
	
	/**
	 * 日志对象
	 */
	private static final Logger log = LogFactory.getLogger(DBscriptRunnerExecutor.class);
	
	private String dataSourceName = ""; 
	
	@Autowired
	@Qualifier("jscriptService") 
	private JscriptService jservice;
	
	/**
	 * 将当前脚本处理器关联到脚本业务处理器中
	 */
	@PostConstruct
	public void regist(){
		jservice.registJscriptExecutor(TYPE, this);
	}
	
	/* (non-Javadoc)
	 * @see ahli.framework.model.script.service.executor.JscriptExecutor#execute(java.lang.String)
	 */
	public String execute(String script) {
		StringBuilder scriptSB = new StringBuilder(script);
		
		if(log.isDebugEnabled()){
			StringBuilder logsb = new StringBuilder();
			logsb.append("执行["+TYPE+"]脚本");
			logsb.append(Constant.LINE_SEPARATOR);
			logsb.append(scriptSB.toString());
			log.debug(logsb.toString());
		}
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		
		// 检查脚本中置否有制定数据源，如果没有特殊指定数据源，则使用datasource作为
		// 数据源
		Connection con = null;//ds.getConnection();
		try
		{
//			con  = dataSourcrManager.getDataSource(scriptSB.toString()).getConnection();
			con  = DataSourceManager.getDataSource(this.getDataSourceName()).getConnection();
			ScriptRunner scriptRunner = new ScriptRunner (con, false, true);
			scriptRunner.setErrorLogWriter(pw);
			scriptRunner.setLogWriter(pw);
			scriptRunner.runScript(new StringReader(scriptSB.toString()));
		} catch (IOException e) {
			log.error(e.toString());
			sw.append(Constant.LINE_SEPARATOR);
			sw.append(e.toString());
		} catch (SQLException e) {
			log.error(e.toString());
			sw.append(Constant.LINE_SEPARATOR);
			sw.append(e.toString());
		} catch (Exception e) {
			log.error(e.toString());
			sw.append(Constant.LINE_SEPARATOR);
			sw.append(e.toString());
		}finally{
			if(null != con){
				try {
					con.close();
				} catch (SQLException e) {}
			}
		}
		return sw.toString();
	}

	@Override
	public String getDataSourceName()
	{
		return this.dataSourceName;
	}

	@Override
	public void setDataSourceName(StringBuilder script, String datasource)
	{
		this.dataSourceName = DataSourceManager.getDataSourceName(script, datasource);
	}
}
