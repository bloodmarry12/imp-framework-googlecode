package com.huawei.imp.framework.model.ibus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.huawei.imp.framework.model.ibus.log.domain.IbusLogerEntity;
import com.huawei.imp.framework.model.ibus.log.service.IbusLogService;
import com.huawei.imp.framework.test.AbstractFrameworkTests;
import com.huawei.imp.framework.utils.RandNumGenerator;

public class IbustLogServiceTestCase extends AbstractFrameworkTests
{

	@Autowired
	@Qualifier("ibusLogService")
	private IbusLogService ibusLogService;
	
	@Test
	public void testAddlog(){
		IbusLogerEntity logEntity = new IbusLogerEntity ();
		logEntity.setProcID("1");
		logEntity.setTransID("1");
		logEntity.setDate1(new Date());
		logEntity.setDate2(new Date());
		logEntity.setUrl("测试IBUS端口");
		logEntity.setMessage1("测试发送日志报文");
		logEntity.setMessage1("测试接受日志报文");
		ibusLogService.addLog(logEntity);
	}
	
	@Test
	public void testMulAddLog(){
		
		final AtomicInteger threadNum  = new AtomicInteger(0);
		final AtomicInteger flag  = new AtomicInteger(0);
		
		
		List<Thread> threadList = new ArrayList<Thread>();
		for(int i = 0 ; i < 100; i ++){
			
			Thread t = new Thread(new Runnable(){
				@Override
				public void run()
				{
					int num = 0;;
					while(num < 1000){
						num ++;
						IbusLogerEntity logEntity = new IbusLogerEntity ();
						logEntity.setProcID(RandNumGenerator.nextUUID());
						logEntity.setTransID("1");
						logEntity.setDate1(new Date());
						logEntity.setDate2(new Date());
						logEntity.setUrl("测试IBUS端口");
						logEntity.setMessage1("测试发送日志报文");
						logEntity.setMessage1("测试接受日志报文");
						ibusLogService.addLog(logEntity);
						try
						{
							Thread.sleep(10);
						}
						catch (InterruptedException e)
						{
							e.printStackTrace();
						}
					}
					threadNum.decrementAndGet();
				}});
			try
			{
				t.join();
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			threadList.add(t);
			threadNum.incrementAndGet();
		}
		
		System.out.println("threadNum.get():" + threadNum.get());
		new Thread(new Runnable(){
			@Override
			public void run()
			{
				while(threadNum.get() > 0){
					System.out.println(" threadNum.get() : " + threadNum.get());
					
					ibusLogService.dataToDatabase();
					try
					{
						Thread.sleep(3000);
					}
					catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				flag.incrementAndGet();
				System.out.println("flag > 0");
			}
		}).start();
		
		for(Thread thread : threadList){
			thread.start();
		}
		
		while(flag.get() == 0){
			try
			{
				Thread.sleep(10000);
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		ibusLogService.dataToDatabase();
		
		System.out.println("end");
	}
	
	@After
	public void test(){
//		ibusLogService.dataToDatabase(100);
	}
}
