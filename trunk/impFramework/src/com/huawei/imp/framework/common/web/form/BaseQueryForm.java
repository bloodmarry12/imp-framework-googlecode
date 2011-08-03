package com.huawei.imp.framework.common.web.form;

import java.util.HashMap;
import java.util.Map;

/**
 * 查询表单基类，提供每页显示数量、当前页码的get、set方法。提供默认空的参数结果Map实现方法
 * 子类在继承该类型，做具体业务分页表单的时候，需要覆盖父类的getParams()方法。
 * @author ahli
 * @version IMPV100R001DA0, Apr 12, 2009
 * @since CMS IMPV100R001DA0
 */
public class BaseQueryForm {
	
	/**
	 * 空返回结果映射
	 */
	private final Map<String, Object> map = new HashMap<String, Object>(0);
	
	/**
	 * 每页显示记录数，默认15条
	 */
	private Integer pageSize = 15;
	
	/**
	 * 当前页码，默认1
	 */
	private Integer pageNum = 1;
	
	/**
	 * 获取页码
	 * @return    返回页码
	 */
	public Integer getPageSize() {
		return pageSize;
	}
	
	/**
	 * 
	 * @param pageSize
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	
	/**
	 * 将条件转换为参数映射
	 * @return
	 */
	public Map<String, Object> getParams(){
		return map;
	}
}
