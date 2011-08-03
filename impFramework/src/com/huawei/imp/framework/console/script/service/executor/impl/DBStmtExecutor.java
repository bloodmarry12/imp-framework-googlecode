/*
 * description: 
 * date:        下午11:04:41
 * author:      ahli
 */
package com.huawei.imp.framework.console.script.service.executor.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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

/**
 * <p><strong>数据库脚本执行器</strong></p>
 * <p></p>
 * @see
 * @version v1.0
 * @since v2.0
 * @author ahli (edit in 2010-5-27)
 */
@Component("com.huawei.imp.framework.console.script.service.executor.impl.DBStmtExecutor")
public class DBStmtExecutor implements JscriptExecutor {

	/**
	 * 脚本类型
	 */
	public static final String TYPE = "DBStmtExecutor";
	
	private String dataSourceName = ""; 
	
	/**
	 * 日志对象
	 */
	private static final Logger log = LogFactory.getLogger(DBStmtExecutor.class);
	
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
		Connection con = null;//ds.getConnection();
		StringBuilder sb = new StringBuilder();
		try
		{			
//			con  = dataSourcrManager.getDataSource(scriptSB.toString()).getConnection();
			con  = DataSourceManager.getDataSource(this.getDataSourceName()).getConnection();
			
			Statement stmt = con.createStatement();
			stmt.execute(scriptSB.toString());
			sb.append("影响的记录数：" + stmt.getUpdateCount());
		} catch (SQLException e) {
			log.error(e.toString());
			sb.append(Constant.LINE_SEPARATOR);
			sb.append(e.toString());
		} catch (Exception e) {
			log.error(e.toString());
			sb.append(Constant.LINE_SEPARATOR);
			sb.append(e.toString());
		}finally{
			if(null != con){
				try {
					con.close();
				} catch (SQLException e) {}
			}
		}
		return sb.toString();
	}
	@Override
	public String getDataSourceName()
	{
		return this.dataSourceName;
	}

	@Override
	public void setDataSourceName(StringBuilder script, String datasource)
	{
		this.dataSourceName = DataSourceManager.getDataSourceName(script, datasource);;
	}
}
