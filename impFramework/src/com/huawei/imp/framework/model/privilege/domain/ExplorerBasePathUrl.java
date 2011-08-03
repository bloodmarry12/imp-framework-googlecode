package com.huawei.imp.framework.model.privilege.domain;

/**
 * Description:
 * 导航
 * @author ahli
 * Apr 28, 2009
 * 
 */
public class ExplorerBasePathUrl {

	private String id;
	private String webBasePath;
	
	public ExplorerBasePathUrl(String id, String webBasePath) {
		super();
		this.id = id;
		this.webBasePath = webBasePath;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWebBasePath() {
		return webBasePath;
	}
	public void setWebBasePath(String webBasePath) {
		this.webBasePath = webBasePath;
	}
}
