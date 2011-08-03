/*
 * description: 
 * date:        上午12:51:13
 * author:      ahli
 */
package com.huawei.imp.framework.core.context;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.springframework.web.context.ContextLoaderListener;

import com.huawei.imp.framework.jee.Path;
import com.huawei.imp.framework.logger.LogFactory;
import com.huawei.imp.framework.logger.Logger;


/**
 * <p><strong>框架自定义的上下文装载监听器</strong></p>
 * <p>
 * 基于js引擎实现的框架启动类装载器。在容器启动的时候，加载该类装载器。
 * </p>
 * @see
 * @version v1.0
 * @since v2.0
 * @author ahli (edit in 2010-6-2)
 */
public class Framework2ContextLoaderListener extends ContextLoaderListener {

	/**
	 * 启动脚本文件
	 */
	public static final String CONTEXT_LOADER_LISTENER_SCRIPTFILE = "/WEB-INF/conf/Framework2ContextLoaderListener.script";

	/**
	 * 日志对象
	 */
	private static final Logger log = LogFactory.getLogger(Framework2ContextLoaderListener.class);
	
	/**
	 * 脚本类型
	 */
	public static final String TYPE = "js";
	
	/**
	 * 脚本执行管理器
	 */
	private ScriptEngineManager mgr = new ScriptEngineManager();
	
	/**
	 * 脚本引擎
	 */
	private ScriptEngine jsEngine = mgr.getEngineByName(TYPE);
	
	/**
	 * 默认空构造函数
	 */
	public Framework2ContextLoaderListener() {
		super();
		init();
	}
	
	/**
	 * 初始化
	 */
	public void init(){
		File scriptFile = new File(Path.getWebRootPath()+ CONTEXT_LOADER_LISTENER_SCRIPTFILE);
		Reader fr = null;
		try {
			fr = new InputStreamReader(new FileInputStream(scriptFile), "UTF-8");
			jsEngine.eval(fr);
		} catch (ScriptException e) {
			log.error(e.toString());
			throw new RuntimeException(e);
		} catch (Exception e){
			log.error(e.toString());
			throw new RuntimeException(e);
		} finally {
			if(null != fr){
				try
				{
					fr.close();
				}
				catch (IOException e){}
			}
		}
	}
}
