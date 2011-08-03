package com.huawei.imp.framework.model.schedule;

public interface Constants {
	/**
	 * javaBean在传递参数的时候的Key
	 */
	static String JOB_DATA_MAP_BEAN = "JOB.DATA.MAP.BEAN";

	/**
	 * 调用方法名在传递阐述的时候的Key
	 */
	static String JOB_DATA_MAP_METHOD = "JOB.DATA.MAP.METHOD";
	
	/**
	 * 调用参数传递的Key
	 */
	static String JOB_DATA_MAP_PARAMETER = "JOB_DATA_MAP_PARAMETER";
	

	/**
	 * 单线程：串行执行定时任务；
	 */
	static int CONCURRENT_SINGLE = 0;

	/**
	 * 多线程:并行执行，意味着第一个任务未完成前，时间可以触发启动新的线程执行任务。
	 */
	static int CONCURRENT_MULT = 1;

	/**
	 * 执行中的任务
	 */
	static int STATUS_RUN = 0;

	/**
	 * 停止任务
	 */
	static int STATUS_STOP = 1;
	
	/**
	 * 默认的调度组名称
	 */
	public static final String Scheduler_GROUP_NAME = "FRWK_SCHEDULE_GROUP";

	/**
	 * 异常信息：更新定时任务配置信息失败
	 */
	public static final String EXCEPTION_CANTUPDATERUNNINGTASK = "common.timetask.exception.cantUpdateRunningTask";
	
	/**
	 * 定时任务延迟启动时间
	 */
	static final int JOB_LAZY_TIME = 2 * 60 * 1000;
}
