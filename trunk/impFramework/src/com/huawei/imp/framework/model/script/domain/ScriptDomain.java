/**
 * 
 */
package com.huawei.imp.framework.model.script.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 脚本域对象
 * @author ahli
 * @version V1.0 2010-7-26
 * @since FRAMEWORK2
 */
public class ScriptDomain implements Serializable{

	/**
	 * 默认的序列化ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	private String id;
	
	/**
	 * 脚本名称
	 */
	private String name;
	
	/**
	 * 脚本内容
	 */
	private byte[] content;
	
	/**
	 * 脚本更新时间
	 */
	private Date date = null;

	/**
	 * 默认空构造函数
	 */
	public ScriptDomain(){}
	
	/**
	 * 构造函数
	 * @param id
	 * @param name
	 * @param content
	 * @param date
	 */
	public ScriptDomain(String id, String name, String content, Date date) {
		super();
		this.id = id;
		this.name = name;
		if(null != content){
			this.content = content.getBytes();
		}
		this.date = (date == null? null : (Date)date.clone());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContentStr() {
		return content == null? null : new String(content);
	}
	
	public byte[] getContent() {
		return content == null?null:content.clone();
	}

	public void setContent(byte[] content) {
		if(null != content){
			this.content = content.clone();
		}else{
			this.content = null;
		}
	}

	public Date getDate() {
		if(null != date){
			return (Date)date.clone();
		}else{
			return null;
		}
	}

	public void setDate(Date date) {
		if(null != date){
			this.date = (Date)date.clone();
		}else{
			this.date = null;
		}
	}
}
