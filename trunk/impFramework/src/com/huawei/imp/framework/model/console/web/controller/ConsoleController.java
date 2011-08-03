package com.huawei.imp.framework.model.console.web.controller;

import java.sql.SQLException;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huawei.imp.framework.common.web.controller.BaseAjaxJsonController;
import com.huawei.imp.framework.utils.BeanHolder;
import com.mchange.v2.c3p0.PooledDataSource;

/**
 * 控制台监控信息
 * <功能描述/>
 * @author ahli
 * @date 2009-9-1
 */
@Controller("com.huawei.imp.framework.model.console.web.controller.consoleController")
public class ConsoleController extends BaseAjaxJsonController{
	
	/**
	 * @param model
	 * @return
	 */
	@RequestMapping("/framework/model/console/status.do")
	public String c3p0Status(Model model){
		
		PooledDataSource pds = (PooledDataSource)BeanHolder.getBean("dataSource0");
		
		JSONObject root = new JSONObject();
		
		try
		{
			JSONObject c3p0 = new JSONObject();
			c3p0.put("num_busy_connections", pds.getNumBusyConnectionsDefaultUser());
			c3p0.put("num_idle_connections", pds.getNumIdleConnectionsDefaultUser());
			root.put("c3p0", c3p0);
			
			JSONObject system = new JSONObject();
            Runtime   rt   =   Runtime.getRuntime();   
            system.put("total", rt.totalMemory());
            system.put("free", rt.freeMemory());
            root.put("system", system);
            
			root.put(JSON_RESULT, RESULT_CODE_DONE);
		}
		catch (SQLException e)
		{
			root.put(JSON_RESULT, RESULT_CODE_FAIL);
		}
		model.addAttribute(ATTRIBUTE_FORM, root);
		return PAGE_CONSOLE_JSON;
	}
	
	/**
	 * @param model
	 * @return
	 */
	@RequestMapping("/framework/model/console/ftp")
	public String ftpStatus(Model model){
		return PAGE_CONSOLE_MODEL + "console/Ftpview";
	}
	
	/**
	 * @param model
	 * @return
	 */
	@RequestMapping("/framework/welcome.do")
	public String console(Model model){
		return "/framework/jsp/welcome";
	}
}
