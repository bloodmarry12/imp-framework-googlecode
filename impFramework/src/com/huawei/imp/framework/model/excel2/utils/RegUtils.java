package com.huawei.imp.framework.model.excel2.utils;

/**
 * 正则表达式校验工具
 * @author ahli
 * @date 2009-9-7
 */
public class RegUtils {

	public static final boolean validate(String source, String regex){
		boolean ret = false;
		if(null != source){
			ret = source.matches(regex);
		}
		return ret;
	}
}
