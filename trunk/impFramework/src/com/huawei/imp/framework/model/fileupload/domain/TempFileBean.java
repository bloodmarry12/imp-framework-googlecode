package com.huawei.imp.framework.model.fileupload.domain;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author ahli
 * @version IMPV100R001DA0, Nov 2, 2009 
 * @see [相关类/方法]
 * @since CMS IMPV100R001DA0
 */
public class TempFileBean {

	/**
	 * UUID
	 */
	private String uuid;
	
	private String name;
	
	private String path;
	
	private long size;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}
}
