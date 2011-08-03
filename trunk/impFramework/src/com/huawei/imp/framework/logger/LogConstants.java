// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   LogConfigConstants.java

package com.huawei.imp.framework.logger;

import com.huawei.imp.framework.utils.Constant;


interface LogConstants extends Constant
{
	/**
	 * 日志信息格格式化模板
	 */
	static String LOG_INFO_FORMAT = "[LogID:\"{0}\", IP: \"{1}\", Msg:\"{2}\"]";
}
