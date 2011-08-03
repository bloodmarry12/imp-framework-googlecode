package com.huawei.imp.framework.test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.transaction.BeforeTransaction;

/**
 * 抽象的CMS单元测试基类
 * @author ahli
 * @version IMPV100R001DA0, May 11, 2009
 * @see [相关类/方法]
 * @since CMS IMPV100R001DA0
 */
@ContextConfiguration(locations={"classpath*:applicationContext.xml"
//		,"classpath*:log4j.properties"
		,"classpath*:dataAccessContext-ds0.xml"
//		,"classpath*:dataAccessContext-ds1.xml"
//		,"classpath*:dataAccessContext-txManage.xml"
		,"classpath*:applicationContext-framework.xml"
		})
//@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
public class AbstractFrameworkTests extends AbstractJUnit4SpringContextTests
{
	
	@BeforeTransaction
	public void beforeTransaction(){
//		BeanHolder.getBean("logService");
	}
	
	/**
	 * 注册用户登录信息到上下文中
	 * @param name	用户名
	 * @param psw	密码明文
	 */
	protected void regLoginUser(String name, String psw){
//		Account accountBO = PrivilegeUtil.loadAccountByPSW(name, psw);
//		ServletSessionUtil.setSessionUserInfo(accountBO.getId(), accountBO.getPassword(), accountBO.getName());
	}
	
	/**
	 * 将DB日志持久化到数据库
	 */
	protected void saveDBLog(){
//		OprLogIntoDBTimeTask task = (OprLogIntoDBTimeTask)BeanHolder.getBean("logService");
//		task.excute();
	}
}
