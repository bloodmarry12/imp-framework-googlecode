package com.huawei.imp.framework.utils;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description: 19位长度随机数生成器,生成规则为当前系统时间(13位长度的) + 毫秒级别的 自增数字。
 * 
 * @author ahli Apr 21, 2009
 */
public class RandNumGenerator
{

	 static AtomicInteger ai = new AtomicInteger(10000);
	
	/**
	 * uuid分隔符
	 */
	private static final String UUID_SP = "-";
	
	/**
	 * uuid替换符
	 */
	private static final String UUID_RP = "";
	
	/**
	 * 私有构造
	 */
	private RandNumGenerator() {
	}

	/**
	 * 同步的生成新的随机数的方法
	 * 
	 * @return 19位长度随机数
	 */
	public static  long nextNumber() {
		long curTime = System.currentTimeMillis() * 100000L;
		// 如果本次随机数产生时间与上一次产生时间在毫秒级别
		// 是一样的，则自增数++，否则自增序列归0
		return curTime + nextCountValue();
	}

	/**
	 * 获取6位长度序列自增数
	 * 
	 * @return
	 */
	private static long nextCountValue() {
		ai.compareAndSet(100000, 10000);
        return ai.getAndAdd(1);
	}
	
	/**
	 * 生成一个UUID
	 * @return    UUID
	 */
	public static String nextUUID(){
		String s = UUID.randomUUID().toString();//
		return s.replaceAll(UUID_SP, UUID_RP);
	}
	
	public static void main(String[] args){
		long start = System.currentTimeMillis();
		for (int i = 0; i < 10000000; i++) {
			nextNumber();
			//System.out.println(nextNumber());
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}
}
