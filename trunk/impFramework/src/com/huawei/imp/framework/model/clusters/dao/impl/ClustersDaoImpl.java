/**
 * 
 */
package com.huawei.imp.framework.model.clusters.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.huawei.imp.framework.common.dao.impl.IbatisCommonDAOImpl;
import com.huawei.imp.framework.model.clusters.dao.ClustersDao;
import com.huawei.imp.framework.model.clusters.domain.SlaveDevice;

/**
 * @author ahli
 *
 */
@Component("clustersDao")
public class ClustersDaoImpl extends IbatisCommonDAOImpl implements ClustersDao {

	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.model.clusters.dao.ClustersDao#queryForListByType(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SlaveDevice> queryForList(String localhost) {
		final String sql = super.warpSqlstatement(SlaveDevice.class, "queryForList");
		return (List<SlaveDevice>)super.sqlMapClientTemplate.queryForList(sql, localhost);
	}
}
