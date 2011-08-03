/**
 * 
 */
package com.huawei.imp.framework.model.script.facade;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.huawei.imp.framework.logger.LogFactory;
import com.huawei.imp.framework.logger.Logger;
import com.huawei.imp.framework.model.script.dao.ScriptDomainDAO;
import com.huawei.imp.framework.model.script.domain.ScriptDomain;
import com.huawei.imp.framework.model.script.exception.ScriptExecuteException;
import com.huawei.imp.framework.reload.ReloadSupport;
import com.huawei.imp.framework.utils.Constant;

/**
 * 
 * @author ahli
 * @version V1.0 2010-7-26
 * @since FRAMEWORK2
 */
@Service("ScriptExecutManagerFacade")
public class ScriptExecutManagerFacadeImpl implements ScriptExecutManagerFacade, ReloadSupport {

	private static final Logger log = LogFactory.getLogger(ScriptExecutManagerFacadeImpl.class);
	
	private ConcurrentHashMap<String, Compilable> compilableMap = new ConcurrentHashMap<String, Compilable>();
	
	private final ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
	
	private Map<String, ScriptDomain> scriptContentMap = new HashMap<String, ScriptDomain>();
	
	@Autowired
	@Qualifier("FK_ScriptDomainDAO")
	private ScriptDomainDAO scriptDomainDAO;
	
	@PostConstruct
	public void init(){
		reload();
	}
	
	private Compilable getCompilable(String engineName){
		Compilable compilable = compilableMap.get(engineName);
		if(null == compilable){
			compilableMap.put(engineName, (Compilable)scriptEngineManager.getEngineByName(engineName));
		}
		return compilableMap.get(engineName);
	}
	
	/* (non-Javadoc)
	 * @see org.framework2.script.facade.ScriptExecutManagerFacade#executeScript(java.lang.String, java.lang.String)
	 */
	@Override
	public void executeScript(String id, String engineName) throws ScriptExecuteException {
		executeScript(id, engineName, null);
	}

	/* (non-Javadoc)
	 * @see org.framework2.script.facade.ScriptExecutManagerFacade#executeScript(java.lang.String, java.lang.String, java.util.Map)
	 */
	@Override
	public void executeScript(String id, String engineName,
			Map<String, Object> params) throws ScriptExecuteException {
		Compilable compilable = getCompilable(engineName);
		if(null == compilable){
			throw new ScriptExecuteException("compilable is null. executeScript(" + id + ", " + engineName + ")");
		}
		ScriptDomain sd = scriptContentMap.get(id);
		if(null == sd){
			throw new ScriptExecuteException("scriptDomain is null. executeScript(" + id + ", " + engineName + ")");
		}
		String scriptContent = sd.getContentStr();
		if(null == scriptContent){
			throw new ScriptExecuteException("scriptContent is null. executeScript(" + id + ", " + engineName + ")");
		}
		if(log.isDebugEnabled()){
			log.debug("scriptContent:" + Constant.LINE_SEPARATOR + scriptContent);
		}
		
		try {
			CompiledScript compiledScript = compilable.compile(scriptContent);
			if(null != params){
				for(Map.Entry<String, Object> entry : params.entrySet()){
					compiledScript.getEngine().put(entry.getKey(), entry.getValue());
				}
			}
			compiledScript.eval();
		} catch (ScriptException e) {
			throw new ScriptExecuteException(e);
		}
	}

	/* (non-Javadoc)
	 * @see org.framework2.script.facade.ScriptExecutManagerFacade#loadScript()
	 */
	@Override
	public void reload() {
		List<ScriptDomain> list = scriptDomainDAO.queryScriptDomainForList(null);
		Map<String, ScriptDomain> _scriptContentMap = new HashMap<String, ScriptDomain>();
		for(ScriptDomain sd : list){
			_scriptContentMap.put(sd.getId(), sd);
		}
		this.scriptContentMap = _scriptContentMap;
	}
	
	public void setScriptInMemory(String id, String content){
		this.scriptContentMap.put(id, new ScriptDomain(id, "name", content, new Date()));
	}

	/* (non-Javadoc)
	 * @see org.framework2.script.facade.ScriptExecutManagerFacade#getCompiledScript(java.lang.String, java.lang.String)
	 */
	@Override
	public CompiledScript getCompiledScript(String id, String engineName) throws ScriptExecuteException {
		Compilable compilable = getCompilable(engineName);
		if(null == compilable){
			throw new ScriptExecuteException("compilable is null. executeScript(" + id + ", " + engineName + ")");
		}
		ScriptDomain sd = scriptContentMap.get(id);
		if(null == sd){
			throw new ScriptExecuteException("scriptDomain is null. executeScript(" + id + ", " + engineName + ")");
		}
		String scriptContent = sd.getContentStr();
		if(null == scriptContent){
			throw new ScriptExecuteException("scriptContent is null. executeScript(" + id + ", " + engineName + ")");
		}
		try {
			return compilable.compile(scriptContent);
			
		} catch (ScriptException e) {
			throw new ScriptExecuteException(e);
		}
	}
}
