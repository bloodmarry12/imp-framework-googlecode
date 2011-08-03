/*
 * 文件名：ConstantValue.java
 * 版权：  Copyright 2000-2009 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 描述：  全局值对象类
 * 修改人：ahli
 * 修改时间：Aug 14, 2009
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.huawei.imp.framework.constant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.huawei.imp.framework.constant.domain.Constant;

/**
 * 全局值对象类
 * @author ahli
 * @version IMPV100R001DA0, Aug 14, 2009 
 * @since CMS IMPV100R001DA0
 */
public class ConstantValue
{
	
	/**
	 * 缓存容器
	 */
	private static final Map<Integer, List<Constant>> map = new ConcurrentHashMap<Integer, List<Constant>>(500);
	
	/**
	 * 语言对象为NULL
	 */
	public static final Constant NULL = new Constant();
	
	/**
	 * 是否可以为空
	 */
	private boolean nullAble = true;
	
	/**
	 * 原始设置的name值
	 */
	private String srcName = null;
	
	/**
	 * 原始设置的value值
	 */
	private String srcValue = null;
	
	/**
	 * 初始化，设置为NULL对象
	 */
	private Constant thisBean = NULL;
	
	/**
	 * 标识名称
	 */
	private Integer modelID = Integer.valueOf(-1);
	
	/**
	 * @param modelID
	 */
	public ConstantValue(Integer modelID){
		super();
		this.modelID = modelID;
	}
	
	/**
	 * @param modelID
	 * @param nullAble
	 */
	public ConstantValue(Integer modelID, boolean nullAble){
		super();
		this.modelID = modelID;
		this.nullAble = nullAble;
	}
	
	public String getValue()
	{
		return thisBean.getValue();
	}
	
	public void setValue(String value)
	{
		this.srcValue = value;
		if (null != value)
		{
			if (null != map.get(modelID))
			{
				for (Constant bean : map.get(modelID))
				{
					if(value.equals(bean.getValue())){
						thisBean = bean.clone();
						return;
					}
				}
			}
		}
		thisBean = NULL;
	}
	
	public String getName()
	{
		return thisBean.getName();
	}
	
	public void setName(String name)
	{
		this.srcName = name;
		if (null != name)
		{
			if (null != map.get(modelID))
			{
				for (Constant bean : map.get(modelID))
				{
					if(name.equals(bean.getName())){
						thisBean = bean.clone();
						return;
					}
				}
			}
		}
		thisBean = NULL;
	}
	
	public Integer getOrder()
	{
		return thisBean.getOrder();
	}
	
	@Override
	public String toString()
	{
		return thisBean.getName();
	}

	/**
	 * 初始化值
	 * @param arg0
	 */
	public static void initValueObjectBeans(List<Constant> arg0){
		
		Integer modelID = null;
		int i = 0;
		for (Constant bean : arg0)
		{
			modelID = bean.getModelID();
			List<Constant> list = map.get(modelID);			
			if(null == list){
				list = new ArrayList<Constant>(10);
				map.put(modelID, list);
			}
			else
			{
				if(i == 0)
				{
					list.clear();
				}				
			}
			i++;
			list.add(bean.clone());
		}
		
		for(Map.Entry<Integer, List<Constant>> entry : map.entrySet()){
			// 排序
			Collections.sort(entry.getValue(), new Comparator<Constant>(){
				@Override
				public int compare(Constant o1, Constant o2)
				{
					return o1.getOrder().compareTo(o2.getOrder());
				}});
		}
	}
	
	/**
	 * 返回当前模型的对象列表
	 * @return
	 */
	public static List<Constant> getList(Integer modelID){
		List<Constant> ret = new ArrayList<Constant>(10);
		if (null != map.get(modelID))
		{
			for (Constant bean : map.get(modelID))
			{
				ret.add(bean.clone());
			}
		}
		return ret;
	}
	
	/**
	 * 返回所有的模型ID
	 * @return
	 */
	public static Set<Integer> getModelIDs(){
		return map.keySet();
	}
	
	/**
	 * 校验传入的值是否是该常量组中的一员
	 * @param name
	 * @return
	 */
	public boolean validate(String name)
	{
		if (null != map.get(modelID))
		{
			for (Constant bean : map.get(modelID))
			{
				if(name.equals(bean.getName())){
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 验证最后一次setName的值是否合法
	 * @return
	 */
	public boolean validateSrcName(){
		if(null == srcName){
			if(nullAble){
				return true;
			}else{
				return false;
			}
		}else{
			return validate(srcName);
		}
	}
	
	/**
	 * 验证最后一次setValue的值是否合法
	 * @return
	 */
	public boolean validateSrcValue(){
		if(null == srcValue){
			if(nullAble){
				return true;
			}else{
				return false;
			}
		}else{
			if (null != map.get(modelID))
			{
				for (Constant bean : map.get(modelID))
				{
					if(this.srcValue.equals(bean.getValue())){
						return true;
					}
				}
			}
			return false;
		}
	}
	
	/**
	 * 获取最后一次setName的值
	 * @return
	 */
	public String getSrcName() {
		return srcName;
	}

	/**
	 * 获取最后一次setValue的值
	 * @return
	 */
	public String getSrcValue() {
		return srcValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((modelID == null) ? 0 : modelID.hashCode());
		result = prime * result
				+ ((srcValue == null) ? 0 : srcValue.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConstantValue other = (ConstantValue) obj;
		if (modelID == null) {
			if (other.modelID != null)
				return false;
		} else if (!modelID.equals(other.modelID))
			return false;
		if (srcValue == null) {
			if (other.srcValue != null)
				return false;
		} else if (!srcValue.equals(other.srcValue))
			return false;
		return true;
	}
}
