/*
 * description: 
 * date:        下午11:04:41
 * author:      ahli
 */
package com.huawei.imp.framework.console.script.service.executor.impl;

import javax.annotation.PostConstruct;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.huawei.imp.framework.logger.LogFactory;
import com.huawei.imp.framework.logger.Logger;
import com.huawei.imp.framework.console.script.service.executor.JscriptExecutor;
import com.huawei.imp.framework.console.script.service.facade.JscriptService;
import com.huawei.imp.framework.utils.BeanHolder;
import com.huawei.imp.framework.utils.Constant;

/**
 * <p><strong>js脚本执行器</strong></p>
 * <p></p>
 * @see
 * @version v1.0
 * @since v2.0
 * @author ahli (edit in 2010-5-27)
 */
@Component("com.huawei.imp.framework.console.script.service.executor.impl.JavascriptExecutor")
public class JavascriptExecutor implements JscriptExecutor {

	/**
	 * 脚本类型
	 */
	public static final String TYPE = "javascript";
	
	/**
	 * 日志对象
	 */
	private static final Logger log = LogFactory.getLogger(JavascriptExecutor.class);
	
	/**
	 * 脚本执行管理器
	 */
	private ScriptEngineManager mgr = new ScriptEngineManager();
	
	/**
	 * 脚本引擎
	 */
	private ScriptEngine jsEngine = mgr.getEngineByName("js");
	
	/**
	 * 将当前脚本处理器关联到脚本业务处理器中
	 */
	@Autowired
	@Qualifier("jscriptService") 
	private JscriptService jservice;
	
	@PostConstruct
	public void regist(){
		jservice.registJscriptExecutor(TYPE, this);
	}
	
	/* (non-Javadoc)
	 * @see ahli.framework.model.script.service.executor.JscriptExecutor#execute(java.lang.String)
	 */
	public String execute(String script) {
		if(log.isDebugEnabled()){
			StringBuilder logsb = new StringBuilder();
			logsb.append("执行["+TYPE+"]脚本");
			logsb.append(Constant.LINE_SEPARATOR);
			logsb.append(script);
			log.debug(logsb.toString());
		}
		StringBuilder sb = new StringBuilder(300);
		
		//注入结果输入对象。在执行jsscript的时候，要求最终输出的结果采用如下格式：
		//out.append(BeanHolder.getMessage("token.key.file.key"))
		jsEngine.put("out", sb);
		
		//注入jsEngine对象，可以动态注入业务对象
		jsEngine.put("jsEngine", jsEngine);
		
		//注入BeanHolder对象，可以通过它获取所有由spring管理的业务对象
		jsEngine.put("BeanHolder",new BeanHolder());
		
		if(log.isDebugEnabled()){
			log.debug("在脚本引擎中注入了返回返回结果StringBuilder对象，out。");
		}
		try {
			jsEngine.eval(script);
		} catch (ScriptException e) {
			log.error(e.toString());
			sb.append("\n script exception! [").append(e.toString()).append("]");
		} catch (Exception e){
			log.error(e.toString());
			sb.append("\n exception! [").append(e.toString()).append("]");
		}
		return sb.toString();
	}
	
	@Override
	public String getDataSourceName()
	{
		return null;
	}

	@Override
	public void setDataSourceName(StringBuilder script, String datasource)
	{
		
	}
}
