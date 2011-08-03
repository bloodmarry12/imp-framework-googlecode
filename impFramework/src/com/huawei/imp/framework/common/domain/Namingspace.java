/*
 * 文件名：Namespace.java
 * 版权：  Copyright 2000-2009 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 描述：  命名空间源注释
 * 修改人：ahli
 * 修改时间：Jul 17, 2009
 */

package com.huawei.imp.framework.common.domain;


/**
 * 
 * 命名空间源注释
 * 类注解，支持继承
 * @author ahli
 * @version IMPV100R001DA0, Jul 17, 2009 
 * @since CMS IMPV100R001DA0
 */
@java.lang.annotation.Retention(value=java.lang.annotation.RetentionPolicy.RUNTIME)
@java.lang.annotation.Target(value={java.lang.annotation.ElementType.TYPE})
@java.lang.annotation.Inherited
public @interface Namingspace
{
	/**
	 * 命名空间
	 */
	String value();
}
