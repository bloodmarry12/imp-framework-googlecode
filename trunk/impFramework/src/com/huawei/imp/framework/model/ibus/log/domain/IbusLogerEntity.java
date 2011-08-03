/**
 * 
 */
package com.huawei.imp.framework.model.ibus.log.domain;

import java.io.Serializable;
import java.util.Date;

import com.huawei.imp.framework.common.domain.Namingspace;
import com.huawei.imp.framework.utils.StringUtil;

/**
 * @author ahli
 *
 */
/**
 * @author ahli
 *
 */
@Namingspace("_framework_ibus_log")
public class IbusLogerEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 消息字段容量
	 */
	public final static int MESSAGE1_SIZE = 1999;
	
	/**
	 * URL字段容量
	 */
	public final static int URL_SIZE = 128;
	
	/**
	 * 业务流水号；标识一次业务操作
	 */
	private String procID;
	
	/**
	 * 事务ID，非必填项；现在仅仅是分发数据有该ID
	 */
	private String transID;
	
	/**
	 * 预留索引字段1
	 */
	private String index1;
	/**
	 * 预留索引字段2
	 */
	private String index2;
	/**
	 * 请求端消息；
	 */
	private String message1a;
	/**
	 * 请求端消息；
	 */
	private String message1b;
	/**
	 * 请求端消息；
	 */
	private String message1c;
	
	/**
	 * 回执端消息；
	 */
	private String message2a;
	/**
	 * 回执端消息；
	 */
	private String message2b;
	/**
	 * 回执端消息；
	 */
	private String message2c;
	
	/**
	 * 请求时间
	 */
	private Date date1;
	
	/**
	 * 获得响应的时间
	 */
	private Date date2;
	
	/**
	 * 数据访问地址
	 */
	private String url;
	
	/**
	 * 日志服务器名称
	 */
	private String server;
	
	/**
	 * 如果操作失败，那么这里保存异常信息
	 */
	private String exception;

	public String getTransID() {
		return transID;
	}

	public void setTransID(String transID) {
		this.transID = transID;
	}

	public String getProcID() {
		return procID;
	}

	public void setProcID(String procID) {
		this.procID = procID;
	}

	public String getIndex1() {
		return index1;
	}

	public void setIndex1(String index1) {
		this.index1 = index1;
	}

	public String getIndex2() {
		return index2;
	}

	public void setIndex2(String index2) {
		this.index2 = index2;
	}

	public String getMessage1() {
		return message1a + message1b + message1c;
	}

	public void setMessage1(String srcData) {
		if(null != srcData){
			if(StringUtil.getStringLength(srcData) > (MESSAGE1_SIZE * 2)){
				message1a = StringUtil.subStringByChar(srcData, 0, MESSAGE1_SIZE);
				message1b = StringUtil.subStringByChar(srcData, MESSAGE1_SIZE, (MESSAGE1_SIZE * 2));
				message1c = StringUtil.subStringByChar(srcData, (MESSAGE1_SIZE * 2), (MESSAGE1_SIZE * 3));
			}else if(StringUtil.getStringLength(srcData) > MESSAGE1_SIZE){
				message1a = StringUtil.subStringByChar(srcData, 0, MESSAGE1_SIZE);
				message1b = StringUtil.subStringByChar(srcData, MESSAGE1_SIZE, (MESSAGE1_SIZE * 2));
			}else{
				message1a = srcData;
			}
		}else{
			message1a = null;
		}
	}

	public String getMessage1a() {
		return message1a;
	}

	public void setMessage1a(String message1) {
		this.message1a  = message1;
	}
	
	public String getMessage1b() {
		return message1b;
	}

	public void setMessage1b(String message1) {
		this.message1b = message1;
	}
	
	public String getMessage1c() {
		return message1c;
	}

	public void setMessage1c(String message1) {
		this.message1c = message1;
	}
	
	public String getMessage2() {
		return message2a + message2b + message2c;
	}

	public void setMessage2(String srcData) {
		if(null != srcData){
			if(StringUtil.getStringLength(srcData) > (MESSAGE1_SIZE * 2)){
				message2a = StringUtil.subStringByChar(srcData, 0, MESSAGE1_SIZE);
				message2b = StringUtil.subStringByChar(srcData, MESSAGE1_SIZE, (MESSAGE1_SIZE * 2));
				message2c = StringUtil.subStringByChar(srcData, (MESSAGE1_SIZE * 2), (MESSAGE1_SIZE * 3));
			}else if(StringUtil.getStringLength(srcData) > MESSAGE1_SIZE){
				message2a = StringUtil.subStringByChar(srcData, 0, MESSAGE1_SIZE);
				message2b = StringUtil.subStringByChar(srcData, MESSAGE1_SIZE, (MESSAGE1_SIZE * 2));
			}else{
				message2a = srcData;
			}
		}else{
			message2a = null;
		}
	}
	
	public String getMessage2a()
	{
		return message2a;
	}

	public void setMessage2a(String message2a)
	{
		this.message2a = message2a;
	}

	public String getMessage2b()
	{
		return message2b;
	}

	public void setMessage2b(String message2b)
	{
		this.message2b = message2b;
	}

	public String getMessage2c()
	{
		return message2c;
	}

	public void setMessage2c(String message2c)
	{
		this.message2c = message2c;
	}

	public Date getDate1() {
		if(null != date1){
			return (Date)date1.clone();
		}else{
			return null;
		}
	}

	public void setDate1(Date date1) {
		if(null != date1){
			this.date1 = (Date)date1.clone();
		}else{
			this.date1 = null;
		}
	}

	public Date getDate2() {
		if(null != date2){
			return (Date)date2.clone();
		}else{
			return null;
		}
	}

	public void setDate2(Date date2) {
		if(null != date2){
			this.date2 = (Date)date2.clone();
		}else{
			this.date2 = null;
		}
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	/**
	 * @return the server
	 */
	public String getServer()
	{
		return server;
	}

	/**
	 * @param server the server to set
	 */
	public void setServer(String server)
	{
		this.server = server;
	}
	
}
