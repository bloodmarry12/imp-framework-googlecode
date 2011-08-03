package com.huawei.imp.framework.model.dblog.annotation;

@java.lang.annotation.Retention(value=java.lang.annotation.RetentionPolicy.RUNTIME)
@java.lang.annotation.Target(value={java.lang.annotation.ElementType.METHOD})
public @interface DBLogEnable
{	
	public boolean value() default true;

}
