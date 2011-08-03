/*
 * 文件名：    Script.java
 * 版权:      Copyright 2000-2008 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 创建人:    申卿
 * 文件描述:  
 * 修改时间:   Jun 2, 2010 11:39:36 AM
 * 修改内容：  新增
 */
package com.huawei.imp.framework.console.script.domain;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.huawei.imp.framework.common.domain.Namingspace;
import com.huawei.imp.framework.utils.StringUtil;

/**
 * <br>类描述： </br>
 * @author  申卿
 * @version Jun 2, 2010 11:39:36 AM
 * @see
 * @since
 */
@Namingspace(value="_framework_script")
public class Script
{
	/**
	 * 操作ID
	 */
	private String operateID;
	/**
	 * 脚本字段容量
	 */
	public final static int SCRIPT_SIZE = 1999;
	/**
	 * 执行该script使用的token
	 */
	public String token;
	/**
	 * 全量的脚本内容
	 */
	private String script;
	/**
	 * 脚本内容1
	 */
	private String script1;
	/**
	 * 脚本内容2
	 */
	private String script2;
	/**
	 * 脚本内容3
	 */
	private String script3;
		
	/**
	 * 脚本类型
	 */
	private String type;
	
	/**
	 * 数据源
	 */
	private String datasource;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 上一次执行时间
	 */
	private Date lastExecuteTime;
	
	/**
	 * 执行次数
	 */
	private int count = 1;
	
	/**
	 * 执行结果
	 */
	private String result;
	
	/**
	 * 执行者
	 */
	private String userID;
	
	public Script(){}
	public Script(String script, String type)
	{
		this.setScript(script);
		this.type = type;
		this.createTime = new Date();
		this.lastExecuteTime = new Date();
	}
	
	public String toString()
	{
		return new ToStringBuilder(this).append("type", type).append(
				"script", this.getScript()).toString();
	}
	
	/**
	 * 获取页面脚本内容
	 * @return    脚本内容
	 */
	public String getScript() {
		return this.script ;
	}

	/**
	 * 设置页面脚本内容
	 * @param script    页面脚本内容
	 */
	public void setScript(String script) {
		this.script = script;
		if(null != script){
			if(StringUtil.getStringLength(script) > (SCRIPT_SIZE * 2)){
				script1 = StringUtil.subStringByChar(script, 0, SCRIPT_SIZE);
				script2 = StringUtil.subStringByChar(script, SCRIPT_SIZE, (SCRIPT_SIZE * 2));
				script3 = StringUtil.subStringByChar(script, (SCRIPT_SIZE * 2), (SCRIPT_SIZE * 3));
			}else if(StringUtil.getStringLength(script) > SCRIPT_SIZE){
				script1 = StringUtil.subStringByChar(script, 0, SCRIPT_SIZE);
				script2 = StringUtil.subStringByChar(script, SCRIPT_SIZE, (SCRIPT_SIZE * 2));
				script3 = "";
			}else{
				script1 = script;
				script2 = "";
				script3 = "";
			}
		}else{
			script1 = script;
			script2 = script;
			script3 = script;
		}
	}

	/**
	 * 获取脚本类型
	 * @return
	 */
	public String getType() {
		return type;
	}

	/**
	 * 设置脚本类型
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}
	public Date getCreateTime()
	{
		return createTime;
	}
	public void setCreateTime(Date createTime)
	{
		if(null != createTime){
			this.createTime = (Date)createTime.clone();
		}else{
			this.createTime = null;
		}
	}
	public Date getLastExecuteTime()
	{
		return lastExecuteTime;
	}
	public void setLastExecuteTime(Date lastExecuteTime)
	{
		if(null != lastExecuteTime){
			this.lastExecuteTime = (Date)lastExecuteTime.clone();
		}else{
			this.lastExecuteTime = null;
		}
	}
	public String getOperateID()
	{
		return operateID;
	}
	public void setOperateID(String operateID)
	{
		this.operateID = operateID;
	}
	public String getScript1()
	{
		return script1;
	}
	
	
	
	public void setScript1(String script1)
	{
		this.script1 = script1;
	}
	public String getScript2()
	{
		return script2;
	}
	public void setScript2(String script2)
	{
		this.script2 = script2;
	}
	public String getScript3()
	{
		return script3;
	}
	public void setScript3(String script3)
	{
		this.script3 = script3;
	}
	public int getCount()
	{
		return count;
	}
	public void setCount(int count)
	{
		this.count = count;
	}
	public String getResult()
	{
		return result;
	}
	public void setResult(String result)
	{
		this.result = result;
	}
	public static int getSCRIPT_SIZE()
	{
		return SCRIPT_SIZE;
	}
	public String getUserID()
	{
		return userID;
	}
	public void setUserID(String userID)
	{
		this.userID = userID;
	}
	public String getToken()
	{
		return token;
	}
	public void setToken(String token)
	{
		this.token = token;
	}
	public String getDatasource()
	{
		return datasource;
	}
	public void setDatasource(String datasource)
	{
		this.datasource = datasource;
	}
		
}
