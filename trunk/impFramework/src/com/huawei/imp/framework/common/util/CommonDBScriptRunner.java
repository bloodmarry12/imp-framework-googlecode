package com.huawei.imp.framework.common.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.huawei.imp.framework.jee.Path;
import com.ibatis.common.jdbc.ScriptRunner;

/**
 * 内存数据库DDL脚本初始化Bean
 * @author ahli
 *
 */
public class CommonDBScriptRunner {

//	private final static Logger log = com.huawei.imp.framework.logger.LogFactory.getLogger(CommonDBScriptRunner.class);
	
	/**
	 * 构造函数
	 * @param ddl    DDL脚本路径
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public CommonDBScriptRunner(DataSource ds, String[] ddlPaths) throws IOException, SQLException{
//		StringWriter logSw = new StringWriter();
//		PrintWriter  logPW = new PrintWriter(logSw);
//		StringWriter errSw = new StringWriter();
//		PrintWriter  errPW = new PrintWriter(errSw);
		
		Connection con = ds.getConnection();
		ScriptRunner scriptRunner = new ScriptRunner (con, false, false);
		scriptRunner.setErrorLogWriter(null);
		scriptRunner.setLogWriter(null);
		try{
			if(null != ddlPaths){
				String classPath = Path.getWebRootPath();
				for(String path : ddlPaths){
					FileReader fileReader = new FileReader(new File(classPath + "/WEB-INF/classes/" + path));
					scriptRunner.runScript(fileReader);
				}
			}
		}finally{
			if(null != con){
				con.close();
			}
		}
	}
}
