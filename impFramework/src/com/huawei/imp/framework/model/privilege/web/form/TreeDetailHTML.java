package com.huawei.imp.framework.model.privilege.web.form;


/**
 * Description:
 * 导航菜单HMTL
 * @author ahli
 * Apr 25, 2009
 * 
 */
public class TreeDetailHTML<T> {

	private static final String EMPTY = "";
	
	/**
	 * 导航菜单a标签中的href地址
	 * @param explorerIndexBO
	 * @return
	 */
	public String ahrefUrl(T bo){
		return EMPTY;
	}
	
	/**
	 * 导航菜单a标签后面位置
	 * @param explorerIndexBO
	 * @return
	 */
	public String afterAtag(T bo){
		return EMPTY;
	}
	
	/**
	 * 导航菜单a标签后面位置
	 * @param explorerIndexBO
	 * @return
	 */
	public String beforeName(T bo){
		return EMPTY;
	}
}
