/*
 * 文件名：BeanProperUtilTestCase.java
 * 版权：  Copyright 2000-2009 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 描述：〈描述〉
 * 修改人：ahli
 * 修改时间：Sep 14, 2009
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.huawei.imp.framework.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.huawei.imp.framework.constant.ConstantValue;
import com.huawei.imp.framework.utils.mock.TestUser;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author ahli
 * @version IMPV100R001DA0, Sep 14, 2009 
 * @see [相关类/方法]
 * @since CMS IMPV100R001DA0
 */

public class BeanProperUtilTestCase
{
	@Before
	public void init(){
		List<com.huawei.imp.framework.constant.domain.Constant> list = new ArrayList<com.huawei.imp.framework.constant.domain.Constant>();
		com.huawei.imp.framework.constant.domain.Constant cons = new com.huawei.imp.framework.constant.domain.Constant();
		cons.setId(1L);
		cons.setModelID(0);
		cons.setName("中文");
		cons.setOrder(1);
		cons.setValue("1");
		list.add(cons);
		ConstantValue.initValueObjectBeans(list);
	}
	
	@Test
	public void createBean() throws InstantiationException, IllegalAccessException{
		ConstantValue cv = new ConstantValue(0);
		cv.setValue("1");
		Map<String, Object> srcMap = new HashMap<String, Object>();
		srcMap.put("name", "用户名");
		srcMap.put("age", 10);
		srcMap.put("language", "中文");
//		srcMap.put("language", cv);
		
		TestUser obj = BeanProperUtil.createBean(TestUser.class, srcMap, 
				new String[]{"name", "age", "language.name"}, 
				new String[]{"name", "age", "language"});
		
		System.out.println(obj);
	}
}
