/**
 * 
 */
package com.huawei.imp.framework.model.security.dao;

import java.util.List;
import java.util.Map;

import org.oproject.framework.orm.ibatis.bytecode.codegenerator.annotations.DynamicIbatisDAO;

/**
 * <p>
 * 安全加固数据访问接口
 * </p>
 * @see 
 * @author aohai.li
 * @version CMSV100R001DB0SP04, 2010-8-16
 * @since CMSV100R001DB0SP04
 */
@DynamicIbatisDAO(value="FK_SecurityVerificationDAO", sqlMapClientTemplate = "sqlMapClientTemplate0")
public interface SecurityVerificationDAO {

	/**
	 * 查询安全验证配置
	 * @param params    查询参数
	 * @return
	 */
	public List<Map<String, Object>> querySecurityVerificationForList(Map<String, Object> params);
}
