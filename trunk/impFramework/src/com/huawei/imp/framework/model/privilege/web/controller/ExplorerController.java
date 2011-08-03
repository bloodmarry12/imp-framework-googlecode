package com.huawei.imp.framework.model.privilege.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.huawei.imp.framework.jee.JEEConstant;
import com.huawei.imp.framework.logger.LogFactory;
import com.huawei.imp.framework.logger.Logger;
import com.huawei.imp.framework.model.privilege.domain.ExplorerIndex;
import com.huawei.imp.framework.model.privilege.service.PrivilegeService;
import com.huawei.imp.framework.model.privilege.web.form.ExplorerIndexGenerator;
import com.huawei.imp.framework.model.privilege.web.form.TreeDetailHTML;

/**
 * Description:
 * 导航条控制器
 * @author ahli
 * Apr 26, 2009
 * 
 */
@Controller
public class ExplorerController implements JEEConstant {

	/**
	 * 日志对象
	 */
	private static final Logger log = LogFactory.getLogger(ExplorerController.class);
	
	@Autowired
	@Qualifier("privilegeService")
	private PrivilegeService service;
	
	@RequestMapping("/framework/model/privilege/explorer/tree.do")
	public String tree(Model model){
		ExplorerIndex explorerIndex = service.loadExplorerIndex();
		ExplorerIndexGenerator eig = new ExplorerIndexGenerator(explorerIndex,
			new TreeDetailHTML<ExplorerIndex>(){
				@Override
				public String afterAtag(ExplorerIndex explorerIndexBO) {
					if(ExplorerIndex.ROOT_NODE_ID == explorerIndexBO.getId()){
						return "(<a href=\"create.do?parentID="+explorerIndexBO.getId()+"\">添加</a>)";
					}else{
						return "(<a href=\"update.do?explorerID="+explorerIndexBO.getId()+"\">编辑</a>," +
							"<span onclick=\"deleteExplorer("+explorerIndexBO.getId()+")\">删除</span>," +
							"<a href=\"create.do?parentID="+explorerIndexBO.getId()+"\">添加</a>)";
					}
				}
				
				@Override
				public String ahrefUrl(ExplorerIndex explorerIndexBO) {
					return " href=\"view.do?explorerID="+explorerIndexBO.getId()+"\"";
				}
			}
		);
		if(log.isDebugEnabled()){
			log.debug(eig.toString());
		}
		model.addAttribute(MODEL_EXPLORER_TREE, eig);
		return PAGE_CONSOLE_MODEL + "privilege/explorer/tree";
	}
	
	/**
	 * 展示页面
	 * @param explorerID	导航ID
	 * @param model			Model
	 * @return
	 */
	@RequestMapping("/framework/model/privilege/explorer/view.do")
	public String view(@RequestParam("explorerID") long explorerID, 
			Model model){
		ExplorerIndex expBO = service.loadExplorerIndexByID(explorerID);
		model.addAttribute(FORM, expBO);
		return PAGE_CONSOLE_MODEL + "privilege/explorer/view";
	}
	
	/**
	 * 删除菜单
	 * @param explorerID	导航ID
	 * @param model			Model
	 * @return
	 */
	@RequestMapping("/framework/model/privilege/explorer/delete.do")
	public String delete(@RequestParam("explorerID") long explorerID, 
			Model model){
		service.removeExplorerIndex(explorerID);
		model.addAttribute("url", "/framework/model/privilege/explorer/tree.do");
		return PAGE_CONSOLE_SUCCESS;
	}
}
