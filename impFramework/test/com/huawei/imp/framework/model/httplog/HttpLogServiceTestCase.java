package com.huawei.imp.framework.model.httplog;

import java.util.Date;

import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.huawei.imp.framework.model.httplog.domain.HttpLoggerEntity;
import com.huawei.imp.framework.model.httplog.service.HttpLoggerService;
import com.huawei.imp.framework.test.AbstractFrameworkTests;
import com.huawei.imp.framework.utils.RandNumGenerator;

public class HttpLogServiceTestCase extends AbstractFrameworkTests
{

	@Autowired
	@Qualifier("httpLoggerService")
	private HttpLoggerService httpLoggerService;
	
	@Test
	public void testAddlog(){
		HttpLoggerEntity log = new HttpLoggerEntity();
		log.setId(String.valueOf(RandNumGenerator.nextNumber()));
		log.setOperateDate(new Date());
		log.setOperateTime(100L);
		log.setParameters("[]");
		log.setRemoteIP("127.0.0.1");
		log.setUid(1L);
		log.setUrl("测试地址");
		log.setLastUrl("lastUrl");
		log.setSessionID("sessionID");
		httpLoggerService.log(log);
	}
	
	@After
	public void test(){
		httpLoggerService.persistenceHttpLoggerEntity(100);
	}
}
