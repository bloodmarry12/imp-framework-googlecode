package com.huawei.imp.framework.transaction.multDatasourceTest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.huawei.imp.framework.test.AbstractFrameworkTests;
import com.huawei.imp.framework.transaction.service.MultDataSourceTestService;

public class TestCase1 extends AbstractFrameworkTests {
	
	@Autowired
	@Qualifier("multDataSourceTestService")
	private MultDataSourceTestService service;
	
	@Test
	public void test1(){
		service.testAddMultDatasourceData();
	}
	
	@Test
	public void test2(){
		service.testAddMultDatasourceDataWithExp();
	}
}
